<template>
    <div class="myCollection">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="list-top mb20">
                <!--table页搜索查询-->
                <table-search :isSelectShow="false"
                              :dateLabel="'创建时间'"
                              @search="searchData"></table-search>
            </div>
            <el-divider></el-divider>
            <template v-if="Array.isArray(listData) && listData.length > 0">
                <ul class="list-box"
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)">
                    <li class="list-item" v-for="item in listData" :key="item.id" @click="goDetail(item.id, item.categoryId)">
                        <h6 class="list-item-title">{{ item.name }}</h6>
                        <div class="list-item-info">
                            <span class="list-item-info-item">
                                <v-icon class="list-icon" name="iconquanbufenlei"></v-icon>
                                <span>分类：{{ item.categoryName }}</span>
                            </span>
                            <span class="list-item-info-item">
                                <v-icon class="list-icon" name="icontouxiang"></v-icon>
                                <span>上传人：{{ item.createUserName }}</span>
                            </span>
                            <span class="list-item-info-item">
                                <v-icon class="list-icon" name="iconshijian-tianchong"></v-icon>
                                <span>上传时间：{{ item.createTime | date('YYYY-MM-DD HH:ss') }}</span>
                            </span>
                        </div>
                    </li>
                </ul>
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
        </div>
    </div>
</template>

<script>
export default {
    name: 'myCollection',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                },
                {
                    name: '知识仓库管理',
                    path: '/repository/warehouseMgt',
                    routeName: 'repositoryWarehouseMgt'
                }, {
                    name: '我评论的',
                    path: '/repository/warehouseMgt/myComment',
                    routeName: 'repositoryMyComment'
                }
            ], // 面包屑导航集合
            categoryOptions: [], // 知识分类下拉选项
            defaultCategoryOptions: null, // 知识分类下拉默认选项
            currentCategoryName: '', // 当前列表知识分类名
            search: {
                pageNum: 1,
                pageSize: 10,
                search: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            listData: [], // list数据集合
            total: 0 // 总条目数
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取文档分类列表数据
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getCurrentCollectionDocPage(this.search);
            this.listData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.search = data.msg;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * 跳转到详情
         * @param id 文档id
         * @param categoryId 文档分类id
         */
        goDetail (id, categoryId) {
            this.$router.push({
                path: '/repository/knowledgeClassify/' + categoryId + '/knowledgeDetails',
                query: { id: id }
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
    .myCollection{
        .content {
            .el-divider {
                margin: 0;
            }
            .list-box {
                padding: 0 40px;
                .el-divider {
                    margin: 0;
                }
                .list-item {
                    padding: 20px 0;
                    cursor: pointer;
                    border-bottom: 1px solid $color-border-base;
                    &:hover, &:focus {
                        background: #F7F7F7;
                    }
                    &-title {
                        width: 50%;
                        margin-bottom: 10px;
                        font-size: $font-size-middle;
                        font-weight:400;
                        color:rgba(0,0,51,1);
                        line-height:25px;
                        @extend %ellipsis
                    }
                    &-info {
                        &-item {
                            margin-right: 30px;
                            .list-icon {
                                margin-right: 8px;
                                font-size: 20px;
                            }
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
