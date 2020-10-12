<template>
    <div class="userEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content p40">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item label="工号：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="工号唯一"></el-input>
                </el-form-item>
                <el-form-item label="用户名：" prop="nickName">
                    <el-input v-model="ruleForm.nickName" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="性别：" prop="sex">
                    <el-select v-model="ruleForm.sex" placeholder="请选择性别">
                        <el-option label="男" :value="0"></el-option>
                        <el-option label="女" :value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="部门：" prop="deptId">
                    <el-select v-model="ruleForm.deptId" placeholder="请选择部门">
                        <el-option v-for="item in deptOptions"
                                   :key="item.id"
                                   :label="item.name"
                                   :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="角色：" prop="roleId">
                    <el-select v-model="ruleForm.roleId" placeholder="请选择角色">
                        <el-option v-for="item in roleOptions"
                                   :key="item.id"
                                   :label="item.name"
                                   :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="邮箱：" prop="mail">
                    <el-input v-model="ruleForm.mail" placeholder="请输入公司企业邮箱"></el-input>
                </el-form-item>
                <el-form-item label="联系方式：" prop="phone">
                    <el-input v-model="ruleForm.phone" placeholder="请输入您的手机号码"></el-input>
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
    name: 'userEdit',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '用户管理',
                    path: '/system/systemSetup/userMgt',
                    routeName: 'systemSetupUserMgt'
                }, {
                    name: this.$route.params.type === 'add' ? '新增用户' : '编辑用户',
                    path: '/system/systemSetup/userMgt/' + this.$route.params.type,
                    routeName: 'systemSetupUserMgtEdit'
                }
            ], // 面包屑导航集合
            deptOptions: [], // 部门下拉选项
            roleOptions: [], // 角色下拉选项
            currentEditUserNum: '', // 修改时当前编辑页面的工号名
            ruleForm: {
                name: '',
                nickName: '',
                sex: '',
                deptId: '',
                mail: '',
                phone: '',
                roleId: ''
            }, // 表单对象
            rules: this.validationRules()// 表单验证规则
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getDeptList();
                vm.getRoleList();
            });
        } else { // 修改
            next(vm => {
                vm.getDeptList();
                vm.getRoleList();
                vm.userId = to.query.id;
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取角色信息
         * @param id 角色id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getUser(params);
            if (this.pageType === 'edit') {
                this.currentEditUserNum = this.ruleForm.name;
            }
        },
        /**
         * 获取部门列表
         */
        async getDeptList () {
            this.deptOptions = await this.$api.getAllDept({});
        },
        /**
         * 获取角色列表
         */
        async getRoleList () {
            this.roleOptions = await this.$api.getAllRole({});
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveUser(params).then(res => {
                            this.$message.success('新增角色成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyUser(params).then(res => {
                            this.$message.success('修改角色成功!');
                        });
                    }
                    this.$router.push({ path: '/system/systemSetup/userMgt' });
                }
            });
        },
        /**
         * 取消（返回用户列表）
         */
        goBack () {
            this.$router.push({ path: '/system/systemSetup/userMgt' });
        },
        /**
         * 设置表单验证规则
         * @returns {{mail: *[], phone: *[], nickName: *[], roleId: {trigger: string, message: string, required: boolean}[], sex: {trigger: string, message: string, required: boolean}[], name: {trigger: string, message: string, required: boolean}[], deptId: {trigger: string, message: string, required: boolean}[]}}
         */
        validationRules () {
            const checkUserOnly = (rule, value, callback) => {
                // 修改用户时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditUserNum) {
                    callback();
                } else {
                    this.$api.checkUserName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该工号已经存在，请重新输入'));
                        }
                    });
                }
            };
            // 邮箱验证
            const checkEmail = (rule, value, callback) => {
                const reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
                if (!(reg.test(value))) return callback(new Error('请输入正确的邮箱格式'));
                callback();
            };
            // 联系电话验证
            const checkPhone = (rule, value, callback) => {
                if (!(/^1[3456789]\d{9}$/.test(value))) return callback(new Error('请填写 11 位手机号'));
                callback();
            };
            return {
                name: [
                    { required: true, message: '请输入工号', trigger: 'blur' },
                    { required: true, validator: checkUserOnly, trigger: 'blur' }
                ],
                nickName: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                deptId: [
                    { required: true, message: '请选择部门', trigger: 'change' }
                ],
                mail: [
                    { required: true, message: '请输入邮箱', trigger: 'blur' },
                    { required: true, validator: checkEmail, trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入联系方式', trigger: 'blur' },
                    { required: true, validator: checkPhone, trigger: 'blur' }
                ],
                roleId: [
                    { required: true, message: '请选择角色', trigger: 'change' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
.userEdit {
    .content {

    }
}
</style>
