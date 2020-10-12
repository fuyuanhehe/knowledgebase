package com.hg.knowledgebase.module.proj.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.proj.entity.Project;
import com.hg.knowledgebase.module.proj.entity.ProjectStage;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectStageVO;
import com.hg.knowledgebase.module.proj.service.IProjectStageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/proj/projectStage")
public class ProjectStageController
{
    private static Logger log = LoggerFactory.getLogger(ProjectStageController.class);

    @Resource
    private IProjectStageService projectStageService;

    /**
     * 功能描述: add 项目阶段记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/saveProjectStage")
    @SystemLog(value = "新增项目阶段记录")
    public ResponseResultBean saveProjectStage(@RequestBody ProjectStage projectStage )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            flg = projectStageService.addProjectStage(projectStage);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---添加项目阶段记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 修改 项目阶段记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/modifyProjectStage")
    @SystemLog(value = "修改项目阶段记录")
    public ResponseResultBean modifyProjectStage(@RequestBody ProjectStage projectStage)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            flg = projectStageService.modifyProjectStage(projectStage);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---修改项目阶段记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  项目阶段记录删除
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/removeProjectStage")
    @SystemLog(value = "删除项目阶段记录")
    public ResponseResultBean removeProjectStageById(@RequestBody ProjectStage projectStage)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = projectStageService.deleteProjectStage(projectStage);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---根据id删除项目阶段记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  项目阶段记录分页
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getAllProjectStage")
    @SystemLog(value = "查询所有项目阶段记录")
    public ResponseResultBean getAllProjectStage(@RequestBody String str)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject json = JSONObject.parseObject(str);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            ProjectStage ProjectStage = json.toJavaObject(ProjectStage.class);

            PageInfo<ProjectStageVO> data = projectStageService.findProjectStagePage(ProjectStage, pageNum,pageSize );
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---获取项目阶段记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 单个查询 项目阶段记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getProjectStage")
    @SystemLog(value = "单个查询项目阶段记录")
    public ResponseResultBean getProjectStage(@RequestBody ProjectStage projectStage )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            ProjectStage data = projectStageService.selectByPrimaryKey(projectStage.getId());
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---单个查询项目阶段记录---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    @PostMapping(value = "/checkProjectStageName")
    @SystemLog(value = "校验项目阶段是否存在")
    public ResponseResultBean checkProjectStageName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);

            if(org.apache.commons.lang3.StringUtils.isBlank(name)){
                return new ResponseResultBean(GlobalConstant.FALSE,GlobalConstant.FAIL);
            }
            ProjectStage projectStage = projectStageService.selectByName(name);

            flg = projectStage != null;
            result = new ResponseResultBean(GlobalConstant.TRUE, GlobalConstant.SUCCESS, flg);
        }
        catch (Exception e)
        {
            log.error("---校验项目阶段是否存在异常---", e);
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

}
