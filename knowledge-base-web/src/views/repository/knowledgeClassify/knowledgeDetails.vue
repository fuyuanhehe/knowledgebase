<!--
@file：知识仓库-知识文档详情页
@createTime：20200323
@updateTime：20200323
@author：Claire
-->
<template>
    <div class="knowledgeDetails">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="knowledge-info">
                <div class="knowledge-info-title">
                    <h5>{{ knowledgeInfo.name }}</h5>
                    <p>
                        <span class="mr15">上传人：{{ knowledgeInfo.createUser }}</span>
                        <span>发布时间：{{ knowledgeInfo.createTime | date('YYYY-MM-DD HH:ss')}}</span>
                    </p>
                </div>
                <v-divider></v-divider>
                <div class="knowledge-info-text">
                    <officeEditor :fileUrl="knowledgeInfo.url" @init="initOfficeEditor" v-if="officeEditorFlag"></officeEditor>
                </div>
                <div class="knowledge-info-other clearfix">
                    <span class="knowledge-info-other-file fl">附件：
                        <span class="c_blue cursor_p" @click="downloadFile(knowledgeInfo.url)">{{ knowledgeInfo.name }}</span>
                    </span>
                    <span class="knowledge-info-other-btns fr">
                        <el-button class="btn btn_border_blue_s" @click="handleDocCorrection">文档纠错</el-button>
                        <i class="btn el-icon-star-off" title="收藏" @click="handleCollection(1)" v-if="!isCollection"></i>
                        <v-icon class="btn collected-icon" name="iconshoucang1" @click.native="handleCollection(0)" v-else></v-icon>
                        <i class="btn el-icon-edit-outline" title="编辑" @click="handleEdit"></i>
                        <!--<i class="btn el-icon-printer" title="打印"></i>-->
                    </span>
                </div>
            </div>
            <v-divider></v-divider>
            <div class="comments-form">
                <div class="tab-box">
                    <span class="tab">评论  ( <span>{{ commentNum }}</span> )</span>
                    <recordPopover ref="recordPopover"
                                   class="display_ib mr20 cursor_p"
                                   :correctionNum="knowledgeInfo.correctionNum"
                                   :recordType="2"
                                   @showRecord="showCorrectionDetail"></recordPopover>
                    <recordPopover ref="recordPopover"
                                   class="display_ib mr20 cursor_p"
                                   :editNum="knowledgeInfo.udpateNum"
                                   :recordType="0"></recordPopover>
                    <recordPopover ref="recordPopover"
                                   class="display_ib mr20 cursor_p"
                                   :downloadNum="knowledgeInfo.downloadNum"
                                   :recordType="1"></recordPopover>
                </div>
                <div class="comments-form-box">
                    <el-form :model="commentForm" :rules="rules" ref="commentForm">
                        <el-form-item label="我来评论" prop="score">
                            <el-rate allow-half v-model="commentForm.score"></el-rate>
                        </el-form-item>
                        <el-form-item class="mt20" label="" prop="content">
                            <el-input type="textarea" v-model="commentForm.content"></el-input>
                        </el-form-item>
                        <el-form-item class="btn-box">
                            <el-button class="btn_bg_blue" @click="commentSubmit()">提交</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <v-divider></v-divider>
            <div class="comments-info">
                <span>评论信息</span>
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
                                        <el-rate allow-half disabled v-model="item.score"></el-rate>
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
                            <!--<div class="comments-item-form" v-if="item.replyInputShow">-->
                                <!--<el-form :model="replyForm" ref="replyForm">-->
                                    <!--<el-form-item label="">-->
                                        <!--<el-input type="textarea" v-model="replyForm.content"-->
                                                  <!--:placeholder="'回复' + item.commentUser + ':'"-->
                                                  <!--@input="replyInput()"></el-input>-->
                                    <!--</el-form-item>-->
                                    <!--<el-form-item class="btn-box">-->
                                        <!--<el-button class="btn_bg_blue" @click="replySubmit(item.id, item)">提交</el-button>-->
                                    <!--</el-form-item>-->
                                <!--</el-form>-->
                            <!--</div>-->
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
        <docCorrectionDialog ref="docCorrectionDialog"
                             :info="knowledgeInfo"
                             :title="knowledgeInfo.name"
                             :categoryName="knowledgeInfo.categoryName"
                             @submit="correctionSubmit"></docCorrectionDialog>
    </div>
</template>

<script>
import docCorrectionDialog from './dialog/docCorrectionDialog'; // 文档纠错对话框
import recordPopover from './popover/recordPopover'; // 文档相关记录弹出框
import officeEditor from 'components/OfficeEditor.vue'; // ONLYOFFICE文档编辑器
import config from 'api/api_conf';
const { global } = config;
export default {
    name: 'knowledgeDetails',
    components: {
        docCorrectionDialog,
        recordPopover,
        officeEditor
    },
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                },
                {
                    name: '知识分类',
                    path: '/repository/knowledgeClassify',
                    routeName: 'repositoryKnowledgeClassify'
                }, {
                    name: '',
                    path: '',
                    routeName: 'repositoryKnowledgeClassifyBase'
                }, {
                    name: '知识详情',
                    path: '',
                    routeName: 'repositoryKnowledgeDetails'
                }
            ], // 面包屑导航集合
            docId: '',
            knowledgeInfo: {
                name: '',
                createUser: '',
                createTime: null,
                content: '',
                url: '',
                commentNum: 0,
                correctionNum: 0,
                udpateNum: 0,
                downloadNum: 0,
                printNum: 0
            },
            commentForm: {
                type: 0,
                pid: '0',
                documentId: this.$route.query.id,
                score: 0,
                content: ''
            },
            rules: this.validationRules(), // 表单验证规则
            loading: false, // 评论信息是否显示加载动画
            commentSearch: {
                pageNum: 1,
                pageSize: 10,
                type: 0,
                documentId: this.$route.query.id
            }, // 评论查询参数对象
            commentNum: 0, // 评论数量
            commentsList: [], // 评论信息列表
            replyForm: {
                type: 0,
                pid: '0',
                documentId: this.$route.query.id,
                content: ''
            }, // 回复表单对象
            firstReplyIndex: '0', // 第一级回复索引
            secondReplyIndex: '0', // 第二级回复索引
            replyTo: '', // 回复人
            replyToId: '', // 回复人id
            replyLevel: '', // 回复层级
            replyComment: '', // 回复内容
            isCollection: false, // 是否收藏
            collectionId: '', // 收藏id
            officeEditor: null, // ONLYOFFICE文档编辑器对象
            officeEditorFlag: false, // 是否显示ONLYOFFICE文档编辑器
            commentDelShow: false, // 评论删除是否显示
            total: 0 // 总条目数
        };
    },
    filters: {
        ellipsis (value, len) {
            if (!value) return '';
            if (value.length > len) {
                return value.slice(0, len) + '... ';
            }
            return value;
        }
    },
    beforeRouteEnter (to, from, next) {
        next(vm => {
            if (from.name == 'repositoryDocumentCommentMgt') {
                vm.commentDelShow = true;
            }
            vm.docId = to.query.id;
            vm.getData(to.query.id);
            vm.getCommentPage();
            vm.getCommentCount();
            vm.getCurrentAllCollection();
        });
    },
    watch: {
        knowledgeInfo: {
            handler (val) {
                this.breadcrumbNav[2].name = val.categoryName;
                this.breadcrumbNav[2].path = '/repository/knowledgeClassify/' + val.categoryId;
                this.breadcrumbNav[3].path = '/repository/knowledgeClassify/' + val.categoryId + '/knowledgeDetails?id=' + val.id;
            },
            immediate: true,
            deep: true
        }
    },
    methods: {
        /**
         * 获取知识文档信息
         * @param id 知识文档id
         */
        async getData (id) {
            const params = { id: id };
            this.knowledgeInfo = await this.$api.getBaseDocument(params);
            this.officeEditorFlag = true;
        },
        /**
         * 初始化ONLYOFFICE文档编辑器
         */
        initOfficeEditor (data) {
            this.officeEditor = data;
        },
        /**
         * 获取知识文档评论分页信息
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
        },
        /**
         * 查询当前用户当前文档收藏详情
         */
        getCurrentAllCollection () {
            this.$api.getCurrentAllCollection({ documentId: this.docId }).then(res => {
                if (Array.isArray(res) && res.length > 0) {
                    this.isCollection = true;
                    this.collectionId = res[0].id;
                } else {
                    this.isCollection = false;
                }
            });
        },
        /**
         * 修改文档（评论，下载，编辑，纠错）
         */
        modifyBaseDocument (modifyObj) {
            const param = {
                id: this.docId
            };
            param[modifyObj] = this.knowledgeInfo[modifyObj] ? this.knowledgeInfo[modifyObj] + 1 : 1;
            this.$api.modifyBaseDocument(param).then(res => {
                this.getData(this.docId);
            });
        },
        /**
         * 附件下载
         */
        downloadFile (url) {
            window.location.href = global + '/resources/download/?path=' + url;
            const param = {
                documentId: this.docId,
                type: 1 // 记录类型（0：编辑；1：下载；2：纠错)
            };
            this.$api.saveBusDocumentRecord(param).then(res => {
                this.modifyBaseDocument('downloadNum');
            });
        },
        /**
         * 文档纠错对话框显示
         */
        handleDocCorrection () {
            this.$refs.docCorrectionDialog.handleShow();
        },
        /**
         * 文档纠错提交
         * @param val 纠错原因
         */
        correctionSubmit (val) {
            console.log('纠错', val);
            const param = {
                documentId: this.docId,
                type: 2, // 记录类型（0：编辑；1：下载；2：纠错)
                reserve2: val.reason
            };
            this.$api.saveBusDocumentRecord(param).then(res => {
                this.$refs.docCorrectionDialog.handleCancel();
                this.$message.success('文档纠错成功');
                this.modifyBaseDocument('correctionNum');
            });
        },
        /**
         * 查看文档纠错详情
         */
        showCorrectionDetail (data) {
            this.$refs.docCorrectionDialog.handleShow(data);
        },
        /**
         * 收藏
         */
        handleCollection (isCollection) {
            this.isCollection = !this.isCollection;
            if (isCollection) {
                const param = {
                    type: 0, // 收藏类型（0：文档类型；1：话题类型）
                    documentId: this.docId // 文档ID（类型为1时可以不填）
                };
                this.$api.saveCollection(param).then(res => {
                    this.getCurrentAllCollection();
                });
            } else {
                this.$api.removeCollection({ id: this.collectionId });
            }
        },
        /**
         * 编辑
         */
        handleEdit () {
            const routeData = this.$router.resolve({
                name: 'docFileEdit',
                query: { url: this.knowledgeInfo.url },
                params: { url: this.knowledgeInfo.url }
            });
            window.open(routeData.href, '_blank');
            const param = {
                documentId: this.docId,
                type: 0 // 记录类型（0：编辑；1：下载；2：纠错)
            };
            this.$api.saveBusDocumentRecord(param).then(res => {
                this.modifyBaseDocument('udpateNum');
            });
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
         * 评论提交表单验证规则
         */
        validationRules () {
            const checkScore = (rule, value, callback) => {
                if (this.commentForm.score) {
                    callback();
                } else {
                    callback(new Error('请给予评分'));
                }
            };
            return {
                score: [
                    { required: true, validator: checkScore, trigger: 'change' }
                ],
                content: [
                    { required: true, message: '请输入评论内容', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在100个字符以内', trigger: 'blur' }
                ]
            };
        },
        /**
         * 评论提交
         */
        commentSubmit () {
            this.$refs.commentForm.validate(async (valid) => {
                if (valid) {
                    const param = this.commentForm;
                    this.$api.saveComment(param).then(res => {
                        this.getCommentPage();
                        this.getCommentCount();
                        this.modifyBaseDocument('commentNum');
                        this.$message.success('评论提交成功');
                        this.commentForm.content = '';
                        this.commentForm.score = 0;
                    });
                }
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
            // ES6的新语法，称为箭头函数， 它是语法糖。
            // 当箭头函数函数体中只有一条语句时，return语句可省略。
            // this.commentsList.forEach((list, idx) => list.replyInputShow = idx == i);
            // 回调函数， 回调函数有三个形参，list当前循环中对象,i表示当前循环中的索引
            // arr 表示循环的数组本身
            // this.commentsList.forEach(function (list, idx, arr) {
            //     if (idx == i) {
            //         list.replyInputShow = true;
            //     } else {
            //         list.replyInputShow = false;
            //     }
            // });
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
.knowledgeDetails {
    .content {
        .knowledge-info {
            &-title {
                text-align: center;
                h5 {
                    font-size: $font-size-middle;
                    font-weight:400;
                    line-height: 45px;
                }
                &>p {
                    color: $color-text-secondary;
                    font-size: $font-size-small;
                }
            }
            &-text {
                width: 100%;
                height: 500px;
                line-height: 24px;
                border: 1px solid #ddd;
                padding: 15px;
                overflow-y: auto;
            }
            &-other {
                margin-top: 10px;
                &-file {
                    height: 38px;
                    line-height: 38px;
                }
                &-btns {
                    .btn {
                        margin-left: 30px;
                    }
                    i {
                        vertical-align: middle;
                        font-size: 24px;
                        color: #868688;
                        cursor: pointer;
                        &:hover,&:focus {
                            color: #016CFF;
                        }
                    }
                }
            }
        }
        .comments-form {
            .tab-box {
                height: 30px;
                line-height: 30px;
                .tab {
                    margin-right: 20px;
                    cursor: pointer;
                }
            }
            &-box {
                .el-form-item {
                    &:first-child {
                        margin-bottom: 10px;
                    }
                }
                /deep/.el-rate {
                    height: 40px;
                    line-height: 50px;
                }
                /deep/.el-textarea {
                    textarea {
                        height: 100px;
                        border-radius: 0;
                    }
                }
            }
        }
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
                        /deep/.el-rate {
                            display: inline-block;
                            height: 21px;
                            line-height: 21px;
                            margin-left: 25px;
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
}
</style>
