package com.hg.knowledgebase.module.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.VO.MenuVO;
import com.hg.knowledgebase.module.sys.mapper.IMenuMapper;
import com.hg.knowledgebase.module.sys.service.IMenuService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 菜单业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class MenuServiceImpl implements IMenuService
{

    @Autowired
    private IMenuMapper menuMapper;

    /**
     * 
     * @Description: 添加菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addMenu(Menu menu) throws GlobalException
    {
        if (menu == null)
        {
            throw new GlobalException("添加菜单信息不能为空，入参【菜单信息：{}】", menu);
        }
        // 执行条数
        int exec = 0;
        menu.setId(StringUtil.getUUID());
        menu.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = menuMapper.insertMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("添加菜单SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeMenuById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除菜单信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = menuMapper.deleteMenuById(id);
            // 删除菜单权限关系
            menuMapper.deleteMenuPowerByMenuId(id);
            // 删除角色菜单
            menuMapper.deleteRoleMenuByMenuId(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除菜单SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id批量删除菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeBatchMenuById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id批量删除菜单信息不能为空，入参【主键：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = menuMapper.deleteBatchMenuById(ids);
            // 删除菜单权限关系
            menuMapper.deleteBatchMenuPowerByMenuId(ids);
            // 删除角色菜单
            menuMapper.deleteBatchRoleMenuByMenuId(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id批量删除菜单SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyMenu(Menu menu) throws GlobalException
    {
        if (menu == null || StringUtils.isEmpty(menu.getId()))
        {
            throw new GlobalException("修改菜单信息主键不能为空，入参【菜单信息：{}】", menu);
        }
        // 执行条数
        int exec = 0;
        menu.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        menu.setUpdateTime(new Date());
        try
        {
            exec = menuMapper.updateMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("修改菜单SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改基础菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBaseMenu(Menu menu) throws GlobalException
    {
        if (menu == null || StringUtils.isEmpty(menu.getId()))
        {
            throw new GlobalException("修改菜单信息主键不能为空，入参【菜单信息：{}】", menu);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = menuMapper.updateMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础菜单SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Menu
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public MenuVO findMenuById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询菜单信息主键不能为空，入参【主键：{}", id);
        }
        try
        {
            return menuMapper.selectMenuById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询菜单SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Menu
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Menu findMenuByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询菜单信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return menuMapper.selectMenuByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询菜单SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Menu> findAllMenu(Menu menu) throws GlobalException
    {
        try
        {
            return menuMapper.selectBaseMenuList(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有菜单SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有当前用户菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Menu> findAllCurrentUserMenu(String menuGroup, String pid)
            throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        try
        {
            return menuMapper.selectUserMenuList(userName, menuGroup, pid);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有当前用户菜单SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询菜单分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param menu
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<MenuVO> findMenuPage(Menu menu, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<MenuVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询菜单分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<MenuVO> menuVOList = menuMapper.selectMenuList(menu);
            pageInfo = new PageInfo<>(menuVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询菜单分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
