import axios from 'axios';
import Vue from 'vue';
import router from '../router';

const baseURL = '';

const service = axios.create({
    baseURL,
    timeout: 88888
});

// axios请求拦截器
service.interceptors.request.use(config => {
    const user = self.$session.get('user');
    // 让每个请求头部都携带token信息
    if (user && user.Authorization) {
        config.headers.Authorization = user.Authorization;
    }
    return config;
}, error => {
    Promise.resolve(error);
});

// axios响应拦截器，统一处理服务器响应和异常
const self = new Vue();
// let timer;
service.interceptors.response.use(
    response => {
        if (response.data && !response.data.success) {
            if (self.$message) {
                self.$message({
                    type: 'error',
                    message: response.data.message
                });
            }
            return Promise.reject(new Error(response.data.message));
        }
        return response.data;
    },
    error => {
        if (error.response) {
            let timer;
            switch (error.response.status) {
            case 401:
                if (timer === 1) return;
                timer = 1;
                self.$confirm('登录超时，是否重新登录?', {
                    center: true
                }).then(() => {
                    timer = null;
                    self.$store.commit('DelToken');
                    router.replace({
                        path: '/',
                        query: {
                            redirect: router.currentRoute.fullPath // 登录之后跳转到对应页面
                        }
                    });
                }).catch(() => {
                    timer = null;
                });
                return;
            case 403: // 权限
                self.$store.commit('DelToken');
                router.replace({
                    path: '/',
                    query: {
                        redirect: router.currentRoute.fullPath // 登录之后跳转到对应页面
                    }
                });
                return;
            }
        }
        if (
            error.response &&
            error.response.data &&
            error.response.data.message
        ) {
            self.$message.error(error.response.data.message);
        } else {
            self.$message.error(
                error.message || '服务器开小差了，请稍后再试'
            );
        }
        return Promise.reject(error);
    }
);

export default service;
