<!--
@file：知识测评-考试管理
@createTime：2020-04-13
@updateTime：2020-04-15
@author：Claire
-->
<template>
    <div class="testMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search dateLabel="创建时间"
                              selectLabel="考试状态"
                              :selectOptions="testStatusOptions"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_bg_blue" @click="editExam('add', null)">新增考试</el-button>
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
                            label="考试名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="beginTime"
                            label="考试开始时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.beginTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="endTime"
                            label="考试截止时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.endTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="status"
                            label="考试状态">
                        <template slot-scope="scope">
                            <span>{{ handlerStatusMsg(scope.row.status) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editExam('edit', scope.row.id)" v-if="!scope.row.status">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="goExamStatistic(scope.row.id)" v-if="scope.row.status === 2">
                                <v-icon class="table-icon mr30" name="icontongji"></v-icon>
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
    name: 'testMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '考试管理',
                    path: '/assess/resourceMgt/testMgt',
                    routeName: 'assessResourceMgtTestMgt',
                    disabled: true
                }
            ], // 面包屑导航集合
            testStatusOptions: [
                { label: '未开始', value: 0 },
                { label: '进行中', value: 1 },
                { label: '已结束', value: 2 }
            ], // 考试状态下拉选项
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
            examIds: [], // table选中的考试id集合
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
            const res = await this.$api.getExamPage(this.search);
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
            this.getData();
        },
        /**
         * 处理考试状态显示文字
         */
        handlerStatusMsg (status) {
            let msg = '';
            switch (status) {
            case 1:
                msg = '进行中';
                break;
            case 2:
                msg = '已结束';
                break;
            default:
                msg = '未开始';
                break;
            }
            return msg;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.examIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 编辑考试
         */
        editExam (type, id) {
            if (type === 'add') {
                this.$router.push('/assess/resourceMgt/examEdit/add');
            } else {
                this.$router.push({
                    path: '/assess/resourceMgt/examEdit/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 跳转到考试统计页
         */
        goExamStatistic (id) {
            this.$router.push({
                path: '/assess/resourceMgt/examStatistic',
                query: { id: id }
            });
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的考试！');
                return;
            }
            const ids = this.examIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的考试!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchExam({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 删除
         * @param id 考试id
         * @param name 考试名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeExam({ id: id }).then(res => {
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
