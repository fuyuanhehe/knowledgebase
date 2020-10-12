<!--
@file：知识测评-题目预览
@createTime：2020-04-09
@updateTime：2020-04-09
@author：Claire
-->
<template>
    <div class="subjectDetail">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="subject-title">
                <span class="subject-title-tag">{{ subjectInfo.type ? '多选': '单选'}}</span>
                <span>1、{{ subjectInfo.name }}？（{{ subjectInfo.score }}分）</span>
            </div>
            <div class="subject-options">
                <template  v-if="!subjectInfo.type">
                    <el-radio-group class="display_b mb10"
                                    value=""
                                    v-for="option in subjectOptions"
                                    :key="option.key">
                        <el-radio :label="option.key"></el-radio>
                        <span class="subject-options-text">、{{ option.value }}</span>
                    </el-radio-group>
                </template>
                <template v-else>
                    <el-checkbox-group class="display_b mb10"
                                       value=""
                                       v-for="option in subjectOptions"
                                       :key="option.key">
                        <el-checkbox :label="option.key" name="answer"></el-checkbox>
                        <span class="subject-options-text">、{{ option.value }}</span>
                    </el-checkbox-group>
                </template>
            </div>
            <div class="subject-answer">答案：{{ subjectInfo.answers }}</div>
            <div class="subject-answer subject-analysis">解析：{{ subjectInfo.analysis }}</div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'subjectDetail',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '题库',
                    path: '/assess/resourceMgt/itemBank',
                    routeName: 'assessResourceMgtItemBank'
                }, {
                    name: '题目预览',
                    path: '/assess/resourceMgt/itemBank/subjectDetail',
                    routeName: 'assessResourceMgtSubjectDetail',
                    disabled: true
                }
            ], // 面包屑导航集合
            subjectInfo: {}, // 题目对象
            subjectOptions: [] // 题目选项
        };
    },
    beforeRouteEnter (to, from, next) {
        next(vm => {
            vm.getData(to.query.id);
        });
    },
    methods: {
        /**
         * 获取题目信息
         * @param id 题目id
         */
        async getData (id) {
            const params = { id: id };
            this.subjectInfo = await this.$api.getSubject(params);
            this.subjectOptions = JSON.parse(this.subjectInfo.options);
            if (!this.subjectInfo.type) {
                const answers = JSON.parse(this.subjectInfo.answers);
                this.subjectInfo.answers = answers.join('');
            } else {
                this.subjectInfo.answers = JSON.parse(this.subjectInfo.answers).join('、');
            }
        }
    }
};
</script>

<style scoped lang="scss">
    .subjectDetail {
        .content {
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
                margin: 20px 0 20px 55px;
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
                margin: 0 0 20px 55px;
            }
            .subject-analysis {
                padding: 19px 13px;
                line-height: 26px;
                background:rgba(246,246,246,1);
                border-radius:4px;
            }
        }
    }
</style>
