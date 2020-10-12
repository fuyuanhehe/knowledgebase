<!--
@file：找回密码
@createTime：2020-04-22
@updateTime：2020-04-22
@author：Claire
-->
<template>
    <div class="findPsd">
        <div class="findPsd-content ">
            <img class="mb20" src="~assets/images/login-logo.png" alt="">
            <div class="findPsd-box">
                <img class="" src="~assets/images/login-picture.png" alt="">
                <div class="findPsd-form-box">
                    <h1 class="mb30">找回密码</h1>
                    <el-form class="findPsd-form" :model="ruleForm" :rules="rules" ref="ruleForm">
                        <el-form-item  class="findPsd-form-item" label="" prop="mail">
                            <img src="~assets/images/login-email-icon.png" alt="">
                            <el-input v-model="ruleForm.mail" placeholder="邮箱"></el-input>
                        </el-form-item>
                        <el-form-item  class="findPsd-form-item clearfix" label="" prop="code">
                            <img src="~assets/images/login-code-icon.png" alt="">
                            <el-input class="findPsd-form-input-small" v-model="ruleForm.code" placeholder="输入验证码"></el-input>
                            <el-button class="btn fr" @click="sendCode($event, 'ruleForm')" :disabled="sendFlg">发送验证码</el-button>
                        </el-form-item>
                        <el-form-item class="findPsd-form-item" label="" prop="newPassword">
                            <img src="~assets/images/login-psd-icon.png" alt="">
                            <el-input type="password" v-model="ruleForm.newPassword" placeholder="设置新密码"></el-input>
                        </el-form-item>
                        <el-form-item class="findPsd-form-item" label="" prop="secondaryPassword">
                            <img src="~assets/images/login-psd-icon.png" alt="">
                            <el-input type="password" v-model="ruleForm.secondaryPassword" placeholder="再次输入密码"></el-input>
                        </el-form-item>
                        <el-form-item class="btn-box">
                            <el-button class="btn" @click="submitForm('ruleForm')">确定</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'findPsd',
    data () {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
            ruleForm: {
                mail: '',
                newPassword: '',
                secondaryPassword: '',
                password: '',
                code: ''
            },
            sendFlg: false,
            rules: {
              mail: [
                { required: true, message: '邮件不能为空', trigger: 'blur'},
                { type: 'email', message: '邮件格式错误', trigger: 'blur'}
                //{ pattern: /^[0-9a-zA-Z]{3,32}$/, message: '用户名3-32位',trigger: 'blur'}
              ],
              newPassword: [
                { required: true, message: '密码不能为空', trigger: 'blur'}
                //{ pattern: /^1[34578]\d{9}$/, message: '密码必须为数字值',trigger: 'blur'}
              ],
              secondaryPassword: [
                { validator: validatePass, trigger: 'blur'}
              ],
              code: [
                { required: true, message: '验证码不能为空', trigger: 'blur'}
              ]
            }
        };
    },
    methods: {
        sendCode (event, formName) {
          let num = 60;
          let _event = event.currentTarget;
          let _this = this;
          this.$refs[formName].validateField('mail', async (valid) => {
            if (!valid) {
              this.ruleForm.password = this.ruleForm.newPassword;
              const res = await this.$api.sendCodeMsg({
                toUserName: this.ruleForm.mail
              });
              this.$message.success("验证码发送成功！");
              this.sendFlg = true;
              let inter = setInterval(function(){
                _event.innerText = num + "s后可重新发送";
                if (num <= 0) {
                  clearInterval(inter);
                  _this.sendFlg = false;
                  _event.innerText = "发送验证码";
                }
                num--;
              },1000);
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        /**
         * 提交修改信息
         */
        submitForm(formName) {
          this.$refs[formName].validate(async (valid) => {
            if (valid) {
              this.ruleForm.password = this.ruleForm.newPassword;
              const res = await this.$api.modifyPassword({
                toUserName: this.ruleForm.mail,
                password: this.ruleForm.newPassword,
                code: this.ruleForm.code,
                msgId: this.ruleForm.msgId
              });
              if (res.coder == 200) {
                this.$message.success(res.message);
                // 登录成功跳转到首页
                this.$router.push({
                  name: 'login'
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
    .findPsd {
        height: 100%;
        &-content {
            width: 780px;
            margin: 0 auto;
            position: relative;
            top: 50%;
            transform: translateY(-50%);
            .findPsd-box {
                position: relative;
                height: 100%;
                &>img {
                    display: inline-block;
                    width: 400px;
                    height: 426px;
                }
                .findPsd-form-box {
                    width: 380px;
                    position: absolute;
                    top: 0;
                    right: 0;
                    height: 426px;
                    padding: 30px 0 0 40px;
                    background: #fff;
                    border-radius: 4px;
                    .findPsd-form {
                        .findPsd-form-item {
                            width:300px;
                            /deep/.el-form-item__content {
                                border-bottom: 1px solid #D4D4D4;
                                img {
                                    margin-top: 2px;
                                }
                                .el-input {
                                    width: 90%;
                                }
                                .findPsd-form-input-small {
                                    width: 50%;
                                }
                                .btn {
                                    padding: 10px;
                                }
                                .el-input__inner {
                                    border: none;
                                    border-radius: 0;
                                }
                            }
                        }
                        .btn-box {
                            .btn {
                                width:300px;
                                height:46px;
                                color: #fff;
                                background:linear-gradient(-90deg,rgba(0,155,255,1),rgba(1,107,255,1));
                                box-shadow:0 7px 8px 0 rgba(1,139,254,0.2);
                                border-radius:4px;
                            }
                        }
                    }
                }
            }
        }
    }
</style>
