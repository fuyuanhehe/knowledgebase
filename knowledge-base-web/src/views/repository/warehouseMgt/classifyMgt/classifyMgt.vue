<!--
@file：知识仓库-知识分类管理
@createTime：20200319
@updateTime：20200319
@author：Claire
-->
<template>
    <div class="classifyMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-button class="btn_bg_blue" @click="editCategory('add')">新增分类</el-button>
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
                            label="分类名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.createTime | date('YYYY-MM-DD HH:ss')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editCategory('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.name)">
                                <i class="table-icon el-icon-delete"></i>
                            </span>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <!--新增/编辑对话框-->
        <el-dialog
                class="dialog-box"
                center
                :title="editType == 'edit' ? '编辑分类' : '新增分类'"
                :visible.sync="dialogVisible"
                width="512px">
            <div class="dialog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                    <el-form-item label="" prop="id">
                        <input v-model="ruleForm.id" hidden>
                    </el-form-item>
                    <el-form-item label="分类名称：" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button class="btn_border_blue" @click="dialogVisible = false">取 消</el-button>
                <el-button class="btn_bg_blue" @click="onsubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'classifyMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                }, {
                    name: '知识仓库管理',
                    path: '/repository/warehouseMgt',
                    routeName: 'repositoryWarehouseMgt'
                }, {
                    name: '知识分类管理',
                    path: '/repository/warehouseMgt/classifyMgt',
                    routeName: 'repositoryClassifyMgt'
                }
            ], // 面包屑导航集合
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            categoryTotalNum: 0, // 现有知识分类数量
            multipleSelection: [], // table选中集合
            dialogVisible: false, // 新增/编辑对话框是否显示
            editType: 'add', // 编辑类型 add:新增； edit:修改
            ruleForm: {
                id: '',
                name: ''
            },
            rules: {
                name: [
                    { required: true, message: '请输入分类名称', trigger: 'blur' }
                ]
            },
            search: {
                name: ''
            } // 数据查询参数对象
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取table数据
         */
        async getData () {
            this.loading = true;
            this.tableData = await this.$api.getAllBaseDocumentCategory(this.search);
            this.loading = false;
            this.categoryTotalNum = this.tableData.length;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
        },
        /**
         * 新增/编辑分类
         * @param type 编辑类型:add:新增； edit:修改
         * @param id 编辑的分类id
         */
        editCategory (type, id) {
            this.editType = type;
            if (type === 'add') {
                if (this.categoryTotalNum >= 10) {
                    this.$message.warning('最多创建10个知识分类哦！');
                    return;
                }
                this.ruleForm = {};
            } else {
                this.$api.getBaseDocumentCategory({ id: id }).then(res => {
                    this.ruleForm = res;
                });
            }
            this.dialogVisible = true;
        },
        /**
         * 分类新增/编辑提交
         */
        onsubmit () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.editType === 'edit') {
                        this.$api.modifyBaseDocumentCategory(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('修改分类成功');
                            this.getData();
                        });
                    } else {
                        this.$api.saveBaseDocumentCategory(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('新增分类成功');
                            this.getData();
                        });
                    }
                }
            });
        },
        /**
         * 删除
         * @param id 文件id
         * @param name 文件名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                this.$api.removeBaseDocumentCategory({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        }
    }
};
</script>

<style scoped lang="scss">
.classifyMgt {
    .content {
        padding: 40px 50px;
        min-height: 600px;
    }
}
</style>
