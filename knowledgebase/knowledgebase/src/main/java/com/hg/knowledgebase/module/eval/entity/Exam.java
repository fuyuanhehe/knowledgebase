package com.hg.knowledgebase.module.eval.entity;

import java.util.Date;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 考试实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Exam extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 考试状态（0：未开始；1：进行中；2：已结束）
     */
    private Integer status;

    /**
     * 考试开始时间
     */
    private Date beginTime;

    /**
     * 考试结束时间
     */
    private Date endTime;

    /**
     * 答题时间
     */
    private Integer answerTime;

    /**
     * 试卷id
     */
    private String testPaperId;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getTestPaperId()
    {
        return testPaperId;
    }

    public void setTestPaperId(String testPaperId)
    {
        this.testPaperId = testPaperId;
    }

    public Integer getAnswerTime()
    {
        return answerTime;
    }

    public void setAnswerTime(Integer answerTime)
    {
        this.answerTime = answerTime;
    }

    @Override
    public String toString()
    {
        return "Exam [status=" + status + ", beginTime=" + beginTime
                + ", endTime=" + endTime + ", answerTime=" + answerTime
                + ", testPaperId=" + testPaperId + "]";
    }
}
