<!--
@file：项目协作-项目详情-项目进度-编辑项目阶段对话框
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="editStageDialog">
        <el-dialog
                class="dialog-wrapper"
                :title="editType === 'add' ? '增加阶段' : '编辑阶段'"
                center
                :visible.sync="dialogVisible"
                width="500px">
            <div class="dialog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="110px">
                    <el-form-item label="阶段名称：" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="阶段负责人：" prop="blameUser">
                        <el-input class="cursor_p"
                                  readonly
                                  v-model="ruleForm.blameUser"
                                  suffix-icon="el-icon-plus"
                                  @click.native="handlerDialogShow('selectChargePersonDialog')"
                                  placeholder="请选择阶段负责人"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间：" prop="beginDate">
                        <el-date-picker type="date"
                                        placeholder="请选择日期"
                                        v-model="ruleForm.beginDate"
                                       ></el-date-picker>
                    </el-form-item>
                    <el-form-item  label="结束时间：" prop="endDate">
                        <el-date-picker type="date"
                                        placeholder="请选择日期"
                                        v-model="ruleForm.endDate"
                                        @blur="dateChange" ></el-date-picker>
                    </el-form-item>
                    <el-form-item label="工期：" prop="validDay">
                        <el-input v-model="ruleForm.validDay" disabled></el-input>
                        <span>天</span>
                    </el-form-item>
                    <el-form-item label="进度：" prop="progress">
                        <el-select v-model="ruleForm.progress" placeholder="请选择进度">
                            <el-option v-for="item in progressOptions"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="handleCancel">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </span>
        </el-dialog>
        <!--项目阶段负责人选择弹框-->
        <selectUserDialog ref="selectChargePersonDialog"
                          @selected="getSelectedChargePerson"
                          selectType="single"
                          :selectedUsers="checkedChargePerson"
                          :selectedUserIds="ruleForm.blameUserId"></selectUserDialog>
    </div>
</template>

<script>
import selectUserDialog from './selectUserDialog';
export default {
    name: 'editStageDialog',
    components: {
        selectUserDialog
    },
    data () {
        return {
            editType: 'add',
            dialogVisible: false, // 对话框是否可见
            ruleForm: {
              name: '',
              blameUser: '',
              blameUserId: '',
              beginDate: '',
              endDate: '',
              validDay: '',
              progress: ''
            },
            rules: this.validationRules(), // 表单验证规则
            checkedChargePerson: {}, // 选中阶段负责人对象
            chargePersonName: '', // 选中阶段负责人显示姓名
            progressOptions: [
                { value: '0', label: '进行中' },
                { value: '1', label: '已完工' },
                { value: '2', label: '失败' },
                { value: '3', label: '暂停' }
            ], // 项目进度下拉选项
        };
    },

  created(){
  },
    methods: {
      /**
       * 对话框显示
       */
      handlerDialogShow (dialogName) {
        this.$refs[dialogName].handleShow();
      },
        /**
         * 对话框显示
         */
        handleShow (data) {
          if (data) {
            this.editType = 'edit';
            this.ruleForm = data;
          } else {
            this.editType = 'add';
            this.ruleForm = {};
          }
            this.dialogVisible = true;
            this.$nextTick(() => {
              this.$refs.ruleForm.clearValidate()
            })
        },
        /**
         * @description 取消
         */
        handleCancel () {
            this.dialogVisible = false;
        },
        /**
         * 获取项目负责人选中对象
         */
        getSelectedChargePerson (data) {
          console.log(data)
            this.ruleForm.blameUserId = data.id;
            this.ruleForm.blameUser = data.nickName;
            this.checkedChargePerson = data;
        },
        dateChange (data) {
            //date1：开始日期，date2结束日期
          const a1 = Date.parse(new Date(this.ruleForm.beginDate));
          const a2 = Date.parse(new Date(this.ruleForm.endDate));
          const day = parseInt((a2-a1)/ (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
            if (a1 && a2) {
              this.ruleForm.validDay = day;
            }
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                  const params = this.ruleForm;
                  console.log(sessionStorage.getItem('projectId'));
                  const projectId = sessionStorage.getItem('projectId');
                  params.projectId = projectId;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveProjectStage(params).then(res => {
                            this.$message.success('新增阶段成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyProjectStage(params).then(res => {
                            this.$message.success('修改阶段成功!');
                        });
                    }
                    this.handleCancel();
                    this.$emit('submitForm');
                }
            });
        },
        /**
         * 表单验证
         */
        validationRules () {
            const checkStageOnly = (rule, value, callback) => {
                // 修改阶段时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditUserNum) {
                    callback();
                } else {
                    this.$api.checkProjectStageName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该阶段名称已经存在，请重新输入'));
                        }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入阶段名称', trigger: 'blur' },
                    { required: true, validator: checkStageOnly, trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                ],
                blameUser: [
                  { required: true, message: '请选择阶段负责人', trigger: 'change' }
                ],
                beginDate: [
                    { required: true, message: '请选择项目开始时间', trigger: 'change' }
                ],
                endDate: [
                    { required: true, message: '请选择项目截止时间', trigger: 'change' }
                ],
                validDay: [
                    { required: true, message: '请输入工期', trigger: 'change' }
                ],
                progress: [
                    { required: true, message: '请选择项目阶段', trigger: 'change' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .editStageDialog {
    }
</style>
