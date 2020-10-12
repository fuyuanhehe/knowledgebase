/**
 * @file 系统设置模块接口配置文件
 * * @time 2020-03-10
 * @author Claire
 */
import axios from '../utils/axiosService.js';
import config from './api_conf';
const { sys } = config;

/* ------菜单模块------ */
// 查询菜单树
export const getMenuTree = (param) => {
    return axios.post(sys + '/menu/getMenuTree', param).then(res => res.data);
};
// 查询当前用户菜单树（动态）
export const getCurrentMenuTree = (param) => {
    return axios.post(sys + '/menu/getCurrentMenuTree', param).then(res => res.data);
};
// 查询当前用户所有菜单（静态）
export const getAllCurrentMenu = (param) => {
    return axios.post(sys + '/menu/getAllCurrentMenu', param).then(res => res.data);
};

/* ------角色模块------ */
// 分页查询角色列表
export const getRolePage = (param) => {
    return axios.post(sys + '/role/getRolePage', param).then(res => res.data);
};
// 查询单个角色
export const getRole = (param) => {
    return axios.post(sys + '/role/getRole', param).then(res => res.data);
};
// 查询所有角色
export const getAllRole = (param) => {
    return axios.post(sys + '/role/getAllRole', param).then(res => res.data);
};
// 判断角色名唯一
export const checkRoleName = (param) => {
    return axios.post(sys + '/role/checkRoleName', param).then(res => res.data);
};
// 新增角色
export const saveRole = (param) => {
    return axios.post(sys + '/role/saveRole', param).then(res => res.data);
};
// 修改角色
export const modifyRole = (param) => {
    return axios.post(sys + '/role/modifyRole', param).then(res => res.data);
};
// 修改角色状态
export const modifyRoleStatus = (param) => {
    return axios.post(sys + '/role/modifyRoleStatus', param).then(res => res.data);
};
// 删除角色
export const removeRole = (param) => {
    return axios.post(sys + '/role/removeRole', param).then(res => res.data);
};

/* ------用户模块------ */
// 查询所有用户
export const getAllUser = (param) => {
    return axios.post(sys + '/user/getAllUser', param).then(res => res.data);
};
// 分页查询用户
export const getUserPage = (param) => {
    return axios.post(sys + '/user/getUserPage', param).then(res => res.data);
};
// 查询单个用户
export const getUser = (param) => {
    return axios.post(sys + '/user/getUser', param).then(res => res.data);
};
// 判断用户名唯一
export const checkUserName = (param) => {
    return axios.post(sys + '/user/checkUserName', param).then(res => res.data);
};
// 新增用户
export const saveUser = (param) => {
    return axios.post(sys + '/user/saveUser', param).then(res => res.data);
};
// 修改用户
export const modifyUser = (param) => {
    return axios.post(sys + '/user/modifyUser', param).then(res => res.data);
};
// 修改用户状态
export const modifyUserStatus = (param) => {
    return axios.post(sys + '/user/modifyUserStatus', param).then(res => res.data);
};
// 修改用户状态
export const resetPwd = (param) => {
    return axios.post(sys + '/user/resetPwd', param).then(res => res.data);
};
// 删除用户
export const removeUser = (param) => {
    return axios.post(sys + '/user/removeUser', param).then(res => res.data);
};
// 批量删除用户
export const removeBatchUser = (param) => {
    return axios.post(sys + '/user/removeBatchUser', param).then(res => res.data);
};
// 导入用户（批量添加用户）
export const importUser = (param) => {
    return axios.post(sys + '/user/inputUser', param).then(res => res.data);
};
// 导入用户下载模板
export const outputTemplete = (param) => {
    return axios.get(sys + '/user/outputTemplete', param).then(res => res);
};
// 导入用户下载模板(二进制）
export const getUserTempleteByte = (param) => {
    return axios.get(sys + '/user/getUserTempleteByte', param).then(res => res.data);
};
// 导出用户
export const outputUser = (param) => {
    return axios.get(sys + '/user/outputUser', param).then(res => res.data);
};
// 导出用户(二进制）
export const getUserByte = (param) => {
    return axios.get(sys + '/user/getUserByte', param).then(res => res.data);
};
// 用户登录
export const login = (param) => {
    return axios.post(sys + '/user/login', param).then(res => res);
};
// 用户修改密码
export const modifyPassword = (param) => {
  return axios.post(sys + '/user/modifyPassword', param).then(res => res);
};

/* ------部门（组织）模块------ */
// 查询所有部门
export const getAllDept = (param) => {
    return axios.post(sys + '/dept/getAllDept', param).then(res => res.data);
};
// 查询部门树
export const getDeptTree = (param) => {
    return axios.post(sys + '/dept/getDeptTree', param).then(res => res.data);
};
// 查询单个部门
export const getDept = (param) => {
    return axios.post(sys + '/dept/getDept', param).then(res => res.data);
};
// 部门单个添加
export const saveDept = (param) => {
    return axios.post(sys + '/dept/saveDept', param).then(res => res.data);
};
// 部门单个删除
export const removeDept = (param) => {
    return axios.post(sys + '/dept/removeDept', param).then(res => res.data);
};
// 部门单个删除
export const modifyDept = (param) => {
    return axios.post(sys + '/dept/modifyDept', param).then(res => res.data);
};
/* ------日志模块------ */
// 操作日志列表分页
export const querySystemLog = (param) => {
    return axios.post(sys + '/log/querySystemLog', param).then(res => res.data);
};
/* ------字典模块------ */
// 查询所有字典
export const getAllDict = (param) => {
    return axios.post(sys + '/dict/getAllDict', param).then(res => res.data);
};
/* ------新闻模块------ */
// 分页查询新闻列表
export const getJournalismPage = (param) => {
  return axios.post(sys + '/journalism/getJournalismPage', param).then(res => res.data);
};
// 查询单个新闻
export const getJournalism = (param) => {
  return axios.post(sys + '/journalism/getJournalism', param).then(res => res.data);
};
// 判断新闻名唯一
export const checkJournalismName = (param) => {
  return axios.post(sys + '/journalism/checkJournalismName', param).then(res => res.data);
};
// 新增新闻
export const saveJournalism = (param) => {
  return axios.post(sys + '/journalism/saveJournalism', param).then(res => res.data);
};
// 修改新闻
export const modifyJournalism = (param) => {
  return axios.post(sys + '/journalism/modifyJournalism', param).then(res => res.data);
};
// 删除新闻
export const removeJournalism = (param) => {
  return axios.post(sys + '/journalism/removeJournalism', param).then(res => res.data);
};
/* ------通知公告模块------ */
// 分页查询通知公告列表
export const getNoticePage = (param) => {
  return axios.post(sys + '/notice/getNoticePage', param).then(res => res.data);
};
// 查询单个通知公告
export const getNotice = (param) => {
  return axios.post(sys + '/notice/getNotice', param).then(res => res.data);
};
// 判断通知公告名唯一
export const checkNoticeName = (param) => {
  return axios.post(sys + '/notice/checkNoticeName', param).then(res => res.data);
};
// 新增通知公告
export const saveNotice = (param) => {
  return axios.post(sys + '/notice/saveNotice', param).then(res => res.data);
};
// 修改通知公告
export const modifyNotice = (param) => {
  return axios.post(sys + '/notice/modifyNotice', param).then(res => res.data);
};
// 删除通知公告
export const removeNotice = (param) => {
  return axios.post(sys + '/notice/removeNotice', param).then(res => res.data);
};
