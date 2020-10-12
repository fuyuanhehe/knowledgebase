<!--
@file：知识测评-学习中心
@createTime：2020-04-14
@updateTime：2020-04-16
@author：Claire
-->
<template>
    <div class="learningCenter">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <ul class="tabs-box clearfix">
                <li class="tab fl"
                    :class="{ 'tab-active' : activeTab == tab.id }"
                    v-for="tab in tabsList"
                    @click="handleTabClick(tab.id)">
                    {{ tab.name }}
                </li>
            </ul>
            <el-divider></el-divider>
            <template v-if="Array.isArray(learningList) && learningList.length > 0">
                <ul class="learning-list"
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)">
                    <li class="learning-item" v-for="item in learningList">
                        <div class="learning-item-info w100p">
                            <el-image
                                    class="learning-item-info-cover w100p"
                                    :src="item.curriculumCoverUrl ? `${$img}${item.curriculumCoverUrl}` : require('assets/images/default-cover.png')"></el-image>
                            <p :title="item.curriculumName">{{ item.curriculumName }}</p>
                        </div>
                        <div class="learning-item-progress">
                            <el-progress :percentage="item.progress" :show-text="false" :color="item.progress === 100 ? '#2ACFD2' : ''"></el-progress>
                            <p :style="item.progress === 100 ? 'color: #2ACFD2' : ''">已学习{{ item.progress }}%</p>
                        </div>
                        <div class="learning-item-btn">
                            <el-button class="w100p"
                                       :class="activeTab == 1 ? 'btn_bg_blue' : 'btn_border_blue'"
                                       @click="goDetail(item.curriculumId)">
                                {{ activeTab == 1 ? '继续学习' : '重新学习' }}
                            </el-button>
                        </div>
                    </li>
                </ul>
            </template>
            <template v-else>
                <no-data v-show="!loading"></no-data>
            </template>
        </div>
    </div>
</template>

<script>
export default {
    name: 'learningCenter',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '学习中心',
                    path: '/assess/assessMgt/learningCenter',
                    routeName: 'assessMgtLearningCenter',
                    disabled: true
                }
            ], // 面包屑导航集合
            tabsList: [
                { id: 1, name: '学习中' },
                { id: 2, name: '已学完' },
                { id: 3, name: '已过期' }
            ], // tab导航集合
            activeTab: 1, // 当前展示列表状态：1--学习中；2--已学完；3--已过期
            learningList: [
                {
                    curriculumCoverUrl: null,
                    curriculumName: '培训学习知识培训学习知识培训学习知识培',
                    progress: 25
                }
            ], // 学习列表
            loading: false // 学习列表加载动画
        };
    },
    created () {
        const params = {
            curriculumStatus: 1,
            completeStatus: 0
        };
        this.getData(params);
    },
    methods: {
        /**
         * tab切换
         * @param type: 1--学习中；2--已学完；3-已过期
         */
        handleTabClick (type) {
            this.activeTab = type;
            let params;
            if (type === 3) { // 3-已过期
                params = {
                    curriculumStatus: 2, // 课程状态：0--未开始；1--学习中；2--已结束(以时间为依据)
                    completeStatus: 0 // 完成状态：0--未学完；1--已学完（以是否点击该课程中所有资源进行查看为依据）
                };
            } else if (type === 2) { // 2--已学完
                params = {
                    completeStatus: 1
                };
            } else { // 1--学习中
                params = {
                    curriculumStatus: 1,
                    completeStatus: 0
                };
            }
            this.getData(params);
        },
        /**
         * 获取学习列表数据
         */
        async getData (params) {
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
                query: { id: id, pageForm: 'learningCenter' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
.learningCenter {
    .content {
        padding: 0;
        .tabs-box {
            padding: 30px 40px 0;
            .tab {
                width:96px;
                margin-right: 50px;
                height: 40px;
                line-height: 40px;
                text-align: center;
                font-size: $font-size-middle;
                cursor: pointer;
            }
            .tab-active {
                color: $color-text-blue;
                background:rgba(242,249,255,1);
                border-radius:4px;
            }
        }
        .learning-list {
            padding: 0 50px 30px;
            .learning-item {
                display: inline-block;
                width: 25%;
                padding: 10px 20px 30px;
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
