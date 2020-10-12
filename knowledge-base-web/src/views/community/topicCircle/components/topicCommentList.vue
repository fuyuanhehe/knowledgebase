<!--
@file：知识社区-圈子话题详情：话题评论列表
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="topicCommentList">
        <div class="comments-info">
            <span>全部评论（{{ commentNum }}）</span>
            <template v-if="Array.isArray(commentsList) && commentsList.length > 0">
                <ul class="comments-list"
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)">
                    <li class="comments-item" v-for="(item,i) in commentsList">
                        <div class="comments-item-user clearfix">
                            <img class="fl" src="~assets/images/user.png" alt="">
                            <div class="comments-item-user-info fl">
                                <div class="comments-item-user-box">
                                    <span class="comments-item-user-name">{{ item.commentUser }}</span>
                                </div>
                                <p class="c_9">{{ item.createTime | date('YYYY-MM-DD HH:ss') }}</p>
                            </div>
                        </div>
                        <div class="comments-item-text clearfix">
                            <p class="fl">
                                <span >{{ item.content }}</span>
                                <el-button type="text" v-if="item.isMoreBtnShow" @click="showMore(item)">显示更多</el-button>
                                <el-button type="text" v-if="!item.isMoreBtnShow && item.isHiddenBtnShow" @click="moreHide(item)">收起</el-button>
                            </p>
                            <div class="fr">
                                <el-button type="text" @click="firstToggleReplyShow(i, item)">回复</el-button>
                                <el-button type="text" @click="deleteComment(item.id)" v-if="commentDelShow">删除</el-button>
                            </div>
                        </div>
                        <div class="comments-item-form" v-show="firstInputShow(i)">
                            <el-form :model="replyForm" ref="replyForm" :rules="rules">
                                <el-form-item label="" prop="content">
                                    <el-input type="textarea" v-model="replyForm.content"
                                              :placeholder="'回复' + replyTo + ':'"></el-input>
                                </el-form-item>
                                <el-form-item class="btn-box">
                                    <el-button class="btn_bg_blue" @click="replySubmit('1')">提交</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                        <v-divider></v-divider>
                        <div class="ml60" v-for="(reply,j) in item.childrenComment">
                            <div class="comments-item-user clearfix">
                                <img class="fl" src="~assets/images/user.png" alt="">
                                <div class="comments-item-user-info fl">
                                    <div class="comments-item-user-box">
                                        <span v-if="!reply.replyUser">{{ reply.commentUser }}</span>
                                        <span v-else>{{reply.commentUser}} > {{ reply.replyUser }}</span>
                                    </div>
                                    <p class="c_9">{{ reply.createTime | date('YYYY-MM-DD HH:ss') }}</p>
                                </div>
                            </div>
                            <div class="comments-item-text clearfix">
                                <p class="w800 fl">
                                    <span>{{ reply.content }}</span>
                                    <el-button type="text" v-if="reply.isMoreBtnShow" @click="showMore(reply)">显示更多</el-button>
                                    <el-button type="text" v-if="!reply.isMoreBtnShow && reply.isHiddenBtnShow" @click="moreHide(reply)">收起</el-button>
                                </p>
                                <div class="fr">
                                    <el-button type="text" @click="secondToggleReplyShow(i,j,reply)">回复</el-button>
                                    <el-button type="text" @click="deleteComment(reply.id)" v-if="commentDelShow">删除</el-button>
                                </div>
                            </div>
                            <v-divider></v-divider>
                            <div class="comments-item-form" v-show="secondInputShow(i,j)">
                                <el-form :model="replyForm" ref="replyForm" :rules="rules">
                                    <el-form-item label="" prop="content">
                                        <el-input type="textarea" v-model="replyForm.content"
                                                  :placeholder="'回复' + replyTo + ':'"></el-input>
                                    </el-form-item>
                                    <el-form-item class="btn-box">
                                        <el-button class="btn_bg_blue" @click="replySubmit('2')">提交</el-button>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </div>
                    </li>
                </ul>
                <!--分页-->
                <div class="pagination-box">
                    <el-pagination
                            layout="prev, pager, next"
                            background
                            prev-text="上一页"
                            next-text="下一页"
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="commentSearch.pageNum"
                            :page-size="commentSearch.pageSize"
                            :total="total">
                    </el-pagination>
                </div>
            </template>
            <template v-else>
                <no-data v-show="!loading"></no-data>
            </template>
        </div>
    </div>
</template>

<script>
export default {
    name: 'topicCommentList',
    data () {
        return {
            loading: false, // 评论信息是否显示加载动画
            commentSearch: {
                pageNum: 1,
                pageSize: 10,
                type: 1,
                topicId: this.$route.query.id
            }, // 评论查询参数对象
            commentNum: 0, // 评论数量
            commentsList: [], // 评论信息列表
            replyForm: {
                type: 1,
                pid: '0',
                topicId: this.$route.query.id,
                content: ''
            }, // 回复表单对象
            rules: {
                content: [
                    { required: true, message: '请输入回复内容', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在100个字符以内', trigger: 'blur' }
                ]
            }, // 表单验证规则
            firstReplyIndex: '0', // 第一级回复索引
            secondReplyIndex: '0', // 第二级回复索引
            replyTo: '', // 回复人
            replyToId: '', // 回复人id
            replyLevel: '', // 回复层级
            replyComment: '', // 回复内容
            commentDelShow: false, // 评论删除是否显示
            total: 0 // 总条目数
        };
    },
    watch: {
        '$route.params.pageFrom': {
            handler (val) {
                if (val === 'manage') {
                    this.commentDelShow = true;
                }
            }
        }
    },
    created () {
        this.getCommentPage();
        this.getCommentCount();
    },
    methods: {
        /**
         * 获取话题评论分页信息
         */
        async getCommentPage (pageNum = this.commentSearch.pageNum, pageSize = this.commentSearch.pageSize) {
            this.commentSearch.pageNum = pageNum;
            this.commentSearch.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getCommentPage(this.commentSearch);
            this.commentsList = res.list;
            this.total = res.total;
            this.loading = false;
            this.handleEllipsis(this.commentsList, 104);
            for (let i = 0; i < this.commentsList.length; i++) {
                this.handleEllipsis(this.commentsList[i].childrenComment, 96);
                this.$set(this.commentsList[i], 'replyInputShow', false);
                const childrenComment = this.commentsList[i].childrenComment;
                if (Array.isArray(childrenComment) && childrenComment.length > 0) {
                    for (let j = 0; j < childrenComment.length; j++) {
                        this.$set(childrenComment[j], 'replyInputShow', false);
                    }
                }
            }
            if (this.$route.params.pageFrom === 'manage') {
                this.commentDelShow = true;
            }
        },
        /**
         * 查询评论条数
         */
        getCommentCount () {
            this.commentSearch.pid = '0';
            this.$api.getCommentCount(this.commentSearch).then(res => {
                this.commentNum = res;
            });
        },
        /**
         * 处理评论文字超出省略显示更多
         * @param arr
         * @param len
         */
        handleEllipsis (arr, len) {
            if (Array.isArray(arr) && arr.length > 0) {
                for (let i = 0; i < arr.length; i++) {
                    this.$set(arr[i], 'isMoreBtnShow', false);
                    this.$set(arr[i], 'isHiddenBtnShow', false);
                    if (Array.isArray(arr[i].content) && arr[i].content.length > 0) {
                        if (arr[i].content.length > len) {
                            this.$set(arr[i], 'isMoreBtnShow', true);
                            this.$set(arr[i], 'commentAll', arr[i].content);
                            arr[i].content = arr[i].content.substr(0, len) + '...';
                            this.$set(arr[i], 'commentHide', arr[i].content);
                        } else {
                            this.$set(arr[i], 'isMoreBtnShow', false);
                        }
                    }
                }
            }
        },
        /**
         * 显示更多
         * @param item
         */
        showMore (item) {
            item.text = item.commentAll;
            item.isMoreBtnShow = false;
            item.isHiddenBtnShow = true;
        },
        /**
         * 收起
         * @param item
         */
        moreHide (item) {
            item.text = item.commentHide;
            item.isMoreBtnShow = true;
            item.isHiddenBtnShow = false;
        },
        /**
         * 是否一级显示回复输入框
         * @param item
         * @param i
         */
        firstToggleReplyShow (i, item) {
            if (this.commentsList[i].replyInputShow) {
                this.commentsList[i].replyInputShow = false;
            } else {
                this.replyForm.content = '';
                this.commentsList[this.firstReplyIndex].replyInputShow = false;
                const commentChild = this.commentsList[this.firstReplyIndex].childrenComment;
                if (commentChild.length > 0) {
                    commentChild[this.secondReplyIndex].replyInputShow = false;
                }
                this.firstReplyIndex = i;
                this.commentsList[i].replyInputShow = true;
                this.replyTo = item.commentUser;
                this.replyToId = item.id;
            }
        },
        /**
         * 是否显示二级回复输入框
         * @param i
         * @param j
         * @param item
         */
        secondToggleReplyShow (i, j, item) {
            if (this.commentsList[i].childrenComment[j].replyInputShow) {
                this.commentsList[i].childrenComment[j].replyInputShow = false;
            } else {
                this.replyForm.content = '';
                this.commentsList[i].childrenComment[this.secondReplyIndex].replyInputShow = false;
                this.commentsList[this.firstReplyIndex].replyInputShow = false;
                this.firstReplyIndex = i;
                this.secondReplyIndex = j;
                this.commentsList[i].childrenComment[j].replyInputShow = true;
                this.replyTo = item.commentUser;
                this.replyToId = item.id;
            }
        },
        /**
         * 返回一级回复输入框显示条件
         */
        firstInputShow (i) {
            return this.commentsList[i].replyInputShow;
        },
        /**
         *返回二级回复输入框显示条件
         */
        secondInputShow (i, j) {
            return this.commentsList[i].childrenComment[j].replyInputShow;
        },
        /**
         * 回复提交
         */
        replySubmit (level) {
            if (!this.replyForm.content) {
                this.$message.warning('请输入回复内容');
            } else {
                this.replyForm.pid = this.replyToId;
                this.replyForm.replyUser = level === '1' ? '' : this.replyTo;
                // console.log('this.replyForm:', this.replyForm);
                const param = this.replyForm;
                this.$api.saveComment(param).then(res => {
                    this.getCommentPage();
                    this.$message.success('回复成功');
                    this.replyForm.content = '';
                    if (level === '1') {
                        this.commentsList[this.firstReplyIndex].replyInputShow = true;
                    } else {
                        this.commentsList[this.firstReplyIndex].childrenComment[this.secondReplyIndex].replyInputShow = true;
                    }
                });
            }
        },
        /**
         * 删除评论
         */
        deleteComment (id) {
            this.$confirm('确定删除该评论么？', '评论删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeComment({ id: id }).then(res => {
                    this.getCommentPage();
                    this.getCommentCount();
                    this.modifyBaseDocument('commentNum');
                    this.$message.success('评论删除成功!');
                });
            }).catch(() => {
                this.$message.info('已取消评论删除');
            });
        },
        /**
         * pageSize 改变时会触发
         * @param pageSize
         */
        handleSizeChange (pageSize) {
            this.getData(1, pageSize);
        },
        /**
         *  currentPage 改变时会触发
         * @param pageNum
         */
        handleCurrentChange (pageNum) {
            this.getData(pageNum, this.commentSearch.pageSize);
        }
    }
};
</script>

<style scoped lang="scss">
    .topicCommentList {
        .comments-info {
            .comments-list {
                .comments-item {
                    &:first-child {
                        margin-top: 20px;
                    }
                    &-user {
                        img {
                            width: 46px;
                            height: 46px;
                        }
                        &-info {
                            height: 46px;
                            margin-left: 15px;
                            .comments-item-user-box {
                                line-height: 28px;
                                &-name {
                                    font-size: $font-size-middle;
                                }
                            }
                        }
                        .c_9 {
                            font-size: $font-size-small;
                        }
                    }
                    &-text {
                        width: 100%;
                        margin-top: 10px;
                        p {
                            width: 857px;
                            padding-left: 60px;
                            line-height: 26px;
                            span {
                                /*text-overflow: -o-ellipsis-lastline;*/
                                /*overflow: hidden;*/
                                /*text-overflow: ellipsis;*/
                                /*display: -webkit-box;*/
                                /*-webkit-line-clamp: 2;*/
                                /*line-clamp: 2;*/
                                /*-webkit-box-orient: vertical;*/
                            }
                        }
                        .el-button {
                            padding: 0;
                            line-height: 26px;
                            margin-left: 45px;
                        }
                    }
                    &-form {
                        padding: 20px 40px 1px;
                        background: #F7F7F7;
                        margin: 20px 0 20px 60px;
                        /deep/.el-form {
                            width: 100%;
                            .el-textarea {
                                width: 100%;
                                textarea {
                                    height: 90px;
                                }
                            }
                            .btn-box {
                                text-align: right;
                            }
                        }
                    }
                }
                .divider {
                    margin: 10px 0;
                }
            }
        }
    }
</style>
