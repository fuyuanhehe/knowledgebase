<!--
@file：文件上传组件
@time：20200319
@author：Claire
-->
<template>
    <div class="upload-file-wrapper">
        <div class="upload-file" :style="{ 'width': `${width}px`, 'height': `${height}px` }">
            <el-button
                class="upload-file-btn"
                size="small"
                :icon="icon"
                :loading="loading">
                {{ btnText }}
            </el-button>
            <upload-input
                class="upload-file-input"
                :width="width"
                :height="height"
                :size="size"
                :accept="accept"
                :suffix="suffix"
                :before-upload="handleBeforeUpload"
                @uploading="handleUploading"
                @disabled="handeleUploadDisabled"
                @success="handleUploadSuccess"
                @fail="handleUploadFail">
            </upload-input>
        </div>
        <div class="upload-file-hint" v-if="hint">{{ hint }}</div>
        <div class="upload-file-display" v-if="fileListShow">
            <div
                class="item-file"
                v-for="(item, index) in fileList"
                :key="item.url">
                <i class="el-icon-document file-icon"></i>
                <div class="file-name" :title="item.name" @click.stop="handleFileNameClick(item)">{{ item.name }}</div>
                <i class="el-icon-close file-icon file-icon-delete" @click.stop="handleDeleteFile(index)"></i>
            </div>
        </div>
    </div>
</template>

<script>
import UploadInput from './upload-input';
import mixin from './mixin';

export default {
    name: 'upload-file',
    components: {
        UploadInput
    },
    props: {
        width: {
            type: Number,
            default: 97
        },
        height: {
            type: Number,
            default: 32
        },
        text: {
            type: String,
            default: '上传文件'
        },

        // 最大上传个数，0 表示不限制
        limit: {
            type: Number,
            default: 0
        },
        // 接受的文件格式，主要用于在文件选择框时筛选出对应的文件格式
        accept: String,
        // 接受的文件的后缀名，用于文件格式校验
        suffix: Array,

        // 文件列表
        fileList: {
            type: Array
        },

        // 文件大小，单位为 kb
        size: Number,

        // 提示文字
        hint: String,

        // 按鈕icon
        icon: {
            type: String,
            default: 'el-icon-upload'
        },

        // 是否展示文件上传列表
        fileListShow: {
            type: Boolean,
            default: true
        },

        // 是否显示消息提示
        isMsgShow: {
            type: Boolean,
            default: true
        }
    },
    mixins: [mixin],
    data () {
        return {
            loading: false
        };
    },
    computed: {
        btnText () {
            return this.loading ? '上传中...' : this.text;
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
         * @description 处理上传成功事件
         * @param {Object} res 后台返回的信息
         * @param {Object} file 文件本身
         */
        handleUploadSuccess (res, file) {
            this.loading = false;
            this.$emit('success', res, file);
            this.fileList.push({ name: file.name, url: res.data[0].url });
            this.$emit('update:fileList', this.fileList);
            if (this.isMsgShow) {
                this.$message.success('文件上传成功！');
            }
        },
        /**
         * @description 处理上传失败事件
         * @param {Object} err 失败信息
         */
        handleUploadFail (err) {
            this.loading = false;
            this.$emit('fail', err);
            this.$message.error('文件上传失败！');
        },
        /**
         * @description 删除文件
         */
        handleDeleteFile (index) {
            this.$confirm('确定移除该文件？', '提示', {
                type: 'warning'
            }).then(() => {
                this.fileList.splice(index, 1);
                this.$emit('update:fileList', this.fileList);
            });
        },
        /**
         * @description 文件上传之前的钩子，返回 false 时取消上传
         */
        handleBeforeUpload () {
            if (this.limit > 0 && this.fileList.length >= this.limit) {
                return false;
            }
            return true;
        },
        /**
         * @description 文件名称点击事件
         */
        handleFileNameClick (data) {
            this.$emit('name-click', data);
        },
        /**
         * @description 处理禁止上传事件
         */
        handeleUploadDisabled (type) {
            this.loading = false;
            let message = '';
            if (type === 'size') {
                message = `请上传 ${this.formatSize(this.size)} 以内的文件`;
            } else if (type === 'before-upload') {
                message = `超过最大上传数，最多只能上传 ${this.limit} 个文件`;
            }

            this.$message.warning(message);
        }
    }
};
</script>

<style lang="scss" scoped>
.upload-file-wrapper {
    .upload-file {
        position: relative;
        overflow: hidden;
        .upload-file-btn {
            width: 100%;
            height: 100%;
            position: absolute;
            z-index: 2;
            &:hover,&:focus {
                border-color: $color-btn-hover;
            }
        }
        .upload-file-input {
            position: absolute;
            text-align: center;
            cursor: pointer;
            z-index: 3;
        }
    }
    .upload-file-hint {
        color: #999999;
        font-size: 12px;
        height: 20px;
        line-height: 20px;
        margin-top: 5px;
    }
    .upload-file-display {
        margin-top: 10px;
        .item-file {
            display: flex;
            align-items: center;
            height: 25px;
            cursor: pointer;
            &:hover {
                background: #f5f7fa;
            }
            .file-name {
                flex: 1;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                color: #606266;
                height: 25px;
                line-height: 25px;
                &:hover {
                    color: #409eff;
                }
            }
            .file-icon {
                flex: 0 0 20px;
                font-size: 14px;
                color: #606266;
                &.file-icon-delete {
                    cursor: pointer;
                }
            }
        }
    }
}
</style>
