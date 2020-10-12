<template>
    <div class="breadcrumb">
        <div class="breadcrumb-items">
            <template v-for="(item,index) in nav">
                <div class="item" @click="onNav(item)">
                    {{item.name}}
                </div>
                <span v-if="index !== nav.length - 1">></span>
            </template>
        </div>
    </div>
</template>

<script>
export default {
    name: 'breadcrumb',
    props: {
        nav: {
            type: Array,
            default: () => []
        }
    },
    data () {
        return {};
    },
    methods: {
        onNav (item) {
            if (item.disabled) {
                return;
            }
            this.$router.push({
                path: item.path
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .breadcrumb {
        padding-bottom: 15px;
        margin-top: -5px;
        .breadcrumb-items {
            display: flex;
            align-items: center;

            .item {
                padding: 0 5px;
                cursor: pointer;
                font-size: $font-size-small;
                color: $color-text-regular;

                &:last-child {
                    color: $color-text-blue;
                }
            }
        }
    }
</style>
