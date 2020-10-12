<!--
@file：项目协作-项目列表
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectList">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <!--table页搜索查询-->
                <table-search :selectOptions="statusOptions"
                              selectLabel="项目状态"
                              dateLabel="创建时间"
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
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="项目名称">
                        <template slot-scope="scope">
                            <span class="cursor_p"  @click="goDetail(scope.row.id)">{{ scope.row.name }}</span>
                        </template>
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
                            label="计划开始时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column> <el-table-column
                            align="center"
                            prop=""
                            label="计划截止时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop=""
                            label="实际开始时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop=""
                            label="实际结束时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="项目状态">
                        <template slot-scope="scope">
                            <span>{{ scope.row.examineStatus ? (scope.row.examineStatus == 1 ? '不通过': '未审核') : '通过' }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop=""
                            label="项目阶段">
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
    name: 'projectList',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '项目协作',
                    path: '/project',
                    routeName: 'project'
                }, {
                    name: '项目列表',
                    path: '/project/projectList',
                    routeName: 'projectList',
                    disabled: true
                }
            ], // 面包屑导航集合
            statusOptions: [
                { value: 1, label: '进行中' },
                { value: 2, label: '已完工' }
            ], // 项目状态下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                status: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
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
            this.search.status = data.option;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * 查看
         */
        goDetail (id) {
          sessionStorage.setItem("projectId",id);
            this.$router.push({
                name: 'projectDetails',
                query: { id: id, pageForm: 'projectList' }
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
