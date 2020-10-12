<template>
    <div class="recordPopover">
        <el-popover
                class="popover-box"
                placement="bottom"
                trigger="click">
            <el-table border
                      :data="tableData"
                      style="width: 100%"
                      v-loading="loading"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      header-cell-class-name="table-th">
                <el-table-column align="center" type="index" width="50" label="序号"></el-table-column>
                <el-table-column align="center" prop="createUser" :label="recordName + '人'"></el-table-column>
                <el-table-column align="center" prop="reserve1" label="所在部门"></el-table-column>
                <el-table-column align="center" prop="createTime" width="150" :label="recordName + '时间'">
                    <template slot-scope="scope">
                        <span>{{ scope.row.createTime | date('YYYY-MM-DD HH:ss')}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" prop="status" :label="recordName + '状态'" v-if="recordType === 2">
                    <template slot-scope="scope">
                        <span>{{ scope.row.status ? '已确认' : '未确认'}}</span>
                    </template>
                </el-table-column>
                <el-table-column align="center" prop="address" :label="recordName + '意见'" v-if="recordType === 2">
                    <template slot-scope="scope">
                        <el-button type="text" @click="showRecord(scope.row.id)">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--分页-->
            <div class="pagination-box">
                <el-pagination
                        layout="prev, pager, next"
                        background
                        prev-text="上一页"
                        next-text="下一页"
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="search.pageNum"
                        :page-size="search.pageSize"
                        :total="total">
                </el-pagination>
            </div>
            <span class="tab" slot="reference" v-if="recordType == 0" @click="getData(1)">
                编辑记录  ( <span>{{ editNum ? editNum : 0}}</span> )
            </span>
            <span class="tab" slot="reference" v-if="recordType == 1" @click="getData(1)">
                下载记录  ( <span>{{ downloadNum ? downloadNum : 0}}</span> )
            </span>
            <span class="tab" slot="reference" v-if="recordType == 2" @click="getData(1)">
                纠错记录 ( <span>{{ correctionNum ? correctionNum : 0}}</span> )
            </span>
        </el-popover>
    </div>
</template>

<script>
export default {
    name: 'recordPopover',
    props: {
        correctionNum: {
            default: 0
        },
        editNum: {
            default: 0
        },
        downloadNum: {
            default: 0
        },
        recordType: {
            type: Number,
            default: 0
        }
    },
    data () {
        return {
            tableData: [],
            loading: false, // table是否显示加载动画
            search: {
                pageNum: 1,
                pageSize: 5,
                documentId: this.$route.query.id,
                type: this.recordType
            },
            total: 0
        };
    },
    computed: {
        recordName () {
            let name = '';
            if (this.recordType === 0) {
                name = '编辑';
            } else if (this.recordType === 1) {
                name = '下载';
            } else {
                name = '纠错';
            }
            return name;
        }
    },
    watch: {
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取table数据
         * @param pageNum
         * @param pageSize
         * @returns {Promise<void>}
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getAllBusDocumentRecord(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        showRecord (id) {
            this.$api.getBusDocumentRecord({ id: id }).then(res => {
                this.$emit('showRecord', res);
            });
        },
        /**
         * pageSize 改变时会触发
         * @param pageSize
         */
        handleSizeChange (pageSize) {
            this.getData(1, pageSize);
        },
        /**
         *  currentPage 改变时会触发
         * @param pageNum
         */
        handleCurrentChange (pageNum) {
            this.getData(pageNum, this.search.pageSize);
        }
    }
};
</script>

<style scoped lang="scss">
    .recordPopover {
    }
</style>
