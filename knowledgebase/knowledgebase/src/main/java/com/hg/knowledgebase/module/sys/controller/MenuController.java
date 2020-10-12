package com.hg.knowledgebase.module.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.global.bean.TreeBean;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.VO.MenuVO;
import com.hg.knowledgebase.module.sys.service.IMenuService;
import com.hg.knowledgebase.util.TreeUtil;

/**
 * 
 * @Description: 菜单控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/menu")
public class MenuController
{

    private static Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    /**
     * 
     * @Description: 保存菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param menu
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveMenu")
    @SystemLog(value = "保存菜单")
    public ResponseResultBean saveMenu(@RequestBody Menu menu)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = menuService.addMenu(menu);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param menu
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyMenu")
    @SystemLog(value = "修改菜单")
    public ResponseResultBean modifyMenu(@RequestBody Menu menu)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = menuService.modifyMenu(menu);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改菜单状态
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyMenuStatus")
    @SystemLog(value = "修改菜单状态")
    public ResponseResultBean modifyMenuStatus(@RequestBody Menu menu)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = menuService.modifyMenu(menu);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改菜单状态异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeMenu")
    @SystemLog(value = "根据id移除菜单")
    public ResponseResultBean removeMenuById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = menuService.removeMenuById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getMenu")
    @SystemLog(value = "根据id查询菜单")
    public ResponseResultBean getMenuById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            MenuVO menuVO = menuService.findMenuById(id);
            flg = menuVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, menuVO);
        } catch (Exception e)
        {
            log.error("---根据id查询菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验菜单名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkMenuName")
    @SystemLog(value = "校验菜单名是否存在")
    public ResponseResultBean checkMenuName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Menu menu = menuService.findMenuByName(name);
            flg = menu != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---根据账号查询菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param menu
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllMenu")
    @SystemLog(value = "获取所有菜单")
    public ResponseResultBean getAllMenu(@RequestBody Menu menu)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Menu> menus = menuService.findAllMenu(menu);
            flg = menus != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, menus);
        } catch (Exception e)
        {
            log.error("---获取所有菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有当前用户菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param menu
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCurrentMenu")
    @SystemLog(value = "获取所有当前用户菜单")
    public ResponseResultBean getAllCurrentMenu(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String menuGroup = jsonObj.getString(GlobalConstant.GROUP);
            String pid = jsonObj.getString(GlobalConstant.PID);
            List<Menu> menus = menuService.findAllCurrentUserMenu(menuGroup,
                    pid);
            flg = menus != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, menus);
        } catch (Exception e)
        {
            log.error("---获取所有菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页菜单
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param menu
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getMenuPage")
    @SystemLog(value = "获取分页菜单")
    public ResponseResultBean getMenuPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Menu menu = JSONObject.parseObject(reqStr, Menu.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<MenuVO> pageInfo = menuService.findMenuPage(menu, pageNum,
                    pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页菜单异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取菜单树结构
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午7:46:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午7:46:08
     * @UpdateRemark: 修改内容
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getMenuTree")
    @SystemLog(value = "获取菜单树结构")
    public ResponseResultBean getMenuTree(@RequestBody Menu menu)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Menu> menus = menuService.findAllMenu(menu);
            List<TreeBean> treeList = TreeUtil.composeTreeExpand(menus);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    treeList);
        } catch (Exception e)
        {
            log.error("---获取菜单树结构异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户菜单树
     * @Author: 况克冬
     * @CreateDate: 2020年3月2日上午10:37:56
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月2日上午10:37:56
     * @UpdateRemark: 修改内容
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentMenuTree")
    @SystemLog(value = "获取当前用户菜单树")
    public ResponseResultBean getCurrentMenuTree(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String menuGroup = jsonObj.getString(GlobalConstant.GROUP);
            String pid = jsonObj.getString(GlobalConstant.PID);
            List<Menu> menus = menuService.findAllCurrentUserMenu(menuGroup,
                    pid);
            List<TreeBean> treeList = TreeUtil.composeTreeExpand(menus);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    treeList);
        } catch (Exception e)
        {
            log.error("---获取当前用户菜单树结构异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
