<template>
    <div class="docFileEdit">
        <officeEditor :fileUrl="fileUrl" editorMode="edit"></officeEditor>
    </div>
</template>

<script>
import officeEditor from 'components/OfficeEditor.vue';
export default {
    name: 'docFileEdit',
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
    .docFileEdit {
        width: 100%;
        height: 800px;
    }
</style>
