package com.hg.knowledgebase.module.act.controller;

import java.util.Map;

import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.act.bean.FormDataBean;
import com.hg.knowledgebase.module.act.bean.ProgressGeneralBean;
import com.hg.knowledgebase.module.act.bean.TaskBean;
import com.hg.knowledgebase.module.act.service.IProcessService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 流程控制层
 * @Author: 况克冬
 * @CreateDate: 2020年5月12日下午2:32:41
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/act/process")
public class ProcessController
{
    private static final Logger log = LoggerFactory
            .getLogger(ProcessController.class);

    @Autowired
    private IProcessService processService;

    /**
     * 业务ID常量
     */
    private static final String BUS_ID = "busId";

    //////////////////////////////////////// 流程模型/////////////////////////////////////////////////
    /**
     * 
     * @Description: 保存流程模型文件
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午2:50:51
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午2:50:51
     * @UpdateRemark: 修改内容
     * @param file
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveProcessModel")
    @SystemLog(value = "保存流程模型文件")
    public ResponseResultBean saveProcessModel(
            @RequestParam(value = GlobalConstant.FILE_NAME) MultipartFile file)
    {
        ResponseResultBean responseResult = null;
        try
        {
            boolean flg = processService.addProcessModel(file);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---保存流程模型文件异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 下载流程文件
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午2:53:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午2:53:24
     * @UpdateRemark: 修改内容
     * @param processId
     * @throws Exception
     *             void
     * @Version: 1.0.0
     */
    @GetMapping(value = "/downloadProcess")
    @SystemLog(value = "下载流程文件")
    public void downloadProcess(String id) throws Exception
    {
        try
        {
            processService.downloadProcess(id);
        } catch (Exception e)
        {
            log.error("---下载流程文件异常---", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 获取流程模型分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getProcessModelPage")
    @SystemLog(value = "保存流程模型文件")
    public ResponseResultBean getProcessModelPage(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<Model> pageInfo = processService
                    .findProcessModelPage(pageNum, pageSize);
            boolean flg = pageInfo != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取流程模型分页信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 删除流程模型信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeProcessModel")
    @SystemLog(value = "删除流程模型信息")
    public ResponseResultBean removeProcessModel(String id)
    {
        ResponseResultBean responseResult = null;
        try
        {
            boolean flg = processService.removeProcessModel(id);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---删除流程模型信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    ///////////////////////////////// 流程部署////////////////////////////////////////
    /**
     * 
     * @Description: 获取流程部署分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getProgressDeploymentPage")
    @SystemLog(value = "获取流程部署分页信息")
    public ResponseResultBean getProgressDeploymentPage(
            @RequestBody String reqStr)
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<ProgressGeneralBean> pageInfo = processService
                    .findProgressDeploymentPage(pageNum, pageSize);
            boolean flg = pageInfo != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取流程部署分页信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 执行流程部署
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午5:57:40
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午5:57:40
     * @UpdateRemark: 修改内容
     * @param modelId
     * @return boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/executeProgressDeployment")
    @SystemLog(value = "执行流程部署")
    public ResponseResultBean executeProgressDeployment(@RequestBody String req)
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(req);
            String id = jsonObj.getString(GlobalConstant.ID);
            boolean flg = processService.executeProgressDeployment(id);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---执行流程部署异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 移除流程部署
     * @Author: 况克冬
     * @CreateDate: 2020年5月13日上午10:20:28
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月13日上午10:20:28
     * @UpdateRemark: 修改内容
     * @param deploymentId
     *            流程部署ID
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeProgressDeployment")
    @SystemLog(value = "移除流程部署")
    public ResponseResultBean removeProgressDeployment(String id)
    {
        ResponseResultBean responseResult = null;
        try
        {
            boolean flg = processService.removeProgressDeployment(id);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---移除流程部署异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    //////////////////////////////////// 流程定义////////////////////////////////////////////////////////////
    /**
     * 
     * @Description: 获取流程定义分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getProgressDefinitionPage")
    @SystemLog(value = "获取流程定义分页信息")
    public ResponseResultBean getProgressDefinitionPage(
            @RequestBody String reqStr) throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<ProgressGeneralBean> pageInfo = processService
                    .findProgressDefinitionPage(pageNum, pageSize);
            boolean flg = pageInfo != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取流程定义分页信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    ///////////////////////////////// 历史流程/////////////////////////////////////////////
    /**
     * 
     * @Description: 获取流程历史分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getProgressHistoryPage")
    @SystemLog(value = "获取流程历史分页信息")
    public ResponseResultBean getProgressHistoryPage(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<ProgressGeneralBean> pageInfo = processService
                    .findProgressHistoryPage(pageNum, pageSize);
            boolean flg = pageInfo != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取流程历史分页信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 预览进度
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午2:57:04
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午2:57:04
     * @UpdateRemark: 修改内容
     * @param processInstanceId
     *            流程实例ID
     * @throws Exception
     *             void
     * @Version: 1.0.0
     */
    @GetMapping(value = "/previewSpeedOfProgress")
    @SystemLog(value = "预览进度")
    public void previewSpeedOfProgress(String id) throws Exception
    {
        try
        {
            processService.previewSpeedOfProgress(id);
        } catch (Exception e)
        {
            log.error("---预览进度异常---", e.getMessage());
        }
    }

    ////////////////////////////////////////////// 流程任务//////////////////////////////////////////////////////
    /**
     * 
     * @Description: 获取流程任务分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月13日上午10:56:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月13日上午10:56:34
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return
     * @throws Exception
     *             ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getProgressTaskPage")
    @SystemLog(value = "获取流程任务分页信息")
    public ResponseResultBean getProgressTaskPage(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            String id = json.getString(GlobalConstant.ID);
            PageInfo<TaskBean> pageInfo = processService
                    .findProgressTaskPage(pageNum, pageSize, id, null);
            boolean flg = pageInfo != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取流程任务分页信息异常---", e);
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 获取任务信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:20:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:20:35
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws Exception
     *             Object
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getTask")
    @SystemLog(value = "获取任务信息")
    public ResponseResultBean getTask(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            String id = json.getString(GlobalConstant.ID);
            TaskBean taskBean = processService.findTask(id);
            boolean flg = taskBean != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    taskBean);
        } catch (Exception e)
        {
            log.error("---获取任务信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 完成任务
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:19:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:19:13
     * @UpdateRemark: 修改内容
     * @param taskId
     * @param variables
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/executeTask")
    @SystemLog(value = "执行任务信息")
    public ResponseResultBean executeTask(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Map<String, Object> variables = json
                    .getObject(GlobalConstant.VARIABLES, Map.class);
            String id = json.getString(GlobalConstant.ID);
            boolean flg = processService.executeTask(id, variables);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---执行任务信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 签收任务
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:19:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:19:13
     * @UpdateRemark: 修改内容
     * @param taskId
     * @param variables
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/claimTask")
    @SystemLog(value = "签收任务信息")
    public ResponseResultBean claimTask(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            String id = json.getString(GlobalConstant.ID);
            boolean flg = processService.claimTask(id);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---签收任务信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }
    
    /**
     * 
     * @Description: 完成任务
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:19:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:19:13
     * @UpdateRemark: 修改内容
     * @param taskId
     * @param variables
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/completeTask")
    @SystemLog(value = "完成任务信息")
    public ResponseResultBean completeTask(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            Map<String, Object> variables = json
                    .getObject(GlobalConstant.VARIABLES, Map.class);
            String id = json.getString(GlobalConstant.ID);
            boolean flg = processService.completeTask(id, variables);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---完成任务信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }
    
    /**
     * 
     * @Description: 获取任务表单数据
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:20:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:20:35
     * @UpdateRemark: 修改内容
     * @param taskId
     * @return
     * @throws Exception
     *             Object
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getTaskFormData")
    @SystemLog(value = "获取任务表单数据")
    public ResponseResultBean getTaskFormData(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            String id = json.getString(GlobalConstant.ID);
            FormDataBean formBean = processService.findTaskFormData(id);
            boolean flg = formBean != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    formBean);
        } catch (Exception e)
        {
            log.error("---获取任务表单数据异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /////////////////////////////////// 运行流程///////////////////////////////////////////////////////////////
    /**
     * 
     * @Description: 启动流程信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:11:43
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:11:43
     * @UpdateRemark: 修改内容
     * @param processId
     * @return
     * @throws Exception
     *             Object
     * @Version: 1.0.0
     */
    @PostMapping(value = "/startProgress")
    @SystemLog(value = "启动流程信息")
    public ResponseResultBean startProgress(@RequestBody String reqStr)
            throws Exception
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject json = JSONObject.parseObject(reqStr);
            String id = json.getString(GlobalConstant.ID);
            String busId = json.getString(BUS_ID);
            Map<String, Object> variables = json
                    .getObject(GlobalConstant.VARIABLES, Map.class);
            boolean flg = processService.startProgressByIdOrKey(id, busId,
                    variables);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---启动流程信息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }
}
