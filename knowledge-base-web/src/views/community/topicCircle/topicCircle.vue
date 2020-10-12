<!--
@file：知识社区-圈子
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="topicCircle">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="list-top mb20">
                <el-input class="w260 mr20" v-model="searchMsg" placeholder="请输入关键字" @change="handleSearch"></el-input>
                <el-button class="btn_bg_blue" @click="edit()">创建话题</el-button>
            </div>
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
                        <div class="list-item-info fr w32p mt55">
                            <span class="list-item-info-item">
                                <span>发布人：{{ item.createUserName }}</span>
                            </span>
                            <span class="list-item-info-item">
                                <span>发布时间：{{ item.createTime | date }}</span>
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
    name: 'topicCircle',
    data () {
        return {
            circleId: this.$route.params.id,
            circleName: '',
            circleObj: {}, // 当前圈子对象
            breadcrumbNav: [
                {
                    name: '知识社区',
                    path: '/community',
                    routeName: 'community'
                },
                {
                    name: '',
                    path: '',
                    routeName: 'communityTopicCircleSort'
                }
            ], // 面包屑导航集合
            listData: [],
            search: {
                pageNum: 1,
                pageSize: 10,
                circleId: '',
                name: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            searchMsg: '', // 搜索框内容
            loading: false, // table是否显示加载动画
            total: 0 // 总条目数
        };
    },
    watch: {
        '$route.params.id': {
            handler (val) {
                this.getCircle(val);
                this.search.circleId = val;
                this.getData();
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
         * 获取圈子类别列表数据
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getTopicPage(this.search);
            this.listData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 根据分类id查询分类详情
         */
        async getCircle (id) {
            await this.$api.getCircle({ id: id }).then(res => {
                this.circleName = res.name;
                this.breadcrumbNav[1].name = res.name;
                this.breadcrumbNav[1].path = '/community/topicCircle/' + id;
            });
        },
        /**
         * 搜索框变化时（失去焦点或点击回车）触发
         */
        handleSearch () {
            this.search.name = this.searchMsg;
            this.getData();
        },
        /**
         * 创建话题
         */
        edit () {
            this.$router.push({
                path: '/community/topicEdit/circle/' + this.circleId + '/add',
                query: { circleName: this.circleName }
            });
        },
        /**
         * 跳转到详情
         * @param id 话题id
         */
        goDetail (id) {
            this.$router.push({
                path: '/community/topicCircle/topicDetails/' + this.circleId + '/circle',
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
.topicCircle {
    .content {
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
