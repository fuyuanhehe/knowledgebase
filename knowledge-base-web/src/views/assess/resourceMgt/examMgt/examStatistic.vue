<!--
@file：知识测评-考试管理-考试统计
@createTime：2020-04-13
@updateTime：2020-04-15
@author：Claire
-->
<template>
    <div class="examStatistic">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="exam-title">{{ examInfo.name }}统计</div>
            <div class="exam-info clearfix">
                <ul class="w50p fl">
                    <li>总题数：{{ examInfo.totalSubjectCount }}道</li>
                    <li>总分：{{ examInfo.sumScore }}分</li>
                    <li>答题时间：{{ examInfo.answerTime }}分钟</li>
                    <li>考试时间：{{ examInfo.beginTime | date }}    ——    {{ examInfo.endTime | date }}</li>
                </ul>
                <ul class="w50p fl">
                    <li>参考人数：{{ examInfo.userCount }}人</li>
                    <li>平均分：{{ examInfo.averageScore }}分</li>
                    <li>最高分：{{ examInfo.maxScore }}分</li>
                    <li>最低分：{{ examInfo.minScore }}分</li>
                </ul>
            </div>
            <!--分割线（虚线）-->
            <v-divider></v-divider>
            <div class="table-box">
                <h5>成绩统计</h5>
                <el-table
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%">
                    <el-table-column
                            align="center"
                            prop="userName"
                            label="姓名">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="deptName"
                            label="所属部门">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="submitTime"
                            label="答题时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.status | date}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="submitPaperTime"
                            label="用时">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="score"
                            label="得分">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="errorNum"
                            label="错题数">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <el-button @click="goDetail(scope.row.id)">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--分页-->
                <div class="pagination-box">
                    <el-pagination
                            layout="prev, pager, next"
                            background
                            prev-text="上一页"
                            next-text="下一页"
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="search.pageNum"
                            :page-size="search.pageSize"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'examStatistic',
    data () {
        return {
            examId: this.$route.query.id, // 考试id
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
                    routeName: 'assessResourceMgtExamStatistic',
                    disabled: true
                }
            ], // 面包屑导航集合
            examInfo: {
                name: '',
                totalSubjectCount: null,
                sumScore: null,
                totalScore: null,
                answerTime: null,
                beginTime: '',
                endTime: '',
                userCount: null,
                maxScore: null,
                minScore: null,
                averageScore: null
            },
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                status: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 0 // 总条目数
        };
    },
    created () {
        this.getExamCountMsg();
        this.getData();
    },
    methods: {
        getExamCountMsg () {
            const params = { id: this.examId };
            this.$api.getExamCountMsg(params).then(res => {
                this.examInfo = res;
            });
        },
        /**
         * 获取table数据
         * @param pageNum
         * @param pageSize
         * @returns {Promise<void>}
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getUserExamPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * pageSize 改变时会触发
         * @param pageSize
         */
        handleSizeChange (pageSize) {
            this.getData(1, pageSize);
        },
        /**
         *  currentPage 改变时会触发
         * @param pageNum
         */
        handleCurrentChange (pageNum) {
            this.getData(pageNum, this.search.pageSize);
        }
    }
};
</script>

<style scoped lang="scss">
    .examStatistic {
        .content {
            .exam-title {
                width:100%;
                height:80px;
                line-height: 80px;
                text-align: center;
                margin-bottom: 20px;
                background: $bg-color-1;
                border-radius:4px;
                font-size: $font-size-large;
            }
            .exam-info {
                width:100%;
                height:208px;
                padding: 22px 60px;
                border:1px solid $color-border-base;
                border-radius:4px;
                ul {
                    li {
                        line-height: 40px;
                    }
                }
            }
            .table-box {
                h5 {
                    margin-bottom: 20px;
                    font-size: $font-size-large;
                    font-weight: 400;
                }
            }
        }
    }
</style>
