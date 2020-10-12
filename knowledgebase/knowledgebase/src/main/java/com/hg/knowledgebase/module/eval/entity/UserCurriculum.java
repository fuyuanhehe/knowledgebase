package com.hg.knowledgebase.module.eval.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 用户课程关系实体
 * @Author: 况克冬
 * @CreateDate: 2020年3月3日上午9:58:08
 * @Version: 1.0.0
 */
public class UserCurriculum implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -1802664931420572733L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 课程id
     */
    private String curriculumId;

    /**
     * 学习进度（%）
     */
    private Double progress;

    /**
     * 学习记录（json数组字符串）
     */
    private String learnRecord;

    /**
     * 学习状态（0：未学习；1：学习中；2：已结束）
     */
    private Integer status;

    /**
     * 完成学习状态（0：未完成；1：已完成）
     */
    private Integer completeStatus;

    /**
     * 学习时间（备用）
     */
    private Date learnTime;

    public Integer getCompleteStatus()
    {
        return completeStatus;
    }

    public void setCompleteStatus(Integer completeStatus)
    {
        this.completeStatus = completeStatus;
    }

    public String getLearnRecord()
    {
        return learnRecord;
    }

    public void setLearnRecord(String learnRecord)
    {
        this.learnRecord = learnRecord;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getCurriculumId()
    {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId)
    {
        this.curriculumId = curriculumId;
    }

    public Double getProgress()
    {
        return progress;
    }

    public void setProgress(Double progress)
    {
        this.progress = progress;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Date getLearnTime()
    {
        return learnTime;
    }

    public void setLearnTime(Date learnTime)
    {
        this.learnTime = learnTime;
    }

    @Override
    public String toString()
    {
        return "UserCurriculum [userId=" + userId + ", curriculumId="
                + curriculumId + ", progress=" + progress + ", status=" + status
                + ", learnTime=" + learnTime + "]";
    }
}
