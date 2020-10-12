<!--
@file：知识测评-学习中心-学习详情
@createTime：2020-04-15
@updateTime：2020-04-16
@author：Claire
-->
<template>
    <div class="learningDetail">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="learning-item learning-info clearfix">
                <el-image
                        class="learning-info-cover fl"
                        :src="learningInfo.curriculumCoverUrl ? `${$img}${learningInfo.curriculumCoverUrl}` : require('assets/images/default-cover.png')"></el-image>
                <div class="learning-info-info fl ml20">
                    <p class="learning-info-info-title">{{ learningInfo. curriculumName}}</p>
                    <div class="learning-info-info-other">
                        <p>
                            <v-icon class="icon" name="iconshijian-tianchong"></v-icon>
                            <span>学习时间：{{ learningInfo.learnBeginTime | date }} — {{ learningInfo.learnEndTime | date }}</span>
                        </p>
                        <p>
                            <v-icon class="icon" name="icontouxiang"></v-icon>
                            <span>学习人数：{{ learningInfo.userCount }}人</span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="learning-item learning-introduction">
                <div class="learning-item-title">
                    <img src="~assets/images/learning-introduction.png" alt="">
                    <span>课程简介</span>
                </div>
                <div class="learning-item-content">{{ learningInfo.curriculumIntroduction }}</div>
            </div>
            <div class="learning-item learning-progress">
                <div class="learning-item-title">
                    <img src="~assets/images/learning-progress.png" alt="">
                    <span>学习进度</span>
                </div>
                <div class="learning-item-content clearfix">
                    <div class="fl w80p">
                        <el-progress :percentage="learningInfo.progress" :show-text="false"></el-progress>
                        <p class="mt10 c_blue">
                            <span>已学习{{ learningInfo.progress }}%</span>
                            <span v-if="learningInfo.learnRecord">（上次学习到：{{ splitFileName(lastLearnCurriculum.name) }}）</span>
                        </p>
                    </div>
                    <div class="fr">
                        <el-button class="btn_bg_blue" @click="goCurriculumDetail(lastLearnCurriculum.index)" v-if="learningInfo.learnRecord">继续学习</el-button>
                    </div>
                </div>
            </div>
            <div class="learning-item learning-list">
                <div class="learning-item-title">
                    <img src="~assets/images/learning-subject.png" alt="">
                    <span>课程</span>
                </div>
                <ul class="w100p learning-list-content">
                    <li class="learning-list-item" v-for="(item,index) in learningList">
                        <div class="cursor_p" @click="goCurriculumDetail(index)">
                            <template v-if="item.curriculumName.indexOf('.mp4') != -1">
                                <video class="learning-list-item-video" :src="`${$img}${item.curriculumUrl}`">
                                    您的浏览器不支持 video 标签。
                                </video>
                                <img class="learning-list-item-mask" src="~assets/images/learning-video-mask.png" alt="">
                            </template>
                            <template v-else>
                                <img class="learning-list-item-ppt" src="~assets/images/learning-default-ppt.png" alt="">
                            </template>
                        </div>
                        <p class="learning-list-item-text"
                           :title="splitFileName(item.curriculumName)"
                           :class="item.isLearned ? 'c_blue': ''">
                            {{ splitFileName(item.curriculumName) }}
                        </p>
                        <div class="learning-list-item-file" v-if="item.dataUrl">
                            <span class="cursor_p" @click="downloadFile(item.dataUrl)">
                                <i class="icon el-icon-download"></i>
                                <span>下载文档</span>
                            </span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import config from 'api/api_conf';
const { global } = config;
export default {
    name: 'learningDetail',
    data () {
        return {
            learningId: this.$route.query.id, // 当前学习课程id
            breadcrumbNav: [], // 面包屑导航集合
            learningInfo: {
                curriculumCoverUrl: '',
                curriculumName: '',
                learnBeginTime: '',
                learnEndTime: '',
                userCount: '',
                curriculumIntroduction: '',
                progress: 0,
                curriculumResources: ''
            },
            learningList: [], // 课程列表
            learnRecord: [], // 学习记录
            lastLearnCurriculum: {} // 上一次学习课程对象
        };
    },
    watch: {
        '$route.query.pageForm': {
            handler (val) {
                if (val === 'learningCenter') {
                    this.breadcrumbNav = [
                        {
                            name: '知识测评',
                            path: '/assess',
                            routeName: 'assess'
                        }, {
                            name: '学习中心',
                            path: '/assess/assessMgt/learningCenter',
                            routeName: 'assessMgtLearningCenter'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail',
                            routeName: 'assessMgtLearningDetail',
                            disabled: true
                        }
                    ];
                } else if (val === 'home') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail',
                            routeName: 'assessMgtLearningDetail',
                            disabled: true
                        }
                    ];
                } else if (val === 'learningMoreList') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '最新学习',
                            path: '/home/learningMoreList',
                            routeName: 'learningMoreList'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail',
                            routeName: 'assessMgtLearningDetail',
                            disabled: true
                        }
                    ];
                }
            },
            immediate: true,
            deep: true
        }
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取课程信息
         */
        getData () {
            const params = { curriculumId: this.learningId };
            this.$api.getCurrentUserCurriculum(params).then(res => {
                this.learningInfo = res;
                this.learningList = [];
                this.learnRecord = res.learnRecord ? JSON.parse(res.learnRecord) : [];
                const arr = JSON.parse(res.curriculumResources);
                for (let i = 0; i < arr.length; i++) {
                    this.learningList.push({
                        curriculumUrl: arr[i].videoUrl[0].url,
                        curriculumName: arr[i].videoUrl[0].name,
                        dataUrl: Array.isArray(arr[i].data) && arr[i].data.length > 0 ? arr[i].data[0].url : '',
                        dataName: Array.isArray(arr[i].data) && arr[i].data.length > 0 ? arr[i].data[0].name : ''
                    });
                    if (this.learnRecord.includes(i)) {
                        this.$set(this.learningList[i], 'isLearned', true);
                    } else {
                        this.$set(this.learningList[i], 'isLearned', false);
                    }
                    if (this.learnRecord[this.learnRecord.length - 1] === i) {
                        this.lastLearnCurriculum = {
                            index: i,
                            name: arr[i].videoUrl[0].name
                        };
                    }
                }
            });
        },
        /**
         * 跳转到课程详情
         */
        goCurriculumDetail (index) {
            // if (!this.learnRecord.includes(index)) {
            //     this.learnRecord.push(index);
            // }
            const includesIndex = this.learnRecord.indexOf(index);
            if (includesIndex > -1) {
                this.learnRecord.splice(includesIndex, 1);
                this.learnRecord.push(index);
            } else {
                this.learnRecord.push(index);
            }
            const params = {
                userId: this.learningInfo.userId,
                curriculumId: this.learningId,
                learnRecord: this.learnRecord
            };
            this.$api.modifyUserCurriculum(params).then(res => {
                this.$router.push({
                    name: 'assessMgtLearningShow',
                    query: { id: this.learningId, showIndex: index, pageForm: this.$route.query.pageForm }
                });
            });
        },
        /**
         * 文档下载
         * @param url
         */
        downloadFile (url) {
            window.location.href = global + '/resources/download/?path=' + url;
        },
        /**
         * 截取文件名
         * @param text
         * @returns {*}
         */
        splitFileName (text) {
            return text.substring(0, text.lastIndexOf('.'));
        }
    }
};
</script>

<style scoped lang="scss">
    .learningDetail {
        .content {
            .learning-item {
                margin-bottom: 50px;
                &-title {
                    vertical-align: middle;
                    font-size: $font-size-large;
                    img {
                        vertical-align: middle;
                    }
                    span {
                        vertical-align: middle;
                        margin-left: 11px;
                    }
                }
                &-content {
                    width: 100%;
                    padding: 30px;
                    margin-top: 16px;
                    background:rgba(246,246,246,1);
                    border-radius:4px;
                }
            }
            .learning-info {
                &-cover {
                    width: 300px;
                    height: 170px;
                }
                &-info {
                    &-title {
                        font-size: 20px;
                        line-height: 55px;
                    }
                    &-other {
                        width:416px;
                        height:116px;
                        background:rgba(246,246,246,1);
                        border-radius:4px;
                        p {
                            padding-top: 26px;
                            .icon {
                                vertical-align: middle;
                                margin: 0 10px;
                                color: #767578;
                                font-size: 20px;
                            }
                            span {
                                vertical-align: middle;
                                color: $color-text-regular;
                            }
                        }
                    }
                }
            }
            .learning-list {
                &-content {
                    .learning-list-item {
                        position: relative;
                        display: inline-block;
                        width: 245px;
                        margin: 17px 20px 30px 0;
                        vertical-align: top;
                        &-video {
                            width: 245px;
                            height: 150px;
                        }
                        &-mask {
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 245px;
                            height: 150px;
                        }
                        &-text {
                            width: 100%;
                            text-overflow: -o-ellipsis-lastline;
                            overflow: hidden;
                            text-overflow: ellipsis;
                            display: -webkit-box;
                            -webkit-line-clamp: 2;
                            line-clamp: 2;
                            -webkit-box-orient: vertical;
                            word-break: break-all;
                        }
                        &-file {
                            width: 100%;
                            text-align: right;
                            color: $color-text-blue;
                            .icon {
                                font-size: 16px;
                            }
                            span {
                                font-size: $font-size-small;
                            }
                        }
                        &-ppt {
                            width: 245px;
                            height: 150px;
                        }
                    }
                }
            }
        }
    }
</style>
