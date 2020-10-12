/**
 * @file 知识测评模块接口配置文件
 * @author Claire
 * @createTime 2020-03-23
 * @updateTime 2020-04-08
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { assess } = config;

/* -------学习资源（课程）模块------- */
// 查询学习资源（课程）分页
export const getCurriculumPage = (param) => {
    return axios.post(assess + '/curriculum/getCurriculumPage', param).then(res => res.data);
};
// 查询学习资源（课程）详情
export const getCurriculum = (param) => {
    return axios.post(assess + '/curriculum/getCurriculum', param).then(res => res.data);
};
// 资源添加
export const saveCurriculum = (param) => {
    return axios.post(assess + '/curriculum/saveCurriculum', param).then(res => res.data);
};
// 资源单个删除
export const removeCurriculum = (param) => {
    return axios.post(assess + '/curriculum/removeCurriculum', param).then(res => res.data);
};
// 资源批量删除
export const removeBatchCurriculum = (param) => {
    return axios.post(assess + '/curriculum/removeBatchCurriculum', param).then(res => res.data);
};
// 资源编辑
export const modifyCurriculum = (param) => {
    return axios.post(assess + '/curriculum/modifyCurriculum', param).then(res => res.data);
};
// 查询当前用户所有课程
export const getAllCurrentUserCurriculum = (param) => {
    return axios.post(assess + '/curriculum/getAllCurrentUserCurriculum', param).then(res => res.data);
};
// 查询用户课程
export const getUserCurriculum = (param) => {
    return axios.post(assess + '/curriculum/getUserCurriculum', param).then(res => res.data);
};
// 查询当前用户课程
export const getCurrentUserCurriculum = (param) => {
    return axios.post(assess + '/curriculum/getCurrentUserCurriculum', param).then(res => res.data);
};
// 查询当前用户课程分页
export const getCurrentUserCurriculumPage = (param) => {
  return axios.post(assess + '/curriculum/getCurrentUserCurriculumPage', param).then(res => res.data);
};
// 查询当前用户课程
export const modifyUserCurriculum = (param) => {
    return axios.post(assess + '/curriculum/modifyUserCurriculum', param).then(res => res.data);
};

/* -------题库模块------- */
// 查询题库分页
export const getSubjectPage = (param) => {
    return axios.post(assess + '/subject/getSubjectPage', param).then(res => res.data);
};
// 查询所有题库
export const getAllSubject = (param) => {
    return axios.post(assess + '/subject/getAllSubject', param).then(res => res.data);
};
// 查询单个题目
export const getSubject = (param) => {
    return axios.post(assess + '/subject/getSubject', param).then(res => res.data);
};
// 校验题目名
export const checkSubjectName = (param) => {
    return axios.post(assess + '/subject/checkSubjectName', param).then(res => res.data);
};
// 题目单个添加
export const saveSubject = (param) => {
    return axios.post(assess + '/subject/saveSubject', param).then(res => res.data);
};
// 题目批量添加（导入）
export const inputSubject = (param) => {
    return axios.post(assess + '/subject/inputSubject', param).then(res => res.data);
};
// 题目模板导出
export const outputSubjectTemplete = (param) => {
    return axios.post(assess + '/subject/outputSubjectTemplete', param).then(res => res.data);
};
// 题目修改
export const modifySubject = (param) => {
    return axios.post(assess + '/subject/modifySubject', param).then(res => res.data);
};
// 题目单个删除
export const removeSubject = (param) => {
    return axios.post(assess + '/subject/removeSubject', param).then(res => res.data);
};
// 题目批量删除
export const removeBatchSubject = (param) => {
    return axios.post(assess + '/subject/removeBatchSubject', param).then(res => res.data);
};

/* -------试卷模块------- */
// 查询试卷分页
export const getTestPaperPage = (param) => {
    return axios.post(assess + '/testPaper/getTestPaperPage', param).then(res => res.data);
};
// 查询试卷所有
export const getAllTestPaper = (param) => {
    return axios.post(assess + '/testPaper/getAllTestPaper', param).then(res => res.data);
};
// 查询单个试卷
export const getTestPaper = (param) => {
    return axios.post(assess + '/testPaper/getTestPaper', param).then(res => res.data);
};
// 校验试卷名
export const checkTestPaperName = (param) => {
    return axios.post(assess + '/testPaper/checkTestPaperName', param).then(res => res.data);
};
// 试卷导出
export const outputTestPaper = (param) => {
    return axios.get(assess + '/testPaper/outputTestPaper', param).then(res => res.data);
};
// 试卷单个添加
export const saveTestPaper = (param) => {
    return axios.post(assess + '/testPaper/saveTestPaper', param).then(res => res.data);
};
// 试卷单个删除
export const removeTestPaper = (param) => {
    return axios.post(assess + '/testPaper/removeTestPaper', param).then(res => res.data);
};
// 试卷单个删除
export const removeBatchTestPaper = (param) => {
    return axios.post(assess + '/testPaper/removeBatchTestPaper', param).then(res => res.data);
};
// 试卷修改
export const modifyTestPaper = (param) => {
    return axios.post(assess + '/testPaper/modifyTestPaper', param).then(res => res.data);
};

/* -------考试管理模块------- */
// 查询考试管理列表分页
export const getExamPage = (param) => {
    return axios.post(assess + '/exam/getExamPage', param).then(res => res.data);
};
// 查询考试管理列表所有
export const getAllExam = (param) => {
    return axios.post(assess + '/exam/getAllExam', param).then(res => res.data);
};
// 查询单个考试
export const getExam = (param) => {
    return axios.post(assess + '/exam/getExam', param).then(res => res.data);
};
// 校验考试名
export const checkExamName = (param) => {
    return axios.post(assess + '/exam/checkExamName', param).then(res => res.data);
};
// 考试单个添加
export const saveExam = (param) => {
    return axios.post(assess + '/exam/saveExam', param).then(res => res.data);
};
// 考试单个删除
export const removeExam = (param) => {
    return axios.post(assess + '/exam/removeExam', param).then(res => res.data);
};
// 考试批量删除
export const removeBatchExam = (param) => {
    return axios.post(assess + '/exam/removeBatchExam', param).then(res => res.data);
};
// 考试修改
export const modifyExam = (param) => {
    return axios.post(assess + '/exam/modifyExam', param).then(res => res.data);
};
// 查询考试统计信息
export const getExamCountMsg = (param) => {
    return axios.post(assess + '/exam/getExamCountMsg', param).then(res => res.data);
};
// 单个考试参考列表
export const getUserExamPage = (param) => {
    return axios.post(assess + '/exam/getUserExamPage', param).then(res => res.data);
};
// 查询当前用户所有考试
export const getAllCurrentUserExam = (param) => {
    return axios.post(assess + '/exam/getAllCurrentUserExam', param).then(res => res.data);
};
// 查询当前用户考试分页
export const getCurrentUserExamPage = (param) => {
  return axios.post(assess + '/exam/getCurrentUserExamPage', param).then(res => res.data);
};
// 查询用户单个考试
export const getUserExam = (param) => {
    return axios.post(assess + '/exam/getUserExam', param).then(res => res.data);
};
// 查询当前用户单个考试
export const getCurrentUserExam = (param) => {
    return axios.post(assess + '/exam/getCurrentUserExam', param).then(res => res.data);
};
// 添加当前用户单个考试（交卷）
export const saveCurrentUserExam = (param) => {
    return axios.post(assess + '/exam/saveCurrentUserExam', param).then(res => res.data);
};
