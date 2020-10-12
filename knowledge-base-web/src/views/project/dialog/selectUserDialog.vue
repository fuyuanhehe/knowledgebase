<!--
@file：项目协作-用户选择弹框
@createTime：2020-04-23
@updateTime：2020-04-23
@author：Claire
-->
<template>
    <div class="selectUserDialog">
        <el-dialog
                class="dialog-wrapper"
                :title="dialogTitle"
                center
                :visible.sync="dialogVisible"
                width="500px">
            <div class="dialog-main">
                <div class="dialog-top clearfix">
                    <div class="dialog-top-left w40p fl">
                        <div class="dialog-top-left-title">
                            <v-icon name="iconziyuan"></v-icon>
                            <span>组织架构</span>
                        </div>
                        <div class="dialog-top-left-tree">
                            <el-tree :data="deptData"
                                     :props="defaultProps"
                                     default-expand-all
                                     highlight-current
                                     :current-node-key="deptId"
                                     check-on-click-node
                                     node-key="id"
                                     @node-click="handleNodeClick">
                            </el-tree>
                        </div>
                    </div>
                    <span class="divider-line"></span>
                    <div class="dialog-top-right w60p pl20 fr" v-for="(item, index) in userList">
                        <template v-if="selectType == 'multi'">
                            <el-checkbox class="checkbox-all"
                                         v-model="userCheckAll"
                                         @change="((val) => { handleCheckAllChange(val,item) })">全选</el-checkbox>
                            <el-checkbox-group v-model="checkedUserIds">
                                <el-checkbox class="display_b"
                                             v-for="user in item.userArr"
                                             :label="user.id"
                                             :key="user.id"
                                             @change="((val) => { handleCheckUserChange(val, user, index) })">
                                    <img src="~assets/images/user.png" alt="">
                                    <span>{{ user.nickName }}</span>
                                </el-checkbox>
                            </el-checkbox-group>
                        </template>
                        <template v-else>
                            <el-radio-group v-model="checkedUserIds">
                                <el-radio class="display_b"
                                          v-for="user in item.userArr"
                                          :label="user.id"
                                          :key="user.id"
                                          @change="((val) => { handleCheckUserRadioChange(val, user, index) })">
                                    <img src="~assets/images/user.png" alt="">
                                    <span>{{ user.nickName }}</span>
                                </el-radio>
                            </el-radio-group>
                        </template>
                    </div>
                </div>
                <el-divider></el-divider>
                <div class="dialog-bottom" v-if="selectType == 'multi'">
                    <span class="display_ib mb20">已选：<span class="c_blue">{{ checkedUsers.length }}人</span></span>
                    <ul class="checkedUser-list clearfix">
                        <li class="checkedUser-item fl" v-for="user in checkedUsers">
                            <div class="checkedUser-item-top">
                                <img src="~assets/images/user.png" alt="">
                                <i class="el-icon-error" @click="cancelCheckedUser(user.id)"></i>
                            </div>
                            <span>{{ user.nickName }}</span>
                        </li>
                    </ul>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitCheckedUsers">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
const isContained = (a, b) => {
    if (!(a instanceof Array) || !(b instanceof Array)) return false;
    if (a.length < b.length) return false;
    for (let i = 0, len = b.length; i < len; i++) {
        if (a.indexOf(b[i]) == -1) return false;
    }
    return true;
};
export default {
    name: 'selectUserDialog',
    // props: ['selectedUsers', 'selectedUserIds', 'selectType', 'dialogTitle'],
    props: {
        selectedUsers: {},
        selectedUserIds: {},
        selectType: {
            type: String,
            default: 'multi'
        },
        dialogTitle: {
            type: String,
            default: ''
        }
    },
    data () {
        return {
            dialogVisible: false, // 学习人员：指定人员对话框是否可见
            deptData: [], // 组织结构树数据
            defaultProps: {
                children: 'children',
                label: 'name'
            }, // 组织结构树配置配置选项
            userCheckAll: false, // 指定人员用户是否全选
            checkedUsers: this.selectedUsers, // 选中人员对象集合
            checkedUserIds: this.selectedUserIds, // 选中人员id集合
            userList: [], // 用户列表
            userAllIds: [], // 所有用户id集合
            deptId: '' // 当前部门id
        };
    },
    watch: {
        userList: {
            handler (val) {
                const userIdArr = [];
                if (Array.isArray(val) && val.length > 0) {
                    const userArr = val[0].userArr;
                    for (let i = 0; i < userArr.length; i++) {
                        userIdArr.push(userArr[i].id);
                    }
                }
                this.userCheckAll = isContained(this.checkedUserIds, userIdArr);
            },
            immediate: true,
            deep: true
        },
        checkedUserIds: {
            handler (val) {
                const userIdArr = [];
                const userList = this.userList;
                if (Array.isArray(userList) && userList.length > 0) {
                    const userArr = userList[0].userArr;
                    for (let i = 0; i < userArr.length; i++) {
                        userIdArr.push(userArr[i].id);
                    }
                }
                this.userCheckAll = isContained(val, userIdArr);
            },
            immediate: true,
            deep: true
        }
    },
    created () {
        this.getDeptTree();
        this.getAllUser({});
    },
    methods: {
        /**
         * 对话框显示
         */
        handleShow () {
            this.dialogVisible = true;
        },
        /**
         * @description 取消
         */
        handleCancel () {
            this.dialogVisible = false;
        },
        /**
         * 获取组织结构树数据
         */
        async getDeptTree () {
            this.deptData = await this.$api.getDeptTree({});
            this.deptId = this.deptData[0].id;
        },
        /**
         * 获取所有用户
         */
        getAllUser (param) {
            this.$api.getAllUser(param).then(res => {
                this.userList = [{
                    deptId: this.deptId,
                    userArr: res
                }];
                for (let i = 0; i < res.length; i++) {
                    this.userAllIds.push(res[i].id);
                }
            });
        },
        /**
         * 组织结构树节点被点击时的回调
         * @param data 该节点所对应的对象
         */
        handleNodeClick (data) {
            const param = {
                deptId: data.id
            };
            this.deptId = data.id;
            this.getAllUser(param);
        },
        /**
         * 用户全选框变化时触发
         * @val 更新后的值（是否选中）
         */
        handleCheckAllChange (val, item) {
            if (val) {
                for (var i = 0; i < item.userArr.length; i++) {
                    let flag = true, flag1 = true;
                    if (Array.isArray(this.checkedUserIds) && this.checkedUserIds.length > 0) {
                        for (var k = 0; k < this.checkedUserIds.length; k++) {
                            if (item.userArr[i].id === this.checkedUserIds[k]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (Array.isArray(this.checkedUsers) && this.checkedUsers.length > 0) {
                        for (var j = 0; j < this.checkedUsers.length; j++) {
                            if (item.userArr[i] === this.checkedUsers[j]) {
                                flag1 = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        this.checkedUserIds.push(item.userArr[i].id);
                    }
                    if (flag1) {
                        this.checkedUsers.push(item.userArr[i]);
                    }
                }
            } else {
                Array.prototype.remove = function (val) {
                    const index = this.indexOf(val);
                    if (index > -1) {
                        this.splice(index, 1);
                    }
                };
                for (var x = 0; x < item.userArr.length; x++) {
                    this.checkedUserIds.remove(item.userArr[x].id);
                    for (var y = 0; y < this.checkedUsers.length; y++) {
                        if (item.userArr[x].id == this.checkedUsers[y].id) {
                            this.checkedUsers.splice(y, 1);
                        }
                    }
                }
            }
        },
        /**
         * 用户多选选择框变化时触发
         * @val 更新后的值（是否选中）
         * @item 当前用户对象
         */
        handleCheckUserChange (val, item) {
            if (val) {
                this.checkedUsers.push(item);
            } else {
                for (let i = 0; i < this.checkedUsers.length; i++) {
                    if (this.checkedUsers[i].id === item.id) {
                        this.checkedUsers.splice(i, 1);
                    }
                }
            }
        },
        /**
         * 用户单选选择框变化时触发
         * @val 更新后的值（是否选中）
         * @item 当前用户对象
         */
        handleCheckUserRadioChange (val, item) {
            this.checkedUsers = item;
        },
        /**
         * 取消用户选择
         */
        cancelCheckedUser (id) {
            this.$confirm('确定删除?', '删除', {
                center: true
            }).then(() => {
                for (let i = 0; i < this.checkedUserIds.length; i++) {
                    if (id == this.checkedUserIds[i]) {
                        this.checkedUserIds.splice(i, 1);
                    }
                }
                for (let j = 0; j < this.checkedUsers.length; j++) {
                    if (id == this.checkedUsers[j].id) {
                        this.checkedUsers.splice(j, 1);
                    }
                }
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 选中用户提交
         */
        submitCheckedUsers () {
            this.$emit('selected', this.checkedUsers);
            this.handleCancel();
        }
    }
};
</script>

<style scoped lang="scss">
    .selectUserDialog {
        .dialog-wrapper {
            /deep/.el-dialog__body {
                /*max-height: 400px;*/
                /*overflow: auto;*/
                .dialog-main {
                    .dialog-top {
                        position: relative;
                        &-left {
                            &-title {
                                span {
                                    height: 34px;
                                    line-height: 34px;
                                    color: $color-text-primary;
                                }
                            }
                            &-tree {
                            }
                        }
                        .divider-line {
                            position: absolute;
                            min-height: 100%;
                            height: 100%;
                            border-right: 1px dashed #ddd;
                        }
                        &-right {
                            max-height: 300px;
                            overflow: auto;
                            .checkbox-all {
                                margin: 0 !important;
                                color: $color-text-primary;
                            }
                            .el-checkbox-group {
                                .el-checkbox {
                                    height: 34px;
                                    line-height: 34px;
                                    margin: 10px 0;
                                    img {
                                        vertical-align: middle;
                                    }
                                    span {
                                        vertical-align: middle;
                                    }
                                }
                            }
                        }
                    }
                    .dialog-bottom {
                        .checkedUser-list {
                            .checkedUser-item {
                                margin: 0 40px 10px 0;
                                &-top {
                                    position: relative;
                                    .el-icon-error {
                                        position: absolute;
                                        right: -2px;
                                        top: 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
</style>
