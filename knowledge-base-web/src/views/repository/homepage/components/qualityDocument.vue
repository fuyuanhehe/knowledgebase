<template>
    <div class="qualityDocument-wrap">
        <h6>精品文档</h6>
        <div class="qualityDocument-content">
            <img class="cursor_p" src="~assets/images/arrow-pre.png" alt=""
                 v-if="total > 3"
                 @click="forward">
            <ul class="doc-list display_ib clearfix">
                <li class="doc-item fl" v-for="item in docList" @click="goDetail(item.id, item.categoryId)">
                    <img :src="item.coverUrl" alt="" v-if="item.defaultShow">
                    <img class="cursor_p" :src="`${$img}${item.coverUrl}`" alt="" v-else>
                    <p :title="item.name">{{ item.name }}</p>
                </li>
            </ul>
            <img class="cursor_p" src="~assets/images/arrow-next.png" alt=""
                 v-if="total > 3"
                 @click="backward">
        </div>
    </div>
</template>

<script>
export default {
    name: 'qualityDocument',
    data () {
        return {
            docList: [], // 精品文档展示列表
            search: {
                pageNum: 1,
                pageSize: 3,
                reserve3: '0'
            }, // 数据搜索对象
            total: 0
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取table数据
         * @param pageNum
         * @param pageSize
         * @returns {Promise<void>}
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            if (this.search.pageNum < 4 || this.search.pageNum > 1) {
                const res = await this.$api.getBaseDocumentPage(this.search);
                this.docList = res.list;
                if (this.docList.length === 1) {
                    for (let i = 0; i < 2; i++) {
                        this.docList.push({
                            name: '',
                            coverUrl: 'static/images/repository-quantity-default.png',
                            defaultShow: true
                        });
                    }
                } else if (this.docList.length === 2) {
                    this.docList.push({
                        name: '',
                        coverUrl: 'static/images/repository-quantity-default.png',
                        defaultShow: true
                    });
                }
                this.total = res.total;
            }
        },
        forward () {
            if (this.search.pageNum === 1) {
                return;
            }
            this.search.pageNum = this.search.pageNum - 1;
            this.getData();
        },
        backward () {
            const totalPage = this.total / this.search.pageSize;
            if (this.search.pageNum >= 3 || this.search.pageNum > totalPage) {
                return;
            }
            this.search.pageNum = this.search.pageNum + 1;
            this.getData();
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
        }
    }
};
</script>
<style scoped lang="scss">
.qualityDocument-wrap {
    h6 {
        margin-bottom: 30px;
        font-weight:400;
        font-size:$font-size-middle;
        color: $color-text-primary;
    }
    .qualityDocument-content {
        display: flex;
        align-items: center;
        justify-content: center;
        .doc-list {
            width: 86%;
            .doc-item  {
                width: 33.33333333%;
                padding: 0 10px;
                text-align: center;
                img {
                    width: 100%;
                    height: 140px;
                }
                p {
                    @extend %ellipsis;
                    line-height: 30px;
                }
            }
        }
    }
}
</style>
