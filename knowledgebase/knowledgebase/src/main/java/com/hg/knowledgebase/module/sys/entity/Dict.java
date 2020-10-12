package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 字典实体类
 * @Author: 况克冬
 * @CreateDate: 2020年3月16日下午1:52:13
 * @Version: 1.0.0
 */
public class Dict extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = 3542618349542834458L;

    /**
     * 字典编号
     */
    private String coder;

    /**
     * 字典组编号
     */
    private String groupCoder;

    /**
     * 字典组名称
     */
    private String groupName;

    /**
     * 字典权重（用作排序，权重大在前）
     */
    private Integer weight;

    /**
     * 字典简介
     */
    private String introduction;

    /**
     * 备用字段
     */
    private String reserve;

    /**
     * 字典类型（0：自定义；1：系统）
     */
    private Integer type;

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getCoder()
    {
        return coder;
    }

    public void setCoder(String coder)
    {
        this.coder = coder;
    }

    public String getGroupCoder()
    {
        return groupCoder;
    }

    public void setGroupCoder(String groupCoder)
    {
        this.groupCoder = groupCoder;
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getReserve()
    {
        return reserve;
    }

    public void setReserve(String reserve)
    {
        this.reserve = reserve;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Dict [coder=" + coder + ", groupCoder=" + groupCoder
                + ", weight=" + weight + ", introduction=" + introduction
                + ", reserve=" + reserve + ", type=" + type + "]";
    }
}
