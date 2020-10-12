<!--
@file：知识测评-考试编辑-试卷选择弹框
@createTime：2020-04-14
@updateTime：2020-04-14
@author：Claire
-->
<template>
    <div class="selectTestPaperDialog">
        <el-dialog
                title="选择试卷"
                center
                :visible.sync="dialogVisible"
                width="920px">
            <div class="dialog-main">
                <!--table页搜索查询-->
                <table-search ref="tableSearch"
                              dateLabel="创建时间"
                              :isSelectShow="false"
                              @search="searchData"></table-search>
                <div class="table-box mt20">
                    <el-table
                            ref="elTable"
                            :data="tableData"
                            v-loading="loading"
                            element-loading-text="拼命加载中"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            header-cell-class-name="table-th"
                            highlight-current-row
                            style="width: 100%"
                            @current-change="handleCurrentRowChange">
                        <el-table-column
                                align="center"
                                prop="name"
                                label="试卷名称">
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
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleCancel">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'selectTestPaperDialog',
    data () {
        return {
            dialogVisible: false, // 文档选择对话框是否可见
            tableData: [],
            loading: false, // table是否显示加载动画
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                beginCreateTime: '',
                endCreateTime: ''
            }, // 数据查询参数对象
            total: 0, // 总条目数
            selectedTestPaperRow: null // 选中的试卷对象
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 对话框显示
         */
        handleShow () {
            this.dialogVisible = true;
        },
        /**
         * @description 取消
         */
        handleCancel () {
            this.dialogVisible = false;
        },
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
            this.getData();
        },
        /**
         * table选中时触发
         */
        handleCurrentRowChange (val) {
            this.selectedTestPaperRow = val;
        },
        /**
         * 试卷选中提交
         */
        submit () {
            if (this.selectedTestPaperRow) {
                this.$emit('selected', this.selectedTestPaperRow);
                this.dialogVisible = false;
            } else {
                this.$message.warning('请选择要添加的试卷！');
            }
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
    .selectTestPaperDialog {
    }
</style>
