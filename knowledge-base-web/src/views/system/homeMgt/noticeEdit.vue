<!--
@file：首页管理-通知公告编辑
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="noticeEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="公告标题：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入公告标题"></el-input>
                </el-form-item>
                <el-form-item label="公告内容：" prop="introduction">
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
    name: 'noticeEdit',
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
                    name: '通知公告',
                    path: '/system/homeMgt/notice',
                    routeName: 'notice'
                }, {
                    name: this.$route.query.type === 'add' ? '新增公告' : '编辑公告',
                    path: '/system/homeMgt/noticeEdit',
                    routeName: 'noticeEdit',
                    disabled: true
                }
            ], // 面包屑导航集合
            ruleForm: {
                name: '',
                introduction: ''
            },
            noticeId: '', // 当前编辑公告id
            currentEditNoticeName: '', // 修改时当前编辑页面的公告标题名
            rules: this.validationRules() // 表单验证规则
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.query.type === 'add') { // 新增
            next(vm => {
            });
        } else { // 修改
            next(vm => {
                vm.noticeId = to.query.id;
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取公告信息
         * @param id 公告id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getNotice(params);
            if (this.pageType === 'edit') {
                this.currentEditNoticeName = this.ruleForm.name;
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
                        await this.$api.saveNotice(params).then(res => {
                         this.$message.success('新增公告成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyNotice(params).then(res => {
                         this.$message.success('修改公告成功!');
                        });
                    }
                    this.goBack();
                }
            });
        },
        /**
         * 取消（返回公告列表）
         */
        goBack () {
            this.$router.push({ name: 'notice' });
        },
        /**
         * 表单验证
         */
        validationRules () {
            const checkNoticeOnly = (rule, value, callback) => {
                // 修改公告时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditNoticeName) {
                    callback();
                } else {
                    this.$api.checkNoticeName({ name: value }).then(res => {
                       if (!res) {
                           callback();
                       } else {
                          callback(new Error('该公告标题已经存在，请重新输入'));
                       }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入公告标题', trigger: 'blur' },
                    { required: true, validator: checkNoticeOnly, trigger: 'blur' },
                    { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                ],
                introduction: [
                    { required: true, message: '请填写公告内容', trigger: 'blur' },
                    { min: 1, max: 10000, message: '长度在 1 到 10000 个字符', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .noticeEdit {
    }
</style>
