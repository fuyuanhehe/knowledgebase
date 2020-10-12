package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.VO.MenuVO;

/**
 * 
 * @Description: 菜单数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IMenuMapper
{
    /**
     * 
     * @Description: 插入菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertMenu(Menu menu) throws SQLException;

    /**
     * 
     * @Description: 插入批量菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param menus
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchMenu(@Param("menus") List<Menu> menus) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteMenuById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchMenuById(@Param("ids") List<String> ids) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateMenu(Menu menu) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Menu
     * @Version: 1.0.0
     */
    MenuVO selectMenuById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Menu
     * @Version: 1.0.0
     */
    Menu selectMenuByName(@Param("name") String name) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<MenuVO> selectMenuList(Menu menu) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param menu
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<Menu> selectBaseMenuList(Menu menu) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础菜单扩展
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<MenuVO> selectBaseMenuExtendList() throws SQLException;

    /**
     * 
     * @Description: 查询用户菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userName
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Menu>
     * @Version: 1.0.0
     */
    List<Menu> selectUserMenuList(@Param("userName") String userName,
            @Param("menuGroup") String menuGroup, @Param("pid") String pid)
            throws SQLException;

    //////////////////////////// 菜单角色关系///////////////////////////////////////
    /**
     * 
     * @Description: 根据菜单id删除角色菜单
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
    int deleteRoleMenuByMenuId(@Param("menuId") String menuId)
            throws SQLException;

    /**
     * 
     * @Description: 根据菜单id批量删除角色菜单
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
    int deleteBatchRoleMenuByMenuId(@Param("menuIds") List<String> menuIds)
            throws SQLException;

    //////////////////////////// 菜单权限关系///////////////////////////////////////////
    /**
     * 
     * @Description: 插入菜单权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:04:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:04:30
     * @UpdateRemark: 修改内容
     * @param menuId
     * @param powerIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertMenuPower(@Param("menuId") String menuId,
            @Param("powerIds") List<String> powerIds) throws SQLException;

    /**
     * 
     * @Description: 根据菜单id删除菜单权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param menuId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteMenuPowerByMenuId(@Param("menuId") String menuId)
            throws SQLException;

    /**
     * 
     * @Description: 根据菜单id批量删除菜单权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param menuId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchMenuPowerByMenuId(@Param("menuIds") List<String> menuIds)
            throws SQLException;
}
