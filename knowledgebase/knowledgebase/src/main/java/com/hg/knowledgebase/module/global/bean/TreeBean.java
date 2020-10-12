package com.hg.knowledgebase.module.global.bean;

import java.util.List;

/**
 * 
 * @Description: 树实体bean
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午4:18:06
 * @Version: 1.0.0
 */
public class TreeBean
{

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父id
     */
    private String pid;

    /**
     * 类型
     */
    private String type;

    /**
     * 地址
     */
    private String url;

    /**
     * 图标地址
     */
    private String iconUrl;

    /**
     * 预留字段（备用）
     */
    private Object reserve;

    private List<TreeBean> children;

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

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public Object getReserve()
    {
        return reserve;
    }

    public void setReserve(Object reserve)
    {
        this.reserve = reserve;
    }

    public List<TreeBean> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeBean> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "TreeBean [id=" + id + ", name=" + name + ", pid=" + pid
                + ", type=" + type + ", url=" + url + ", iconUrl=" + iconUrl
                + ", reserve=" + reserve + ", children=" + children + "]";
    }
}
