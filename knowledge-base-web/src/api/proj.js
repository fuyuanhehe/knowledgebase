/**
 * @file 项目协作模块接口配置文件
 * @time 2020-04-23
 * @author Claire
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { proj } = config;

/* ------项目模块------ */
// 查询项目分页
export const getProjectPage = (param) => {
    return axios.post(proj + '/project/getProjectPage', param).then(res => res.data);
};
// 查询所有项目
export const getAllProject = (param) => {
    return axios.post(proj + '/project/getAllProject', param).then(res => res.data);
};
// 查询单个项目
export const getProject = (param) => {
    return axios.post(proj + '/project/getProject', param).then(res => res.data);
};
// 校验项目名
export const checkProjectName = (param) => {
    return axios.post(proj + '/project/checkProjectName', param).then(res => res.data);
};
// 新建项目
export const saveProject = (param) => {
    return axios.post(proj + '/project/saveProject', param).then(res => res.data);
};
// 删除项目
export const removeProject = (param) => {
    return axios.post(proj + '/project/removeProject', param).then(res => res.data);
};
// 修改项目
export const modifyProject = (param) => {
    return axios.post(proj + '/project/modifyProject', param).then(res => res.data);
};

/* ------项目阶段模块------ */
// 新增项目阶段
export const saveProjectStage = (param) => {
    return axios.post(proj + '/projectStage/saveProjectStage', param).then(res => res.data);
};
// 修改项目阶段
export const modifyProjectStage = (param) => {
    return axios.post(proj + '/projectStage/modifyProjectStage', param).then(res => res.data);
};
// 删除项目阶段
export const removeProjectStage = (param) => {
    return axios.post(proj + '/projectStage/removeProjectStage', param).then(res => res.data);
};
// 查询所有项目阶段
export const getAllProjectStage = (param) => {
    return axios.post(proj + '/projectStage/getAllProjectStage', param).then(res => res.data);
};
// 查询单个项目阶段
export const getProjectStage  = (param) => {
    return axios.post(proj + '/projectStage/getProjectStage', param).then(res => res.data);
};

// 项目阶段查重
export const checkProjectStageName  = (param) => {
  return axios.post(proj + '/projectStage/checkProjectStageName', param).then(res => res.data);
};


