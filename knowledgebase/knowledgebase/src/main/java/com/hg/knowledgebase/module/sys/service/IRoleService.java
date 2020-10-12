package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Role;
import com.hg.knowledgebase.module.sys.entity.VO.RoleVO;

/**
 * 
 * @Description: 角色服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IRoleService
{

    /**
     * 
     * @Description: 添加角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addRole(RoleVO roleVO) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeRoleById(String id) throws GlobalException;

    /**
     * 
     * @Description: 修改角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyRole(RoleVO roleVO) throws GlobalException;

    /**
     * 
     * @Description: 修改基础角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBaseRole(Role role) throws GlobalException;

    /**
     * 
     * @Description: 根据id查询角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Role
     * @Version: 1.0.0
     */
    RoleVO findRoleById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Role
     * @Version: 1.0.0
     */
    Role findRoleByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 查询所有角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Role>
     * @Version: 1.0.0
     */
    List<Role> findAllRole(Role role) throws GlobalException;

    /**
     * 
     * @Description: 查询角色分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Role>
     * @Version: 1.0.0
     */
    PageInfo<Role> findRolePage(Role role, Integer pageNum, Integer pageSize)
            throws GlobalException;
}
