package com.hg.knowledgebase.module.eval.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 用户考试关系实体类
 * @Author: 况克冬
 * @CreateDate: 2020年3月2日下午3:52:41
 * @Version: 1.0.0
 */
public class UserExam implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -6473503397889808593L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 考试id
     */
    private String examId;

    /**
     * 交卷时间
     */
    private String submitPaperTime;

    /**
     * 交卷答案（json）
     */
    private String answers;

    /**
     * 分数
     */
    private Double score;

    /**
     * 考试状态（0：未考；1：已考）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 错误数
     */
    private Integer errorNum;

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

    public Integer getErrorNum()
    {
        return errorNum;
    }

    public void setErrorNum(Integer errorNum)
    {
        this.errorNum = errorNum;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getSubmitTime()
    {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime)
    {
        this.submitTime = submitTime;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getExamId()
    {
        return examId;
    }

    public void setExamId(String examId)
    {
        this.examId = examId;
    }

    public String getSubmitPaperTime()
    {
        return submitPaperTime;
    }

    public void setSubmitPaperTime(String submitPaperTime)
    {
        this.submitPaperTime = submitPaperTime;
    }

    public String getAnswers()
    {
        return answers;
    }

    public void setAnswers(String answers)
    {
        this.answers = answers;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
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
        return "UserExam [userId=" + userId + ", examId=" + examId
                + ", submitPaperTime=" + submitPaperTime + ", answers="
                + answers + ", score=" + score + ", status=" + status
                + ", createTime=" + createTime + ", submitTime=" + submitTime
                + "]";
    }
}
