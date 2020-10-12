<template>
    <div class="bannerDetails">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <img class="w100p" :src="`${$img}${bannerInfo.url}`" alt="">
            <div v-html="bannerInfo.reserve2" class="w1200 banner-text"></div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'bannerDetails',
    data () {
        return {
            bannerId: this.$route.params.id,
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                }, {
                    name: '知识仓库首页',
                    path: '/repository/homepage',
                    routeName: 'repositoryHomepage'
                }, {
                    name: 'banner详情',
                    path: '/repository/homepage/bannerDetails/' + this.$route.params.id,
                    routeName: 'bannerDetails'
                }
            ], // 面包屑导航集合
            bannerInfo: {}
        };
    },
    created () {
        this.getBaseDocument();
    },
    methods: {
        getBaseDocument () {
            this.$api.getBaseDocument({ id: this.$route.params.id }).then(res => {
                this.bannerInfo = res;
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .bannerDetails {
        .breadcrumb-box {
            /deep/.breadcrumb {
                margin-top: 0;
                height: 30px;
                line-height: 30px;
            }
        }
        .content {
            padding: 0;
            background: $color-text-white;
            .banner-text {
                line-height: 30px;
            }
        }
    }
</style>
