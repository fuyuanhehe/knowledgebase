<template>
    <div class="organization">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <ul class="organization-list">
                <li class="organization-list-title">
                    <img src="~assets/images/framework-icon.png" alt="">
                    <span>和贯科技</span>
                </li>
                <li class="organization-list-item" v-for="item in organList">{{ item.name }}</li>
            </ul>
            <div class="organization-table">
                <div class="organization-table-title">
                    <span @click="editDept('add')"><i class="el-icon-circle-plus-outline"></i>新增部门</span>
                </div>
                <div class="table-box">
                    <el-table
                            border
                            :data="organList"
                            style="width: 100%">
                        <el-table-column
                                align="center"
                                prop="name"
                                label="部门名称">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="pname"
                                label="上级部门">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                label="操作">
                            <template slot-scope="scope">
                            <span class="cursor_p" @click="editDept('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                                <span class="cursor_p" @click="deleteDept(scope.row.id, scope.row.name)">
                                <i class="table-icon el-icon-delete"></i>
                            </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </div>
        <!--新增/编辑部门对话框-->
        <el-dialog
                class="dialog-box"
                center
                :title="editType == 'edit' ? '编辑部门' : '新增部门'"
                :visible.sync="dialogVisible"
                width="428px">
            <div class="dialog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                    <el-form-item label="部门名称：" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="上级部门：" prop="leader">
                        <el-select v-model="ruleForm.pid" placeholder="请选择上级部门">
                            <el-option v-for="item in organList"
                                       :key="item.id"
                                       :label="item.name"
                                       :value="item.id">{{ item.name }}</el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button class="btn_border_blue" @click="importDialogVisible = false">取 消</el-button>
                <el-button class="btn_bg_blue" @click="onsubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'organization',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '组织架构',
                    path: '/system/systemSetup/organization',
                    routeName: 'systemSetupOrganization'
                }
            ], // 面包屑导航集合
            organList: [], // 组织架构集合
            dialogVisible: false, // 新增/编辑部门对话框是否显示
            dialogTitle: '', // 对话框标题
            isShow: true, // 是否显示
            editType: 'add', // 编辑类型 add:新增； edit:修改
            currentDeptId: '', // 当前编辑部门id
            ruleForm: {
                name: '',
                pid: ''
            },
            rules: {
                name: [
                    { required: true, message: '请输入部门名称', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                ]
            }
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取组织架构数据
         */
        async getData () {
            this.organList = await this.$api.getAllDept({});
        },
        /**
         * 新增/编辑部门
         * @param type 编辑类型
         * @param id 编辑的部门id
         */
        editDept (type, id) {
            this.dialogVisible = true;
            this.editType = type;
            if (type === 'add') {
                this.isShow = true;
                this.ruleForm = {
                    name: '',
                    pid: ''
                };
            } else {
                this.isShow = false;
                this.currentDeptId = id;
                this.$api.getDept({ id: id }).then(res => {
                    this.ruleForm = res;
                });
            }
        },
        /**
         * 部门新增/编辑提交
         */
        onsubmit () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.editType === 'edit') {
                        this.$set(params, 'id', this.currentDeptId);
                        this.$api.modifyDept(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('修改部门成功');
                            this.getData();
                        });
                    } else {
                        this.$api.saveDept(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('新增部门成功');
                            this.getData();
                        });
                    }
                }
            });
        },
        /**
         * 删除部门
         * @param id 部门id
         * @param name 部门名称
         */
        deleteDept (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeDept({ id: id }).then(res => {
                    this.$message.success('删除成功!');
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
.organization {
    .content {
        width: 80%;
        margin: 38px auto 120px;
        padding: 0;
        height:598px;
        border:1px solid $color-border-base;
        .organization-list {
            display: inline-block;
            width: 20%;
            height: 100%;
            vertical-align: top;
            border-right: 1px solid $color-border-base;
            font-size: $font-size-middle;
            &-title {
                width: 100%;
                height:60px;
                line-height: 60px;
                margin-bottom: 20px;
                vertical-align: middle;
                color: $color-text-blue;
                background:rgba(242,249,255,1);
                border-bottom:1px solid $color-border-base;
                img {
                    margin-left: 40px;
                    vertical-align: middle;
                }
                span {
                    margin-left: 14px;
                    vertical-align: middle;
                }
            }
            &-item {
                padding-left: 72px;
                line-height: 40px;
            }
        }
        .organization-table {
            display: inline-block;
            width: 80%;
            height: 100%;
            &-title {
                width: 100%;
                height:60px;
                line-height: 60px;
                text-align: right;
                background:rgba(242,249,255,1);
                border-bottom:1px solid $color-border-base;
                span {
                    color: $color-text-regular;
                    margin-right: 25px;
                    cursor: pointer;
                    i {
                        margin-right: 8px;
                    }
                }
            }
            .table-box {
                width: 80%;
                margin: 35px auto;
                /deep/.el-table {
                    th {
                        background:rgba(246,246,246,1);
                    }
                }
            }
        }
    }
}
</style>
