<template>
    <div class="selectDocDialog">
        <!--文档选择对话框-->
        <el-dialog
                class="docSelect-dialog"
                title="添加文档"
                center
                :visible.sync="dialogVisible"
                width="920px">
            <div class="dialog-main">
                <!--table页搜索查询-->
                <table-search ref="tableSearch"
                              :selectOptions="docCategoryOptions"
                              selectLabel="分类"
                              :isDatepickerShow="false"
                              :isOptionsAllShow="false"
                              defaultOption="company"
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
                            @row-click="rowClick"
                            @current-change="handleCurrentRowChange">
                        <el-table-column
                                class-name="table-column-name"
                                label="名称"
                                width="400"
                                show-overflow-tooltip>
                            <template slot-scope="scope">
                                <img class="doc-icon" src="~assets/images/doc-type-folder.png" alt="" v-if="scope.row.type==0">
                                <template v-if="scope.row.type==1">
                                    <img class="doc-icon" src="~assets/images/doc-type-w.png" alt=""
                                         v-if="scope.row.suffix == '.doc'">
                                    <img class="doc-icon" src="~assets/images/doc-type-x.png" alt=""
                                         v-if="scope.row.suffix=='xls'">
                                    <img class="doc-icon" src="~assets/images/doc-type-p.png" alt=""
                                         v-if="scope.row.suffix=='pptx' || scope.row.suffix=='ppt'">
                                    <img class="doc-icon" src="~assets/images/doc-type-other.png" alt="" v-else>
                                </template>
                                <span class="table-column-name-edit" v-if="scope.row.isEdit">
                                    <el-input class="w250 ml20"
                                              autocomplete
                                              v-focus="scope.row.isFocus"
                                              ref="nameEdit"
                                              v-model="scope.row.name"
                                              @blur="tableInputBlur(scope.$index, scope.row.id, scope.row.name)"></el-input>
                                </span>
                                <span v-else>
                                    <span class="doc-name ml20">{{ scope.row.name }}</span>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="类型">
                            <template slot-scope="scope">
                                <span v-if="scope.row.type===0">文件夹</span>
                                <span v-else>{{ scope.row.suffix }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="updateTime"
                                label="更新时间">
                            <template slot-scope="scope">
                                <span>{{scope.row.updateTime | date('YYYY-MM-DD HH:ss')}}</span>
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
    name: 'selectDocDialog',
    data () {
        return {
            dialogVisible: false, // 文档选择对话框是否可见
            docCategoryOptions: [], // 文档分类下拉选项
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                weight: 0,
                pid: 0
            }, // 数据查询参数对象
            total: 0, // 总条目数
            lastDbId: 0, // 上级文件夹id/当前文件夹父id （用于当前是最后一个层级且当前层级为空时查询数据）
            selectedDocRow: null // 选中的文件对象
        };
    },
    created () {
    },
    methods: {
        /**
         * 对话框显示
         */
        handleShow () {
            this.getKnowledgeCategoryList();
            this.getData();
            this.dialogVisible = true;
        },
        /**
         * 对话框隐藏
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
            const res = await this.$api.getBaseDocumentPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
            if (this.search.pid != '0') {
                this.tableData.unshift({
                    id: '-1',
                    name: '...（返回上一级）',
                    type: '',
                    size: '',
                    time: ''
                });
            }
        },
        /**
         * 查询
         */
        searchData (data) {
            if (data.option == 'company') {
                this.search = {
                    pageNum: 1,
                    pageSize: 10,
                    name: data.msg,
                    weight: 0,
                    pid: 0
                };
            } else {
                this.search = {
                    pageNum: 1,
                    pageSize: 10,
                    name: data.msg,
                    weight: 3,
                    categoryId: data.option,
                    type: 1,
                    pid: 0
                };
            }
            this.getData(1, this.search.pageSize);
        },
        /**
         * 获取知识分类
         */
        getKnowledgeCategoryList () {
            const param = {};
            this.$api.getAllBaseDocumentCategory(param).then(res => {
                const list = res;
                for (let i = 0; i < list.length; i++) {
                    this.docCategoryOptions.push({
                        label: list[i].name,
                        value: list[i].id
                    });
                }
                this.docCategoryOptions.unshift({
                    label: '公司文档',
                    value: 'company'
                });
            });
        },
        /**
         * 某一行被点击时触发
         * * @param row [type:文档类型 0：文件夹  1：文件]
         */
        rowClick (row) {
            if (row.id === '-1') { // 返回上一级
                let id = '';
                if (this.tableData.length <= 1) {
                    // 如果是最后一个层级且当前层级为空，用自己存的上级文件夹id查数据
                    id = this.lastDbId;
                } else {
                    // 如果不是最后一个层级，用当前层级中返回的数据的pid
                    id = this.tableData[1].pid;
                }
                this.getDocDetail(id);
            }
            if (row.type) { // 文件
                this.$refs.elTable.setCurrentRow(row);
            } else { // 文件夹
                if (row.id != '-1') { // '返回上一级'无双击事件
                    this.search.pid = row.id;
                    this.lastDbId = row.id;
                    this.getData();
                }
            }
        },
        /**
         * 查询某个文档获取到pid，查询table数据
         * @param id
         * @returns {Promise<void>}
         */
        async getDocDetail (id) {
            await this.$api.getBaseDocument({ id: id }).then(res => {
                this.search.pid = res.pid;
                this.getData();
            });
        },
        /**
         * table选中时触发
         */
        handleCurrentRowChange (val) {
            this.selectedDocRow = val;
        },
        /**
         * 文档选中提交
         */
        submit () {
            if (this.selectedDocRow) {
                console.log('this.selectedDocRow：', this.selectedDocRow);
                this.$emit('selected', this.selectedDocRow);
                this.dialogVisible = false;
            } else {
                this.$message.warning('请选择要添加的文件！');
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
    .selectDocDialog {
        .docSelect-dialog {
            .dialog-main {
                .table-box {
                    /deep/.el-table {
                        td, th {
                            padding: 5px 0;
                        }
                    }
                    .doc-icon, .doc-name {
                        vertical-align: middle;
                    }
                }
            }
        }
    }
</style>
