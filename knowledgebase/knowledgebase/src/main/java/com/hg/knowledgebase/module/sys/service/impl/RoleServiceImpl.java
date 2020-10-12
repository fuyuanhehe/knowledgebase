package com.hg.knowledgebase.module.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Role;
import com.hg.knowledgebase.module.sys.entity.VO.RoleVO;
import com.hg.knowledgebase.module.sys.mapper.IRoleMapper;
import com.hg.knowledgebase.module.sys.service.IRoleService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 角色业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class RoleServiceImpl implements IRoleService
{

    @Autowired
    private IRoleMapper roleMapper;

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
    @Override
    public boolean addRole(RoleVO roleVO) throws GlobalException
    {
        if (roleVO == null)
        {
            throw new GlobalException("添加角色信息不能为空，入参【角色信息：{}】", roleVO);
        }
        // 执行条数
        int exec = 0;
        String roleId = StringUtil.getUUID();
        roleVO.setId(roleId);
        roleVO.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = roleMapper.insertRole(roleVO);
            // 添加角色菜单
            Set<String> menuIdSet = roleVO.getMenuIds();
            // 如果有菜单数据，则添加角色菜单关系数据
            if (menuIdSet != null && menuIdSet.size() > 0)
            {
                roleMapper.insertRoleMenu(roleId, menuIdSet);
            }
            // 添加角色权限
            Set<String> powerIdSet = roleVO.getPowerIds();
            // 如果有权限数据，则添加角色权限关系数据
            if (powerIdSet != null && powerIdSet.size() > 0)
            {
                roleMapper.insertRolePower(roleId, powerIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("添加角色SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean removeRoleById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除角色信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = roleMapper.deleteRoleById(id);
            // 删除角色菜单关系
            roleMapper.deleteRoleMenuByRoleId(id);
            // 删除角色权限关系
            roleMapper.deleteRolePowerByRoleId(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除角色SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean modifyRole(RoleVO roleVO) throws GlobalException
    {
        String roleId = roleVO.getId();
        if (roleVO == null || StringUtils.isEmpty(roleId))
        {
            throw new GlobalException("修改角色信息主键不能为空，入参【角色信息：{}】", roleVO);
        }
        // 执行条数
        int exec = 0;
        roleVO.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        roleVO.setUpdateTime(new Date());
        try
        {
            exec = roleMapper.updateRole(roleVO);
            // 删除角色菜单
            roleMapper.deleteRoleMenuByRoleId(roleId);
            // 添加角色菜单
            Set<String> menuIdSet = roleVO.getMenuIds();
            // 如果有菜单数据，则添加角色菜单关系
            if (menuIdSet != null && menuIdSet.size() > 0)
            {
                roleMapper.insertRoleMenu(roleId, menuIdSet);
            }
            // 删除角色权限
            roleMapper.deleteRolePowerByRoleId(roleId);
            // 添加角色权限
            Set<String> powerIdSet = roleVO.getPowerIds();
            // 如果有权限数据，则添加角色权限关系
            if (powerIdSet != null && powerIdSet.size() > 0)
            {
                roleMapper.insertRolePower(roleId, powerIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("修改角色SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean modifyBaseRole(Role role) throws GlobalException
    {
        String roleId = role.getId();
        if (role == null || StringUtils.isEmpty(roleId))
        {
            throw new GlobalException("修改角色信息主键不能为空，入参【角色信息：{}】", role);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = roleMapper.updateRole(role);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础角色SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Transactional(readOnly = true)
    @Override
    public RoleVO findRoleById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询角色信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return roleMapper.selectRoleById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询角色SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询角色
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
    @Transactional(readOnly = true)
    @Override
    public Role findRoleByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询角色信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return roleMapper.selectRoleByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询角色SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public List<Role> findAllRole(Role role) throws GlobalException
    {
        try
        {
            return roleMapper.selectBaseRoleList(role);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有角色SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Role> findRolePage(Role role, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<Role> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询角色分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<Role> roleVOList = roleMapper.selectRoleList(role);
            pageInfo = new PageInfo<>(roleVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询角色分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
