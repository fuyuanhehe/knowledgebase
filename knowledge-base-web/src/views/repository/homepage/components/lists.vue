<template>
    <div class="lists-wrap">
        <div class="lists-title clearfix">
            <h6 class="fl">{{listsName}}</h6>
            <span class="fr cursor_p"
                  v-if="total > 0"
                  @click="showMore">更多>></span>
        </div>
        <ul class="lists-list">
            <template v-if="Array.isArray(listsList) && listsList.length > 0">
                <li class="lists-item clearfix" v-for="item in listsList" @click="goDetail(item.id, item.categoryId)">
                <span class="lists-item-title fl">
                    <i class="display_ib dot-blue"></i>
                    <span class="cursor_p">{{ item.name }}</span>
                </span>
                    <span class="fr">{{ item.createUser }}</span>
                </li>
            </template>
            <template v-else>暂无数据</template>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'lists',
    props: {
        listsName: {
            type: String,
            default: ''
        },
        listsList: {
            type: Array,
            default: () => []
        },
        listsType: {},
        total: {
            default: 0
        }
    },
    methods: {
        showMore () {
            this.$router.push('/repository/ranking/' + this.listsType);
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
.lists-wrap {
    .lists-title {
        h6 {
            margin-bottom: 30px;
            font-weight:400;
            font-size:$font-size-middle;
            color: $color-text-primary;
        }
        span {
            &:hover {
                color: $color-text-blue;
            }
        }
    }
    .lists-list {
        .lists-item {
            width: 100%;
            color: $color-text-regular;
            margin-top: 18px;
            &:first-child {
                margin-top: 0;
            }
            .lists-item-title {
                @extend %ellipsis;
                width: 80%;
            }
        }
    }
}
</style>
