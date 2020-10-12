<!--
@file：首页-通知公告组件
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="noticeArea">
        <div class="noticeArea-title clearfix">
            <span class="title fl">通知公告</span>
            <el-button class="btn fr" @click="showMore">更多</el-button>
        </div>
        <ul class="noticeArea-list">
            <img src="~assets/images/home-notice-img.png" alt="">
            <li class="noticeArea-list-item"
                v-for="notice in noticeList"
                :key="notice.id"
                @click="goDetails(notice.id)">
                <p>{{ notice.name }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'noticeArea',
    data () {
        return {
          noticeList: [
            /*{
              id: 1,
              name: '关于开展网络培训的通知'
            }, {
              id: 2,
              name: '关于开展网络培训的通知关于开展网网...'
            }, {
              id: 3,
              name: '关于开展网络培训的通知'
            }, {
              id: 4,
              name: '关于开展网络培训的通知关于开展网网...'
            }, {
              id: 5,
              name: '关于开展网络培训的通知'
            }, {
              id: 6,
              name: '关于开展网络培训的通知关于开展网网...'
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
            this.$router.push({ name: 'noticeList' });
        },
        /**
         * 获取通知公告列表数据
         */
        async getData () {
          const params = {pageNum: 0, pageSize: 6};
          this.loading = true;
          const res = await this.$api.getNoticePage(params);
          this.noticeList = res.list;
          this.loading = false;
        },
        /**
         * 查看详情
         * @param id
         */
        goDetails (id) {
            this.$router.push({
                name: 'noticeDetails',
                query: { id: id, pageForm: 'home' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .noticeArea {
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
            padding: 20px 28px 6px;
            background:rgba(255,255,255,1);
            box-shadow:0 1px 24px 0 rgba(0, 0, 0, 0.06);
            &-item {
                cursor: pointer;
                p {
                    @extend %ellipsis;
                    width: 100%;
                    line-height: 35px;
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
                &:hover, &:focus {
                    background: #F7F7F7;
                }
            }
        }
    }
</style>
