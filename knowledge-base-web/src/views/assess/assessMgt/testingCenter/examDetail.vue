<!--
@file：知识测评-考试中心-考试详情（查看解析）
@createTime：2020-04-20
@updateTime：2020-04-22
@author：Claire
-->
<template>
    <div class="examDetail">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="exam-title">{{ examInfo.name }}</div>
            <div class="exam-content">
                <div class="w65p display_ib va_t">
                    <template v-for="(item,index) in testPaperList">
                        <div class="ml20"  v-if="currentIndex == index">
                            <div class="subject-title">
                                <span class="subject-title-tag mr10">{{ item.type ? '多选': '单选'}}</span>
                                <span>{{index + 1 }}、{{ item.name }}？（{{ item.score }}分）</span>
                            </div>
                            <div class="subject-options">
                                <template  v-if="!item.type">
                                    <el-radio-group class="display_b mb10"
                                                    v-model="item.userChecked[0]"
                                                    v-for="option in JSON.parse(item.options)"
                                                    :key="option.key">
                                        <template v-if="pageType != 'examCompleted'">
                                            <el-radio :label="option.key"></el-radio>
                                            <span class="subject-options-text"
                                                  :class="item.userChecked[0] == option.key ? 'c_blue' : ''">
                                                    、{{ option.value }}
                                            </span>
                                        </template>
                                       <template v-else>
                                           <el-radio :class="item.userChecked[0] == option.key && item.userChecked[0] != JSON.parse(item.answers).join('') ? 'examCompleted-radio' : ''" :label="option.key"></el-radio>
                                           <span class="subject-options-text"
                                                 v-if="item.userChecked[0] != JSON.parse(item.answers).join('')"
                                                 :class="item.userChecked[0] == option.key ? 'c_red' : ''">
                                                   、{{ option.value }}
                                           </span>
                                           <span v-else class="subject-options-text"
                                                 :class="item.userChecked[0] == option.key ? 'c_blue' : ''">、{{ option.value }}</span>
                                       </template>
                                    </el-radio-group>
                                </template>
                                <template v-else>
                                    <el-checkbox-group class="display_b mb10"
                                                       v-model="item.userChecked"
                                                       v-for="option in JSON.parse(item.options)"
                                                       :key="option.key">
                                        <template v-if="pageType != 'examCompleted'">
                                            <el-checkbox :label="option.key" name="answer"></el-checkbox>
                                            <span class="subject-options-text"
                                                  :class="item.userChecked.includes(option.key) ? 'c_blue' : ''">
                                                、{{ option.value }}
                                            </span>
                                        </template>
                                        <template v-else>
                                            <el-checkbox :class="item.userChecked.includes(option.key) && item.userChecked != JSON.parse(item.answers) ? 'examCompleted-checkbox' : ''" :label="option.key"></el-checkbox>
                                            <span class="subject-options-text"
                                                  :class="item.userChecked.includes(option.key) && item.userChecked != JSON.parse(item.answers) ? 'c_red' : ''">
                                            、{{ option.value }}
                                        </span>
                                        </template>
                                    </el-checkbox-group>
                                </template>
                            </div>
                            <div class="subject-answer" v-if="item.type">正确答案：{{ JSON.parse(item.answers).join('、') }}</div>
                            <div class="subject-answer" v-else>正确答案：{{ JSON.parse(item.answers).join('') }}</div>
                            <div class="subject-answer subject-analysis">解析：{{ item.analysis }}</div>
                        </div>
                    </template>
                    <div class="text_c mt110">
                        <el-button class="btn_border_blue"
                                   v-if="currentIndex > 0"
                                   @click="currentIndex--">上一题</el-button>
                        <el-button class="btn_bg_blue"
                                   v-if="currentIndex < testPaperList.length - 1"
                                   @click="currentIndex++">下一题</el-button>
                    </div>
                </div>
                <div class="w30p display_ib va_t">
                    <template v-if="pageType == 'examCompleted'">
                        <p class="exam-score">考试成绩：<span class="c_red">{{ userExamResult.score }}</span></p>
                        <p class="">考试用时：{{ timeFormat(userExamResult.submitPaperTime) }}</p>
                    </template>
                    <div class="exam-card">
                        <p class="c_blue">第{{ currentIndex + 1 }}题/共{{ testPaperList.length }}题</p>
                        <ul class="exam-card-list clearfix">
                            <li class="exam-card-item fl"
                                :class="currentIndex == index ? 'active-item' : ''"
                                v-for="(item,index) in testPaperList" @click="currentIndex = index">
                                {{ index + 1 }}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'examDetail',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '考试中心',
                    path: '/assess/assessMgt/testingCenter',
                    routeName: 'assessMgtTestingCenter'
                }, {
                    name: '考试详情',
                    path: '/assess/assessMgt/examDetail',
                    routeName: 'assessMgtExamDetail',
                    disabled: true
                }
            ], // 面包屑导航集合
            // 页面类型： 'examCompleted'--已考完，看解析；'examOverdue'--已逾期，看解析
            pageType: this.$route.query.type,
            examId: this.$route.query.id, // 考试id
            examInfo: {
                name: '',
                answerTime: ''
            }, // 考试信息
            testPaperList: [],
            currentIndex: 0, // 当前展示题目索引
            answerTime: '', // 答题时间
            userAnswers: [], // 用户选中答案集合
            userExamResult: {} // 用户考试结果
        };
    },
    created () {
        this.getData();
    },
    mounted () {
    },
    methods: {
        /**
         * 获取考试信息
         */
        async getData () {
            // 渲染考试信息
            const params = {
                id: this.examId
            };
            this.loading = true;
            this.examInfo = await this.$api.getExam(params);
            this.answerTime = this.examInfo.answerTime * 60; // 答题时间转换成秒
            this.loading = false;
            // 渲染考试题目数据
            const param2 = {
                id: this.examInfo.testPaperId
            };
            const res = await this.$api.getTestPaper(param2);
            this.testPaperList = JSON.parse(res.subjects);
            for (let i = 0; i < this.testPaperList.length; i++) {
                let checked;
                if (this.testPaperList[i].type) { // 多选
                    checked = [];
                } else { // 单选
                    checked = [{}];
                }
                this.$set(this.testPaperList[i], 'userChecked', checked);
            }
            if (this.pageType == 'examCompleted') {
                // 渲染考试结果数据
                const param3 = {
                    examId: this.examId
                };
                const userExam = await this.$api.getCurrentUserExam(param3);
                this.userExamResult = userExam;
                if (userExam != null && userExam.answers != null) {
                    const answers = JSON.parse(userExam.answers);
                    for (let i = 0; i < this.testPaperList.length; i++) {
                        this.$set(this.testPaperList[i], 'userChecked', answers[i]);
                    }
                }
            }
        },
        /**
         * 时间戳转换时分秒
         * @param time
         * @returns {string}
         */
        timeFormat (time) {
            // 总秒数
            const second = Math.floor(time / 1000);
            // 小时
            const hr = Math.floor(second / 3600 % 24);
            // 分钟
            const min = Math.floor(second / 60 % 60);
            // 秒
            const sec = Math.floor(second % 60);
            return (hr > 0 ? hr + '时' : '') + (min > 0 ? min + '分' : '') + sec + '秒';
        }
    }
};
</script>

<style scoped lang="scss">
    .examDetail {
        .content {
            .exam-title {
                width: 100%;
                height:80px;
                line-height: 80px;
                text-align: center;
                background: $bg-color-1;
                border-radius:4px;
            }
            .exam-content {
                height: 600px;
                margin-top: 20px;
                .w65p {
                    height: 100%;
                    border-right: 1px dashed #ddd;
                    margin-right: 40px;
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
                        .examCompleted-radio {
                            /deep/.el-radio__input.is-checked+.el-radio__label {
                                color: $color-danger;
                            }
                        }
                        .examCompleted-checkbox {
                            /deep/.el-checkbox__input.is-checked+.el-checkbox__label {
                                color: $color-danger;
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
            .w30p {
                .exam-score {
                    margin-bottom: 10px;
                    font-size: $font-size-large;
                }
                .exam-card {
                    width:100%;
                    padding: 20px;
                    margin-top: 20px;
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    border-radius:4px;
                    .exam-card-list {
                        .exam-card-item {
                            display: inline-block;
                            width: 30px;
                            height: 30px;
                            line-height: 30px;
                            text-align: center;
                            margin: 10px;
                            border:1px solid rgba(207,207,207,1);
                            border-radius:4px;
                            color: $color-text-secondary;
                            cursor: pointer;
                        }
                        .active-item {
                            color: $color-text-blue;
                            border-color: $color-text-blue;
                            background: $bg-color-1;
                        }
                    }
                }
            }
        }
    }
</style>
