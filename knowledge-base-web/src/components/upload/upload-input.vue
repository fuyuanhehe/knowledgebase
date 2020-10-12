<!--
    模块：上传核心组件
    时间：2019-09-17
    备注：--
-->
<template>
    <input
        class="upload-input"
        ref="file"
        type="file"
        :style="{ 'width': `${width}px`, 'height': `${height}px` }"
        :title="title"
        :accept="accept"
        @change="handleFileChange">
</template>

<script>
export default {
    props: {
        width: {
            type: Number,
            default: 178
        },
        height: {
            type: Number,
            default: 178
        },

        title: String,

        // 接受的文件格式，主要用于在文件选择框时筛选出对应的文件格式
        accept: String,

        // 接受的文件的后缀名，用于文件格式校验
        suffix: Array,

        // 图片大小，单位为 kb
        size: Number,

        // 文件上传前回调函数，返回 false 时表示不上传
        beforeUpload: Function
        // 上传接口名
        // interfaceName: {
        //     type: String,
        //     required: true
        // }
    },
    data () {
        return {
            file: null
        };
    },
    methods: {
        /**
         * @description 处理文件改变事件
         */
        handleFileChange (e) {
            this.file = e.target.files[0];

            if (!this.checkFormat()) {
                this.$emit('disabled', 'format');
                this.clear();
                return;
            }

            if (!this.checkSize()) {
                this.$emit('disabled', 'size');
                this.clear();
                return;
            }

            if (this.beforeUpload && typeof this.beforeUpload === 'function') {
                if (!this.beforeUpload()) {
                    this.$emit('disabled', 'before-upload');
                    this.clear();
                    return;
                }
            }

            this.$emit('uploading');
            this.$emit('change', e);

            this.$api.uploadFile(this.file).then(res => {
                this.$emit('success', res, this.file);
                // this.clear()
            }, err => {
                this.$emit('fail', err);
                this.clear();
            }).catch(err => {
                this.$emit('fail', err);
                this.clear();
            });
        },
        /**
         * @description 清空文件
         */
        clear () {
            if (this.$refs.file) {
                this.$refs.file.value = '';
            }
        },
        /**
         * @description 校验格式
         * @returns {Boolean} true 表示格式正确，false 反之
         */
        checkFormat () {
            if (Array.isArray(this.suffix) && this.suffix.length > 0) {
                const name = this.file.name;
                const index = name.lastIndexOf('.');
                const suffix = name.substr(index + 1);

                return this.suffix.includes(suffix);
            }

            return true;
        },
        /**
         * @description 校验大小，单位 K
         * @returns {Boolean} true 表示格式正确，false 反之
         */
        checkSize () {
            if (this.size) {
                return (this.file.size / 1024) < this.size;
            }

            return true;
        }
    }
};
</script>

<style lang="scss" scoped>
.upload-input {
    opacity: 0;
    cursor: pointer;
    &:hover,&:focus {
        border-color: $color-btn-hover;
    }
}
</style>
