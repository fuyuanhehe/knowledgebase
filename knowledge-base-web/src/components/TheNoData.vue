<template>
    <div class="no-data-icon-box" style="line-height: 1.1">
        <v-icon class="iconfont" name="iconwushuju"></v-icon>
        <p >暂无数据</p>
    </div>
</template>

<script>

export default {
    data () {
        return {
        };
    },
    created () { // 在vue初始化时调用
    },
    watch: {},
    methods: {
    }
};
</script>

<style lang="scss" scoped>
    .no-data-icon-box {
        text-align: center;
        color: #999999;
        .iconfont {
            font-size: 150px;
            color: #999999;
        }
    }
</style>
