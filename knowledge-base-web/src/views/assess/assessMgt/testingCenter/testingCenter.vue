<!--
@file：知识测评-考试中心
@createTime：2020-04-16
@updateTime：2020-04-16
@author：Claire
-->
<template>
    <div class="testingCenter">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div>
                <span>考试状态：</span>
                <el-select v-model="currentStatus" placeholder="请选择" @change="getData">
                    <el-option
                            v-for="item in examOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </div>
            <v-divider></v-divider>
            <div class="exam-list"
                 v-loading="loading"
                 element-loading-text="拼命加载中"
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0, 0, 0, 0.8)">
                <template v-if="Array.isArray(examList) && examList.length > 0">
                    <el-row :gutter="30">
                        <el-col class="mb30" :span="8" v-for="item in examList" :key="item.examId">
                            <el-row class="exam-item" :gutter="20">
                                <el-col :span="16" class="exam-item-info">
                                    <p class="exam-item-info-title" :title="item.examName">{{ item.examName }}</p>
                                    <p class="c_6">
                                        截止时间：
                                        <span :class="item.status || checkTime(item.examEndTime) ? 'c_6': 'c_blue'">
                                        {{ item.examEndTime | date }}
                                    </span>
                                    </p>
                                    <p class="c_6">
                                        已考人数：
                                        <span :class="item.status || checkTime(item.examEndTime) ? 'c_6': 'c_blue'">
                                        {{ item.examedCount ? item.examedCount : 0 }}人
                                    </span>
                                    </p>
                                    <template v-if="item.status">
                                        <el-button class="btn btn_border_gray"
                                                   @click="goDetail('examCompleted', item.examId)">
                                            已考试，看解析 >
                                        </el-button>
                                    </template>
                                    <template v-else>
                                        <el-button class="btn btn_border_gray"
                                                   v-if="checkTime(item.examEndTime)"
                                                   @click="goDetail('examOverdue', item.examId)">
                                            已逾期，看解析 >
                                        </el-button>
                                        <el-button class="btn btn_border_blue" v-else @click="takeExam(item.examId)">
                                            参加考试 >
                                        </el-button>
                                    </template>
                                </el-col>
                                <el-col :span="8">
                                    <img src="~assets/images/exam-picture.png" alt="" width="110" height="110">
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </template>
                <template v-else>
                    <no-data v-show="!loading"></no-data>
                </template>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'testingCenter',
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
                    routeName: 'assessMgtTestingCenter',
                    disabled: true
                }
            ], // 面包屑导航集合
            examOptions: [
                { value: '', label: '全部' },
                { value: 0, label: '未考试' },
                { value: 1, label: '已考试' }
            ],
            currentStatus: '', // 当前考试状态
            examList: [],
            loading: false // 考试列表加载动画
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
            const params = {
                status: this.currentStatus
            };
            this.loading = true;
            this.examList = await this.$api.getAllCurrentUserExam(params);
            this.loading = false;
        },
        /**
         * 检查考试截止时间是否过期
         */
        checkTime (time) {
            const now = new Date();
            let flg = false;
            if (now > time) {
                flg = true;
            }
            return flg;
        },
        /**
         * 参加考试
         * @param id 考试id
         */
        takeExam (id) {
            this.$router.push({
                name: 'assessMgtTakeExam',
                query: { id: id, pageForm: 'testingCenter' }
            });
        },
        /**
         * 查看解析
         * @param type: 'examCompleted'--已考完，看解析；'examOverdue'--已逾期，看解析
         *  * @param id 考试id
         */
        goDetail (type, id) {
            this.$router.push({
                name: 'assessMgtExamDetail',
                query: { id: id, type: type }
            });
        }
    }
};
</script>

<style scoped lang="scss">
.testingCenter {
    .exam-list {
        .exam-item {
            padding: 15px;
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
                .btn_border_gray {
                    width: 116px;
                }
                .btn_border_blue {
                    width: 86px;
                }
            }
        }
    }
}
</style>
