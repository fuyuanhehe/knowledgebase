<!--
@file：首页-公司新闻/通知公告更多列表页
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="newsList">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <ul class="news-list">
                <template v-if="Array.isArray(dataList) && dataList.length > 0">
                    <li class="news-list-item"
                        v-for="item in dataList"
                        :key="item.id"
                        @click="goDetails(item.id)">
                        <div class="clearfix mb10">
                            <h3 class="fl">{{ item.name }}</h3>
                            <span class="fr">{{ item.createTime | date}}</span>
                        </div>
                        <p>{{ item.introduction | filterLabel}}</p>
                    </li>
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
    name: 'newsList',
    data () {
        return {
            pageName: this.$route.name,
            breadcrumbNav: [
                {
                    name: '首页',
                    path: '/home',
                    routeName: 'home'
                }, {
                    name: this.$route.name === 'newsList' ? '公司新闻' : '通知公告',
                    path: '/home/newsList',
                    routeName: 'newsList',
                    disabled: true
                }
            ], // 面包屑导航集合
            dataList: [],
            search: {
                pageNum: 1,
                pageSize: 10
            },
            loading: false, // table是否显示加载动画
            total: 0 // 总条目数
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取列表数据
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            if (this.pageName === 'newsList') { // 查询公司新闻列表
              const res = await this.$api.getJournalismPage(this.search);
              this.dataList = res.list;
              this.total = res.total;
            } else { // 查询通知公告列表
               const res = await this.$api.getNoticePage(this.search);
               this.dataList = res.list;
               this.total = res.total;
            }
            this.loading = false;
        },
        /**
         * 查看详情
         * @param id
         */
        goDetails (id) {
            if (this.pageName === 'newsList') {
                this.$router.push({
                    name: 'newsDetails',
                    query: {
                        id: id,
                        pageForm: 'moreList'
                    }
                });
            } else {
                this.$router.push({
                    name: 'noticeDetails',
                    query: {
                        id: id,
                        pageForm: 'moreList'
                    }
                });
            }
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
    .newsList {
        width: 1200px;
        margin: 0 auto;
        .breadcrumb-box {
            background: transparent;
            /deep/.breadcrumb {
                margin: 0;
                padding: 0;
                height: 40px;
                line-height: 40px;
            }
        }
        .content {
            padding: 0;
            background: #fff;
            .news-list {
                width: 100%;
                padding: 20px 30px;
                &-item {
                    padding: 20px 30px;
                    border-bottom: 1px dashed $color-border-2;
                    cursor: pointer;
                    &:hover, &:focus {
                        background: #F7F7F7;
                    }
                    h3 {
                        font-size: $font-size-middle;
                        &:before {
                            content: '';
                            display: inline-block;
                            width: 4px;
                            height: 4px;
                            margin-right: 15px;
                            vertical-align: middle;
                            background: $color-text-blue;
                            border-radius: 50%;
                        }
                    }
                    span {
                        color: $color-text-regular;
                    }
                    p {
                        width: 80%;
                        margin-left: 15px;
                        text-overflow: -o-ellipsis-lastline;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        display: -webkit-box;
                        -webkit-line-clamp: 2;
                        line-clamp: 2;
                        -webkit-box-orient: vertical;
                        color: $color-text-regular;
                        line-height: 26px;
                    }
                }
            }
        }
    }
</style>
