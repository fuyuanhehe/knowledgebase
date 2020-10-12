/**
 * @file 流程模块（协同办公）接口配置文件
 * @author Claire
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { base } = config;

// 获取字典
export const getBaseKeyBookList = (param) => {
    return axios.post(base + '/baseKeyBook/list', param).then(res => res.data);
};

// 获取行业类别
export const getCategoryList = (param) => {
    return axios.post(base + '/category/list', param).then(res => res.data);
};
//
// // 获取地区数据
// export const getBaseAreaList = (param) => {
//   return axios.post(base + `/baseArea/list`, param).then(res => res.data)
// }
