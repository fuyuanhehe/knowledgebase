package com.hg.knowledgebase.module.act.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 流程通用bean
 * @Author: 况克冬
 * @CreateDate: 2020年5月20日上午10:53:37
 * @Version: 1.0.0
 */
public class ProgressGeneralBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -7728084033982589891L;

    private String id;

    private String key;

    private String name;

    private Date time;

    private String definitionId;

    private String deploymentId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public String getDefinitionId()
    {
        return definitionId;
    }

    public void setDefinitionId(String definitionId)
    {
        this.definitionId = definitionId;
    }

    public String getDeploymentId()
    {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId)
    {
        this.deploymentId = deploymentId;
    }

    public ProgressGeneralBean(String id, String key, String name)
    {
        super();
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public ProgressGeneralBean(String id, String key, String name, Date time)
    {
        super();
        this.id = id;
        this.key = key;
        this.name = name;
        this.time = time;
    }

    public ProgressGeneralBean(String id, String key, String name,
            String definitionId)
    {
        super();
        this.id = id;
        this.key = key;
        this.name = name;
        this.definitionId = definitionId;
    }

    public ProgressGeneralBean()
    {
        super();
    }

    @Override
    public String toString()
    {
        return "ProgressGeneralBean [id=" + id + ", key=" + key + ", name="
                + name + ", time=" + time + ", definitionId=" + definitionId
                + "]";
    }
}
