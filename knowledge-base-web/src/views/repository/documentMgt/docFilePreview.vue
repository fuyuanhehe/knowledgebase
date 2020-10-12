<template>
    <div class="docFilePreview">
        <officeEditor :fileUrl="fileUrl" ></officeEditor>
    </div>
</template>

<script>
import officeEditor from 'components/OfficeEditor.vue';
export default {
    name: 'docFilePreview',
    components: {
        officeEditor
    },
    data () {
        return {
            fileUrl: this.$route.query.url
        };
    },
    methods: {}
};
</script>

<style scoped lang="scss">
    .docFilePreview {
        width: 100%;
        height: 800px;
    }
</style>
