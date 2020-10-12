<template>
    <div class="banner-wrap">
        <div class="banner">
            <template v-if="Array.isArray(carouselData) && carouselData.length > 0">
                <el-carousel  height="296px">
                    <el-carousel-item v-for="(item, index) in carouselData" :key="index">
                        <img class="cursor_p" :src="`${$img}${item.url}`" alt="" @click="toDetails(item.id)">
                    </el-carousel-item>
                </el-carousel>
            </template>
            <template v-else>
                <img src="~assets/images/banner.png" alt="">
            </template>
        </div>
    </div>
</template>

<script>
export default {
    name: 'banner',
    data () {
        return {
            carouselData: [],
            search: {
                weight: 4,
                pid: 0
            } // 数据查询参数对象
        };
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取table数据
         */
        async getData () {
            this.loading = true;
            this.carouselData = await this.$api.getAllBaseDocument(this.search);
            this.loading = false;
        },
        toDetails (id) {
            this.$router.push({ path: '/repository/homepage/bannerDetails/' + id });
        }
    }
};
</script>

<style scoped lang="scss">
.banner-wrap {
    .banner {
        max-width: 100%;
        margin: 0 auto;
        height: 310px;
        padding: 10px 10px 0 0;
        background:rgba(255,255,255,1);
        box-shadow:0 1px 18px 0 rgba(153,151,151,0.08);
        /deep/.el-carousel__item {
            img {
                width: 100%;
                height: 100%;
            }
        }
    }
}
</style>
