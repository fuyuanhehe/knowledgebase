<!--
@file：知识测评-考试管理-考试编辑
@createTime：2020-04-13
@updateTime：2020-04-14
@author：Claire
-->
<template>
    <div class="examEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form class="form-box" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item label="考试名称：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入考试名称"></el-input>
                </el-form-item>
                <el-form-item label="考试时间：" required>
                    <el-form-item class="display_ib" prop="beginTime">
                        <el-date-picker class="w200" type="datetime"
                                        placeholder="请选择考试开始时间"
                                        v-model="ruleForm.beginTime"></el-date-picker>
                    </el-form-item>
                    <span class="mlr10">—</span>
                    <el-form-item class="display_ib" prop="endTime">
                        <el-date-picker class="w200" type="datetime"
                                        placeholder="请选择考试结束时间"
                                        v-model="ruleForm.endTime"></el-date-picker>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="答题时间：" prop="answerTime">
                    <el-input class="w220 mr10" v-model.number="ruleForm.answerTime" placeholder="请输入答题时间"></el-input>
                    <span>分钟</span>
                </el-form-item>
                <el-form-item label="考试试卷：" prop="testPaperId">
                    <!--<el-input v-model="ruleForm.testPaperId" placeholder="请选择考试试卷"></el-input>-->
                    <el-input class="cursor_p"
                              readonly
                              v-model="ruleForm.testPaperName"
                              suffix-icon="el-icon-plus"
                              @click.native="handlerDialogShow('selectTestPaperDialog')"
                              placeholder="请选择考试试卷"></el-input>
                </el-form-item>
                <el-form-item label="考试人员：" required>
                    <el-radio-group v-model="examiners" @change="handleExaminersChange">
                        <el-radio :label="0">全部人员</el-radio>
                        <el-radio :label="1" @click.native="handlerDialogShow('examinerDialog')">指定人员</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm()">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
        <selectTestPaperDialog ref="selectTestPaperDialog" @selected="getSelectedTestPaper"></selectTestPaperDialog>
        <template v-if="pageType == 'add'">
            <examinerDialog ref="examinerDialog"
                            @selected="getSelectedExaminer"
                            :checkedUsers="checkedUsers"
                            :designatedUserIds="checkedUserIds"></examinerDialog>
        </template>
        <template v-else>
            <examinerDialog ref="examinerDialog"
                            v-if="Array.isArray(checkedUserIds) && checkedUserIds.length > 0 || examinerDialogFlg"
                            @selected="getSelectedExaminer"
                            :checkedUsers="checkedUsers"
                            :designatedUserIds="checkedUserIds"></examinerDialog>
        </template>
    </div>
</template>

<script>
import selectTestPaperDialog from './dialog/selectTestPaperDialog';
import examinerDialog from './dialog/examinerDialog';
export default {
    name: 'examEdit',
    components: {
        selectTestPaperDialog,
        examinerDialog
    },
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            examId: '', // 考试id
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '考试管理',
                    path: '/assess/resourceMgt/testMgt',
                    routeName: 'assessResourceMgtTestMgt'
                }, {
                    name: this.$route.params.type === 'add' ? '新增考试' : '编辑考试',
                    path: '/assess/resourceMgt/examEdit',
                    routeName: 'assessResourceMgtExamEdit',
                    disabled: true
                }
            ], // 面包屑导航集合
            ruleForm: {
                name: '',
                beginTime: '',
                endTime: '',
                answerTime: null,
                testPaperName: '',
                testPaperId: '',
                users: ''
            }, // 表单对象
            rules: this.validationRules(), // 表单验证规则
            examiners: 0, // 考试人员
            examinerDialogFlg: false, // 指定人员对话框是否显示
            userAllIds: [], // 所有用户id集合
            checkedUserIds: [], // 指定人员选中用户id集合
            checkedUsers: [], // 指定人员选中的用户对象集合
            currentEditExamName: '' // 修改时当前编辑页面的考试名称
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getAllUser({});
            });
        } else { // 修改
            next(vm => {
                vm.examId = to.query.id;
                vm.getAllUser({});
                vm.getData(to.query.id);
            });
        }
    },
    created () {
        // this.getAllUser({});
    },
    methods: {
        /**
         * 获取考试信息
         * @param id 考试id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getExam(params);
            if (this.pageType === 'edit') {
                this.currentEditExamName = this.ruleForm.name;
                if (this.userAllIds.length == this.ruleForm.users.length) {
                    this.examiners = 0;
                    this.examinerDialogFlg = true;
                } else {
                    this.examiners = 1;
                    this.examinerDialogFlg = false;
                    this.checkedUserIds = this.ruleForm.users;
                    this.$api.getAllUser({ ids: this.checkedUserIds }).then(res => {
                        this.checkedUsers = res;
                    });
                }
            }
        },
        /**
         * 获取所有用户
         */
        getAllUser (param) {
            this.$api.getAllUser(param).then(res => {
                for (let i = 0; i < res.length; i++) {
                    this.userAllIds.push(res[i].id);
                }
            });
        },
        /**
         * 对话框显示
         */
        handlerDialogShow (dialogName) {
            this.$refs[dialogName].handleShow();
        },
        /**
         * 考试人员选中变化时触发
         * @param label 选中的 Radio label 值
         */
        handleExaminersChange (label) {
            if (label) {
                this.handlerDialogShow('examinerDialog');
            }
        },
        /**
         * 获取选中试卷
         * @param val 选中的试卷对象
         */
        getSelectedTestPaper (val) {
            this.ruleForm.testPaperName = val.name;
            this.ruleForm.testPaperId = val.id;
        },
        /**
         * 获取选中考试人员
         * @param val 选中的考试人员id集合
         */
        getSelectedExaminer (val) {
            this.checkedUserIds = val;
        },
        /**
         * 提交表单
         */
        submitForm () {
            if (this.examiners) {
                this.ruleForm.users = this.checkedUserIds;
            } else {
                this.ruleForm.users = this.userAllIds;
            }
            if (this.examiners == '1' || (Array.isArray(this.ruleForm.users) && this.ruleForm.users.length <= 0)) {
                this.$message.warning('请选择指定人员！');
                return;
            }
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveExam(params).then(res => {
                            this.$message.success('新增考试成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyExam(params).then(res => {
                            this.$message.success('修改考试成功!');
                        });
                    }
                    this.goBack();
                }
            });
        },
        /**
         * 取消（返回考试管理列表）
         */
        goBack () {
            this.$router.push({ path: '/assess/resourceMgt/testMgt' });
        },
        /**
         * 表单验证规范
         * @returns
         */
        validationRules () {
            const checkSubjectOnly = (rule, value, callback) => {
                // 修改考试时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditExamName) {
                    callback();
                } else {
                    this.$api.checkExamName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该考试名已经存在，请重新输入'));
                        }
                    });
                }
            };
            const checkPositiveInt = (rule, value, callback) => {
                if (Number.isInteger(Number(value)) && Number(value) > 0) {
                    callback();
                } else {
                    callback(new Error('请输入正整数'));
                }
            };
            return {
                name: [
                    { required: true, message: '请输入考试名称', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度在50个字符以内', trigger: 'blur' },
                    { validator: checkSubjectOnly, trigger: 'blur' }
                ],
                beginTime: [
                    { required: true, message: '请选择考试开始时间', trigger: 'change' }
                ],
                endTime: [
                    { required: true, message: '请选择考试结束时间', trigger: 'change' }
                ],
                answerTime: [
                    { required: true, message: '请输入答题时间', trigger: 'blur' },
                    { validator: checkPositiveInt, trigger: 'blur' }
                ],
                testPaperId: [
                    { required: true, message: '请选择考试试卷', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .examEdit {
    }
</style>
