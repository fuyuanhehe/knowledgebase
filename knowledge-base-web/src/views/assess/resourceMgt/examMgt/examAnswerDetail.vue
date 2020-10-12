<!--
@file：知识测评-考试管理-考试答题详情
@createTime：2020-04-14
@updateTime：2020-04-14
@author：Claire
-->
<template>
    <div class="examAnswerDetail">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div v-for="(item,index) in examAnswerList">
                <div class="subject-title">
                    <span v-if="item.type">{{index + 1 }}、{{ item.name }}？（正确答案是：{{ JSON.parse(item.answers).join('、') }}）</span>
                    <span v-else>{{index + 1 }}、{{ item.name }}？（正确答案是：{{ JSON.parse(item.answers).join('') }}）</span>
                </div>
                <div class="subject-options">
                    <template  v-if="!item.type">
                        <el-radio-group class="display_b mb10"
                                        value=""
                                        v-for="option in JSON.parse(item.options)"
                                        :key="option.key">
                            <el-radio :label="option.key"></el-radio>
                            <span class="subject-options-text">、{{ option.value }}</span>
                        </el-radio-group>
                    </template>
                    <template v-else>
                        <el-checkbox-group class="display_b mb10"
                                           value=""
                                           v-for="option in JSON.parse(item.options)"
                                           :key="option.key">
                            <el-checkbox :label="option.key" name="answer"></el-checkbox>
                            <span class="subject-options-text">、{{ option.value }}</span>
                        </el-checkbox-group>
                    </template>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'examAnswerDetail',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '考试管理',
                    path: '/assess/resourceMgt/testMgt',
                    routeName: 'assessResourceMgtTestMgt'
                }, {
                    name: '考试统计',
                    path: '/assess/resourceMgt/examStatistic',
                    routeName: 'assessResourceMgtExamStatistic'
                }, {
                    name: '考试答题详情',
                    path: '/assess/resourceMgt/examAnswerDetail',
                    routeName: 'assessResourceMgtExamAnswerDetail',
                    disabled: true
                }
            ], // 面包屑导航集合
            examAnswerList: []
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取考试答题信息
         */
        async getData () {
            const params = { id: this.testPaperId };
            const res = await this.$api.getTestPaper(params);
            this.examAnswerList = JSON.parse(res.subjects);
            console.log('this.examAnswerList:', this.examAnswerList);
        }
    }
};
</script>

<style scoped lang="scss">
    .examAnswerDetail {
        .test-title {
            font-size: $font-size-large;
            margin-bottom: 20px;
        }
        .subject-title {
            height:26px;
            line-height: 26px;
            &-tag {
                display: inline-block;
                width:54px;
                height: 100%;
                text-align: center;
                background:rgba(242,249,255,1);
                border-radius:4px;
                color: $color-text-blue;
            }
        }
        .subject-options {
            margin: 20px 0 20px 65px;
            &-text {
                font-size: $font-size-base;
            }
            /deep/.el-checkbox {
                .el-checkbox__label {
                    padding-left: 20px;
                }
            }
            /deep/.el-radio {
                margin-right: 0;
                .el-radio__label {
                    padding-left: 20px;
                }
            }
        }
    }
</style>
