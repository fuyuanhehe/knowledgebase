<template>
    <div class="officeEditor">
        <div id="iframeEditor" style="width: 100%;height: 100%"></div>
    </div>
</template>

<script>
export default {
    name: 'officeEditor',
    props: {
        fileUrl: {
            required: true,
            default: ''
        },
        editorMode: {
            type: String,
            default: 'view'
        }
    },
    data () {
        return {
            docEditor: null
        };
    },
    mounted () {
        this.init();
    },
    methods: {
        init () {
            const _this = this;
            const param = {
                fileName: this.fileUrl,
                mode: this.editorMode
            };
            this.$api.getConfig(param).then(result => {
                // console.log('config: ', result);
                const script = document.createElement('script'); // 创建一个script标签
                script.type = 'text/javascript';
                script.src = result.apiUrl;
                document.getElementsByTagName('head')[0].appendChild(script);
                result.events = {
                    onAppReady: this.onAppReady,
                    onDocumentStateChange: this.onDocumentStateChange,
                    onRequestEditRights: this.onRequestEditRights,
                    onError: this.onError,
                    onOutdatedVersion: this.onOutdatedVersion
                };
                setTimeout(function () {
                    _this.docEditor = new DocsAPI.DocEditor('iframeEditor', result);
                    _this.$emit('init', _this.docEditor);
                    // console.log('this.docEditor:', _this.docEditor);
                }, 8000);
            });
        },
        innerAlert (message) {
            // console.log('--innerAlert---');
            // if (console && console.log) {
            //     console.log(message);
            // }
        },
        onAppReady () {
            // console.log('--onAppReady---');
            this.innerAlert('Document editor ready');
        },
        onDocumentStateChange (event) {
            // console.log('--onDocumentStateChange---');
            const title = document.title.replace(/\*$/g, '');
            document.title = title + (event.data ? '*' : '');
        },
        onRequestEditRights () {
            // console.log('--onRequestEditRights---');
            location.href = location.href.replace(RegExp('mode = view\&?', 'i'), '');
        },
        onError (event) {
            // console.log('--onError---');
            /* if (event)
                innerAlert(event.data); */
        },
        onOutdatedVersion (event) {
            // console.log('--onOutdatedVersion---');
            location.reload(true);
        }
    }
};
</script>

<style scoped lang="scss">
    .officeEditor {
        width: 100%;
        height: 100%;
    }
</style>
