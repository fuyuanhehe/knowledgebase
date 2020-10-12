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
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.VO.DeptVO;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import com.hg.knowledgebase.util.TreeUtil;

/**
 * 
 * @Description: 部门控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/dept")
public class DeptController
{

    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private IDeptService deptService;

    /**
     * 
     * @Description: 保存部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param dept
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveDept")
    @SystemLog(value = "保存部门")
    public ResponseResultBean saveDept(@RequestBody Dept dept)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = deptService.addDept(dept);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param dept
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyDept")
    @SystemLog(value = "修改部门")
    public ResponseResultBean modifyDept(@RequestBody Dept dept)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = deptService.modifyDept(dept);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeDept")
    @SystemLog(value = "根据id移除部门")
    public ResponseResultBean removeDeptById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = deptService.removeDeptById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getDept")
    @SystemLog(value = "根据id查询部门")
    public ResponseResultBean getDeptById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            DeptVO deptVO = deptService.findDeptById(id);
            flg = deptVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, deptVO);
        } catch (Exception e)
        {
            log.error("---根据id查询部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentDept")
    @SystemLog(value = "获取当前用户部门")
    public ResponseResultBean getCurrentDept()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Dept dept = deptService.findCurrentDept();
            flg = dept != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, dept);
        } catch (Exception e)
        {
            log.error("---获取当前用户部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验部门名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkDeptName")
    @SystemLog(value = "校验部门名是否存在")
    public ResponseResultBean checkDeptName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Dept dept = deptService.findDeptByName(name);
            flg = dept != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验部门名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param dept
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllDept")
    @SystemLog(value = "获取所有部门")
    public ResponseResultBean getAllDept(@RequestBody Dept dept)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<DeptVO> depts = deptService.findAllDept(dept);
            flg = depts != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, depts);
        } catch (Exception e)
        {
            log.error("---获取所有部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param dept
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getDeptPage")
    @SystemLog(value = "获取分页部门")
    public ResponseResultBean getDeptPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Dept dept = JSONObject.parseObject(reqStr, Dept.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<DeptVO> pageInfo = deptService.findDeptPage(dept, pageNum,
                    pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页部门异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取部门树结构
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午7:46:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午7:46:08
     * @UpdateRemark: 修改内容
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getDeptTree")
    @SystemLog(value = "获取部门树结构")
    public ResponseResultBean getDeptTree()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Dept> depts = deptService.findAllBaseDept(null);
            List<TreeBean> treeList = TreeUtil.composeTreeExpand(depts);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    treeList);
        } catch (Exception e)
        {
            log.error("---获取部门树结构异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
