<!--
@file：知识测评-考试中心-交卷成功
@createTime：2020-04-20
@updateTime：2020-04-22
@author：Claire
-->
<template>
    <div class="examCompleted">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="exam-info">
                <div class="exam-info-title">{{ examInfo.examName }}</div>
                <div class="exam-info-content">
                    <p class="exam-info-score">成绩：{{ examInfo.score }}分</p>
                    <p>满分：{{ examInfo.totalScore }}分</p>
                    <p>用时：{{ timeFormat(examInfo.submitPaperTime) }}</p>
                    <el-button class="btn_bg_blue" @click="goDetail">查看解析</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'examCompleted',
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
                    name: '交卷成功',
                    path: '/assess/assessMgt/examCompleted',
                    routeName: 'assessMgtExamCompleted',
                    disabled: true
                }
            ], // 面包屑导航集合
            examId: this.$route.query.examId, // 考试id
            examInfo: {} // 考试信息
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取考试信息
         */
        async getData () {
            const params = {
                examId: this.examId
            };
            this.loading = true;
            this.examInfo = await this.$api.getCurrentUserExam(params);
            this.loading = false;
        },
        /**
         * 查看解析
         */
        goDetail () {
            this.$router.push({
                name: 'assessMgtExamDetail',
                query: { id: this.examId, type: 'examCompleted' }
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
    }
};
</script>

<style scoped lang="scss">
    .examCompleted {
        .content {
            .exam-info {
                width:500px;
                height:320px;
                margin: 80px auto;
                text-align: center;
                box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                border-radius:4px;
                &-title {
                    height:62px;
                    line-height: 62px;
                    background:rgba(242,249,255,1);
                    border-radius:4px;
                }
                &-content {
                    p {
                        font-size: 16px;
                        margin-bottom: 30px;
                    }
                    .exam-info-score {
                        color: $color-danger;
                        font-size: 30px;
                        margin-top: 20px;
                    }
                }
            }
        }
    }
</style>
