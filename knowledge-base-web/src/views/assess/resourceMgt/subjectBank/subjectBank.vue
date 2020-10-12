<!--
@file：知识测评-题库
@createTime：2020-04-08
@updateTime：2020-04-09
@author：Claire
-->
<template>
    <div class="subjectBank">
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
                    <el-button class="btn_bg_blue" @click="editSubject('add')">新增题目</el-button>
                    <el-button class="btn_border_blue" @click="dialogVisible = true">导入</el-button>
                    <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
                    <input type="file" ref="uploadExcl" hidden @change="uploadExcl($event)"/>
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
                            prop="type"
                            label="题型">
                        <template slot-scope="scope">
                            <span class="cursor_p">{{ scope.row.type ? '多选' : '单选' }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="题目名称">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="goDetail(scope.row.id)">{{ scope.row.name }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="difficultyName"
                            label="难度">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="score"
                            label="分值">
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
                            <span class="cursor_p" @click="editSubject ('edit', scope.row.id)">
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
        <!--导入对话框-->
        <el-dialog
                class="dialog-box"
                center
                title="EXCEL导入"
                :visible.sync="dialogVisible"
                width="512px">
            <div class="dialog-main">
                <div class="import-item mr40" @click="downloadTemplate">
                    <img src="~assets/images/download.png" alt="">
                    <p>下载excel模板</p>
                </div>
                <div class="import-item" @click="$refs.uploadExcl.click()">
                    <img src="~assets/images/upload.png" alt="">
                    <p>上传题目文件</p>
                </div>
            </div>
            <!--<span slot="footer" class="dialog-footer">-->
                <!--<el-button class="btn_border_blue" @click="dialogVisible = false">取 消</el-button>-->
                <!--<el-button class="btn_bg_blue" @click="dialogVisible = false">确 定</el-button>-->
            <!--</span>-->
        </el-dialog>
    </div>
</template>

<script>
import config from 'api/api_conf';
const { assess } = config;
export default {
    name: 'subjectBank',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '题库',
                    path: '/assess/resourceMgt/itemBank',
                    routeName: 'assessResourceMgtItemBank',
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
            total: 0, // 总条目数
            dialogVisible: false // 导入对话框是否显示
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
            const res = await this.$api.getSubjectPage(this.search);
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
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的题目！');
                return;
            }
            const ids = this.subjectIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的题目!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchSubject({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
            });
        },
        /**
         * 导入excel
         */
        uploadExcl (event) {
            if (event != null && event != '') {
                var file = event.srcElement.files;
                var fileName = file[0].name;// 获取文件名字
                if (fileName == '') {
                    this.$message.warning('请选择上传的Excel文件');
                    return;
                }
                var fileType = (fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length)).toLowerCase();
                console.log(111223);
                if (fileType != 'xls' && fileType != 'xlsx') {
                    console.log('fileType', fileType);
                    this.$message.error('文件格式不对，请上传xlsx,xls的格式文件');
                    return;
                }
                var formData = new FormData();
                formData.append('fileName', file[0]);
                console.log('formData:', formData);
                this.$api.inputSubject(formData).then(res => {
                    this.$message.success('导入成功');
                    this.getData();
                    this.dialogVisible = false;
                });
            }
        },
        /**
         * 模板下载
         */
        downloadTemplate () {
            window.location.href = assess + '/subject/outputSubjectTemplete';
            this.dialogVisible = false;
        },
        /**
         * 跳转到详情
         * @param id 题目id
         */
        goDetail (id) {
            this.$router.push({
                path: '/assess/resourceMgt/itemBank/subjectDetail/',
                query: { id: id }
            });
        },
        /**
         * 删除
         * @param id 题目id
         * @param name 题目名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeSubject({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 编辑题目
         */
        editSubject (type, id) {
            this.$router.push({
                path: '/assess/resourceMgt/itemBank/subjectEdit/' + type,
                query: { id: id }
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
.subjectBank {
    .dialog-box {
        /deep/.el-dialog__body {
            padding: 38px;
        }
        .dialog-main {
            .import-item {
                display: inline-block;
                width:198px;
                height:152px;
                text-align: center;
                border:1px solid rgba(216,216,216,1);
                border-radius:4px;
                img {
                    margin-top: 29px;
                }
                p {
                    line-height: 40px;
                }
            }
        }
    }
}
</style>
