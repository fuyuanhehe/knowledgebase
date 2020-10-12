<!--
@file：知识测评-试卷预览
@createTime：2020-04-09
@updateTime：2020-04-10
@author：Claire
-->
<template>
    <div class="testPaperDetail">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="test-title">{{ testPaperName }}</div>
            <div class="ml20" v-for="(item,index) in testPaperList">
                <div class="subject-title">
                    <span class="subject-title-tag mr10">{{ item.type ? '多选': '单选'}}</span>
                    <span>{{index + 1 }}、{{ item.name }}？（{{ item.score }}分）</span>
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
                <div class="subject-answer" v-if="item.type">答案：{{ JSON.parse(item.answers).join('、') }}</div>
                <div class="subject-answer" v-else>答案：{{ JSON.parse(item.answers).join('') }}</div>
                <div class="subject-answer subject-analysis">解析：{{ item.analysis }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'testPaperDetail',
    data () {
        return {
            testPaperId: this.$route.params.id,
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '试卷库',
                    path: '/assess/resourceMgt/testPaperLibrary',
                    routeName: 'assessResourceMgtTestPaperLibrary'
                }, {
                    name: '试卷预览',
                    path: '/assess/resourceMgt/testPaperDetail',
                    routeName: 'assessResourceMgtTestPaperDetail',
                    disabled: true
                }
            ], // 面包屑导航集合
            testPaperName: '',
            testPaperList: []
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取试卷信息
         */
        async getData () {
            const params = { id: this.testPaperId };
            const res = await this.$api.getTestPaper(params);
            this.testPaperName = res.name;
            this.testPaperList = JSON.parse(res.subjects);
            console.log('this.testPaperList:', this.testPaperList);
        }
    }
};
</script>

<style scoped lang="scss">
    .testPaperDetail {
        .content {
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
            .subject-answer {
                margin: 0 0 20px 65px;
            }
            .subject-analysis {
                width: 70%;
                padding: 19px 13px;
                line-height: 26px;
                background:rgba(246,246,246,1);
                border-radius:4px;
            }
        }
    }
</style>
