/**
 * @file 知识社区模块接口配置文件
 * @createTime：20200228
 * @updateTime：20200403
 * @author Claire
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { community } = config;

/* -------文档评论模块------- */
// 评论添加
export const saveComment = (param) => {
    return axios.post(community + '/comment/saveComment', param).then(res => res.data);
};
// 评论删除
export const removeComment = (param) => {
    return axios.post(community + '/comment/removeComment', param).then(res => res.data);
};
// 查询单个评论
export const getComment = (param) => {
    return axios.post(community + '/comment/getComment', param).then(res => res.data);
};
// 查询所有评论
export const getAllComment = (param) => {
    return axios.post(community + '/comment/getAllComment', param).then(res => res.data);
};
// 查询评论分页
export const getCommentPage = (param) => {
    return axios.post(community + '/comment/getCommentPage', param).then(res => res.data);
};
// 查询当前用户评论分页
export const getCurrentCommentDocPage = (param) => {
    return axios.post(community + '/comment/getCurrentCommentDocPage', param).then(res => res.data);
};
// 查询评论条数
export const getCommentCount = (param) => {
    return axios.post(community + '/comment/getCommentCount', param).then(res => res.data);
};
/* -------文档收藏模块------- */
// 添加收藏
export const saveCollection = (param) => {
    return axios.post(community + '/collection/saveCollection', param).then(res => res.data);
};
// 删除收藏
export const removeCollection = (param) => {
    return axios.post(community + '/collection/removeCollection', param).then(res => res.data);
};
// 获取当前用户文档收藏
export const getCurrentAllCollection = (param) => {
    return axios.post(community + '/collection/getCurrentAllCollection', param).then(res => res.data);
};
// 获取当前用户文档收藏分页
export const getCurrentCollectionDocPage = (param) => {
    return axios.post(community + '/collection/getCurrentCollectionDocPage', param).then(res => res.data);
};

/* -------圈子模块------- */
// 查询所有圈子
export const getAllCircle = (param) => {
    return axios.post(community + '/circle/getAllCircle', param).then(res => res.data);
};
// 查询圈子分页
export const getCirclePage = (param) => {
    return axios.post(community + '/circle/getCirclePage', param).then(res => res.data);
};
// 查询单个圈子
export const getCircle = (param) => {
    return axios.post(community + '/circle/getCircle', param).then(res => res.data);
};
// 新建圈子
export const saveCircle = (param) => {
    return axios.post(community + '/circle/saveCircle', param).then(res => res.data);
};
// 删除圈子
export const removeCircle = (param) => {
    return axios.post(community + '/circle/removeCircle', param).then(res => res.data);
};
// 批量删除圈子
export const removeBatchCircle = (param) => {
    return axios.post(community + '/circle/removeBatchCircle', param).then(res => res.data);
};
// 修改圈子
export const modifyCircle = (param) => {
    return axios.post(community + '/circle/modifyCircle', param).then(res => res.data);
};
// 校验圈子名
export const checkCircleName = (param) => {
    return axios.post(community + '/circle/checkCircleName', param).then(res => res.data);
};
// 知识社区圈子话题统计
export const getCircleStatistics = (param) => {
    return axios.post(community + '/circle/getCircleStatistics', param).then(res => res.data);
};
/* -------话题模块------- */
// 获取当前圈子下的话题分页
export const getTopicPage = (param) => {
    return axios.post(community + '/topic/getTopicPage', param).then(res => res.data);
};
// 获取所有话题
export const getAllTopic = (param) => {
    return axios.post(community + '/topic/getAllTopic', param).then(res => res.data);
};
// 查询单个话题
export const getTopic = (param) => {
    return axios.post(community + '/topic/getTopic', param).then(res => res.data);
};
// 查询话题评论分页
export const getTopicCommentPage = (param) => {
    return axios.post(community + '/topic/getTopicCommentPage', param).then(res => res.data);
};
// 校验话题名
export const checkTopicName = (param) => {
    return axios.post(community + '/topic/checkTopicName', param).then(res => res.data);
};
// 新建话题
export const saveTopic = (param) => {
    return axios.post(community + '/topic/saveTopic', param).then(res => res.data);
};
// 话题删除
export const removeTopic = (param) => {
    return axios.post(community + '/topic/removeTopic', param).then(res => res.data);
};
// 话题批量删除
export const removeBatchTopic = (param) => {
    return axios.post(community + '/topic/removeBatchTopic', param).then(res => res.data);
};
// 话题修改
export const modifyTopic = (param) => {
    return axios.post(community + '/topic/modifyTopic', param).then(res => res.data);
};
