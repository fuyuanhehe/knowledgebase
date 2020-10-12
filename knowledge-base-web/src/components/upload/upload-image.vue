<!--
@file：图片上传组件
@time：20200319
@author：Claire
-->
<template>
    <div class="upload-image-wrapper">
        <div
            class="upload-image"
            :style="imageStyle"
            v-loading="loading"
            element-loading-text="图片上传中..."
            element-loading-spinner="el-icon-loading"
            @mouseenter="showMask = true"
            @mouseleave="showMask = false">
            <img v-if="src" class="upload-image-img" :src="`${$img}${src}`" :style="imageStyle">
            <i
                v-if="!src || showMask"
                :class="['el-icon-plus upload-image-icon-plus', { 'mask': src && showMask }]"
                :style="{ ...imageStyle, 'line-height': `${height}px` }">
            </i>
            <upload-input
                class="upload-image-input"
                :title="title"
                :accept="accept"
                :suffix="suffix"
                :width="width"
                :height="height"
                :size="size"
                @uploading="handleUploading"
                @disabled="handeleUploadDisabled"
                @success="handleUploadSuccess"
                @fail="handleUploadFail">
            </upload-input>
        </div>
        <div class="upload-image-hint" v-if="hint">{{ hint }}</div>
    </div>
</template>

<script>
import UploadInput from './upload-input';
import mixin from './mixin';

export default {
    name: 'upload-image',
    components: {
        UploadInput
    },
    props: {
        width: {
            type: Number,
            default: 178
        },
        height: {
            type: Number,
            default: 178
        },
        src: String,

        // 图片大小，单位为 kb
        size: Number,

        // 提示文字
        hint: String
        // 上传接口名
        // interfaceName: {
        //     type: String,
        //     required: true
        // }
    },
    mixins: [mixin],
    data () {
        return {
            loading: false,
            showMask: false,
            file: null,
            accept: 'image/gif, image/jpeg, image/jpg, image/png, image/svg',
            suffix: ['gif', 'jpeg', 'jpg', 'png', 'svg'] // 可上传的图片后缀名
        };
    },
    computed: {
        imageStyle () {
            return {
                width: `${this.width}px`,
                height: `${this.height}px`
            };
        },
        title () {
            return this.file ? '点击更换图片' : '点击上传图片';
        }
    },
    methods: {
        /**
         * @description 处理文件上传中事件
         */
        handleUploading () {
            this.loading = true;
        },
        /**
         * @description 处理禁止上传事件
         */
        handeleUploadDisabled (type) {
            this.loading = false;
            let message = '';
            if (type === 'format') {
                message = `请上传 ${this.suffix.join('、')} 格式的图片`;
            } else if (type === 'size') {
                message = `请上传 ${this.formatSize(this.size)} 以内的图片`;
            }

            this.$message.warning(message);
        },
        /**
         * @description 处理上传成功事件
         * @param {Object} res 后台返回的信息
         * @param {Object} file 文件本身
         */
        handleUploadSuccess (res, file) {
            this.loading = false;
            this.file = file;
            this.$emit('update:src', res.data[0].url);
            this.$emit('success', res, file);
            this.$message.success('图片上传成功！');
        },
        /**
         * @description 处理上传失败事件
         * @param {Object} err 失败信息
         */
        handleUploadFail (err) {
            this.loading = false;
            this.$emit('fail', err);
            this.$message.error('图片上传失败！');
        }
    }
};
</script>

<style lang="scss" scoped>
.upload-image-wrapper {
    overflow: hidden;
    .upload-image {
        overflow: hidden;
        border: 1px dashed #d9d9d9;
        cursor: pointer;
        position: relative;
        &:hover {
            border-color: #409eff;
        }
        .upload-image-img {
            position: absolute;
            z-index: 1;
        }
        .upload-image-icon-plus {
            position: absolute;
            font-size: 28px;
            color: #8c939d;
            text-align: center;
            z-index: 2;
            &.mask {
                background: #000000;
                opacity: 0.4;
            }
        }
        .upload-image-input {
            position: absolute;
            text-align: center;
            z-index: 3;
        }
    }
    .upload-image-hint {
        color: #999999;
        font-size: 12px;
        height: 20px;
        line-height: 20px;
        margin-top: 5px;
    }
}
</style>
