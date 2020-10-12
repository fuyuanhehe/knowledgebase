<!--
@file：知识测评-试卷库
@createTime：2020-04-09
@updateTime：2020-04-13
@author：Claire
-->
<template>
    <div class="testPaperLibrary">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search dateLabel="创建时间"
                              :isSelectShow="false"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_bg_blue" @click="editTestPaper('add', null)">新增试卷</el-button>
                    <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
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
                            label="试卷名称">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="goDetail(scope.row.id)">{{ scope.row.name }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="totalNum"
                            label="题目数">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="totalScore"
                            label="总分数">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="commentCount"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="exportData(scope.row.id)">
                                <i class="table-icon el-icon-download mr30"></i>
                            </span>
                            <span class="cursor_p" @click="editTestPaper('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.name)">
                                <i class="table-icon el-icon-delete"></i>
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
import config from 'api/api_conf';
const { assess } = config;
export default {
    name: 'testPaperLibrary',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '试卷库',
                    path: '/assess/resourceMgt/testPaperLibrary',
                    routeName: 'assessResourceMgtTestPaperLibrary',
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
            subjectIds: [], // table选中的题目id集合
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
            const res = await this.$api.getTestPaperPage(this.search);
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
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.subjectIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 预览试卷
         */
        goDetail (id) {
            this.$router.push('/assess/resourceMgt/testPaperDetail/' + id);
        },
        /**
         * 编辑试卷
         */
        editTestPaper (type, id) {
            this.$router.push({
                path: '/assess/resourceMgt/testPaperEdit/' + type + '/' + id
            });
        },
        /**
         * 试卷导出
         */
        exportData (id) {
            window.location.href = assess + '/testPaper/outputTestPaper?id=' + id;
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的试卷！');
                return;
            }
            const ids = this.subjectIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的试卷!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchTestPaper({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 删除
         * @param id 试卷id
         * @param name 试卷名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeTestPaper({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
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
