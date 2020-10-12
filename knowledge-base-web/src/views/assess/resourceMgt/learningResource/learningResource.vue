<!--
@file：知识测评-学习资源管理列表
@time：20200323
@author：Claire
-->
<template>
    <div class="learningResource">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :selectOptions="statusOptions"
                              selectLabel="学习状态"
                              dateLabel="创建时间"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_border_blue" @click="editResource('add')">新增学习资源</el-button>
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
                            label="名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="coverUrl"
                            label="文档封面">
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="`${$img}${scope.row.coverUrl}`"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="learnBeginTime"
                            label="开始时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.learnBeginTime | date('YYYY-MM-DD HH:ss')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="learnEndTime"
                            label="结束时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.learnEndTime | date('YYYY-MM-DD HH:ss')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="status"
                            label="学习状态">
                        <template slot-scope="scope">
                            <span>{{ scope.row.status === 0 ? '未开始' : (scope.row.status === 1 ? '学习中' : '已结束')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editResource('edit', scope.row.id)">
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
export default {
    name: 'learningResource',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '资源管理',
                    path: '/assess/resourceMgt',
                    routeName: 'assessResourceMgt'
                }, {
                    name: '学习资源',
                    path: '/assess/resourceMgt/learningResource',
                    routeName: 'assessResourceMgtLearningResource'
                }
            ], // 面包屑导航集合
            statusOptions: [
                { label: '未开始', value: 0 },
                { label: '学习中', value: 1 },
                { label: '已结束', value: 2 }
            ], // 学习状态下拉选项
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
            multipleSelection: [], // table选中集合
            userIds: [], // table选中的用户id集合
            total: 0, // 总条目数
            dialogVisible: false // 导入对话框是否显示
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
            const res = await this.$api.getCurriculumPage(this.search);
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
         * 新增/编辑用户
         * @param type 操作类型：add--新增；edit--修改
         * @param id 用户id
         */
        editResource (type, id) {
            if (type === 'add') {
                this.$router.push('/assess/resourceMgt/learningResource/add');
            } else {
                this.$router.push({
                    path: '/assess/resourceMgt/learningResource/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的学习资源！');
                return;
            }
            const ids = this.userIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的学习资源!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchCurriculum({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
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
                this.$api.removeCurriculum({ id: id }).then(res => {
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
