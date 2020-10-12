package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.Power;
import com.hg.knowledgebase.module.sys.entity.Role;
import com.hg.knowledgebase.module.sys.entity.VO.RoleVO;

/**
 * 
 * @Description: 角色数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IRoleMapper
{
    /**
     * 
     * @Description: 插入角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertRole(Role role) throws SQLException;

    /**
     * 
     * @Description: 插入批量角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param roles
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchRole(@Param("roles") List<Role> roles) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteRoleById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchRoleById(@Param("ids") List<String> ids) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateRole(Role role) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Role
     * @Version: 1.0.0
     */
    RoleVO selectRoleById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Role
     * @Version: 1.0.0
     */
    Role selectRoleByName(@Param("name") String name) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Role>
     * @Version: 1.0.0
     */
    List<Role> selectRoleList(Role role) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param role
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Role>
     * @Version: 1.0.0
     */
    List<Role> selectBaseRoleList(Role role) throws SQLException;

    //////////////////////////// 角色菜单关系///////////////////////////////////////////
    /**
     * 
     * @Description: 插入角色菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:04:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:04:30
     * @UpdateRemark: 修改内容
     * @param roleId
     * @param menuIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertRoleMenu(@Param("roleId") String roleId,
            @Param("menuIds") Set<String> menuIds) throws SQLException;

    /**
     * 
     * @Description: 根据角色id删除角色菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param roleId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteRoleMenuByRoleId(@Param("roleId") String roleId)
            throws SQLException;

    /**
     * 
     * @Description: 根据角色id查询菜单集合
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午6:18:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午6:18:19
     * @UpdateRemark: 修改内容
     * @param roleId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<Menu> selectMenuListByRoleId(@Param("roleId") String roleId)
            throws SQLException;

    //////////////////////////// 角色权限关系///////////////////////////////////////////
    /**
     * 
     * @Description: 插入角色权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:04:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:04:30
     * @UpdateRemark: 修改内容
     * @param roleId
     * @param powerIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertRolePower(@Param("roleId") String roleId,
            @Param("powerIds") Set<String> powerIds) throws SQLException;

    /**
     * 
     * @Description: 根据角色id删除角色权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param roleId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteRolePowerByRoleId(@Param("roleId") String roleId)
            throws SQLException;

    /**
     * 
     * @Description: 根据角色id查询权限集合
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午6:18:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午6:18:19
     * @UpdateRemark: 修改内容
     * @param roleId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<Power> selectPowerListByRoleId(@Param("roleId") String roleId)
            throws SQLException;
}
