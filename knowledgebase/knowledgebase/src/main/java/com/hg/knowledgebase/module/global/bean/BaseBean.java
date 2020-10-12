package com.hg.knowledgebase.module.global.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 基础bean
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午3:52:06
 * @Version: 1.0.0
 */
public class BaseBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5169150165414150561L;

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 开始创建时间
     */
    private Date beginCreateTime;

    /**
     * 结束创建时间
     */
    private Date endCreateTime;

    /**
     * 扩展查询（非数据库字段）
     */
    private String search;

    /**
     * 扩展排序查询（非数据库字段）
     */
    private String orderBy;

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

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

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getSearch()
    {
        return search;
    }

    public void setSearch(String search)
    {
        this.search = search;
    }

    public Date getBeginCreateTime()
    {
        return beginCreateTime;
    }

    public void setBeginCreateTime(Date beginCreateTime)
    {
        this.beginCreateTime = beginCreateTime;
    }

    public Date getEndCreateTime()
    {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime)
    {
        this.endCreateTime = endCreateTime;
    }

    @Override
    public String toString()
    {
        return "BaseBean [id=" + id + ", name=" + name + ", createUser="
                + createUser + ", createTime=" + createTime + ", updateUser="
                + updateUser + ", updateTime=" + updateTime
                + ", beginCreateTime=" + beginCreateTime + ", endCreateTime="
                + endCreateTime + ", search=" + search + "]";
    }
}
