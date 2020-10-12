<!--
@file：系统设置-角色编辑
@time：20200306
@author：Claire
-->
<template>
    <div class="roleEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item  label="角色名称：" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="角色描述：" prop="describe">
                    <el-input type="textarea" v-model="ruleForm.describe"></el-input>
                </el-form-item>
                <v-divider></v-divider>
                <div class="power-box">
                    <h6>操作权限</h6>
                    <div class="power-item">
                        <el-form-item label="" prop="menuIds">
                            <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                            <span class="c_9 ml25">注：勾选后，即表示用户登录后可以看到的菜单</span>
                            <div class="power-group-box">
                                <!--<div v-for="(item, index) in powerList" :key="item.id">-->
                                    <!--<el-checkbox-group v-model="ruleForm.menuIds">-->
                                        <!--<el-checkbox name="menuIds"-->
                                                     <!--:label="item.id"-->
                                                     <!--@change="((val) => { handleCheckedPowersChange(1,index) })">-->
                                            <!--{{ item.name }}-->
                                        <!--</el-checkbox>-->
                                        <!--<div class="power-group ml25"  v-for="i in item.children" :key="i.id">-->
                                            <!--<el-checkbox name="menuIds"-->
                                                         <!--:label="i.id"-->
                                                         <!--@change="((val) => { handleCheckedPowersChange(2,index) })">{{ i.name }}</el-checkbox>-->
                                            <!--<div class="power-group ml50">-->
                                                <!--<el-checkbox name="menuIds"-->
                                                             <!--v-for="x in i.children"-->
                                                             <!--:key="x.id" :label="x.id"-->
                                                             <!--@change="((val) => { handleCheckedPowersChange(3,index) })">{{ x.name }}</el-checkbox>-->
                                            <!--</div>-->
                                        <!--</div>-->
                                    <!--</el-checkbox-group>-->
                                <!--</div>-->
                                <el-tree
                                        :data="powerList"
                                        show-checkbox
                                        default-expand-all
                                        node-key="id"
                                        ref="tree"
                                        highlight-current
                                        :props="defaultProps"
                                        @check-change="treeChange">
                                </el-tree>
                            </div>
                        </el-form-item>
                    </div>
                </div>
                <el-form-item class="btn-box">
                    <el-button class="btn_bg_blue" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import VDivider from 'components/VDivider';
export default {
    name: 'roleEdit',
    components: {
        VDivider
    },
    data () {
        return {
            roleId: '', // 角色id
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '角色管理',
                    path: '/system/systemSetup/roleMgt',
                    routeName: 'systemSetupRoleMgt'
                }, {
                    name: this.$route.params.type === 'add' ? '新增角色' : '编辑角色',
                    path: '/system/systemSetup/roleMgt/' + this.$route.params.type,
                    routeName: 'systemSetupRoleMgt'
                }
            ], // 面包屑导航集合
            ruleForm: {
                name: '',
                describe: '',
                menuIds: [] // 权限组选中集合
            }, // 表单对象
            rules: this.validationRules(), // 表单验证规则
            checkAll: false, // 操作权限全选
            powerList: [], // 操作权限集合
            powerIds: [], // 操作权限所有id集合
            currentEditRoleName: '', // 修改时当前编辑页面的角色名
            defaultProps: {
                children: 'children',
                label: 'name'
            }
        };
    },
    watch: {
        'ruleForm.menuIds': {
            // 监听选中权限菜单集合，当选中的数组长度等于所有权限菜单长度时，将总全选框设置选中，反之则不选
            handler (val) {
                this.checkAll = val.length && val.length === this.powerIds.length;
                console.log('val.length:', val.length);
                console.log('powerTotalNum:', this.powerIds.length);
            },
            immediate: true,
            deep: true
        }
    },
    computed: {
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getPowerList();
            });
        } else { // 修改
            next(vm => {
                vm.getPowerList();
                vm.roleId = to.query.id;
                // vm.getData(to.query.id);
            });
        }
    },
    mounted () {
    },
    methods: {
        /**
         * 获取角色信息
         * @param id 角色id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getRole(params);
            if (this.pageType === 'edit') {
                this.currentEditRoleName = this.ruleForm.name;
                this.$refs.tree.setCheckedKeys(this.ruleForm.menuIds);
            }
        },
        /**
         * 获取权限列表
         */
        async getPowerList () {
            const params = {};
            await this.$api.getMenuTree(params).then(res => {
                this.powerList = res;
                const ids = [];
                for (let i = 0; i < this.powerList.length; i++) {
                    ids.push(this.powerList[i].id);
                    // this.powerList[i].children.map(item => {
                    //     ids.push(item.id);
                    // });
                    const secList = this.powerList[i].children;
                    for (let j = 0; j < secList.length; j++) {
                        ids.push(secList[j].id);
                        const thirdList = secList[j].children;
                        for (let k = 0; k < thirdList.length; k++) {
                            ids.push(thirdList[k].id);
                        }
                    }
                }
                this.powerIds = ids;
                // 修改角色时获取角色信息,因为要获取到总的权限菜单数量，
                // 并与当前角色选中权限对比，判断是否需要将总全选框选中，所以在此处调用
                if (this.pageType === 'edit') {
                    this.getData(this.roleId);
                }
            });
        },
        /**
         * 操作权限全选/取消
         * @param val 是否全选
         */
        handleCheckAllChange (val) {
            if (val) {
                // for (let i = 0; i < this.powerList.length; i++) {
                //     this.ruleForm.menuIds.push(this.powerList[i].id);
                //     this.powerList[i].children.map(item => {
                //         this.ruleForm.menuIds.push(item.id);
                //     });
                // }
                // const ids = [];
                // for (let i = 0; i < this.powerList.length; i++) {
                //     ids.push(this.powerList[i].id);
                //     // this.powerList[i].children.map(item => {
                //     //     ids.push(item.id);
                //     // });
                //     const secList = this.powerList[i].children;
                //     for (let j = 0; j < secList.length; j++) {
                //         ids.push(secList[j].id);
                //         const thirdList = secList[j].children;
                //         for (let k = 0; k < thirdList.length; k++) {
                //             ids.push(thirdList[k].id);
                //         }
                //     }
                // }
                // this.$refs.tree.setCheckedKeys(ids);
                this.$refs.tree.setCheckedKeys(this.powerIds);
            } else {
                // this.ruleForm.menuIds = [];
                this.$refs.tree.setCheckedKeys([]);
            }
        },
        /**
         * 操作权限树节点选中状态发生变化时的回调
         */
        treeChange () {
            const treeCheckedId = this.$refs.tree.getCheckedKeys();
            this.checkAll = treeCheckedId.length && treeCheckedId.length === this.powerIds.length;
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.ruleForm.menuIds = this.$refs.tree.getCheckedKeys();
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveRole(params).then(res => {
                            this.$message.success('新增角色成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyRole(params).then(res => {
                            this.$message.success('修改角色成功!');
                        });
                    }
                    this.$router.push({ path: '/system/systemSetup/roleMgt' });
                }
            });
        },
        /**
         * 取消（返回角色列表）
         */
        goBack () {
            this.$router.push({ path: '/system/systemSetup/roleMgt' });
        },
        validationRules () {
            const checkRoleOnly = (rule, value, callback) => {
                // 修改角色时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditRoleName) {
                    callback();
                } else {
                    this.$api.checkRoleName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该角色名已经存在，请重新输入'));
                        }
                    });
                }
            };
            const checkMenu = (rule, value, callback) => {
                if (this.ruleForm.menuIds.length > 0) {
                    callback();
                } else {
                    callback(new Error('请至少选择一个操作权限'));
                }
            };
            return {
                name: [
                    { required: true, message: '请输入角色名称', trigger: 'blur' },
                    { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
                    { required: true, validator: checkRoleOnly, trigger: 'blur' }
                ],
                describe: [
                    { required: true, message: '请输入角色描述', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                ],
                menuIds: [
                    { type: 'array', required: true, message: '请至少选择一个操作权限', trigger: 'change' },
                    { type: 'array', validator: checkMenu, trigger: 'change' }
                ]
            };
        }
        /**
         * 权限选择框变化时触发
         * @param type 权限组选择框层级 1：一级菜单（全选/取消）   2：二级菜单（选中/取消）
         * @param i 当前权限组索引
         */
        // handleCheckedPowersChange (type, i = 0) {
        //     if (type === 1) {
        //         if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) > -1) {
        //             const secList = this.powerList[i].children;
        //             for (let j = 0; j < secList.length; j++) {
        //                 if (this.ruleForm.menuIds.findIndex((n) => n === secList[j].id) < 0) {
        //                     this.ruleForm.menuIds.push(secList[j].id);
        //                 }
        //                 const thirdList = secList[j].children;
        //                 for (let k = 0; k < thirdList.length; k++) {
        //                     if (this.ruleForm.menuIds.findIndex((n) => n === thirdList[k].id) < 0) {
        //                         this.ruleForm.menuIds.push(thirdList[k].id);
        //                     }
        //                 }
        //             }
        //         } else {
        //             this.powerList[i].children.map(item => {
        //                 if (this.ruleForm.menuIds.findIndex((n) => n === item.id) > -1) {
        //                     this.ruleForm.menuIds.splice(this.ruleForm.menuIds.findIndex((n) => n === item.id), 1);
        //                 }
        //             });
        //             const secList = this.powerList[i].children;
        //             for (let j = 0; j < secList.length; j++) {
        //                 if (this.ruleForm.menuIds.findIndex((n) => n === secList[j].id) > -1) {
        //                     this.ruleForm.menuIds.splice(this.ruleForm.menuIds.findIndex((n) => n === secList[j].id), 1);
        //                 }
        //                 const thirdList = secList[j].children;
        //                 for (let k = 0; k < thirdList.length; k++) {
        //                     if (this.ruleForm.menuIds.findIndex((n) => n === thirdList[k].id) > -1) {
        //                         this.ruleForm.menuIds.push(this.ruleForm.menuIds.findIndex((n) => n === thirdList[k].id), 1);
        //                     }
        //                 }
        //             }
        //         }
        //     } else {
        //         let index = 0;
        //         this.powerList[i].children.map(item => {
        //             if (this.ruleForm.menuIds.indexOf(item.id) > -1) {
        //                 index += 1;
        //             }
        //         });
        //         if (index > 0) {
        //             if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) < 0) {
        //                 this.ruleForm.menuIds.push(this.powerList[i].id);
        //             }
        //         } else {
        //             if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) >= 0) {
        //                 this.ruleForm.menuIds.splice(this.ruleForm.menuIds.indexOf(this.powerList[i].id), 1);
        //             }
        //         }
        //     }
        // if (type === 1) {
        //     if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) > -1) {
        //         this.powerList[i].children.map(item => {
        //             if (this.ruleForm.menuIds.findIndex((n) => n === item.id) < 0) {
        //                 this.ruleForm.menuIds.push(item.id);
        //             }
        //         });
        //     } else {
        //         this.powerList[i].children.map(item => {
        //             if (this.ruleForm.menuIds.findIndex((n) => n === item.id) > -1) {
        //                 this.ruleForm.menuIds.splice(this.ruleForm.menuIds.findIndex((n) => n === item.id), 1);
        //             }
        //         });
        //     }
        // } else {
        //     let index = 0;
        //     this.powerList[i].children.map(item => {
        //         if (this.ruleForm.menuIds.indexOf(item.id) > -1) {
        //             index += 1;
        //         }
        //     });
        //     if (index > 0) {
        //         if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) < 0) {
        //             this.ruleForm.menuIds.push(this.powerList[i].id);
        //         }
        //     } else {
        //         if (this.ruleForm.menuIds.indexOf(this.powerList[i].id) >= 0) {
        //             this.ruleForm.menuIds.splice(this.ruleForm.menuIds.indexOf(this.powerList[i].id), 1);
        //         }
        //     }
        // }
        // },
        /**
         * 权限组一级菜单选择框变化时触发
         * @param val
         * @param items
         * @param i
         */
        /* handleGroupCheckedChange (val, items, i) {
            if (val) {
                for (let i = 0; i < item.children.length; i++) {
                    this.powerGroupCheckedList.push(item.children[i].id);
                }
            } else {
                Array.prototype.remove = function (val) {
                    const index = this.indexOf(val);
                    if (index > -1) {
                        this.splice(index, 1);
                    }
                };
                for (let i = 0; i < item.children.length; i++) {
                    this.powerGroupCheckedList.remove(item.children[i].id);
                }
            }
            console.log('ruleForm.menuIds:', this.ruleForm.menuIds);
        }, */
        /**
         * 权限组二级菜单选择框变化时触发
         * @param val
         * @param items
         * @param i
         */
        /* handleItemChecked (val, items, i = 0) {
            if (val) {
                this.powerGroupCheckedList.push(item.id);
            } else {
                for (let i = 0; i < this.powerGroupCheckedList.length; i++) {
                    if (this.powerGroupCheckedList[i] === item.id) {
                        this.powerGroupCheckedList.splice(i, 1);
                    }
                }
            }
            console.log('powerGroupCheckedList111:', this.powerGroupCheckedList);
        }, */
    }
};
</script>

<style scoped lang="scss">
.roleEdit {
    .content {
        padding: 40px;
        .power-box {
            h6 {
                display: inline-block;
                width: 96px;
                height: 38px;
                line-height: 38px;
                text-align: left;
                margin-right: 30px;
                cursor: pointer;
                font-size: $font-size-middle;
                font-weight: 400;
                &::before {
                    content: '*';
                    color: #F56C6C;
                    margin-right: 4px;
                }
            }
            .power-item{
                /deep/.el-form-item {
                    margin-top: 10px;
                    .el-form-item__label {
                        text-align: left;
                        width: 80px !important;
                    }
                    .el-form-item__content {
                        margin-left: 0 !important;
                    }
                }
                .power-group-box {
                    width:730px;
                    height:390px;
                    padding: 10px 20px;
                    margin-top: 10px;
                    overflow: auto;
                    background:rgba(248,248,248,1);
                    border:1px solid rgba(216,216,216,1);
                }
            }
        }
        .btn-box {
            /deep/ .el-form-item__content {
                    margin-left: 0 !important;
            }
        }
    }
}
</style>
