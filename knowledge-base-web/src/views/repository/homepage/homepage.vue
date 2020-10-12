<template>
    <div class="repositoryHomepage w100p">
        <div class="home-top" flex>
            <!--banner图-->
            <banner class="display_ib w68p"/>
            <!--数据汇总-->
            <data-summary class="display_ib w32p"/>
        </div>
        <div class="home-middle mt50" flex>
            <!--精品文档展示-->
            <qualityDocument class="display_ib w68p"/>
            <!--知识排行列表展示-->
            <lists class="display_ib home-lists"
                   :listsName="'知识排行'"
                   :listsType="3"
                   :total="knowledgeRankingTotal"
                   :listsList="knowledgeRankingLists"/>
        </div>
        <div class="home-bottom mt50">
            <!--最新知识列表展示-->
            <lists class="display_ib home-lists"
                   :listsName="'最新知识'"
                   :listsType="0"
                   :total="newestKnowledgeTotal"
                   :listsList="newestKnowledgeLists"/>
            <!--评论最多列表展示-->
            <lists class="display_ib home-lists"
                   :listsName="'评论最多'"
                   :listsType="1"
                   :total="commentMostTotal"
                   :listsList="commentMostLists"/>
            <!--下载最多列表展示-->
            <lists class="display_ib home-lists"
                   :listsName="'下载最多'"
                   :listsType="2"
                   :total="downloadMostTotal"
                   :listsList="downloadMostLists"/>
        </div>
    </div>
</template>

<script>
import banner from './components/banner';
import dataSummary from './components/dataSummary';
import qualityDocument from './components/qualityDocument';
import lists from './components/lists';
export default {
    name: 'repositoryHomepage',
    components: {
        banner,
        dataSummary,
        qualityDocument,
        lists
    },
    data () {
        return {
            knowledgeRankingLists: [
                {
                    title: '销售知识排行销售知识排行销售知识排行排行排行排行排行排行排行排行排行',
                    author: '张三'
                }, {
                    title: '销售知识排行销售知识排行销售知识排行排行',
                    author: '张三'
                }, {
                    title: '销售知识排行销售知识排行销售知识排行排行',
                    author: '张三'
                }, {
                    title: '销售知识排行销售知识排行销售知识排行排行',
                    author: '张三'
                }, {
                    title: '销售知识排行销售知识排行销售知识排行排行',
                    author: '张三'
                }
                // , {
                //     title: '销售知识排行销售知识排行销售知识排行排行',
                //     author: '张三'
                // }
            ],
            newestKnowledgeLists: [],
            commentMostLists: [],
            downloadMostLists: [],
            knowledgeRankingTotal: 0,
            newestKnowledgeTotal: 0,
            commentMostTotal: 0,
            downloadMostTotal: 0,
            search: {
                pageNum: 1,
                pageSize: 5,
                weight: 3,
                number: 0
            }
        };
    },
    created () {
        this.getKnowledgeRankingLists();
        this.getNewestKnowledgeLists();
        this.getCommentMostLists();
        this.getDownloadMostLists();
    },
    methods: {
        async getKnowledgeRankingLists () {
            this.search.number = 3;
            await this.$api.getCompareStatistics(this.search).then(res => {
                this.knowledgeRankingLists = res.list;
                this.knowledgeRankingTotal = res.total;
            });
        },
        async getNewestKnowledgeLists () {
            await this.$api.getCompareStatistics(this.search).then(res => {
                this.newestKnowledgeLists = res.list;
                this.newestKnowledgeTotal = res.total;
            });
        },
        async getCommentMostLists () {
            this.search.number = 1;
            await this.$api.getCompareStatistics(this.search).then(res => {
                this.commentMostLists = res.list;
                this.commentMostTotal = res.total;
            });
        },
        async getDownloadMostLists () {
            this.search.number = 2;
            await this.$api.getCompareStatistics(this.search).then(res => {
                this.downloadMostLists = res.list;
                this.downloadMostTotal = res.total;
            });
        }
    }
};
</script>

<style scoped lang="scss">
.repositoryHomepage {
    padding: 20px 20px 40px 30px;
    .home-middle {
        .home-lists {
            width: 29% !important;
            margin-left: 25px;
        }
    }
    .home-bottom {
        .home-lists {
            width: 29% !important;
            margin-right: 74px;
            &:last-child {
                margin-right: 0;
            }
        }
    }
}
</style>
