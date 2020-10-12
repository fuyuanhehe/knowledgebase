<template>
    <vue-ueditor-wrap
            @ready="ready"
            class="vue-ueditor-wrap" style="line-height: initial" v-model="ueditorText"
            :config="myConfig"></vue-ueditor-wrap>
</template>

<script>
import VueUeditorWrap from 'vue-ueditor-wrap'; // ES6 Module
export default {
    components: {
        VueUeditorWrap
    },
    props: ['value'],
    data () {
        const self = this;
        return {
            ueditorText: '',
            myConfig: {
                // 编辑器不自动被内容撑高
                autoHeightEnabled: false,
                // 初始容器高度
                initialFrameHeight: 400,
                // 初始容器宽度
                initialFrameWidth: '100%',
                // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
                serverUrl: 'http://35.201.165.105:8000/controller.php',
                // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
                UEDITOR_HOME_URL: '/UEditor/',
                toolbars: [[
                    'fullscreen', 'source', '|', 'undo', 'redo', '|',
                    'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                    'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                    'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                    'directionalityltr', 'directionalityrtl', 'indent', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                    'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                    'simpleupload', 'emotion', 'insertvideo', 'music', 'attachment', 'insertcode', 'pagebreak', 'template', 'background', '|',
                    'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                    'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                    'print', 'preview', 'searchreplace', 'drafts', 'help'
                ]],
                async simpleupload (e, callback) {
                    const file = e.target.files[0];
                    let url = '';
                    await self.$api.upload(file).then(res => {
                        url = res[0].url;
                        console.log('uploadUrl', url);
                    });
                    callback(url);
                },
                imageUrlPrefix: this.$img
            }
        };
    },
    watch: {
        ueditorText (val) {
            this.$emit('input', val);
        },
        value (v) {
            this.ueditorText = v || '';
        }
    },
    created () {
        this.ueditorText = this.value || '';
    },
    mounted () {

    },
    methods: {
        ready () {
            // let dom = document.getElementsByClassName('edui-for-simpleupload')
            // dom[0].onclick = () => {
            //     new this.$Upload(this.fileChange)
            // }
        },
        /**
             * 文件件选择变化
             */
        async fileChange (file) {
            let data = '';
            await this.$api.upload(file).then(res => {
                data = res.url;
            });
            console.log('data:', this.$img + data);
            this.ueditorText += `<img src=${this.$img + data} />`;
        }
    }
};
</script>

<style lang="scss">
    #edui_fixedlayer {
        z-index: 9999 !important;
    }
    .vue-ueditor-wrap {
        line-height: 1;
    }
</style>
