<!--
@file：知识测评-考试中心-考试详情(参加考试)
@createTime：2020-04-26
@updateTime：2020-04-26
@author：Claire
-->
<template>
    <div class="testing">
        <div class="header">
            <img src="~assets/images/logo.png" alt="">
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
                                        <el-radio :label="option.key"></el-radio>
                                        <span class="subject-options-text"
                                              :class="item.userChecked[0] == option.key ? 'c_blue' : ''">、{{ option.value }}</span>
                                    </el-radio-group>
                                </template>
                                <template v-else>
                                    <el-checkbox-group class="display_b mb10"
                                                       v-model="item.userChecked"
                                                       v-for="option in JSON.parse(item.options)"
                                                       :key="option.key">
                                        <el-checkbox :label="option.key"></el-checkbox>
                                        <span class="subject-options-text"
                                              :class="item.userChecked.includes(option.key) ? 'c_blue' : ''">
                                            、{{ option.value }}
                                        </span>
                                    </el-checkbox-group>
                                </template>
                            </div>
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
                    <p class="c_red">剩余时间：{{ showTime }}</p>
                    <div class="exam-card">
                        <p class="c_blue">第{{ currentIndex + 1 }}题/共{{ testPaperList.length }}题</p>
                        <ul class="exam-card-list clearfix">
                            <li class="exam-card-item fl"
                                :class="currentIndex == index ? 'active-item' : ''"
                                v-for="(item,index) in testPaperList" @click="currentIndex = index">
                                {{ index + 1 }}
                            </li>
                        </ul>
                        <el-divider></el-divider>
                        <div class="text_c">
                            <el-button class="btn_bg_blue" @click="submitExam">交卷</el-button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'testing',
    data () {
        return {
            examId: this.$route.query.id, // 考试id
            startTime: null, // 开始考试时间
            examInfo: {
                name: '',
                answerTime: ''
            }, // 考试信息
            testPaperList: [],
            currentIndex: 0, // 当前展示题目索引
            answerTime: '', // 答题时间
            interval: {}, // 定时器
            userAnswers: [], // 用户选中答案集合
            userExamResult: {} // 用户考试结果
        };
    },
    computed: {
        // 用计算属性显示结果
        showTime () {
            const time = this.answerTime;
            // 剩余秒数<=0
            if (time <= 0) {
                return '00:00:00';
                // 结束event
            } else { // 剩余秒数>0
                let hour = Math.floor(time / 3600);
                let min = Math.floor((time % 3600) / 60);
                let sec = Math.floor((time % 3600) % 60);
                hour = hour > 9 ? hour : '0' + hour;
                min = min > 9 ? min : '0' + min;
                sec = sec > 9 ? sec : '0' + sec;
                return hour + ': ' + min + ': ' + sec;
            }
        }
    },
    created () {
        this.startTime = new Date().getTime();
        this.getData();
    },
    mounted () {
        this.interval = setInterval(this.intervalEvent, 1000);
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
        },
        /**
         * 间隔事件: 剩余秒数--, 当剩余秒数为0时,清除间隔事件.
         */
        intervalEvent () {
            if (this.answerTime > 0) {
                this.answerTime--;
            } else {
                clearInterval(this.interval);
            }
        },
        /**
         * 交卷
         */
        submitExam () {
            let isChecked = true;
            this.userAnswers = [];
            for (let i = 0; i < this.testPaperList.length; i++) {
                const checked = this.testPaperList[i].userChecked;
                if (this.testPaperList[i].type) { // 多选
                    if (!Array.isArray(checked) && checked.length <= 0) {
                        isChecked = false;
                    }
                } else { // 单选
                    if (JSON.stringify(checked[0]) == '{}') {
                        isChecked = false;
                    }
                }
                this.userAnswers.push(checked);
            }
            if (!isChecked) {
                this.saveExam('您有题目未完成，确定交卷吗？');
            } else {
                this.saveExam('确定交卷吗？');
            }
        },
        /**
         * 保存考试
         */
        async saveExam (tips) {
            const endTime = new Date().getTime();
            const params = {
                examId: this.examId, // 考试id
                submitPaperTime: endTime - this.startTime, // 完成试卷耗时
                answers: JSON.stringify(this.userAnswers) // 交卷答案（json）
            };
            this.$confirm(tips, '交卷', {
                center: true
            }).then(async () => {
                await this.$api.saveCurrentUserExam(params);
                this.$router.push({
                    name: 'assessMgtExamCompleted',
                    query: { examId: this.examId }
                });
            }).catch(() => {
                this.$message.info('已取消交卷');
            });
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
    },
    // 当离开页面时，清除倒计时
    beforeDestroy () {
        clearInterval(this.interval);
    }
};
</script>

<style scoped lang="scss">
    .testing {
        background: #fff;
        .header {
            height: 70px;
            background: linear-gradient(-90deg, #009BFF, #016BFF);
            box-shadow: 0 7px 8px 0 rgba(1, 139, 254, 0.2);
            img {
                margin: 15px 56px;
            }
        }
        .content {
            width: 1200px;
            margin: 0 auto;
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
