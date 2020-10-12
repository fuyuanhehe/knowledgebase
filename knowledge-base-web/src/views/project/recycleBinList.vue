<!--
@file：项目协作-回收站
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="recycleBin">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :isSelectShow="false"
                              dateLabel="创建时间"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_border_blue" @click="reapply()">重新申请</el-button>
                </div>
            </div>
            <div class="table-box">
                <el-table
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            align="center"
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="项目名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="type"
                            label="项目类型">
                        <template slot-scope="scope">
                            <span>{{ scope.row.type ? '外部项目' : '内部项目'}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="createUserNick"
                            label="创建人">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop=""
                            label="撤销时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editProject(scope.row.id)">
                                <i class="table-icon el-icon-edit-outline"></i>
                            </span>
                        </template>
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
    name: 'recycleBin',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '项目协作',
                    path: '/project',
                    routeName: 'project'
                }, {
                    name: '回收站',
                    path: '/project/recycleBin',
                    routeName: 'recycleBin',
                    disabled: true
                }
            ], // 面包屑导航集合
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            multipleSelection: [], // table选中集合
            projectIds: [], //  table选中项目id集合
            total: 0 // 总条目数
        };
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
            const res = await this.$api.getProjectPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.name = data.msg;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * 重新申请
         */
        reapply () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要重新申请的项目！');
                return;
            }
            const ids = this.projectIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要重新申请的项目!');
                return;
            }
            this.$confirm('确认重新申请吗？', '提示', {
                center: true
            }).then(async () => {
                // await this.$api.removeBatchUser({ ids: ids });
                // this.$message.success('重新申请成功');
                // this.getData();
            });
        },
        /**
         * 编辑项目
         */
        editProject (id) {
            this.$router.push({
                name: 'projectEdit',
                query: { id: id, type: 'edit', pageForm: 'recycleBin' }
            });
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.projectIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 撤销
         */
        cancelProject (id, name) {
            this.$confirm('确定撤销“' + name + '”?', '撤销', {
                center: true
            }).then(() => {
                this.$message.success('撤销成功');
            }).catch(() => {
                this.$message.info('已取消撤销');
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

</style>
