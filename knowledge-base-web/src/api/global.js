/**
 * @file 全局公共模块接口配置文件
 * @author Claire
 * @time 2020-03-25
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { global } = config;

// 查询文档管理分页
export const getConfig = (param) => {
    return axios.post(global + '/office/getConfig', param).then(res => res.data);
};

// 发送验证码消息
export const sendCodeMsg = (param) => {
  return axios.post(global + '/msg/sendCodeMsg', param).then(res => res.data);
};
