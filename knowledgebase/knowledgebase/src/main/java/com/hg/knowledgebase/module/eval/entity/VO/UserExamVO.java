package com.hg.knowledgebase.module.eval.entity.VO;

import java.util.Date;

import com.hg.knowledgebase.module.eval.entity.UserExam;

/**
 * 
 * @Description: 用户考试扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月2日下午4:34:58
 * @Version: 1.0.0
 */
public class UserExamVO extends UserExam
{

    /**
     * 
     */
    private static final long serialVersionUID = 9010090965030871596L;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 已考试条数
     */
    private Integer examedCount;

    /**
     * 总分数
     */
    private Double totalScore;

    /**
     * 总题数
     */
    private Integer totalNum;

    /**
     * 试卷id
     */
    private String testPaperId;

    /**
     * 考试结束时间
     */
    private Date examEndTime;

    /**
     * 题目json
     */
    private String subjects;

    public String getSubjects()
    {
        return subjects;
    }

    public void setSubjects(String subjects)
    {
        this.subjects = subjects;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public Date getExamEndTime()
    {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime)
    {
        this.examEndTime = examEndTime;
    }

    public String getTestPaperId()
    {
        return testPaperId;
    }

    public void setTestPaperId(String testPaperId)
    {
        this.testPaperId = testPaperId;
    }

    public Double getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(Double totalScore)
    {
        this.totalScore = totalScore;
    }

    public Integer getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum)
    {
        this.totalNum = totalNum;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getExamedCount()
    {
        return examedCount;
    }

    public void setExamedCount(Integer examedCount)
    {
        this.examedCount = examedCount;
    }

    public String getExamName()
    {
        return examName;
    }

    public void setExamName(String examName)
    {
        this.examName = examName;
    }

    @Override
    public String toString()
    {
        return "UserExamVO [examName=" + examName + ", userName=" + userName
                + ", examedCount=" + examedCount + ", totalScore=" + totalScore
                + ", totalNum=" + totalNum + ", testPaperId=" + testPaperId
                + ", examEndTime=" + examEndTime + "]";
    }
}
