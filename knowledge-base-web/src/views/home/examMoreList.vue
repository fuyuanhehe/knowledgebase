<!--
@file：首页-最新考试更多页
@createTime：2020-04-26
@updateTime：2020-04-26
@author：Claire
-->
<template>
    <div class="examMoreList">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <template v-if="Array.isArray(examList) && examList.length > 0">
                <div class="exam-list"
                     v-loading="loading"
                     element-loading-text="拼命加载中"
                     element-loading-spinner="el-icon-loading"
                     element-loading-background="rgba(0, 0, 0, 0.8)">
                    <el-row :gutter="30">
                        <el-col class="mb30" :span="12" v-for="item in examList" :key="item.examId">
                            <el-row class="exam-item" :gutter="20">
                                <el-col :span="18" class="exam-item-info ">
                                    <p class="exam-item-info-title" :title="item.examName">{{ item.examName }}</p>
                                    <p class="c_6">
                                        截止时间：
                                        <span class="c_blue">{{ item.examEndTime | date }}</span>
                                    </p>
                                    <p class="c_6">
                                        已考人数：
                                        <span class="c_blue">{{ item.examedCount ? item.examedCount : 0 }}人</span>
                                    </p>
                                    <el-button class="btn btn_border_blue" @click="takeExam(item.examId)">
                                        参加考试 >
                                    </el-button>
                                </el-col>
                                <el-col :span="6" class="">
                                    <img :src="require('assets/images/exam-picture.png')" alt="" width="110" height="110">
                                </el-col>
                            </el-row>
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
    name: 'examMoreList',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '首页',
                    path: '/home',
                    routeName: 'home'
                }, {
                    name: '最新考试',
                    path: '/home/examMoreList',
                    routeName: 'examMoreList',
                    disabled: true
                }
            ], // 面包屑导航集合
            examList: [
                /*{
                    id: 1,
                    examName: '19年xxxx第三季度电力知识模拟考试模拟考试'
                }, {
                    id: 2,
                    examName: '19年xxxx第三季度电力知识模拟考试'
                }, {
                    id: 3,
                    examName: '19年xxxx第三季度电力知识模拟考试'
                }, {
                    id: 4,
                    examName: '19年xxxx第三季度电力知识模拟考试'
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
       * 获取考试列表数据
       */
      async getData () {
        const params = {};
        this.loading = true;
        this.examList =await this.$api.getAllCurrentUserExam(params);
        this.loading = false;
      },
      /**
         * 参加考试
         * @param id 考试id
         */
        takeExam (id) {
            this.$router.push({
                name: 'assessMgtTakeExam',
                query: { id: id, pageForm: 'examMoreList' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .examMoreList {
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
            .exam-list {
                .exam-item {
                    padding: 20px;
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    border-radius:4px;
                    &-info {
                        vertical-align: top;
                        p {
                            line-height: 26px;
                        }
                        &-title {
                            @extend %ellipsis;
                            width: 100%;
                            line-height: 32px !important;
                            font-size: $font-size-middle;
                        }
                        .btn {
                            height: 30px;
                            line-height: 30px;
                            margin-top: 6px;
                            font-size: 12px;
                        }
                        /*.btn_border_blue {*/
                            /*width: 86px;*/
                        /*}*/
                    }
                }
            }
        }
    }
</style>
