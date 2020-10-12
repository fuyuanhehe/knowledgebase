<!--
@file：首页-最新学习更多页
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="learningMoreList">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <template v-if="Array.isArray(learningList) && learningList.length > 0">
                <div class="learning-list"
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)">
                    <el-row class="w100p ml0" :gutter="20">
                        <el-col :span="8"
                                v-for="item in learningList"
                                :key="item.id">
                            <div class="learning-item" >
                                <div class="learning-item-info w100p">
                                    <el-image
                                            class="learning-item-info-cover w100p"
                                            :src="item.curriculumCoverUrl ? `${$img}${item.curriculumCoverUrl}` : require('assets/images/default-cover.png')"></el-image>
                                    <p :title="item.curriculumName">{{ item.curriculumName }}</p>
                                </div>
                                <div class="learning-item-progress">
                                    <el-progress :percentage="item.progress" :show-text="false"></el-progress>
                                    <p>已学习{{ item.progress }}%</p>
                                </div>
                                <div class="learning-item-btn">
                                    <el-button class="w100p btn_border_blue"
                                               @click="goDetail(item.curriculumId)">继续学习</el-button>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
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
    name: 'learningMoreList',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '首页',
                    path: '/home',
                    routeName: 'home'
                }, {
                    name: '最新学习',
                    path: '/home/learningMoreList',
                    routeName: 'learningMoreList',
                    disabled: true
                }
            ], // 面包屑导航集合
            learningList: [
                /*{
                    id: 1,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识',
                    progress: 25
                },
                {
                    id: 2,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识',
                    progress: 25
                },
                {
                    id: 3,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识',
                    progress: 25
                }*/
            ],
            loading: false // 学习列表加载动画
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取学习列表数据
         */
        async getData () {
            const params = {};
            this.loading = true;
            this.learningList = await this.$api.getAllCurrentUserCurriculum(params);
            this.loading = false;
        },
        /**
         * 进入课程学习详情
         * @param id 课程id
         */
        goDetail (id) {
            // this.$router.push('/assess/assessMgt/learningDetail/' + id);
            this.$router.push({
                name: 'assessMgtLearningDetail',
                query: { id: id, pageForm: 'learningMoreList' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .learningMoreList {
        width: 1200px;
        margin: 0 auto;
        .breadcrumb-box {
            background: transparent;
            /deep/.breadcrumb {
                margin: 0;
                padding: 0;
                height: 40px;
                line-height: 40px;
            }
        }
        .content {
            background: #fff;
            .learning-list {
                .learning-item {
                    padding: 10px;
                    &-info {
                        box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                        border-radius:4px;
                        &-cover {
                            width: 245px;
                            height: 150px;
                        }
                        p {
                            @extend %ellipsis;
                            line-height: 40px;
                            padding: 0 10px;
                        }
                    }
                    &-progress {
                        margin: 20px 0 10px;
                        p {
                            font-size: $font-size-small;
                            color: $color-text-blue;
                            margin-top: 10px;
                        }
                    }
                }
            }
        }
    }
</style>
