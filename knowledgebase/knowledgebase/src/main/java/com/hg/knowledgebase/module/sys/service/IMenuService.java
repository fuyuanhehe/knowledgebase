package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.VO.MenuVO;

/**
 * 
 * @Description: 菜单服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IMenuService
{

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
    boolean addMenu(Menu menu) throws GlobalException;

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
    boolean removeMenuById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id批量删除菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchMenuById(List<String> ids) throws GlobalException;

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
    boolean modifyMenu(Menu menu) throws GlobalException;

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
    boolean modifyBaseMenu(Menu menu) throws GlobalException;

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
    MenuVO findMenuById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询菜单
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
    Menu findMenuByName(String name) throws GlobalException;

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
    List<Menu> findAllMenu(Menu menu) throws GlobalException;

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
    List<Menu> findAllCurrentUserMenu(String menuGroup, String pid)
            throws GlobalException;

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
    PageInfo<MenuVO> findMenuPage(Menu menu, Integer pageNum, Integer pageSize)
            throws GlobalException;
}
