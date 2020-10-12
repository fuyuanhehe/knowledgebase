<!--
@file：项目协作-项目详情-项目风险
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectRIsk">
        <el-form class="ruleForm" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="110px">
            <el-form-item label="风险描述：" prop="riskDescription">
                <el-input class="ruleForm-textarea" type="textarea" v-model="ruleForm.riskDescription"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button class="btn_bg_blue" @click="submitForm">保存</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'projectRIsk',
    data () {
        return {
            ruleForm: { riskDescription: '',
              id: sessionStorage.getItem('projectId')
            },
            rules: this.validationRules() // 表单验证规则

        };
    },
    methods: {
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                  const params = this.ruleForm;
                   await this.$api.modifyProject(params).then(res => {
                     this.$message.success('修改风险描述成功!');
                   })
                    // const params = this.ruleForm;
                /*    if (this.pageType === 'add') { // 新增
                        // await this.$api.saveProjectStage(params).then(res => {
                        //     this.$message.success('新增阶段成功!');
                        // });
                    } else { // 修改
                        // await this.$api.modifyProjectStage(params).then(res => {
                        //     this.$message.success('修改阶段成功!');
                        // });
                    }*/
                    this.$emit('submitForm');
                }
            });
        },
        /**
         * 表单验证
         */
        validationRules () {
            return {
                riskDescription: [
                    { required: true, message: '请输入风险描述', trigger: 'blur' },
                    { min: 1, max: 1000, message: '长度在 1 到 1000 个字符', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .projectRIsk {
        .ruleForm {
            &-textarea {
                /deep/.el-textarea__inner {
                    width: 700px;
                    height: 325px;
                }
            }
        }
    }
</style>
