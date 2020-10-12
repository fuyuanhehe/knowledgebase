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
import com.hg.knowledgebase.module.sys.entity.Power;
import com.hg.knowledgebase.module.sys.entity.VO.PowerVO;
import com.hg.knowledgebase.module.sys.service.IPowerService;

/**
 * 
 * @Description: 权限控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/power")
public class PowerController
{

    private static Logger log = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    private IPowerService powerService;

    /**
     * 
     * @Description: 保存权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param power
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/savePower")
    @SystemLog(value = "保存权限")
    public ResponseResultBean savePower(@RequestBody Power power)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = powerService.addPower(power);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param power
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyPower")
    @SystemLog(value = "修改权限")
    public ResponseResultBean modifyPower(@RequestBody Power power)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = powerService.modifyPower(power);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removePower")
    @SystemLog(value = "根据id移除权限")
    public ResponseResultBean removePowerById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = powerService.removePowerById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getPower")
    @SystemLog(value = "根据id查询权限")
    public ResponseResultBean getPowerById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            PowerVO powerVO = powerService.findPowerById(id);
            flg = powerVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    powerVO);
        } catch (Exception e)
        {
            log.error("---根据id查询权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验权限名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkPowerName")
    @SystemLog(value = "校验权限名是否存在")
    public ResponseResultBean checkPowerName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Power power = powerService.findPowerByName(name);
            flg = power != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验权限名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param power
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllPower")
    @SystemLog(value = "获取所有权限")
    public ResponseResultBean getAllPower(@RequestBody Power power)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Power> powers = powerService.findAllPower(power);
            flg = powers != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, powers);
        } catch (Exception e)
        {
            log.error("---获取所有权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有当前用户权限coder
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param power
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/findAllCurrentPower")
    @SystemLog(value = "获取所有当前用户权限coder")
    public ResponseResultBean findAllCurrentPower()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<String> coders = powerService.findAllCurrentUserPower();
            flg = coders != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, coders);
        } catch (Exception e)
        {
            log.error("---获取所有当前用户权限coder异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param power
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getPowerPage")
    @SystemLog(value = "获取分页权限")
    public ResponseResultBean getPowerPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Power power = JSONObject.parseObject(reqStr, Power.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<PowerVO> pageInfo = powerService.findPowerPage(power,
                    pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页权限异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
