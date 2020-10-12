/**
 * @file 统计分析模块接口配置文件
 * @author Claire
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { stat } = config;

/* ------员工统计模块------ */
// 查询员工总数
export const getUserCount = (param) => {
    return axios.post(stat + '/user/getUserCount', param).then(res => res.data);
};
// 查询部门总数
export const getDeptCount = (param) => {
    return axios.post(stat + '/user/getDeptCount', param).then(res => res.data);
};
// 查询部门人数统计
export const getDeptStat = (param) => {
    return axios.post(stat + '/user/getDeptStat', param).then(res => res.data);
};
// 查询用户性别统计
export const getSexStat = (param) => {
    return axios.post(stat + '/user/getSexStat', param).then(res => res.data);
};

/* ------测评考试统计模块------ */
// 查询考试总数
export const getExamCount = (param) => {
    return axios.post(stat + '/eval/getExamCount', param).then(res => res.data);
};
// 查询试卷总数
export const getTestPaperCount = (param) => {
    return axios.post(stat + '/eval/getTestPaperCount', param).then(res => res.data);
};
// 查询题库总数
export const getSubjectCount = (param) => {
    return axios.post(stat + '/eval/getSubjectCount', param).then(res => res.data);
};
// 查询发布学习总数
export const getCurriculumCount = (param) => {
    return axios.post(stat + '/eval/getCurriculumCount', param).then(res => res.data);
};
// 查询考试统计
export const getUserExamStat = (param) => {
    return axios.post(stat + '/eval/getUserExamStat', param).then(res => res.data);
};
// 查询题目更新统计
export const getSubjectStat = (param) => {
    return axios.post(stat + '/eval/getSubjectStat', param).then(res => res.data);
};
// 查询题目分类统计
export const getSubjectTypeStat = (param) => {
    return axios.post(stat + '/eval/getSubjectTypeStat', param).then(res => res.data);
};
// 查询用户已经学习（学习人数）统计
export const getUserLearnedStat = (param) => {
    return axios.post(stat + '/eval/getUserLearnedStat', param).then(res => res.data);
};

