/**
 * @file 知识仓库模块接口配置文件
 * @author Claire
 * @time 2020-03-25
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { repo } = config;

/* -------文档管理模块------- */
// 查询文档管理分页
export const getBaseDocumentPage = (param) => {
    return axios.post(repo + '/baseDocument/getBaseDocumentPage', param).then(res => res.data);
};
// 查询所有文档管理
export const getAllBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/getAllBaseDocument', param).then(res => res.data);
};
// 查询单个文档
export const getBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/getBaseDocument', param).then(res => res.data);
};
// 新增文档
export const saveBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/saveBaseDocument', param).then(res => res.data);
};
// 删除文档
export const removeBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/removeBaseDocument', param).then(res => res.data);
};
// 批量删除文档
export const removeBaseDocumentList = (param) => {
    return axios.post(repo + '/baseDocument/removeBaseDocumentList', param).then(res => res.data);
};
// 修改文档
export const modifyBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/modifyBaseDocument', param).then(res => res.data);
};
// 修改多个文档（转存）
export const updMultiBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/updMultiBaseDocument', param).then(res => res.data);
};
// 新增多个文档（上传）
export const addMultiBaseDocument = (param) => {
    return axios.post(repo + '/baseDocument/addMultiBaseDocument', param).then(res => res.data);
};
// 获取当前文档中的所有文件
export const getMultiFile = (param) => {
    return axios.post(repo + '/baseDocument/getMultiFile', param).then(res => res.data);
};
// 获取文档统计数
export const getStatistics = (param) => {
    return axios.post(repo + '/baseDocument/getStatistics', param).then(res => res.data);
};
// 获取文档排行统计
export const getCompareStatistics = (param) => {
    return axios.post(repo + '/baseDocument/getCompareStatistics', param).then(res => res.data);
};

/* ------知识分类管理------ */
// 知识分类管理列表分页
export const getBaseDocumentCategoryPage = (param) => {
    return axios.post(repo + '/baseDocumentCategory/getBaseDocumentCategoryPage', param).then(res => res.data);
};
// 查询所有知识分类管理
export const getAllBaseDocumentCategory = (param) => {
    return axios.post(repo + '/baseDocumentCategory/getAllBaseDocumentCategory', param).then(res => res.data);
};
// 查询单个知识分类
export const getBaseDocumentCategory = (param) => {
    return axios.post(repo + '/baseDocumentCategory/getBaseDocumentCategory', param).then(res => res.data);
};
// 新增知识分类
export const saveBaseDocumentCategory = (param) => {
    return axios.post(repo + '/baseDocumentCategory/saveBaseDocumentCategory', param).then(res => res.data);
};
// 修改知识分类
export const modifyBaseDocumentCategory = (param) => {
    return axios.post(repo + '/baseDocumentCategory/modifyBaseDocumentCategory', param).then(res => res.data);
};
// 删除知识分类
export const removeBaseDocumentCategory = (param) => {
    return axios.post(repo + '/baseDocumentCategory/removeBaseDocumentCategory', param).then(res => res.data);
};

/* -----文档记录（编辑，纠错，下载）------ */
// 添加记录
export const saveBusDocumentRecord = (param) => {
    return axios.post(repo + '/busDocumentRecord/saveBusDocumentRecord ', param).then(res => res.data);
};
// 获取记录分页
export const getAllBusDocumentRecord = (param) => {
    return axios.post(repo + '/busDocumentRecord/getAllBusDocumentRecord  ', param).then(res => res.data);
};
// 记录单个查询
export const getBusDocumentRecord = (param) => {
    return axios.post(repo + '/busDocumentRecord/getBusDocumentRecord  ', param).then(res => res.data);
};


// 根据分类查询文档
export const getNumberOfDocumentsCount = (param) => {
  return axios.post(repo +'/baseDocument/getNumberOfDocumentsCount', param).then(res => res.data);
};

// 根据文档分类 统计下载数量
export const getDownloadCount = (param) => {
  return axios.post(repo + '/baseDocument/getDownloadCount', param).then(res => res.data);
};

// 分时统计文档
export const getStatisticsByTime = (param) => {
  return axios.post(repo +'/baseDocument/getStatisticsByTime', param).then(res => res.data);
};

// 统计增加记录
export const addBaseStatistics = (param) => {
  return axios.post(repo +'/baseDocument/addBaseStatistics', param).then(res => res.data);
};

