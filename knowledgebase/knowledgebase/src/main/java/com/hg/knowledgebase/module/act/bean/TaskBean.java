package com.hg.knowledgebase.module.act.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @Description: 流程任务bean
 * @Author: 况克冬
 * @CreateDate: 2020年5月21日上午11:09:02
 * @Version: 1.0.0
 */
public class TaskBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 998274023556543085L;

    private String id;

    private String name;

    private String processInstanceId;

    private String processDefinitionId;

    private String parentTaskId;

    private String formKey;

    private String description;

    private Map<String, Object> taskLocalVariables;

    private Map<String, Object> processVariables;

    private Date dueDate;

    private Date claimTime;

    private Date createTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getParentTaskId()
    {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }

    public String getFormKey()
    {
        return formKey;
    }

    public void setFormKey(String formKey)
    {
        this.formKey = formKey;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Map<String, Object> getTaskLocalVariables()
    {
        return taskLocalVariables;
    }

    public void setTaskLocalVariables(Map<String, Object> taskLocalVariables)
    {
        this.taskLocalVariables = taskLocalVariables;
    }

    public Map<String, Object> getProcessVariables()
    {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables)
    {
        this.processVariables = processVariables;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public Date getClaimTime()
    {
        return claimTime;
    }

    public void setClaimTime(Date claimTime)
    {
        this.claimTime = claimTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public TaskBean(String id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }

    public TaskBean()
    {
        super();
    }

    @Override
    public String toString()
    {
        return "TaskBean [id=" + id + ", name=" + name + ", processInstanceId="
                + processInstanceId + ", processDefinitionId="
                + processDefinitionId + ", parentTaskId=" + parentTaskId
                + ", formKey=" + formKey + ", description=" + description
                + ", taskLocalVariables=" + taskLocalVariables
                + ", processVariables=" + processVariables + ", dueDate="
                + dueDate + ", claimTime=" + claimTime + ", createTime="
                + createTime + "]";
    }
}
