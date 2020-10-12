package com.hg.knowledgebase.module.act.service;

import java.util.Map;

import org.activiti.engine.repository.Model;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.act.bean.FormDataBean;
import com.hg.knowledgebase.module.act.bean.ProgressGeneralBean;
import com.hg.knowledgebase.module.act.bean.TaskBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: 流程服务层
 * @Author: 况克冬
 * @CreateDate: 2020年5月12日下午2:42:04
 * @Version: 1.0.0
 */
public interface IProcessService
{

    ////////////////////////////////////////// 流程模型/////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 PageInfo<Model>
     * @Version: 1.0.0
     */
    PageInfo<Model> findProcessModelPage(Integer pageNum, Integer pageSize)
            throws GlobalException;

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
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean addProcessModel(MultipartFile file) throws GlobalException;

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
     *             业务异常时候出现的异常 void
     * @Version: 1.0.0
     */
    void downloadProcess(String processId) throws GlobalException;

    /**
     * 
     * @Description: 移除流程模型
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午5:54:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午5:54:19
     * @UpdateRemark: 修改内容
     * @param modelId
     *            模型id
     * @return
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean removeProcessModel(String modelId) throws GlobalException;

    ////////////////////////////////////// 流程部署////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 PageInfo<Object>
     * @Version: 1.0.0
     */
    PageInfo<ProgressGeneralBean> findProgressDeploymentPage(Integer pageNum,
            Integer pageSize) throws GlobalException;

    /**
     * 
     * @Description: 执行流程部署
     * @Author: 况克冬
     * @CreateDate: 2020年5月12日下午5:57:40
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月12日下午5:57:40
     * @UpdateRemark: 修改内容
     * @param modelId
     *            模型ID
     * @return boolean
     * @Version: 1.0.0
     */
    boolean executeProgressDeployment(String modelId) throws GlobalException;

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
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean removeProgressDeployment(String deploymentId)
            throws GlobalException;

    ///////////////////////////////////////////////////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 PageInfo<Object>
     * @Version: 1.0.0
     */
    PageInfo<ProgressGeneralBean> findProgressDefinitionPage(Integer pageNum,
            Integer pageSize) throws GlobalException;

    //////////////////////////////// 历史流程//////////////////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 PageInfo<Object>
     * @Version: 1.0.0
     */
    PageInfo<ProgressGeneralBean> findProgressHistoryPage(Integer pageNum,
            Integer pageSize) throws GlobalException;

    /////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// 流程任务////////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 PageInfo<Object>
     * @Version: 1.0.0
     */
    PageInfo<TaskBean> findProgressTaskPage(Integer pageNum, Integer pageSize,
            String deploymentId, String userIdForCandidateAndAssignee)
            throws GlobalException;

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
     *             业务异常时候出现的异常 Object
     * @Version: 1.0.0
     */
    ProgressGeneralBean findProgress(String processId) throws GlobalException;

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
     *             业务异常时候出现的异常 Object
     * @Version: 1.0.0
     */
    TaskBean findTask(String taskId) throws GlobalException;

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
     *             业务异常时候出现的异常 Object
     * @Version: 1.0.0
     */
    FormDataBean findTaskFormData(String taskId) throws GlobalException;

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
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean executeTask(String taskId, Map<String, Object> variables)
            throws GlobalException;

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
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean claimTask(String taskId) throws GlobalException;

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
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    boolean completeTask(String taskId, Map<String, Object> variables)
            throws GlobalException;
    
    ////////////////////////////////////// 历史流程//////////////////////////////////////////////////
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
     *             业务异常时候出现的异常 void
     * @Version: 1.0.0
     */
    void previewSpeedOfProgress(String processInstanceId)
            throws GlobalException;

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
     *             业务异常时候出现的异常 Object
     * @Version: 1.0.0
     */
    boolean startProgressByIdOrKey(String processDefinitionKey,
            String businessKey, Map<String, Object> variables)
            throws GlobalException;
}
