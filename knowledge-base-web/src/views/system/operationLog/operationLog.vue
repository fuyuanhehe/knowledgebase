<template>
    <div class="operationLog">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb30">
                <!--table页搜索查询-->
                <table-search :isSelectShow="false"
                              :dateLabel="'操作时间'"
                              @search="searchData"></table-search>
            </div>
            <div class="table-box">
                <el-table
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%">
                    <el-table-column
                            align="center"
                            prop="coder"
                            label="日志编号">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="operationUser"
                            label="操作人">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="operationMsg"
                            label="操作方式">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.operationTime | date('YYYY-MM-DD')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="operationUserIp"
                            label="操作IP">
                    </el-table-column>
                </el-table>
            </div>
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
        </div>
    </div>
</template>

<script>
export default {
    name: 'operationLog',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '操作日志',
                    path: '/system/homeMgt/news',
                    routeName: 'news'
                }
            ], // 面包屑导航集合
            search: {
                pageNum: 1,
                pageSize: 10,
                operationMsg: '',
                logStartTime: '',
                logEndTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 0 // 总条目数
        };
    },
    created () {
        this.getData(1, this.search.pageSize);
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
            const res = await this.$api.querySystemLog(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.operationMsg = data.msg;
            this.search.logStartTime = data.startDate;
            this.search.logEndTime = data.endDate;
            this.getData(1, this.search.pageSize);
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
    .operationLog {
        .content {
            padding: 40px 50px;
        }
    }
</style>
