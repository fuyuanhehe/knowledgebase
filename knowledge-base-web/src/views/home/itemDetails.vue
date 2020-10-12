<!--
@file：首页-通知公告/公司新闻详情
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="itemDetails">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <h1>{{ dataInfo.name }}</h1>
            <span>发布于{{ dataInfo.createTime | date}}</span>
            <p v-html="dataInfo.introduction"></p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'itemDetails',
    data () {
        return {
          breadcrumbNav: [
                {
                    name: '首页',
                    path: '/home',
                    routeName: 'home'
                }
            ], // 面包屑导航集合
            dataInfo: {
                /*name: '重庆再添艺术新阵地',
                date: '2019-10-12 09:40',
                content:
                  '                                      瑞士的高空网状隧道、巴西的悬崖瀑布、美国加州的崖壁天梯……这些闻名远近的景点，一看都是以惊险著称。近日，世界九大最惊险的高空项目榜单出炉，中国有两个项目入围，其中，重庆的一个景点也榜上有名。\n' +
                  '      中国上榜的两个项目：一个是位于重庆万盛的奥陶纪景区，上榜的项目叫做“步步惊心”——一条长约15米，建在悬崖外的镂空平台，四周无护栏，游客要体验，只吊着保险绳走过去；另一个入选的项目，是有着七百多年历史的华山长空栈道——离地1524米，系万仞绝壁上镶嵌石钉搭木椽而筑，被喻为“华山第一天险”。\n' +
                  '      实际上，得知这个榜单出炉消息的万盛奥陶纪景区负责人刘洋昨天说，“步步惊心”这个项目在奥陶纪景区，惊险刺激指数顶多只能排第三。排在第二的，是“步步惊心”的升级版——极限飞跃，说直接点，就是镂空平台之间的宽度，是“步步惊心”的两倍，必须用“飞跃”这种方式才能迈向下一块踏板。\n' +
                  '惊险刺激度排第一的，是8米悬崖秋千——秋千建在悬崖边，被机械拉到半空中，然后一放挂钩，人像钟摆一样飞了出去。当然，要体验这些高空项目，首先要衡量自己的身体状况，高血压、心脏病等相关疾病患者，几乎不适合该类项目。 \n' +
                  '      曾经挑战过“步步惊心”的小罗说，其实“步步惊心”和很多拓展训练当中的高空项目很类似，只是更加惊险。“当站在平台一端时，一定要给自己积极的鼓励与暗示，减轻高度产生的心理负担。去做自己不敢做的事情叫做挑战，‘步步惊心’就是这样的挑战。”\n' +
                  '                            '
            */
            }
        };
    },
    watch: {
        '$route.query.pageForm': {
            handler (val) {
                if (val === 'home') {
                    this.breadcrumbNav[1] = {
                        name: this.$route.name === 'newsDetails' ? '公司新闻详情' : '通知公告详情',
                        path: this.$route.name === 'newsDetails' ? '/home/newsDetails' : '/home/noticeDetails',
                        routeName: this.$route.name === 'newsDetails' ? 'newsDetails' : 'noticeDetails',
                        disabled: true
                    };
                } else {
                    this.breadcrumbNav[1] = {
                        name: this.$route.name === 'newsDetails' ? '公司新闻' : '通知公告',
                        path: this.$route.name === 'newsDetails' ? '/home/newsList' : '/home/noticeList',
                        routeName: this.$route.name === 'newsDetails' ? 'newsList' : 'noticeList'
                    };
                    this.breadcrumbNav[2] = {
                        name: this.$route.name === 'newsDetails' ? '公司新闻详情' : '通知公告详情',
                        path: this.$route.name === 'newsDetails' ? '/home/newsDetails' : '/home/noticeDetails',
                        routeName: this.$route.name === 'newsDetails' ? 'newsDetails' : 'noticeDetails',
                        disabled: true
                    };
                }
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
       * 获取数据
       */
      async getData () {
        let id = this.$route.query.id; // 当前id
        if (this.$route.name === "newsDetails") { //新闻详情
          this.dataInfo = await this.$api.getJournalism({id: id});
        } else if (this.$route.name === "noticeDetails") { //通知公告详情
          this.dataInfo = await this.$api.getNotice({id: id});
        }
      },
    }
};
</script>

<style scoped lang="scss">
    .itemDetails {
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
            background: #fff;
            h1 {
                text-align: center;
                margin-bottom: 20px;
                font-size:25px;
            }
            span {
                display: inline-block;
                width: 100%;
                text-align: center;
                margin-bottom: 50px;
                font-size:12px;
                font-weight:400;
            }
            p {
                line-height: 26px;
            }
        }
    }
</style>
