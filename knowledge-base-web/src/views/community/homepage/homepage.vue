<template>
    <div class="homepage p40">
        <div class="homepage-top" flex>
            <div class="display_ib w65p mr30 display-banner">
                <img class="w100p" src="~assets/images/community-banner.png" alt="">
            </div>
            <div class="display-piece mr30">
                <img src="~assets/images/community-circle.png" alt="">
                <p class="c_blue">{{ statisticsInfo.circles }}</p>
                <p class="display-piece-title">圈子类别</p>
            </div>
            <div class="display-piece">
                <img src="~assets/images/community-topic.png" alt="">
                <p class="c_blue">{{ statisticsInfo.topic }}</p>
                <p class="display-piece-title">话题</p>
            </div>
        </div>
        <el-divider></el-divider>
        <div class="content">
            <div class="list-top mb20">
                <el-button class="btn_bg_blue" @click="edit()">创建话题</el-button>
            </div>
            <ul class="tabs-box clearfix">
                <li class="tab fl"
                    :class="{ 'tab-active' : activeTab == tab.id }"
                    v-for="tab in tabsList"
                    @click="handleTabClick(tab.id)">
                    {{ tab.name }}
                </li>
            </ul>
            <el-divider></el-divider>
            <ul class="list-box"
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)">
                <template v-if="Array.isArray(listData) && listData.length > 0">
                    <li class="list-item clearfix" v-for="item in listData" :key="item.id" @click="goDetail(item.id)">
                        <div class="list-item-info fl w55p">
                            <h6 class="list-item-info-title">{{ item.name }}</h6>
                            <div class="list-item-info-content" v-html="item.content"></div>
                        </div>
                        <div class="list-item-info fr w35p mt55">
                            <span class="list-item-info-item">
                                <span>发布人：{{ item.createUserName }}</span>
                            </span>
                            <span class="list-item-info-item">
                                <span>发布时间：{{ item.createTime | date('YYYY-MM-DD HH:ss') }}</span>
                            </span>
                        </div>
                    </li>
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
                </template>
                <template v-else>
                    <no-data v-show="!loading"></no-data>
                </template>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'communityHomepage',
    data () {
        return {
            statisticsInfo: {
                circles: 0,
                topic: 0
            }, // 圈子话题数量统计对象
            tabsList: [
                { id: 'newest', name: '最新话题' },
                { id: 'active', name: '活跃话题' }
            ], // tab导航集合
            activeTab: 'newest',
            listData: [],
            search: {
                pageNum: 1,
                pageSize: 5,
                orderBy: ''
            },
            searchMsg: '', // 搜索框内容
            loading: false, // table是否显示加载动画
            total: 0 // 总条目数
        };
    },
    created () {
        this.getCircleStatistics();
        this.getData();
    },
    methods: {
        async getCircleStatistics () {
            this.statisticsInfo = await this.$api.getCircleStatistics();
        },
        /**
         * 获取最新话题列表数据
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.search.orderBy = 'time';
            this.loading = true;
            const res = await this.$api.getTopicPage(this.search);
            this.listData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 获取活跃话题列表数据
         */
        async getTopicCommentPage (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.search.orderBy = 'count';
            this.loading = true;
            const res = await this.$api.getTopicCommentPage(this.search);
            this.listData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 创建话题
         */
        edit () {
            this.$router.push({
                path: '/community/topicEdit/home/null/add'
            });
        },
        /**
         * 话题tab切换
         * @param type: newest--最新话题；active--活跃话题
         */
        handleTabClick (type) {
            this.activeTab = type;
            if (type === 'active') {
                this.getTopicCommentPage();
            } else {
                this.getData();
            }
        },
        /**
         * 跳转到详情
         * @param id 话题id
         */
        goDetail (id) {
            this.$router.push({
                path: '/community/topicCircle/topicDetails/' + this.circleId + '/home',
                query: { id: id, circleName: this.circleName }
            });
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
.homepage {
    .homepage-top {
        .display-banner {
            height: 250px;
            img {
                height: 100%;
            }
        }
        .display-piece {
            display: inline-block;
            width: 15%;
            height: 250px;
            padding-top: 25px;
            text-align: center;
            background:rgba(255,255,255,1);
            box-shadow:0 4px 18px 0 rgba(153,151,151,0.42);
            border-radius:10px;
            .c_blue {
                font-size: 44px;
            }
            &-title {
                font-size: 30px;
            }
        }
    }
    .content {
        .tabs-box {
            margin-left: 40px;
            .tab {
                margin-right: 50px;
                height: 40px;
                line-height: 40px;
                font-size: $font-size-middle;
                cursor: pointer;
            }
            .tab-active {
                border-bottom: 2px solid $color-primary;
            }
        }
        .el-divider {
            margin: 0;
        }
        .list-box {
            padding: 0 40px;
            .list-item {
                padding: 20px 0;
                cursor: pointer;
                border-bottom: 1px dashed $color-border-base;
                &:hover, &:focus {
                    background: #F7F7F7;
                }
                &-info {
                    &-title {
                        width: 50%;
                        margin-bottom: 10px;
                        font-size: $font-size-middle;
                        font-weight:400;
                        color:$color-text-black;
                        line-height:25px;
                        @extend %ellipsis
                    }
                    &-content {
                        width: 100%;
                        text-overflow: -o-ellipsis-lastline;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        display: -webkit-box;
                        -webkit-line-clamp: 2;
                        line-clamp: 2;
                        -webkit-box-orient: vertical;
                        color: $color-text-regular;
                    }
                    &-item {
                        margin-right: 30px;
                        span {
                            color: $color-text-regular;
                        }
                    }
                }
            }
        }
    }
}
</style>
