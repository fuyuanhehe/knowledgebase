package com.hg.knowledgebase.module.stat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import com.hg.knowledgebase.module.sys.service.IUserService;

/**
 * 
* @Description: 用户统计控制层
* @Author: 况克冬
* @CreateDate: 2020年3月3日下午1:44:09
* @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/stat/user")
public class UserStatController
{

    private static Logger log = LoggerFactory.getLogger(UserStatController.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IDeptService deptService;
    
    /**
     * 
    * @Description: 获取用户总数
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserCount")
    @SystemLog(value = "获取用户总数")
    public ResponseResultBean getUserCount(@RequestBody User user)
    {
        ResponseResultBean result = null;
        try
        {
            int count = userService.findUserCount(user);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取用户总数异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取用户性别统计
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午2:29:33
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午2:29:33
    * @UpdateRemark: 修改内容
    * @param user
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getSexStat")
    @SystemLog(value = "获取用户性别统计")
    public ResponseResultBean getSexStat(@RequestBody User user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = userService.findSexStat(user);
            flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("---获取用户性别统计异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取部门总数
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getDeptCount")
    @SystemLog(value = "获取部门总数")
    public ResponseResultBean getDeptCount(@RequestBody Dept dept)
    {
        ResponseResultBean result = null;
        try
        {
            int count = deptService.findDeptCount(dept);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取部门总数异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取部门统计
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午2:29:33
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午2:29:33
    * @UpdateRemark: 修改内容
    * @param dept
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getDeptStat")
    @SystemLog(value = "获取部门统计")
    public ResponseResultBean getDeptStat(@RequestBody Dept dept)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = deptService.findDeptStat(dept);
            flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("---获取部门统计异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
