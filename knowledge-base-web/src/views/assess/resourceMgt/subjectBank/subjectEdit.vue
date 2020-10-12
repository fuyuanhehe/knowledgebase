<!--
@file：知识测评-题目新增编辑
@createTime：2020-04-08
@updateTime：2020-04-08
@author：Claire
-->
<template>
    <div class="subjectEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form class="form-box" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item label="题目：" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="题目类型：" prop="type">
                    <el-select v-model="ruleForm.type" placeholder="请选择题目类型">
                        <el-option label="单选" :value="0"></el-option>
                        <el-option label="多选" :value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="题目难度：" prop="difficulty">
                    <el-select v-model="ruleForm.difficulty" placeholder="请选择题目难度">
                        <el-option :label="item.name" :value="item.coder" v-for="item in difficultyList" :key="item.coder"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="分值：" prop="score">
                    <el-input v-model="ruleForm.score"></el-input>
                </el-form-item>
                <el-form-item label="选项设置" prop="answers">
                    <span class="c_red">（选中后表示为正确答案）</span>
                    <div class="subject-option-box">
                        <div class="subject-option-item mb10">
                            <template  v-if="!ruleForm.type">
                                <el-radio-group class="display_b mb10"
                                                v-model="ruleForm.answers"
                                                v-for="(option, index) in subjectOptions"
                                                :key="option.key">
                                    <el-radio :label="option.key"></el-radio>
                                    <el-input v-model="option.value" class="subject-option-text"></el-input>
                                    <span class="del-btn cursor_p c_6 ml20" @click="deleteOption(option.key)" v-if="index != 0">
                                        <i class="el-icon-remove-outline mr10"></i>删除选项
                                    </span>
                                </el-radio-group>
                            </template>
                            <template v-else>
                                <el-checkbox-group class="display_b mb10"
                                                   v-model="ruleForm.answers"
                                                   v-for="(option, index) in subjectOptions"
                                                   :key="option.key">
                                    <el-checkbox :label="option.key" name="answer"></el-checkbox>
                                    <el-input v-model="option.value" class="ml10"></el-input>
                                    <span class="del-btn cursor_p c_6 ml20" @click="deleteOption(option.key)" v-if="index != 0">
                                        <i class="el-icon-remove-outline mr10"></i>删除选项
                                    </span>
                                </el-checkbox-group>
                            </template>
                        </div>
                        <div class="c_6 display_ib cursor_p" @click="addOptions">
                            <i class="el-icon-circle-plus-outline mr10"></i>新增选项
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="解 析：" prop="analysis">
                    <el-input type="textarea" v-model="ruleForm.analysis" placeholder="100字以内"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm()">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'subjectEdit',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '题库',
                    path: '/assess/resourceMgt/itemBank',
                    routeName: 'assessResourceMgtItemBank'
                }, {
                    name: this.$route.params.type === 'add' ? '新增题目' : '编辑题目',
                    path: '/assess/resourceMgt/itemBank/subjectEdit',
                    routeName: 'assessResourceMgtSubjectEdit',
                    disabled: true
                }
            ], // 面包屑导航集合
            difficultyList: [], // 题目难度下拉列表
            ruleForm: {
                name: '',
                type: 0,
                difficulty: '',
                score: null,
                options: '',
                answers: null,
                analysis: ''
            }, // 表单对象
            rules: this.validationRules(), // 表单验证规则
            subjectOptions: [
                {
                    key: 'A',
                    value: ''
                }
            ], // 题目选项对象集合
            currentEditSubjectName: '', // 修改时当前编辑页面的题目名
            subjectId: '' // 修改时当前题目id
        };
    },
    watch: {
        'ruleForm.type': {
            handler (val) {
                if (this.pageType == 'add') {
                    if (!val) {
                        this.ruleForm.answers = '';
                    } else {
                        this.ruleForm.answers = [];
                    }
                }
            },
            immediate: true,
            deep: true
        }
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getDifficultyList();
            });
        } else { // 修改
            next(vm => {
                vm.subjectId = to.query.id;
                vm.getDifficultyList();
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取题目信息
         * @param id 题目id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getSubject(params);
            if (this.pageType === 'edit') {
                this.currentEditSubjectName = this.ruleForm.name;
                this.subjectOptions = JSON.parse(this.ruleForm.options);
                if (!this.ruleForm.type) {
                    const answers = JSON.parse(this.ruleForm.answers);
                    this.ruleForm.answers = answers.join('');
                } else {
                    this.ruleForm.answers = JSON.parse(this.ruleForm.answers);
                }
            }
        },
        /**
         *获取题目难度下拉选项（字典查询）
         */
        async getDifficultyList () {
            const params = { groupCoder: 'subject_type' };
            this.difficultyList = await this.$api.getAllDict(params);
        },
        /**
         * 删除选项
         */
        deleteOption (name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                const options = this.subjectOptions;
                for (let i = 0; i < options.length; i++) {
                    if (name === options[i].name) {
                        this.subjectOptions.splice(i, 1);
                    }
                }
                this.$message.success('删除成功');
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 添加选项
         */
        addOptions () {
            if (this.subjectOptions.length >= 7) {
                this.$message.warning('最多添加7个选项！');
                return;
            }
            const lastOptionName = this.subjectOptions[this.subjectOptions.length - 1].key;
            let name = '';
            switch (lastOptionName) {
            case 'A':
                name = 'B';
                break;
            case 'B':
                name = 'C';
                break;
            case 'C':
                name = 'D';
                break;
            case 'D':
                name = 'E';
                break;
            case 'E':
                name = 'F';
                break;
            case 'F':
                name = 'G';
                break;
            }
            this.subjectOptions.push({
                key: name,
                value: ''
            });
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    this.ruleForm.options = JSON.stringify(this.subjectOptions);
                    if (!this.ruleForm.type) {
                        const answers = this.ruleForm.answers.split('');
                        this.ruleForm.answers = JSON.stringify(answers);
                    } else {
                        this.ruleForm.answers = JSON.stringify(this.ruleForm.answers);
                    }
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveSubject(params).then(res => {
                            this.$message.success('新增题目成功!');
                        });
                    } else { // 修改
                        await this.$api.modifySubject(params).then(res => {
                            this.$message.success('修改题目成功!');
                        });
                    }
                    this.$router.push({ path: '/assess/resourceMgt/itemBank' });
                }
            });
        },
        /**
         * 取消（返回题库列表）
         */
        goBack () {
            this.$router.push({ path: '/assess/resourceMgt/itemBank' });
        },
        /**
         * 表单验证规范
         * @returns
         */
        validationRules () {
            const checkSubjectOnly = (rule, value, callback) => {
                // 修改题目时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditSubjectName) {
                    callback();
                } else {
                    this.$api.checkSubjectName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该题目名已经存在，请重新输入'));
                        }
                    });
                }
            };
            const checkSubjectOptions = (rule, value, callback) => {
                for (let i = 0; i < this.subjectOptions.length; i++) {
                    if (!this.subjectOptions[i].value) {
                        callback(new Error('请输入选项内容'));
                    }
                }
                callback();
            };
            const checkScore = (rule, value, callback) => {
                value = Number(value);
                if (typeof value === 'number' && !isNaN(value)) {
                    if (value <= 0 || value > 100 || value % 0.5 != 0) {
                        callback(new Error('请输入1-100间的数字,且只允许小数为.5'));
                    } else {
                        callback();
                    }
                } else {
                    callback(new Error('请输入数字值'));
                }
            };
            return {
                name: [
                    { required: true, message: '请输入题目名称', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度在50个字符以内', trigger: 'blur' },
                    { validator: checkSubjectOnly, trigger: 'blur' }
                ],
                type: [
                    { required: true, message: '请选择题目类型', trigger: 'change' }
                ],
                difficulty: [
                    { required: true, message: '请选择题目难道', trigger: 'change' }
                ],
                score: [
                    { required: true, message: '请输入题目分值', trigger: 'blur' },
                    { validator: checkScore, trigger: 'blur' }
                ],
                answers: [
                    { required: true, message: '请选择答案', trigger: 'change' },
                    { required: true, validator: checkSubjectOptions, trigger: 'blur' }
                ],
                analysis: [
                    { required: true, message: '请输入题目解析', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在100个字符以内', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .subjectEdit {
        .content {
            .form-box {
                .c_red {
                    margin-left: -15px;
                }
                .subject-option-box {
                    .subject-option-item {
                        margin-left: -45px;
                        .del-btn {
                            font-size: $font-size-base;
                        }
                        .subject-option-text {
                            margin-left: -15px;
                        }
                    }
                }
            }
        }
    }
</style>
