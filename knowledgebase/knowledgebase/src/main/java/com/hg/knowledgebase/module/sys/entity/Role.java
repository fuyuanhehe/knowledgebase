package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 角色实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Role extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 角色描述
     */
    private String describe;

    /**
     * 角色状态（0：禁用；1：启用）
     */
    private Integer status;

    public String getDescribe()
    {
        return describe;
    }

    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Role [describe=" + describe + ", status=" + status + "]";
    }
}
