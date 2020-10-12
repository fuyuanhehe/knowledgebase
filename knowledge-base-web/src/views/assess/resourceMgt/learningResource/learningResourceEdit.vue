<template>
    <div class="learningResourceEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content p40">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="名称：" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item label="学习时间：" required>
                    <el-col :span="4">
                        <el-form-item prop="learnBeginTime">
                            <el-date-picker type="datetime" placeholder="请选择日期" v-model="ruleForm.learnBeginTime" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <span class="plr10 c_B fl">—</span>
                    <el-col :span="4">
                        <el-form-item prop="learnEndTime">
                            <el-date-picker type="datetime" placeholder="请选择日期" v-model="ruleForm.learnEndTime" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="封面图：" prop="coverUrl" required>
                    <upload-image :src.sync="ruleForm.coverUrl"
                                  :width="210"
                                  :height="140"
                                  hint="上传图片大小在3M以内，建议尺寸210*140">
                    </upload-image>
                </el-form-item>
                <el-form-item label="课程简介：" prop="introduction">
                    <el-input type="textarea" v-model="ruleForm.introduction" placeholder="请输入课程简介"></el-input>
                </el-form-item>
                <el-form-item label="学习人员：">
                    <el-radio-group v-model="learners" @change="handleLearnersChange">
                        <el-radio :label="0">全部人员</el-radio>
                        <el-radio :label="1" @click.native="dialogVisible = true">指定人员</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="资源和课件：" prop="resources" required>
                    <div class="file-box" v-for="(item, index) in resourcesList">
                        <upload-file
                                class="file-item file-resource"
                                :limit="1"
                                accept="video/mp4, application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation"
                                :suffix="['mp4', 'pptx', 'ppt']"
                                :file-list.sync="item.videoUrl"
                                :width="110"
                                text="资源上传"
                                hint="建议上传300M以内的MP4格式视频或PPT（必选）">
                        </upload-file>
                        <upload-file
                                class="file-item file-courseware"
                                :limit="1"
                                accept="aplication/zip"
                                :suffix="['zip']"
                                :file-list.sync="item.data"
                                :width="110"
                                text="课件上传"
                                hint="建议上传10M以内zip格式压缩文件（可选）">
                        </upload-file>
                        <span class="delete-btn" v-show="index !== 0" @click="delResources(index)"><i class="el-icon-close"></i></span>
                    </div>
                    <div class="add-btn" @click="addResources"><i class="el-icon-plus"></i></div>
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--学习人员：指定人员对话框-->
        <el-dialog
                class="dept-dialog"
                title="指定人员"
                center
                :visible.sync="dialogVisible"
                width="600px">
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
                        <el-checkbox class="checkbox-all"
                                     v-model="userCheckAll"
                                     @change="((val) => { handleCheckAllChange(val,item) })">全选</el-checkbox>
                        <el-checkbox-group v-model="checkedUserIds" @change="handleCheckedUsersChange">
                            <el-checkbox class="display_b"
                                         v-for="user in item.userArr"
                                         :label="user.id"
                                         :key="user.id"
                                         @change="((val) => { handleCheckUserChange(val, user, index) })">
                                <img src="~assets/images/user.png" alt="">
                                <span>{{ user.nickName }}</span>
                            </el-checkbox>
                        </el-checkbox-group>
                    </div>
                </div>
                <el-divider></el-divider>
                <div class="dialog-bottom">
                    <span class="display_ib mb20">已选：<span class="c_blue">{{ checkedUsers.length }}人</span></span>
                    <ul class="checkedUser-list clearfix">
                        <li class="checkedUser-item fl" v-for="user in checkedUsers">
                            <div class="checkedUser-item-top">
                                <img src="~assets/images/user.png" alt="">
                                <i class="el-icon-error cursor_p" @click="cancelCheckedUser(user.id)"></i>
                            </div>
                            <span>{{ user.nickName }}</span>
                        </li>
                    </ul>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script src="./learningResourceEdit.js"></script>

<style scoped lang="scss">
    .learningResourceEdit {
        .content {
            .el-form {
                .file-box {
                    position: relative;
                    width: 650px;
                    background:rgba(242,242,242,1);
                    border-radius:4px;
                    margin-bottom: 20px;
                    .file-item {
                        display: inline-block;
                        width: 42%;
                        margin: 22px;
                        vertical-align: top;
                        border-radius:4px;
                    }
                    .file-resource {
                        /deep/.upload-file-btn {
                            background:rgba(17,160,255,1);
                            box-shadow:0 3px 10px 0 rgba(16,60,85,0.17);
                            color: #fff;
                        }
                    }
                    .file-courseware {
                        /deep/.upload-file-btn {
                            background: transparent;
                            border:1px solid rgba(17,160,255,1);
                            color: #11A0FF;
                        }
                    }
                    .delete-btn {
                        position: absolute;
                        right: 10px;
                        cursor: pointer;
                    }
                }
                .add-btn {
                    width:650px;
                    height:40px;
                    line-height: 40px;
                    text-align: center;
                    border:1px dashed rgba(221,221,221,1);
                    border-radius:6px;
                    cursor: pointer;
                }
            }
        }
        .dept-dialog {
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
