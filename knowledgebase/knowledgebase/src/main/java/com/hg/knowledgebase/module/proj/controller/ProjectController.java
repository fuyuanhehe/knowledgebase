package com.hg.knowledgebase.module.proj.controller;

import java.sql.SQLException;
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
import com.hg.knowledgebase.module.proj.entity.Project;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectVO;
import com.hg.knowledgebase.module.proj.entity.VO.UserProjectVO;
import com.hg.knowledgebase.module.proj.service.IProjectService;

/**
 *
* @Description: 项目控制层
* @Author: 符源
* @CreateDate: 2020年4月14日上午9:55:44
* @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/proj/project")
public class ProjectController
{

    private static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private IProjectService projectService;

    /**
     *
    * @Description: 保存项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日上午9:55:58
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日上午9:55:58
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/saveProject")
    @SystemLog(value = "保存项目")
    public ResponseResultBean saveProject(@RequestBody ProjectVO projectVO)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = projectService.addProject(projectVO);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---添加项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 修改项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日上午10:15:08
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日上午10:15:08
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyProject")
    @SystemLog(value = "修改项目")
    public ResponseResultBean modifyProject(@RequestBody ProjectVO projectVO)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = projectService.modifyProject(projectVO);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---修改项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 根据id移除项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日上午10:15:20
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日上午10:15:20
    * @UpdateRemark: 修改内容
    * @param id
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/removeProject")
    @SystemLog(value = "根据id移除项目")
    public ResponseResultBean removeProjectById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = projectService.removeProjectById(id);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---根据id删除项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 根据id查询项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日上午10:17:16
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日上午10:17:16
    * @UpdateRemark: 修改内容
    * @param id
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getProject")
    @SystemLog(value = "根据id查询项目")
    public ResponseResultBean getProjectById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            ProjectVO project = projectService.findProjectById(id);
            flg = project != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, project);
        }
        catch (Exception e)
        {
            log.error("---根据id查询项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 校验项目名是否存在
    * @Author: 符源
    * @CreateDate: 2020年4月14日上午10:18:16
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日上午10:18:16
    * @UpdateRemark: 修改内容
    * @param name
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/checkProjectName")
    @SystemLog(value = "校验项目名是否存在")
    public ResponseResultBean checkProjectName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Project project = projectService.findProjectByName(name);
            flg = project != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        }
        catch (Exception e)
        {
            log.error("---校验项目名是否存在异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 获取所有项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日下午12:28:33
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日下午12:28:33
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllProject")
    @SystemLog(value = "获取所有项目")
    public ResponseResultBean getAllProject(@RequestBody Project project)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Project> projects = projectService.findAllProject(project);
            flg = projects != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, projects);
        }
        catch (Exception e)
        {
            log.error("---获取所有项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     *
    * @Description: 获取分页项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日下午12:28:33
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日下午12:28:33
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getProjectPage")
    @SystemLog(value = "获取分页项目")
    public ResponseResultBean getProjectPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Project project = JSONObject.parseObject(reqStr, Project.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<ProjectVO> pageInfo = projectService.findProjectPage(project, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, pageInfo);
        }
        catch (Exception e)
        {
            log.error("---获取分页项目异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    //////////////////////////////////////用户项目关系///////////////////////////////////////////////
    /**
     *
    * @Description: 根据用户查询用户项目列表
    * @Author: 符源
    * @CreateDate: 2020年3月5日上午11:50:16
    * @UpdateUser: 符源
    * @UpdateDate: 2020年3月5日上午11:50:16
    * @UpdateRemark: 修改内容
    * @param userIds
    * @return
    * @throws SQLException
    * List<UserProjectVO>
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserProjectByUser")
    @SystemLog(value = "根据用户查询用户项目列表")
    public ResponseResultBean getUserProjectByUser(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<String> userIds = JSONObject.parseObject(reqStr).getJSONArray(GlobalConstant.IDS).toJavaList(String.class);
            List<UserProjectVO> userProjectVOs = projectService.findUserProjectByUserId(userIds);
            flg = userProjectVOs != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, userProjectVOs);
        }
        catch (Exception e)
        {
            log.error("---根据用户查询用户项目列表异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
