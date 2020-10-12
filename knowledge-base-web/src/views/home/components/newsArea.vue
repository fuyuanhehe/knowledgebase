<!--
@file：首页-公司新闻组件
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="newsArea">
       <div class="newsArea-title clearfix">
           <span class="title fl">公司新闻</span>
           <el-button class="btn fr" @click="showMore">更多</el-button>
       </div>
        <ul class="newsArea-list">
            <li class="newsArea-list-item"
                v-for="news in newsList"
                :key="news.id" @click="goDetails(news.id)">
                <div class="clearfix mb10">
                    <h3 class="fl">{{ news.name }}</h3>
                    <span class="fr">{{ news.createTime | date}}</span>
                </div>
                <p>{{ news.introduction | filterLabel}}</p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'newsArea',
    data () {
        return {
          newsList: [
            /*{
              id: 1,
              name: '数字办公保障透明管理促进公司发展',
              date: '2020-04-22',
              content: '昨日，北京市出台《关于推进文化创意产业创新发展的意见》（以下简称《意见》），言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑之有范编辑部言之有范编辑部言之有范编辑之有范编辑部言之有范编辑部言之有范编辑之有范编辑部言之有范编辑部言之有范编辑之有范编辑部言之有范编辑部言之有范编辑'
            },
            {
              id: 2,
              name: '数字办公保障透明管理促进公司发展',
              date: '2020-04-22',
              content: '昨日，北京市出台《关于推进文化创意产业创新发展的意见》（以下简称《意见》），言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑...'
            },
            {
              id: 3,
              name: '数字办公保障透明管理促进公司发展',
              date: '2020-04-22',
              content: '昨日，北京市出台《关于推进文化创意产业创新发展的意见》（以下简称《意见》），言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑部言之有范编辑...'
            }*/
          ]
        };
    },
    created () {
      this.getData();
    },
    methods: {
        /**
         * 更多
         */
        showMore () {
            this.$router.push({ name: 'newsList' });
        },
        /**
         * 获取新闻列表数据
         */
        async getData () {
          const params = {pageNum: 0, pageSize: 6};
          this.loading = true;
          const res = await this.$api.getJournalismPage(params);
          this.newsList = res.list;
          this.loading = false;
        },
        /**
         * 查看详情
         * @param id
         */
        goDetails (id) {
            this.$router.push({
                name: 'newsDetails',
                query: { id: id, pageForm: 'home' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .newsArea {
        &-title {
            height: 46px;
            line-height: 46px;
            margin-bottom: 20px;
            .title {
                font-size:26px;
            }
            .btn {
                width:74px;
                height:28px;
                line-height: 28px;
                padding: 0;
                margin-top: 10px;
            }
        }
        &-list {
            width: 100%;
            background:rgba(255,255,255,1);
            box-shadow:0 1px 24px 0 rgba(0, 0, 0, 0.06);
            &-item {
                padding: 20px 30px;
                border-bottom: 1px dashed $color-border-2;
                cursor: pointer;
                &:last-child {
                    border-bottom: none;
                }
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
</style>
