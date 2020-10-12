<!--
@file：知识社区-圈子话题详情
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="topicDetails">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="topic-info">
                <div class="topic-info-title">
                    <h5>{{ topicInfo.name }}</h5>
                    <p>
                        <span class="mr15">发布人：{{ topicInfo.createUserName }}</span>
                        <span>发布时间：{{ topicInfo.createTime | date('YYYY-MM-DD HH:ss')}}</span>
                    </p>
                </div>
                <v-divider></v-divider>
                <div class="topic-info-text" v-html="topicInfo.content"></div>
            </div>
            <v-divider></v-divider>
            <div class="comments-form-box">
                <h6>发表评论</h6>
                <el-form :model="commentForm" :rules="rules" ref="commentForm">
                    <el-form-item class="mt20" label="" prop="content">
                        <el-input type="textarea" v-model="commentForm.content"></el-input>
                    </el-form-item>
                    <el-form-item class="btn-box">
                        <el-button class="btn_bg_blue" @click="commentSubmit()">提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <v-divider></v-divider>
            <topicCommentList ref="topicCommentList"></topicCommentList>
        </div>
    </div>
</template>

<script>
import topicCommentList from './components/topicCommentList';
export default {
    name: 'topicDetails',
    components: {
        topicCommentList
    },
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识社区',
                    path: '/community',
                    routeName: 'community'
                }
            ], // 面包屑导航集合
            lastPath: '', // 返回上一页路由地址
            topicId: this.$route.query.id,
            topicInfo: {}, // 话题对象
            commentForm: {
                type: 1,
                pid: '0',
                topicId: this.$route.query.id,
                content: ''
            },
            rules: {
                content: [
                    { required: true, message: '请输入评论内容', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在100个字符以内', trigger: 'blur' }
                ]
            } // 表单验证规则
        };
    },
    watch: {
        '$route.params.pageFrom': {
            handler (val) {
                const lastNav = {
                    name: '话题详情',
                    path: '/community/topicCircle/topicDetails',
                    routeName: 'communityTopicDetails',
                    disabled: true
                };
                if (val === 'home') {
                    this.breadcrumbNav[1] = lastNav;
                    this.lastPath = this.breadcrumbNav[0].path;
                } else if (val === 'manage') {
                    this.breadcrumbNav[1] = {
                        name: '话题管理',
                        path: '/community/communityMgt/topicMgt',
                        routeName: 'communityTopicMgt'
                    };
                    this.breadcrumbNav[2] = lastNav;
                    this.lastPath = this.breadcrumbNav[1].path;
                } else {
                    this.breadcrumbNav[1] = {
                        name: this.$route.query.circleName,
                        path: '/community/topicCircle/' + this.$route.params.circleId,
                        routeName: 'communityTopicCircleSort'
                    };
                    this.breadcrumbNav[2] = lastNav;
                    this.lastPath = this.breadcrumbNav[1].path;
                }
            },
            immediate: true
        }
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取话题详情信息
         */
        async getData () {
            const params = { id: this.topicId };
            this.topicInfo = await this.$api.getTopic(params);
        },
        /**
         * 评论提交
         */
        commentSubmit () {
            this.$refs.commentForm.validate(async (valid) => {
                if (valid) {
                    const param = this.commentForm;
                    this.$api.saveComment(param).then(res => {
                        this.$refs.topicCommentList.getCommentPage();
                        this.$refs.topicCommentList.getCommentCount();
                        this.$message.success('评论提交成功');
                        this.commentForm.content = '';
                    });
                }
            });
        },
    }
};
</script>

<style scoped lang="scss">
    .topicDetails {
        .content {
            .topic-info {
                &-title {
                    text-align: center;

                    h5 {
                        font-size: $font-size-middle;
                        font-weight: 400;
                        line-height: 45px;
                    }

                    & > p {
                        color: $color-text-secondary;
                        font-size: $font-size-small;
                    }
                }
                &-text {
                    width: 100%;
                    max-height: 400px;
                    line-height: 24px;
                    border: 1px solid #ddd;
                    padding: 15px;
                    overflow-y: auto;
                }
            }
            .comments-form-box {
                h6 {
                    font-size: $font-size-middle;
                    font-weight: 400;
                }
                /deep/.el-textarea {
                    textarea {
                        height: 100px;
                        border-radius: 0;
                    }
                }
            }
        }
    }
</style>
