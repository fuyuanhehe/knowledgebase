<!--
@file：首页管理-公司新闻-新闻新增/编辑
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="newsEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="新闻标题：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入新闻标题"></el-input>
                </el-form-item>
                <el-form-item label="新闻内容：" prop="introduction">
                    <ueditor v-model="ruleForm.introduction" />
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'newsEdit',
    data () {
        return {
            pageType: this.$route.query.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '首页管理',
                    path: '/system/homeMgt',
                    routeName: 'homeMgt',
                    disabled: true
                }, {
                    name: '公司新闻',
                    path: '/system/homeMgt/news',
                    routeName: 'news'
                }, {
                    name: this.$route.query.type === 'add' ? '新增新闻' : '编辑新闻',
                    path: '/system/homeMgt/newsEdit',
                    routeName: 'newsEdit',
                    disabled: true
                }
            ], // 面包屑导航集合
            ruleForm: {
                name: '',
                introduction: ''
            },
            newsId: '', // 当前编辑新闻id
            currentEditNewsName: '', // 修改时当前编辑页面的新闻标题名
            rules: this.validationRules() // 表单验证规则
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.query.type === 'add') { // 新增
            next(vm => {
            });
        } else { // 修改
            next(vm => {
                vm.newsId = to.query.id;
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取新闻信息
         * @param id 新闻id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getJournalism(params);
            if (this.pageType === 'edit') {
                this.currentEditNewsName = this.ruleForm.name;
            }
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveJournalism(params).then(res => {
                          this.$message.success('新增新闻成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyJournalism(params).then(res => {
                         this.$message.success('修改新闻成功!');
                        });
                    }
                    this.goBack();
                }
            });
        },
        /**
         * 取消（返回新闻列表）
         */
        goBack () {
            this.$router.push({ name: 'news' });
        },
        /**
         * 表单验证
         */
        validationRules () {
            const checkNewsOnly = (rule, value, callback) => {
                // 修改新闻时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditNewsName) {
                    callback();
                } else {
                    this.$api.checkJournalismName({ name: value }).then(res => {
                       if (!res) {
                           callback();
                       } else {
                           callback(new Error('该新闻标题已经存在，请重新输入'));
                       }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入新闻标题', trigger: 'blur' },
                    { required: true, validator: checkNewsOnly, trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                ],
                introduction: [
                    { required: true, message: '请填写新闻内容', trigger: 'blur' },
                    { min: 1, max: 10000, message: '长度在 1 到 10000 个字符', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .newsEdit {
    }
</style>
