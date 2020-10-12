<template>
    <i class="v-icon">
        <svg class="icon" aria-hidden="true">
            <use :xlink:href="xlinkHref"></use>
        </svg>
    </i>
</template>

<script>
export default {
    name: 'VIcon',
    props: {
        name: {
            type: String,
            required: true
        }
    },
    computed: {
        xlinkHref () {
            return `#${this.name}`;
        }
    }
};
</script>

<style scoped lang="scss">
.v-icon {
    .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
}
</style>
