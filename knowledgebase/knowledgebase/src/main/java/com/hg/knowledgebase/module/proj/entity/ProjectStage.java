package com.hg.knowledgebase.module.proj.entity;

import java.util.Date;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 *
* @Description: 项目阶段
* @Author: 况克冬
* @CreateDate: 2020年4月1日下午4:34:26
* @Version: 1.0.0
 */
public class ProjectStage extends BaseBean
{

    /**
     *
     */
    private static final long serialVersionUID = -7323703872088657872L;

    /**
     * 责任人ID
     */
    private String blameUserId;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 进度      { value: '0', label: '进行中' },
     *                 { value: '1', label: '已完工' },
     *                 { value: '2', label: '失败' },
     *                 { value: '4', label: '暂停' }
     */
    private String progress;

    /**
     * 工期
     */
    private Integer validDay;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    public String getBlameUserId()
    {
        return blameUserId;
    }

    public void setBlameUserId(String blameUserId)
    {
        this.blameUserId = blameUserId;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProgress()
    {
        return progress;
    }

    public void setProgress(String progress)
    {
        this.progress = progress;
    }

    public Integer getValidDay()
    {
        return validDay;
    }

    public void setValidDay(Integer validDay)
    {
        this.validDay = validDay;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    @Override
    public String toString()
    {
        return "ProjectStage [blameUserId=" + blameUserId + ", projectId=" + projectId + ", progress=" + progress
                + ", validDay=" + validDay + ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
    }
}
