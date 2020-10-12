package com.hg.knowledgebase.module.proj.entity;

import java.io.Serializable;

/**
 * 
* @Description: 用户项目实体
* @Author: 况克冬
* @CreateDate: 2020年3月5日上午10:58:59
* @Version: 1.0.0
 */
public class UserProject implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -2031087310200166651L;

    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 关系类型（0：参与；1：推送）
     */
    private Integer type;
    
    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    @Override
    public String toString()
    {
        return "UserProject [userId=" + userId + ", projectId=" + projectId + ", type=" + type + "]";
    }
}
