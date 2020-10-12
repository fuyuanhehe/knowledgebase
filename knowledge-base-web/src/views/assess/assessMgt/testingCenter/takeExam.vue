<!--
@file：知识测评-考试中心-参加考试
@createTime：2020-04-20
@updateTime：2020-04-20
@author：Claire
-->
<template>
    <div class="takeExam">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <h3 class="text_c mb50">{{ examInfo.name }}</h3>
            <div class="exam-content">
                <div class="pt45">
                    <h6>考试信息</h6>
                    <el-row class="pl40">
                        <el-col :span="11" class="c_6">考试时间：{{ examInfo.beginTime | date }} — {{ examInfo.endTime | date }}</el-col>
                        <el-col :span="8" class="c_6">答卷时间：{{ examInfo.answerTime }}分钟</el-col>
                        <el-col :span="5" class="c_6">答卷方式：在线</el-col>
                    </el-row>
                </div>
                <div class="pt45">
                    <h6>考生信息</h6>
                    <el-row class="pl40">
                        <el-col :span="11" class="c_6">考试姓名：{{ $store.state.user.nickName }}</el-col>
                        <el-col :span="8" class="c_6">工号：{{ $store.state.user.name }}</el-col>
                        <el-col :span="5" class="c_6">所属部门：{{ $store.state.user.deptName }}</el-col>
                    </el-row>
                </div>
            </div>
            <p class="exam-tip">温馨提示：开始考试后会启动计时器，交卷时间到系统将自动提交分数，请考生检查答题是否完毕。</p>
            <div class="text_c">
                <el-button class="btn_bg_blue" @click="startExam">开始考试</el-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'takeExam',
    data () {
        return {
            breadcrumbNav: [], // 面包屑导航集合
            examId: this.$route.query.id, // 考试id
            examInfo: {}, // 考试信息
            lastPageName: '' // 上一页路由名
        };
    },
    watch: {
        '$route.query.pageForm': {
            handler (val) {
                if (val === 'testingCenter') {
                    this.breadcrumbNav = [
                        {
                            name: '知识测评',
                            path: '/assess',
                            routeName: 'assess'
                        }, {
                            name: '考试中心',
                            path: '/assess/assessMgt/testingCenter',
                            routeName: 'assessMgtTestingCenter'
                        }, {
                            name: '参加考试',
                            path: '/assess/assessMgt/takeExam',
                            routeName: 'assessMgtTakeExam',
                            disabled: true
                        }
                    ];
                    this.lastPageName = 'assessMgtTestingCenter';
                } else if (val === 'home') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '参加考试',
                            path: '/assess/assessMgt/takeExam',
                            routeName: 'assessMgtTakeExam',
                            disabled: true
                        }
                    ];
                    this.lastPageName = 'home';
                } else if (val === 'examMoreList') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '最新考试',
                            path: '/home/examMoreList',
                            routeName: 'examMoreList'
                        }, {
                            name: '参加考试',
                            path: '/assess/assessMgt/takeExam',
                            routeName: 'assessMgtTakeExam',
                            disabled: true
                        }
                    ];
                    this.lastPageName = 'examMoreList';
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
         * 获取考试信息
         */
        async getData () {
            const params = {
                id: this.examId
            };
            this.loading = true;
            this.examInfo = await this.$api.getExam(params);
            this.loading = false;
        },
        /**
         * 开始考试
         */
        startExam () {
            window.open('/#/assess/assessMgt/testing/?id=' + this.examId);
            this.$router.push({
                name: this.lastPageName
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .takeExam {
        .content {
            h3 {
                font-size: $font-size-large;
            }
            .exam-content {
                width:1000px;
                height:290px;
                margin: 0 auto;
                padding: 0 90px;
                box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                border-radius:4px;
                h6 {
                    font-size: $font-size-middle;
                    margin-bottom: 30px;
                }
            }
            .exam-tip {
                width:1000px;
                margin: 20px auto 30px;
                color: $color-danger;
            }
        }
    }
</style>
