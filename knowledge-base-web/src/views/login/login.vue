<!--
@file：登录
@createTime：2020-04-22
@updateTime：2020-04-22
@author：Claire
-->
<template>
    <div class="login">
        <div class="login-content ">
            <img class="mb20" src="~assets/images/login-logo.png" alt="">
            <div class="login-box">
                <img class="" src="~assets/images/login-picture.png" alt="">
                <div class="login-form-box">
                    <h1 class="mb30">用户登录</h1>
                    <el-form class="login-form" :model="ruleForm" :rules="rules" ref="ruleForm">
                        <el-form-item  class="login-form-item" label="" prop="name">
                            <img src="~assets/images/login-user-icon.png" alt="">
                            <el-input v-model="ruleForm.name" placeholder="用户名" @change="loadRecordPassword"></el-input>
                        </el-form-item>
                        <el-form-item class="login-form-item login-form-noMargin" label="" prop="password">
                            <img src="~assets/images/login-psd-icon.png" alt="">
                            <el-input type="password" v-model="ruleForm.password" placeholder="密码"></el-input>
                        </el-form-item>
                        <el-form-item class="login-form-noMargin">
                            <el-checkbox v-model="isRememberPsd" @change="recordedUser">记住密码</el-checkbox>
                        </el-form-item>
                        <el-form-item class="login-form-noMargin">
                            <el-button class="btn" @click="submitForm('ruleForm')">登录</el-button>
                        </el-form-item>
                        <el-form-item class="login-form-noMargin text_r">
                            <el-button type="text" class="btn-findPsd" @click="findPsd">找回密码</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'login',
    data () {
        return {
            ruleForm: {
                name: '',
                password: ''
            },
            rules: {
                name: [
                    { required: true, message: '用户名不能为空', trigger: 'blur' }
                // { pattern: /^[0-9a-zA-Z]{3,32}$/, message: '用户名3-32位',trigger: 'blur'}
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                // { pattern: /^1[34578]\d{9}$/, message: '密码必须为数字值',trigger: 'blur'}
                ]
            },
            isRememberPsd: false,
            userHistoryRecordMap: {}
        };
    },
    created () {
        // 获取用户记录
        this.userHistoryRecordMap = this.$store.state.userHistoryRecord;
        console.log(this.userHistoryRecordMap);
    },
    methods: {
        /**
         * 找回密码
         */
        findPsd () {
            this.$router.push({
                name: 'findPsd'
            });
        },
        /**
       * 记录用户
       */
        recordedUser () {
            const ss = 'xx';
            if (this.isRememberPsd && this.ruleForm.name && this.ruleForm.password) {
                // 记录用户信息
                this.$store.commit('changeUserHistoryRecord', this.ruleForm);
            }
        },
        /**
       * 加载记录密码
       */
        loadRecordPassword () {
            if (!this.ruleForm.password && this.ruleForm.name) {
                this.ruleForm.password = this.userHistoryRecordMap.get(this.ruleForm.name);
            }
        },
        /**
       * 提交登录信息
       */
        submitForm (formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    const res = await this.$api.login(this.ruleForm);
                    if (res.coder == 200) {
                        const data = res.data;
                        const user = {
                            name: data.name, // 工号
                            nickName: data.nickName, // 昵称
                            deptName: data.deptName,
                            Authorization: data.token
                        };
                        // 更新用户信息
                        this.$store.commit('changeLogin', user);
                        // 登录成功跳转到首页
                        this.$router.push({
                            name: 'home'
                        });
                    } else {
                        this.$message.error(res.message);
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .login {
        height: 100%;
        &-content {
            width: 780px;
            margin: 0 auto;
            position: relative;
            top: 50%;
            transform: translateY(-50%);
            .login-box {
                position: relative;
                height: 100%;
                &>img {
                    display: inline-block;
                    width: 400px;
                    height: 426px;
                }
                .login-form-box {
                    width: 380px;
                    position: absolute;
                    top: 0;
                    right: 0;
                    height: 426px;
                    padding: 60px 0 0 40px;
                    background: #fff;
                    border-radius: 4px;
                    .login-form {
                        .login-form-item {
                            width:300px;
                            /deep/.el-form-item__content {
                                border-bottom: 1px solid #D4D4D4;
                                img {
                                    margin-top: 2px;
                                }
                                .el-input {
                                    width: 90%;
                                }
                                .el-input__inner {
                                    border: none;
                                    border-radius: 0;
                                }
                            }
                        }
                        .login-form-noMargin {
                            width:300px;
                            margin: 0;
                            .btn {
                                width:300px;
                                height:46px;
                                color: #fff;
                                background:linear-gradient(-90deg,rgba(0,155,255,1),rgba(1,107,255,1));
                                box-shadow:0 7px 8px 0 rgba(1,139,254,0.2);
                                border-radius:4px;
                            }
                            .btn-findPsd {
                                color: $color-text-secondary;
                                font-size: 12px;
                            }
                        }
                    }
                }
            }
        }
    }
</style>
