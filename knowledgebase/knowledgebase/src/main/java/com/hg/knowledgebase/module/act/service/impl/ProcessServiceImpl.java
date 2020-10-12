package com.hg.knowledgebase.module.act.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.FormType;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.act.bean.FieldBean;
import com.hg.knowledgebase.module.act.bean.FormDataBean;
import com.hg.knowledgebase.module.act.bean.ProgressGeneralBean;
import com.hg.knowledgebase.module.act.bean.TaskBean;
import com.hg.knowledgebase.module.act.service.IProcessService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.util.FileUtil;

/**
 * 
 * @Description: 流程实现类
 * @Author: 况克冬
 * @CreateDate: 2020年5月12日下午3:29:56
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class ProcessServiceImpl implements IProcessService
{

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @Autowired
    private ProcessEngine processEngine;
    /**
     * 流程文件后缀常量
     */
    private static final String PROCESS_FILE_SUFFIX = ".bpmn";

    /**
     * 流程压缩文件后缀常量
     */
    private static final String PROCESS_COMPRESS_FILE_SUFFIX = ".zip";

    //////////////////////////////////////// 流程模型/////////////////////////////////////////////////
    /**
     * 
     * @Description: 添加流程模型文件
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午2:50:51
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午2:50:51
     * @UpdateRemark: 修改内容
     * @param file
     * @return
     * @throws GlobalException
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addProcessModel(MultipartFile file) throws GlobalException
    {
        boolean flg = false;
        if (file == null)
        {
            throw new GlobalException("添加流程模型文件不能为空");
        }
        try
        {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            if (cheakProcessFileFormat(fileName))
            {
                Model model = repositoryService.newModel();
                model.setName(fileName);
                repositoryService.saveModel(model);
                repositoryService.addModelEditorSourceExtra(model.getId(),
                        bytes);
                flg = true;
            } else
            {
                throw new GlobalException("添加流程模型文件格式不合法");
            }
        } catch (IOException e)
        {
            throw new GlobalException("添加流程模型文件异常：{}", e.getMessage());
        }
        return flg;
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
     * @throws GlobalException
     *             void
     * @Version: 1.0.0
     */
    @Override
    public void downloadProcess(String modelId) throws GlobalException
    {
        OutputStream out = null;
        if (StringUtils.isEmpty(modelId))
        {
            throw new GlobalException("下载流程文件失败，入参【模型id：{}】", modelId);
        }
        try
        {
            Model model = repositoryService.getModel(modelId);
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(
                            FileUtil.getFileName(model.getName()),
                            GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            byte[] bytes = repositoryService
                    .getModelEditorSourceExtra(model.getId());
            out.write(bytes);
        } catch (IOException e)
        {
            throw new GlobalException("下载流程文件异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }

    /**
     * 
     * @Description: 查询流程模型分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     *             PageInfo<Object>
     * @Version: 1.0.0
     */
    @Override
    public PageInfo<Model> findProcessModelPage(Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<Model> page = null;
        if (pageSize == null || pageSize == 0)
        {
            throw new GlobalException("添加流程模型文件不能为空");
        }
        List<Model> modelList = repositoryService.createModelQuery()
                .orderByCreateTime().desc().listPage(pageNum, pageSize);
        long count = repositoryService.createModelQuery().count();
        page = new PageInfo<>();
        page.setList(modelList);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(count);
        return page;
    }

    /**
     * 
     * @Description: 删除流程模型信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     *             List<Object>
     * @Version: 1.0.0
     */
    @Override
    public boolean removeProcessModel(String modelId) throws GlobalException
    {
        if (StringUtils.isEmpty(modelId))
        {
            throw new GlobalException("删除流程模型信息输入参数不能为空，入参【模型id：{}】", modelId);
        }
        repositoryService.deleteModel(modelId);
        return true;
    }

    ///////////////////////////////// 流程部署////////////////////////////////////////
    /**
     * 
     * @Description: 查询流程部署分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     *             List<Object>
     * @Version: 1.0.0
     */
    @Override
    public PageInfo<ProgressGeneralBean> findProgressDeploymentPage(
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        ProgressGeneralBean pro = null;
        PageInfo<ProgressGeneralBean> page = null;
        if (pageSize == null || pageSize == 0)
        {
            throw new GlobalException("查询流程部署分页信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        List<ProgressGeneralBean> list = new LinkedList<>();
        List<Deployment> deploymentList = repositoryService
                .createDeploymentQuery().orderByDeploymenTime().desc()
                .listPage(pageNum, pageSize);
        long count = repositoryService.createDeploymentQuery().count();
        for (Deployment dep : deploymentList)
        {
            pro = new ProgressGeneralBean(dep.getId(), dep.getKey(),
                    dep.getName(), dep.getDeploymentTime());
            list.add(pro);
        }
        page = new PageInfo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setList(list);
        return page;
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
    @Override
    public boolean executeProgressDeployment(String modelId)
            throws GlobalException
    {
        boolean flg = false;
        if (StringUtils.isEmpty(modelId))
        {
            throw new GlobalException("部署流程信息输入参数不能为空，入参【模型id：{}】", modelId);
        }
        Model model = repositoryService.getModel(modelId);
        String modelName = model.getName();
        if (StringUtils.isEmpty(modelName))
        {
            throw new GlobalException("部署流程信息输入参数不能为空，入参【模型名称：{}】", modelName);
        }
        byte[] bytes = repositoryService.getModelEditorSourceExtra(modelId);
        InputStream in = new ByteArrayInputStream(bytes);
        try
        {
            if (modelName.endsWith(PROCESS_FILE_SUFFIX)) // 单文件
            {
                Deployment deployment = repositoryService.createDeployment()
                        .addInputStream(modelName, in).deploy();
                System.out.println(JSONObject.toJSONString(deployment));
                flg = true;
            } else if (modelName.endsWith(PROCESS_COMPRESS_FILE_SUFFIX)) // 压缩文件
            {
                ZipInputStream zipInputStream = new ZipInputStream(in);
                Deployment deployment = repositoryService.createDeployment()
                        .addZipInputStream(zipInputStream).deploy();
                System.out.println(JSONObject.toJSONString(deployment));
                flg = true;
            } else
            {
                throw new GlobalException("部署流程信息输入参数格式错误，入参【模型名称：{}】",
                        modelName);
            }
        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
        return flg;
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
     * @throws GlobalException
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeProgressDeployment(String deploymentId)
            throws GlobalException
    {
        if (StringUtils.isEmpty(deploymentId))
        {
            throw new GlobalException("移除流程部署输入参数不能为空，入参【流程部署id：{}】",
                    deploymentId);
        }
        repositoryService.deleteDeployment(deploymentId, true);
        return true;
    }

    //////////////////////////////////// 流程定义////////////////////////////////////////////////////////////
    /**
     * 
     * @Description: 查询流程定义分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     *             List<Object>
     * @Version: 1.0.0
     */
    @Override
    public PageInfo<ProgressGeneralBean> findProgressDefinitionPage(
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        ProgressGeneralBean pro = null;
        PageInfo<ProgressGeneralBean> page = null;
        if (pageSize == null || pageSize == 0)
        {
            throw new GlobalException("查询流程定义分页信息信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        List<ProgressGeneralBean> list = new LinkedList<>();
        List<ProcessDefinition> processDefinitionList = repositoryService
                .createProcessDefinitionQuery().orderByProcessDefinitionId()
                .desc().listPage(pageNum, pageSize);
        for (ProcessDefinition def : processDefinitionList)
        {
            pro = new ProgressGeneralBean(def.getId(), def.getKey(),
                    def.getName());
            pro.setDeploymentId(def.getDeploymentId());
            list.add(pro);
        }
        long count = repositoryService.createProcessDefinitionQuery().count();
        page = new PageInfo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setList(list);
        return page;
    }

    ///////////////////////////////// 历史流程/////////////////////////////////////////////
    /**
     * 
     * @Description: 查询流程历史分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:06:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:06:38
     * @UpdateRemark: 修改内容
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException
     *             List<Object>
     * @Version: 1.0.0
     */
    @Override
    public PageInfo<ProgressGeneralBean> findProgressHistoryPage(
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        ProgressGeneralBean pro = null;
        PageInfo<ProgressGeneralBean> page = null;
        if (pageSize == null || pageSize == 0)
        {
            throw new GlobalException("查询流程历史分页信息信息信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        List<ProgressGeneralBean> list = new LinkedList<>();
        List<HistoricProcessInstance> hisList = historyService
                .createHistoricProcessInstanceQuery()
                .orderByProcessInstanceEndTime().desc()
                .listPage(pageNum, pageSize);
        for (HistoricProcessInstance his : hisList)
        {
            pro = new ProgressGeneralBean(his.getId(),
                    his.getProcessDefinitionKey(), his.getName(),
                    his.getEndTime());
            pro.setDeploymentId(his.getDeploymentId());
            list.add(pro);
        }
        long count = historyService.createHistoricProcessInstanceQuery()
                .count();
        page = new PageInfo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setList(list);
        return page;
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
     * @throws GlobalException
     *             void
     * @Version: 1.0.0
     */
    @Override
    public void previewSpeedOfProgress(String processInstanceId)
            throws GlobalException
    {
        OutputStream out = null;
        HistoricProcessInstance historicProcessInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        if (StringUtils.isEmpty(processInstanceId))
        {
            throw new GlobalException("预览进度流程实例不存在，入参【流程实例ID：{}】",
                    processInstanceId);
        }
        BpmnModel bpmnModel = repositoryService
                .getBpmnModel(historicProcessInstance.getProcessDefinitionId());
        ProcessEngineConfiguration processEngineConfiguration = processEngine
                .getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration(
                (ProcessEngineConfigurationImpl) processEngineConfiguration);
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration
                .getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService
                .getProcessDefinition(
                        historicProcessInstance.getProcessDefinitionId());

        List<HistoricActivityInstance> highLightedActivitList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceEndTime().asc().list();
        // 完成的任务或事件id集合
        List<String> completeLightedActivitis = new ArrayList<String>(1);
        // 高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>(1);
        for (HistoricActivityInstance tempActivity : highLightedActivitList)
        {
            highLightedActivitis.add(tempActivity.getActivityId());
            //如果活动任务或事件已经完成，则添加ID到完成的任务或事件id集合
            if (tempActivity.getEndTime() != null)
            {
                completeLightedActivitis.add(tempActivity.getActivityId());
            }
        }
        // 高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(bpmnModel,
                highLightedActivitList, completeLightedActivitis);
        // 中文显示的是口口口，设置字体就好了
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel,
                "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", "宋体",
                null, 1.0);
        try
        {
            ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse resp = request.getResponse();
            resp.setHeader("content-type", "application/octet-stream");
            resp.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(
                            definitionEntity.getName() + ".png", "UTF-8"));
            out = resp.getOutputStream();
            byte[] by = new byte[1024];
            int a = -1;
            while ((a = imageStream.read(by)) != -1)
            {
                out.write(by, 0, a);
            }
        } catch (IOException e)
        {
            throw new GlobalException("预览进度异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
            if (imageStream != null)
            {
                try
                {
                    imageStream.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }

    ////////////////////////////////////////////// 流程任务//////////////////////////////////////////////////////
    /**
     * 
     * @Description: 查询流程任务分页信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月13日上午10:56:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月13日上午10:56:34
     * @UpdateRemark: 修改内容
     * @param pageNum
     *            开始页面
     * @param pageSize
     *            查询条数
     * @param deploymentId
     *            流程部署ID
     * @param userIdForCandidateAndAssignee
     *            受理人或者候选人
     * @return
     * @throws GlobalException
     *             List<Object>
     * @Version: 1.0.0
     */
    @Override
    public PageInfo<TaskBean> findProgressTaskPage(Integer pageNum,
            Integer pageSize, String deploymentId,
            String userIdForCandidateAndAssignee) throws GlobalException
    {
        TaskBean taskBean = null;
        PageInfo<TaskBean> page = null;
        if (pageSize == null || pageSize == 0)
        {
            return page;
        }
        TaskQuery taskQuery = taskService.createTaskQuery()
                .orderByTaskCreateTime().desc();
        if (!StringUtils.isEmpty(deploymentId))
        {
            taskQuery.deploymentId(deploymentId);
        }
        if (!StringUtils.isEmpty(userIdForCandidateAndAssignee))
        {
            taskQuery.taskCandidateOrAssigned(userIdForCandidateAndAssignee);
        }
        List<Task> taskList = taskQuery.listPage(pageNum, pageSize);
        List<TaskBean> list = new LinkedList<>();
        for (Task task : taskList)
        {
            taskBean = new TaskBean(task.getId(), task.getName());
            taskBean.setParentTaskId(task.getParentTaskId());
            taskBean.setProcessInstanceId(task.getProcessInstanceId());
            taskBean.setProcessDefinitionId(task.getProcessDefinitionId());
            taskBean.setCreateTime(task.getCreateTime());
            taskBean.setClaimTime(task.getClaimTime());
            taskBean.setDueDate(task.getDueDate());
            list.add(taskBean);
        }
        long count = taskQuery.count();
        page = new PageInfo<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setList(list);
        return page;
    }

    /**
     * 
     * @Description: 查看任务信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:20:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:20:35
     * @UpdateRemark: 修改内容
     * @param taskId
     * @return
     * @throws GlobalException
     *             Object
     * @Version: 1.0.0
     */
    @Override
    public TaskBean findTask(String taskId) throws GlobalException
    {
        TaskBean taskBean = null;
        if (StringUtils.isEmpty(taskId))
        {
            throw new GlobalException("查看任务信息输入参数不能为空，入参【任务ID：{}】", taskId);
        }
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskBean = new TaskBean(task.getId(), task.getName());
        taskBean.setClaimTime(task.getClaimTime());
        taskBean.setCreateTime(task.getCreateTime());
        taskBean.setDescription(task.getDescription());
        taskBean.setDueDate(task.getDueDate());
        taskBean.setFormKey(task.getFormKey());
        taskBean.setParentTaskId(task.getParentTaskId());
        taskBean.setProcessDefinitionId(task.getProcessDefinitionId());
        taskBean.setProcessInstanceId(task.getProcessInstanceId());
        taskBean.setProcessVariables(taskBean.getProcessVariables());
        taskBean.setTaskLocalVariables(taskBean.getTaskLocalVariables());
        return taskBean;
    }

    /**
     * 
     * @Description: 执行任务
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:19:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:19:13
     * @UpdateRemark: 修改内容
     * @param taskId
     * @param variables
     * @return
     * @throws GlobalException
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean executeTask(String taskId, Map<String, Object> variables)
            throws GlobalException
    {
        // 如果签收任务成功，则完成任务
        if (claimTask(taskId))
        {
            completeTask(taskId, variables);
        }
        return true;
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
     * @throws GlobalException
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean completeTask(String taskId, Map<String, Object> variables)
            throws GlobalException
    {
        if (StringUtils.isEmpty(taskId))
        {
            throw new GlobalException("完成任务输入参数不能为空，入参【任务ID：{}】", taskId);
        }
        taskService.complete(taskId, variables);
        return true;
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
     * @return
     * @throws GlobalException
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean claimTask(String taskId) throws GlobalException
    {
        if (StringUtils.isEmpty(taskId))
        {
            throw new GlobalException("签收任务任务输入参数不能为空，入参【任务ID：{}】", taskId);
        }
        taskService.claim(taskId, "root");
        return true;
    }

    /**
     * 
     * @Description: 查看任务表单数据
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:20:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:20:35
     * @UpdateRemark: 修改内容
     * @param taskId
     * @return
     * @throws GlobalException
     *             Object
     * @Version: 1.0.0
     */
    @Override
    public FormDataBean findTaskFormData(String taskId) throws GlobalException
    {
        //字段对象
        FieldBean field = null;
        //表单数据对象
        FormDataBean formDataBean = null;
        if (StringUtils.isEmpty(taskId)) 
        {
            throw new GlobalException("查看任务表单数据输入参数不能为空，入参【任务ID：{}】", taskId);
        }
        TaskBean task = findTask(taskId);
        if (task == null)
        {
            return formDataBean;
        }
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        //如果任务存在表单数据，则获取表单数据属性
        if (taskFormData != null) 
        {
            //获取表单属性
            List<FormProperty> propertyList = taskFormData.getFormProperties();
            List<FieldBean> fieldList = new LinkedList<>();
            //因为表单属性不会为null，因此可以省约校验值为空
            for (FormProperty property : propertyList) 
            {
                field = new FieldBean();
                field.setId(property.getId());
                field.setName(property.getName());
                field.setType(property.getType() == null ? null : property.getType().getName());
                field.setValue(property.getValue());
                field.setReadable(property.isReadable());
                field.setRequired(property.isRequired());
                field.setWritable(property.isWritable());
                fieldList.add(field);
            }
            formDataBean = new FormDataBean();
            formDataBean.setFields(fieldList);
            formDataBean.setFormKey(task.getFormKey() != null);
        }
        return formDataBean;
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
     * @throws GlobalException
     *             Object
     * @Version: 1.0.0
     */
    @Override
    public boolean startProgressByIdOrKey(String processDefinitionIdOrKey,
            String businessKey, Map<String, Object> variables)
            throws GlobalException
    {
        //暂时保留
        ProcessInstance processInstance = null;
        if (StringUtils.isEmpty(processDefinitionIdOrKey))
        {
            throw new GlobalException("启动流程信息输出参数不能为空，入参【流程定义id或key：{}】",
                    processDefinitionIdOrKey);
        }
        identityService.setAuthenticatedUserId("root");
        if (processDefinitionIdOrKey.contains(":")) // 流程定义ID
        {
            processInstance = runtimeService.startProcessInstanceById(
                    processDefinitionIdOrKey, businessKey, variables);
        } else // 流程定义key
        {
            processInstance = runtimeService.startProcessInstanceByKey(
                    processDefinitionIdOrKey, businessKey, variables);
        }
        return true;
    }

    /**
     * 
     * @Description: 查询流程信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午3:11:43
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午3:11:43
     * @UpdateRemark: 修改内容
     * @param processId
     * @return
     * @throws GlobalException
     *             Object
     * @Version: 1.0.0
     */
    @Override
    public ProgressGeneralBean findProgress(String processId)
            throws GlobalException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
    * @Description: 获取高亮度流程流向id集合
    * @Author: 况克冬
    * @CreateDate: 2020年6月3日上午10:40:31
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年6月3日上午10:40:31
    * @UpdateRemark: 修改内容
    * @param bpmnModel 流程模型对象
    * @param historicActivityInstances 流程活动任务或事件集合对象
    * @param completeActivitis 完成的任务或事件ID集合对象
    * @return
    * List<String>
    * @Version: 1.0.0
     */
    private static List<String> getHighLightedFlows(BpmnModel bpmnModel,
            List<HistoricActivityInstance> historicActivityInstances,
            List<String> completeActivitis)
    {
        // 流程流向节点对象
        FlowNode flowNode = null;
        // 输出流程流向线集合
        List<SequenceFlow> outFlowList = null;
        // 完成的输出流程流向线id集合
        List<String> completeFlowList = new ArrayList<>(1);
        // 参数空值校验变量
        boolean flg = bpmnModel == null || historicActivityInstances == null
                || completeActivitis == null
                || historicActivityInstances.size() == 0;
        //如果参数不合法则返回一个初始化的完成的输出流程流向线id集合
        if (flg)
        {
            return completeFlowList;
        }
        // 获取当前模型的所有流程（主流程和子流程）
        List<org.activiti.bpmn.model.Process> proList = bpmnModel
                .getProcesses();
        for (HistoricActivityInstance hai : historicActivityInstances)
        {
            // 因为一个流程至少会有一个主流程，因此可以省约流程集合判断
            for (org.activiti.bpmn.model.Process pro : proList)
            {
                flowNode = (FlowNode) pro.getFlowElement(hai.getActivityId(),
                        true);
                outFlowList = flowNode.getOutgoingFlows();
                for (SequenceFlow flow : outFlowList)
                {
                    // 如果完成的activiti任务或者事件id集合包含当前节点ID，则添加到顺序流集合
                    if (completeActivitis.contains(flow.getSourceRef()))
                    {
                        completeFlowList.add(flow.getId());
                    }
                }
            }
        }
        return completeFlowList;
    }

    /**
     * 获取已经流转的线(暂时保留)
     * 
     * @param bpmnModel
     * @param historicActivityInstances
     * @return
     */
    private static List<String> getHighLightedFlows(BpmnModel bpmnModel,
            List<HistoricActivityInstance> historicActivityInstances)
    {
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlowIds = new ArrayList<>();
        // 全部活动节点
        List<FlowNode> historicActivityNodes = new ArrayList<>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstances = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances)
        {
            FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess()
                    .getFlowElement(historicActivityInstance.getActivityId(),
                            true);
            historicActivityNodes.add(flowNode);
            if (historicActivityInstance.getEndTime() != null)
            {
                finishedActivityInstances.add(historicActivityInstance);
            }
        }

        FlowNode currentFlowNode = null;
        FlowNode targetFlowNode = null;
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstances)
        {
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess()
                    .getFlowElement(currentActivityInstance.getActivityId(),
                            true);
            List<SequenceFlow> sequenceFlows = currentFlowNode
                    .getOutgoingFlows();

            /**
             * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转：
             * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             */
            if ("parallelGateway"
                    .equals(currentActivityInstance.getActivityType())
                    || "inclusiveGateway"
                            .equals(currentActivityInstance.getActivityType()))
            {
                // 遍历历史活动节点，找到匹配流程目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlows)
                {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess()
                            .getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicActivityNodes.contains(targetFlowNode))
                    {
                        highLightedFlowIds.add(targetFlowNode.getId());
                    }
                }
            } else
            {
                List<Map<String, Object>> tempMapList = new ArrayList<>();
                for (SequenceFlow sequenceFlow : sequenceFlows)
                {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstances)
                    {
                        if (historicActivityInstance.getActivityId()
                                .equals(sequenceFlow.getTargetRef()))
                        {
                            Map<String, Object> map = new HashMap<>();
                            map.put("highLightedFlowId", sequenceFlow.getId());
                            map.put("highLightedFlowStartTime",
                                    historicActivityInstance.getStartTime()
                                            .getTime());
                            tempMapList.add(map);
                        }
                    }
                }

                if (!CollectionUtils.isEmpty(tempMapList))
                {
                    // 遍历匹配的集合，取得开始时间最早的一个
                    long earliestStamp = 0L;
                    String highLightedFlowId = null;
                    for (Map<String, Object> map : tempMapList)
                    {
                        long highLightedFlowStartTime = Long.valueOf(
                                map.get("highLightedFlowStartTime").toString());
                        if (earliestStamp == 0
                                || earliestStamp >= highLightedFlowStartTime)
                        {
                            highLightedFlowId = map.get("highLightedFlowId")
                                    .toString();
                            earliestStamp = highLightedFlowStartTime;
                        }
                    }

                    highLightedFlowIds.add(highLightedFlowId);
                }

            }
        }
        return highLightedFlowIds;
    }

    /**
     * 
     * @Description: 校验流程文件格式
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午4:38:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午4:38:30
     * @UpdateRemark: 修改内容
     * @param fileName
     * @return boolean
     * @Version: 1.0.0
     */
    private boolean cheakProcessFileFormat(String fileName)
    {
        boolean flg = false;
        if (!StringUtils.isEmpty(fileName)
                && (fileName.endsWith(PROCESS_FILE_SUFFIX)
                        || fileName.endsWith(PROCESS_COMPRESS_FILE_SUFFIX)))
        {
            flg = true;
        }
        return flg;
    }
}
