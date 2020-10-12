<!--
@file：知识仓库-精品文档管理-添加/编辑精品
@time：20200319
@author：Claire
-->
<template>
    <div class="qualityDocumentEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="docObj" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="文档名称：" prop="name">
                    <el-input class="cursor_p"
                              readonly
                              v-if="pageType === 'add'"
                              v-model="docObj.name"
                              suffix-icon="el-icon-plus"
                              @click.native="selectDoc"></el-input>
                    <el-input v-else disabled v-model="docObj.name" suffix-icon="el-icon-plus"></el-input>
                </el-form-item>
                <!--<el-form-item label="文档创建人：" prop="createBy">-->
                    <!--<el-input v-model="docObj.createUser" disabled></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="所属部门：" prop="depName">-->
                    <!--<el-input v-model="docObj.depName" disabled></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="文档创建时间：" prop="createTime">-->
                    <!--<el-input v-bind:value="docObj.createTime | date('YYYY-MM-DD HH:ss')" disabled></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="封面图片：" prop="coverUrl">
                    <upload-image :src.sync="docObj.coverUrl" :width="210" :height="140"></upload-image>
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
        <selectDocDialog ref="selectDocDialog" @selected="getSelectedDoc"></selectDocDialog>
    </div>
</template>

<script>
import selectDocDialog from './dialog/selectDocDialog';
export default {
    name: 'qualityDocumentEdit',
    components: {
        selectDocDialog
    },
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型： add-新增；edit-编辑
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
                    name: '精品文档管理',
                    path: '/repository/warehouseMgt/qualityDocument',
                    routeName: 'repositoryQualityDocumentMgt'
                }, {
                    name: this.$route.params.type === 'add' ? '添加精品' : '编辑精品',
                    path: '/repository/warehouseMgt/qualityDocument/' + this.$route.params.type,
                    routeName: 'repositoryQualityDocumentEdit'
                }
            ], // 面包屑导航集合
            docId: '', // 当前编辑精品文档id
            ruleForm: {
                id: '',
                reserve3: '0',
                coverUrl: ''
            }, // 表单对象
            docObj: {
                name: '',
                createUser: '',
                createTime: '',
                depName: '',
                coverUrl: ''
            }, // 选中文档对象
            docName: '',
            rules: this.validationRules() // 表单验证规则
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
            });
        } else { // 修改
            next(vm => {
                vm.docId = to.query.id;
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取精品文档信息
         * @param id 精品文档id
         */
        async getData (id) {
            const params = { id: id };
            this.docObj = await this.$api.getBaseDocument(params);
            this.ruleForm.id = this.docObj.id;
        },
        /**
         * 选择文档(显示选择文档对话框)
         */
        selectDoc () {
            this.$refs.selectDocDialog.handleShow();
        },
        /**
         * 获取选中文件
         * @param val 选中的文件对象
         */
        getSelectedDoc (val) {
            // this.docObj = val;
            this.docObj.name = val.name;
            this.ruleForm.id = val.id;
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.ruleForm.coverUrl = this.docObj.coverUrl;
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.modifyBaseDocument(params).then(res => {
                            this.$message.success('添加精品文档成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyBaseDocument(params).then(res => {
                            this.$message.success('修改精品文档成功!');
                        });
                    }
                    this.$router.push({ path: '/repository/warehouseMgt/qualityDocument' });
                }
            });
        },
        /**
         * 取消（返回精品文档列表）
         */
        goBack () {
            this.$router.push({ path: '/repository/warehouseMgt/qualityDocument' });
        },
        /**
         * 表单验证规范
         * @returns
         */
        validationRules () {
            return {
                name: [
                    { required: true, message: '请选择文档', trigger: 'blur' }
                ],
                coverUrl: [
                    { required: true, message: '请选择封面图片', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
.qualityDocumentEdit {
    .content {
    }
    .docSelect-dialog {
        /deep/.el-dialog__body {
            .dialog-main {
                .dialog-bottom {
                    position: relative;
                    margin-top: 20px;
                    max-height: 350px;
                    overflow: auto;
                    &-menu {
                        .menu-item {
                            height: 30px;
                            line-height: 30px;
                        }
                    }
                    .divider-line {
                        position: absolute;
                        min-height: 100%;
                        height: 100%;
                        border-right: 1px solid #ddd;
                    }
                    &-tree {
                    }
                }
            }
        }
    }
}
</style>
