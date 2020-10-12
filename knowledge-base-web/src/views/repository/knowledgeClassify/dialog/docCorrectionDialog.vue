<template>
    <div class="docCorrectionDialog">
        <el-dialog
                title="文档纠错"
                center
                :visible.sync="dialogVisible"
                width="920px">
            <div class="dialog-main">
                <div class="doc-info">
                    <span>纠错人：{{ correctionUser }}</span>
                    <span>所属部门：{{ correctionUserDeptName }}</span>
                </div>
                <div class="doc-info">
                    <span>知识标题：{{ info.name }}</span>
                    <span>知识所属分类：{{ info.reserve4 }}</span>
                </div>
                <div class="doc-info" v-if="dialogType">
                    <span>纠错时间：{{ correctionTime | date('YYYY-MM-DD HH:ss')}}</span>
                    <span>纠错确认状态：{{ correctionStatus }}</span>
                </div>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                    <el-form-item label="纠错原因：" prop="reason">
                        <ueditor v-model="ruleForm.reason" />
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer" v-if="!dialogType">
                <el-button @click="handleCancel">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'docCorrectionDialog',
    props: ['info'],
    data () {
        return {
            dialogVisible: false, // 文档选择对话框是否可见
            ruleForm: {
                reason: ''
            },
            rules: {
                reason: [
                    { required: true, message: '请填写纠错原因', trigger: 'blur' }
                ]
            },
            dialogType: 0, // 对话框类型：0--新增；1--编辑
            correctionUser: '张三',
            correctionUserDeptName: '财务部',
            correctionTime: '', // 纠错时间
            correctionStatus: '' // 纠错确认状态
        };
    },
    methods: {
        /**
         * 对话框显示
         */
        handleShow (data) {
            if (data) {
                this.dialogType = 1;
                this.correctionUser = data.createUser;
                this.correctionUserDeptName = data.reserve1;
                this.correctionStatus = data.status ? '已确认' : '未确认';
                this.correctionTime = data.createTime;
                this.info.name = data.reserve2;
                this.info.reserve4 = data.reserve3;
                // this.ruleForm.reason = data;
            } else {
                this.ruleForm.reason = '';
            }
            // this.getKnowledgeCategoryList();
            // this.getData();
            this.dialogVisible = true;
        },
        /**
         * @description 取消
         */
        handleCancel () {
            this.dialogVisible = false;
        },
        submit () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    this.$emit('submit', params);
                }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .docCorrectionDialog {
        .dialog-main {
            .doc-info {
                height: 40px;
                line-height: 40px;
                span {
                    display: inline-block;
                    width: 50%;
                }
            }
            /deep/.el-form {
                .el-form-item__label {
                    margin-left: -19px;
                }
                .el-form-item__content {
                    margin-top: 14px;
                    margin-left: 70px !important;
                }
            }
        }
    }
</style>
