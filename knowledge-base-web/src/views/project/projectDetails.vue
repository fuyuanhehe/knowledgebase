<!--
@file：项目协作-项目详情
@createTime：2020-04-23
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectDetails">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <template v-if="pageForm === 'projectList'">
                <ul class="tabs-box clearfix">
                    <li class="tab fl"
                        :class="{ 'tab-active' : activeTab == tab.id }"
                        v-for="tab in tabsList"
                        :key="tab.id"
                        @click="handleTabClick(tab.id)">
                        {{ tab.name }}
                    </li>
                </ul>
                <el-divider></el-divider>
            </template>
            <div class="project-content" v-if="activeTab === 1">
                <div class="project-item">
                    <h3>项目简介</h3>
                    <div class="project-item-content">
                        <p>{{ projectInfo.introduction }}</p>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>项目信息</h3>
                    <div class="project-item-content">
                        <el-row class="mb10">
                            <el-col :span="6"><span>项目名称：{{ projectInfo.name }}</span></el-col>
                            <el-col :span="6"><span>项目类别：{{ projectInfo.type }}</span></el-col>
                            <el-col :span="6"><span>项目创建人：{{ projectInfo.createUserNick }}</span></el-col>
                        </el-row>
                        <el-row class="mb10">
                            <el-col :span="6"><span>计划开始时间：{{ projectInfo.beginDate | date('YYYY-MM-DD') }}</span></el-col>
                            <el-col :span="6"><span>计划截止时间：{{ projectInfo.endDate | date('YYYY-MM-DD') }}</span></el-col>
                            <el-col :span="6"><span>共计：{{ projectInfo.term }}天</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="6"><span>项目总金额：{{ projectInfo.totalMoney }}元</span></el-col>
                        </el-row>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>客户信息</h3>
                    <div class="project-item-content">
                        <el-row class="mb10">
                            <el-col :span="6"><span>客户名称：{{ projectInfo.customer }}</span></el-col>
                            <el-col :span="6"><span>客户对接人：{{ projectInfo.conscientiousUser }}</span></el-col>
                            <el-col :span="6"><span>对接人手机号：{{ projectInfo.phone }}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="6"><span>对接人座机：{{ projectInfo.landline }}</span></el-col>
                            <el-col :span="6"><span>客户地址：{{ projectInfo.customerAddr }}</span></el-col>
                        </el-row>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>项目负责人</h3>
                    <div class="project-item-content">
                        <div class="checkedUser-item">
                            <div class="checkedUser-item-top">
                                <img src="~assets/images/user.png" alt="">
                            </div>
                            <span>{{ projectInfo.chargePerson }}</span>
                        </div>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>项目参与人</h3>
                    <div class="project-item-content">
                        <ul class="checkedUser-list clearfix">
                            <li class="checkedUser-item fl" v-for="user in projectInfo.participatePerson">
                                <div class="checkedUser-item-top">
                                    <img src="~assets/images/user.png" alt="">
                                </div>
                                <span>{{ user.nick_name }}</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>项目抄送人</h3>
                    <div class="project-item-content">
                        <ul class="checkedUser-list clearfix">
                            <li class="checkedUser-item fl" v-for="user in projectInfo.duplicatePerson">
                                <div class="checkedUser-item-top">
                                    <img src="~assets/images/user.png" alt="">
                                </div>
                                <span>{{ user.nick_name }}</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item">
                    <h3>项目相关附件</h3>
                    <div class="project-item-content">
                        <ul class="checkedUser-list clearfix">
                            <li class="checkedUser-item fl c_blue" v-for="file in projectInfo.enclosures">
                                {{ file.name }}
                            </li>
                        </ul>
                    </div>
                </div>
                <v-divider></v-divider>
                <div class="project-item" v-if="pageForm == 'projectApply'">
                    <h3>审核状态：<span class="c_red">{{projectInfo.examineStatus == 0?'通过':projectInfo.examineStatus == 1?'不通过':'未审核'}}</span></h3>
                    <h3>审核意见</h3>
                    <div class="project-item-content">
                        <el-table
                                border
                                :data="tableData"
                                v-loading="loading"
                                element-loading-text="拼命加载中"
                                element-loading-spinner="el-icon-loading"
                                element-loading-background="rgba(0, 0, 0, 0.8)"
                                header-cell-class-name="table-th"
                                style="width: 100%">
                            <el-table-column
                                    align="center"
                                    prop=""
                                    label="审核人">
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    prop=""
                                    label="审核时间">
                                <template slot-scope="scope">
                                    <span>{{ scope.row.createTime | date }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    prop=""
                                    label="审核结果">
                            </el-table-column>
                            <el-table-column
                                    align="center"
                                    prop=""
                                    label="审核意见">
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </div>
            <projectDocument v-if="activeTab === 2"></projectDocument>
            <projectProgress v-if="activeTab === 3"></projectProgress>
            <projectRIsk v-if="activeTab === 4"></projectRIsk>
        </div>
    </div>
</template>

<script>
import projectDocument from './components/projectDocument';
import projectProgress from './components/projectProgress';
import projectRIsk from './components/projectRIsk';
export default {
    name: 'projectDetails',
    components: {
        projectDocument,
        projectProgress,
        projectRIsk
    },
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '项目协作',
                    path: '/project',
                    routeName: 'project'
                }, {
                    name: '项目立项',
                    path: '/project/projectApply',
                    routeName: 'projectApply'
                }, {
                    name: '项目详情',
                    path: '/project/projectDetails',
                    routeName: 'projectDetails',
                    disabled: true
                }
            ], // 面包屑导航集合
            projectId: this.$route.query.id,
            pageForm: this.$route.query.pageForm,
            tabsList: [
                { id: 1, name: '项目基本信息' },
                { id: 2, name: '项目文档' },
                { id: 3, name: '项目进度' },
                { id: 4, name: '项目风险' }
            ], // tab导航集合
            activeTab: 1, // 当前展示tab：1--项目基本信息；2--项目文档；3--项目进度；4--项目风险
            projectInfo: {
                name: '',
                introduction: '',
                type: '',
                createUserNick: '', /*this.$store.state.user.nickName*/ // 创建人昵称
                beginDate: '',
                endDate: '',
                term: '',
                totalMoney: '',
                customer: '', // 客户名称
                conscientiousUser: '', // 客户对接人
                phone: '', // 对接人手机号
                landline: '', // 对接人座机
                customerAddr: '', // 客户地址
                chargePerson: '', // 项目负责人
                participationUsers: [], // 项目参与人
                duplicateUsers: [], // 项目抄送人
                enclosures: '' // 附件
            },
            loading: false, // table是否显示加载动画
            tableData: [] // table数据集合
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取项目信息
         */
        async getData () {
          const this_ = this;
            const params = { id: this.projectId };
            this.projectInfo = await this.$api.getProject(params);

            if (this_.projectInfo.enclosures){
              this_.projectInfo.enclosures = JSON.parse(this_.projectInfo.enclosures);
            }

        },
        /**
         * tab切换
         * @param type: 1--项目基本信息；2--项目文档；3--项目进度；4--项目风险
         */
        handleTabClick (type) {
            this.activeTab = type;
        }
    }
};
</script>

<style scoped lang="scss">
    .projectDetails {
        .content {
            .tabs-box {
                padding: 30px 40px 0;
                .tab {
                    width:96px;
                    margin-right: 50px;
                    height: 40px;
                    line-height: 40px;
                    text-align: center;
                    font-size: $font-size-middle;
                    cursor: pointer;
                }
                .tab-active {
                    color: $color-text-blue;
                    background:rgba(242,249,255,1);
                    border-radius:4px;
                }
            }
            .project-content {
                .project-item {
                    h3 {
                        height:36px;
                        line-height: 36px;
                        text-align: left;
                        margin-bottom: 20px;
                        padding-left: 20px;
                        font-size: $font-size-middle;
                        background:rgba(246,246,246,1);
                        border-radius:4px;
                    }
                    &-content {
                        padding: 0 20px;
                        &>p {
                            line-height: 26px;
                        }
                        .checkedUser-item {
                            margin: 0 30px 20px 0;
                            span {
                                display: inline-block;
                                width: 100%;
                                text-align: center;
                            }
                        }
                        /deep/.table-th {
                            height:44px;
                            line-height: 44px;
                            padding: 0 !important;
                            color: #333;
                            font-weight: 400;
                            background:rgba(246,246,246,1)  !important;
                        }
                    }
                }
            }
        }
    }
</style>
