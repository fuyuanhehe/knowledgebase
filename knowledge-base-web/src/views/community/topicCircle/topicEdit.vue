<!--
@file：知识社区-话题编辑
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="topicEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="圈子类别：" prop="circleId">
                    <el-select v-model="ruleForm.circleId" placeholder="请选择圈子类别" :disabled="isCircleDisabled">
                        <el-option v-for="item in circleOptions"
                                   :key="item.id"
                                   :label="item.name"
                                   :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="话题名称：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入话题名称"></el-input>
                </el-form-item>
                <el-form-item label="题主：" prop="createBy">
                    <el-input v-model="$store.state.user.nickName" disabled></el-input>
                </el-form-item>
                <el-form-item label="话题内容：" prop="content">
                    <ueditor v-model="ruleForm.content" />
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
    name: 'topicEdit',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            circleId: '',
            breadcrumbNav: [
                {
                    name: '知识社区',
                    path: '/community',
                    routeName: 'community'
                }
            ], // 面包屑导航集合
            lastPath: '', // 返回上一页路由地址
            circleOptions: [], // 圈子类别下拉选项
            isCircleDisabled: false, // 圈子类别下拉是否可选（是否禁用）
            ruleForm: {
                circleId: '',
                name: '',
                content: ''
            },
            rules: this.validationRules() // 表单验证规则
        };
    },
    watch: {
        '$route.params.pageFrom': {
            handler (val) {
                const lastNav = {
                    name: this.pageType === 'add' ? '创建话题' : '编辑话题',
                    path: '/community/topicEdit',
                    routeName: 'communityTopicCircleEdit',
                    disabled: true
                };
                if (val === 'manage') {
                    this.breadcrumbNav[1] = {
                        name: '话题管理',
                        path: '/community/communityMgt/topicMgt',
                        routeName: 'communityTopicMgt'
                    };
                    this.breadcrumbNav[2] = lastNav;
                    this.lastPath = this.breadcrumbNav[1].path;
                } else if (val === 'home') {
                    this.breadcrumbNav[1] = lastNav;
                    this.lastPath = this.breadcrumbNav[0].path;
                } else {
                    this.isCircleDisabled = true;
                    this.breadcrumbNav[1] = {
                        name: this.$route.query.circleName,
                        path: '/community/topicCircle/' + this.$route.params.circleId,
                        routeName: 'communityTopicCircleSort'
                    };
                    this.breadcrumbNav[2] = lastNav;
                    this.lastPath = this.breadcrumbNav[1].path;
                }
            },
            immediate: true,
            deep: true
        }
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getAllCircle();
            });
        } else { // 修改
            next(vm => {
                vm.circleId = to.params.circleId;
                vm.getAllCircle();
                vm.getData(to.params.circleId);
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
            this.ruleForm = await this.$api.getTopic(params);
        },
        /**
         * 获取圈子类别下拉选项
         */
        async getAllCircle () {
            this.circleOptions = await this.$api.getAllCircle({});
            if (this.$route.params.pageFrom === 'circle') {
                this.ruleForm.circleId = this.$route.params.circleId;
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
                        await this.$api.saveTopic(params).then(res => {
                            this.$message.success('创建话题成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyTopic(params).then(res => {
                            this.$message.success('修改话题成功!');
                        });
                    }
                    this.$router.push({ path: this.lastPath });
                }
            });
        },
        /**
         * 取消（返回精品文档列表）
         */
        goBack () {
            this.$router.push({ path: this.lastPath });
        },
        /**
         * 表单验证规范
         * @returns
         */
        validationRules () {
            const checkTopicOnly = (rule, value, callback) => {
                // 修改话题时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditRoleName) {
                    callback();
                } else {
                    this.$api.checkTopicName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该话题名已经存在，请重新输入'));
                        }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入话题名称', trigger: 'blur' },
                    { required: true, validator: checkTopicOnly, trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在20个字符以内', trigger: 'blur' }
                ],
                circleId: [
                    { required: true, message: '请选择圈子类别', trigger: 'change' }
                ],
                content: [
                    { required: true, message: '请填写话题内容', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .topicEdit {
    }
</style>
