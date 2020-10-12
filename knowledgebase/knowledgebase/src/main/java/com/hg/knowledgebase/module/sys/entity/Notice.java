package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 通知公告实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:14:46
 * @Version: 1.0.0
 */
public class Notice extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = -6811225446225681339L;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    private String introduction;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    @Override
    public String toString()
    {
        return "Notice [name=" + name + ", introduction=" + introduction + "]";
    }
}
