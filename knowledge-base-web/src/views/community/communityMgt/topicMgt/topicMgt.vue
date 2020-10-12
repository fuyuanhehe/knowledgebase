<!--
@file：知识社区-话题管理
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="topicMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :selectOptions="circleOptions"
                              :selectLabel="'所属圈子'"
                              :isDatepickerShow="false"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
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
                            label="话题名称">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="goDetail(scope.row.id)">{{ scope.row.name }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="circleName"
                            label="所属圈子">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="createUserName"
                            label="发布人">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="commentCount"
                            label="评论数">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editTopic(scope.row.id, scope.row.circleName)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.circleName)">
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
export default {
    name: 'topicMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识社区',
                    path: '/community',
                    routeName: 'community'
                }, {
                    name: '话题管理',
                    path: '/community/communityMgt/topicMgt',
                    routeName: 'communityTopicMgt',
                    disabled: true
                }
            ], // 面包屑导航集合
            circleOptions: [], // 圈子下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                circleId: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            multipleSelection: [], // table选中集合
            userIds: [], // table选中的用户id集合
            total: 0 // 总条目数
        };
    },
    created () {
        this.getCircleList();
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
            const res = await this.$api.getTopicCommentPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 获取部门列表
         */
        async getCircleList () {
            this.circleOptions = await this.$api.getAllCircle({});
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.name = data.msg;
            this.search.circleId = data.option;
            this.getData(1, this.search.pageSize);
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.userIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的用户！');
                return;
            }
            const ids = this.userIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的用户!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchTopic({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
            });
        },
        /**
         * 跳转到详情
         * @param id 话题id
         */
        goDetail (id) {
            this.$router.push({
                path: '/community/topicCircle/topicDetails/' + this.circleId + '/manage',
                query: { id: id, circleName: this.circleName }
            });
        },
        /**
         * 删除
         * @param id 用户id
         * @param name 用户名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeTopic({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 编辑话题
         */
        editTopic (id, name) {
            this.$router.push({
                path: '/community/topicEdit/manage/' + id + '/edit',
                query: { circleName: name }
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
