package com.hg.knowledgebase.module.eval.entity;

import java.util.Date;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 课程实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Curriculum extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = 4408397128035994484L;

    /**
     * 课程封面地址
     */
    private String coverUrl;

    /**
     * 课程简介
     */
    private String introduction;

    /**
     * 课程资源（json）
     */
    private String resources;

    /**
     * 学习开始时间
     */
    private Date learnBeginTime;

    /**
     * 学习结束时间
     */
    private Date learnEndTime;

    /**
     * 学习状态（0：未开始；1：学习中；2：已结束）
     */
    private Integer status;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getCoverUrl()
    {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl)
    {
        this.coverUrl = coverUrl;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getResources()
    {
        return resources;
    }

    public void setResources(String resources)
    {
        this.resources = resources;
    }

    public Date getLearnBeginTime()
    {
        return learnBeginTime;
    }

    public void setLearnBeginTime(Date learnBeginTime)
    {
        this.learnBeginTime = learnBeginTime;
    }

    public Date getLearnEndTime()
    {
        return learnEndTime;
    }

    public void setLearnEndTime(Date learnEndTime)
    {
        this.learnEndTime = learnEndTime;
    }

    @Override
    public String toString()
    {
        return "Curriculum [coverUrl=" + coverUrl + ", introduction="
                + introduction + ", resources=" + resources
                + ", learnBeginTime=" + learnBeginTime + ", learnEndTime="
                + learnEndTime + "]";
    }
}
