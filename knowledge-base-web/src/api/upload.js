import Vue from 'vue';
import axios from '../utils/axiosService';
import config from './api_conf';
const { global } = config;

export const download = (uri) => {
    return axios.get(global + `/resources/download/${uri}`, null).then(res => res.data);
};

// 压缩
var ys = {
    // 处理图片
    onUpload: function (file) {
        var that = this;
        // 通过canvas压缩图片
        var reader = new FileReader();
        reader.readAsDataURL(file);
        var img = new Image();

        const promise = new Promise((resolve, reject) => {
            reader.onload = function (e) {
                const quality = 0.5; // 图像质量
                const canvas = document.createElement('canvas');
                const drawer = canvas.getContext('2d');

                img.src = this.result;
                img.onload = function () {
                    canvas.width = img.width;
                    canvas.height = img.height;

                    drawer.drawImage(img, 0, 0, canvas.width, canvas.height);
                    img.src = canvas.toDataURL('image/jpeg', quality);
                    resolve(that.dataURItoBlob(canvas.toDataURL('image/jpeg', quality), file));
                };
            };
        });
        return promise.then(async (f) => {
            const param = new FormData();
            param.append('fileNames', f);
            // param.append('name', file.name)
            const data = await axios.post(global + '/resources/upload', param).then(res => res.data);
            return data;
        });
    },
    // base64转文件
    dataURItoBlob: function (dataURI, file) {
        var byteString = atob(dataURI.split(',')[1]);
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        const blob = new Blob([ab], { type: mimeString });
        // let tempFile = new File([blob], file.name);
        return blob;
    }
};

const MAX_SIZE = 2;
export const upload = (file) => {
    const param = new FormData();
    param.append('fileNames', file);
    var size = file.size;
    if (size > MAX_SIZE * 1024 * 1024) {
        return new Promise((resolve, reject) => {
            new Vue().$message.error('图片大小不能超过' + MAX_SIZE + 'M');
            reject({
                sucess: false, message: '图片大小不能超过' + MAX_SIZE + 'M'
            });
        });
    } else {
        return axios.post(global + '/resources/upload', param).then(res => res.data);
        // return ys.onUpload(file)
    }
};

const MAX_SIZE2 = 20;
export const upload2 = (file) => {
    const param = new FormData();
    param.append('fileNames', file);
    return axios.post(global + '/resources/upload', param).then(res => res.data);
    // if (size > MAX_SIZE2 * 1024 * 1024) {
    //     new Vue().$message.error('文件大小不能超过' + MAX_SIZE2 +'M')
    //     return () => {}
    // }
    // let param = new FormData()
    // param.append('file', file)
    // return axios.post(global + '/upload/upload', param).then(res => res.data)
};

/**
 * @description 文件上传
 * @param {Object} file 要上传的文件
 * @param {Object} params 附带的参数
 */
export const uploadFile = (file, params) => {
    const formData = new FormData();

    // 额外参数
    if (params && Object.keys(params).length) {
        for (const key in params) {
            if (params.hasOwnProperty(key) && key !== 'file') {
                formData.append(key, params[key]);
            }
        }
    }

    formData.append('fileNames', file);

    return axios.post(global + '/resources/upload', formData).then(res => res);
};
