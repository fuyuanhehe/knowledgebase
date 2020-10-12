<template>
    <div class="dataSummary-wrap">
        <ul class="data-list clearfix">
            <li class="data-item text_c">
                <p class="data-item-val">{{ statisticsInfo.companyDocuments }}</p>
                <p class="data-item-title">公司文档数</p>
            </li>
            <li class="data-item text_c">
                <p class="data-item-val">{{ statisticsInfo.departmentalDocuments }}</p>
                <p class="data-item-title">部门文档数</p>
            </li>
            <li class="data-item text_c">
                <p class="data-item-val">{{ statisticsInfo.personalDocuments }}</p>
                <p class="data-item-title">个人文档数</p>
            </li>
            <li class="data-item text_c">
                <p class="data-item-val">{{ statisticsInfo.knowledgeDocuments }}</p>
                <p class="data-item-title">知识分类数</p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'dataSummary',
    data () {
        return {
            statisticsInfo: {}
        };
    },
    created () {
        this.getStatistics();
    },
    methods: {
        getStatistics () {
            this.$api.getStatistics().then(res => {
                this.statisticsInfo = res;
            });
        }
    }
};
</script>

<style scoped lang="scss">
.dataSummary-wrap {
    .data-list {
        .data-item {
            width: 50%;
            height: 160px;
            float: left;
            padding-top: 36px;
            color: $color-text-white;
            &:nth-child(1) {
                background: url("~assets/images/data-bg1.png") no-repeat center;
                background-size: cover;
                /*background-position: center;*/
            }
            &:nth-child(2) {
                background: url("~assets/images/data-bg2.png") no-repeat;
                background-size: cover;
            }
            &:nth-child(3) {
                background: url("~assets/images/data-bg3.png") no-repeat;
                background-size: cover;
            }
            &:nth-child(4) {
                background: url("~assets/images/data-bg4.png") no-repeat;
                background-size: cover;
            }
            &-val {
                font-size: 28px;
                line-height: 50px;
            }
            &-title {
                font-size: 20px;
                line-height: 30px;
            }
        }
    }
}
</style>
