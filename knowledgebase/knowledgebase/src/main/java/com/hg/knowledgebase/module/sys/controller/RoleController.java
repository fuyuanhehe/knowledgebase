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
import com.hg.knowledgebase.module.sys.entity.Role;
import com.hg.knowledgebase.module.sys.entity.VO.RoleVO;
import com.hg.knowledgebase.module.sys.service.IRoleService;

/**
 * 
 * @Description: 角色控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/role")
public class RoleController
{

    private static Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    /**
     * 
     * @Description: 保存角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param role
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveRole")
    @SystemLog(value = "保存角色")
    public ResponseResultBean saveRole(@RequestBody RoleVO role)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = roleService.addRole(role);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param role
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyRole")
    @SystemLog(value = "修改角色")
    public ResponseResultBean modifyRole(@RequestBody RoleVO role)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = roleService.modifyRole(role);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改角色状态
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param role
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyRoleStatus")
    @SystemLog(value = "修改角色状态")
    public ResponseResultBean modifyRoleStatus(@RequestBody Role role)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = roleService.modifyBaseRole(role);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改角色状态异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeRole")
    @SystemLog(value = "根据id移除角色")
    public ResponseResultBean removeRoleById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = roleService.removeRoleById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getRole")
    @SystemLog(value = "根据id查询角色")
    public ResponseResultBean getRoleById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            RoleVO role = roleService.findRoleById(id);
            flg = role != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, role);
        } catch (Exception e)
        {
            log.error("---根据id查询角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验角色名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkRoleName")
    @SystemLog(value = "校验角色名是否存在")
    public ResponseResultBean checkRoleName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Role role = roleService.findRoleByName(name);
            flg = role != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验角色名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param role
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllRole")
    @SystemLog(value = "获取所有角色")
    public ResponseResultBean getAllRole(@RequestBody Role role)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Role> roles = roleService.findAllRole(role);
            flg = roles != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, roles);
        } catch (Exception e)
        {
            log.error("---获取所有角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页角色
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateRole: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param role
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getRolePage")
    @SystemLog(value = "获取分页角色")
    public ResponseResultBean getRolePage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Role role = JSONObject.parseObject(reqStr, Role.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<Role> pageInfo = roleService.findRolePage(role, pageNum,
                    pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页角色异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
