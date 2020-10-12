<!--
@file：项目协作-项目立项
@createTime：2020-04-23
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectApply">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :selectOptions="statusOptions"
                              selectLabel="审核状态"
                              dateLabel="创建时间"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_border_blue" @click="addProject()">新建项目</el-button>
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
                            prop="createTime"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="审核状态">
                        <template slot-scope="scope">
                            <span>{{ scope.row.examineStatus ? (scope.row.examineStatus == 1 ? '不通过': '未审核') : '通过' }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <el-button class="table-btn" @click="goDetail(scope.row.id)">查看</el-button>
                            <el-button class="table-btn" @click="cancelProject(scope.row.id, scope.row.name)">撤销</el-button>
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
    name: 'projectApply',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '项目协作',
                    path: '/project',
                    routeName: 'project'
                }, {
                    name: '项目立项',
                    path: '/project/projectApply',
                    routeName: 'projectApply',
                    disabled: true
                }
            ], // 面包屑导航集合
            statusOptions: [
                { value: '0', label: '通过' },
                { value: '1', label: '不通过' },
                { value: '2', label: '未审核' }
            ], // 审核状态下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                examineStatus: '',
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
            this.search.examineStatus = data.option;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * 新建项目
         */
        addProject () {
            this.$router.push({
                name: 'projectEdit',
                query: { type: 'add' }
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
         * 查看
         */
        goDetail (id) {
            this.$router.push({
                name: 'projectDetails',
                query: { id: id, pageForm: 'projectApply' }
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
