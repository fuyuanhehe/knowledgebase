package com.hg.knowledgebase.module.eval.entity.VO;

import java.util.Set;

import com.hg.knowledgebase.module.eval.entity.Exam;

/**
 * 
 * @Description: 考试扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月2日下午3:27:40
 * @Version: 1.0.0
 */
public class ExamVO extends Exam
{

    /**
     * 
     */
    private static final long serialVersionUID = 8122576753849350206L;

    /**
     * 试卷名称
     */
    private String testPaperName;

    /**
     * 考试用户id集合
     */
    private Set<String> users;

    /**
     * 试卷题json
     */
    private String testPaperSubjects;

    /**
     * 总题目数
     */
    private Integer totalSubjectCount;

    /**
     * 总分数
     */
    private Double totalScore;

    /**
     * 总人数
     */
    private Integer userCount;

    /**
     * 平均分
     */
    private Double averageScore;

    /**
     * 最高分
     */
    private Double maxScore;

    /**
     * 最低分
     */
    private Double minScore;

    /**
     * 求和分数
     */
    private Double sumScore;

    public Double getSumScore()
    {
        return sumScore;
    }

    public void setSumScore(Double sumScore)
    {
        this.sumScore = sumScore;
    }

    public Integer getTotalSubjectCount()
    {
        return totalSubjectCount;
    }

    public void setTotalSubjectCount(Integer totalSubjectCount)
    {
        this.totalSubjectCount = totalSubjectCount;
    }

    public Double getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(Double totalScore)
    {
        this.totalScore = totalScore;
    }

    public Integer getUserCount()
    {
        return userCount;
    }

    public void setUserCount(Integer userCount)
    {
        this.userCount = userCount;
    }

    public Double getAverageScore()
    {
        return averageScore;
    }

    public void setAverageScore(Double averageScore)
    {
        this.averageScore = averageScore;
    }

    public Double getMaxScore()
    {
        return maxScore;
    }

    public void setMaxScore(Double maxScore)
    {
        this.maxScore = maxScore;
    }

    public Double getMinScore()
    {
        return minScore;
    }

    public void setMinScore(Double minScore)
    {
        this.minScore = minScore;
    }

    public String getTestPaperSubjects()
    {
        return testPaperSubjects;
    }

    public void setTestPaperSubjects(String testPaperSubjects)
    {
        this.testPaperSubjects = testPaperSubjects;
    }

    public String getTestPaperName()
    {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName)
    {
        this.testPaperName = testPaperName;
    }

    public Set<String> getUsers()
    {
        return users;
    }

    public void setUsers(Set<String> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "ExamVO [testPaperName=" + testPaperName + ", users=" + users
                + ", testPaperSubjects=" + testPaperSubjects
                + ", totalSubjectCount=" + totalSubjectCount + ", totalScore="
                + totalScore + ", userCount=" + userCount + ", averageScore="
                + averageScore + ", maxScore=" + maxScore + ", minScore="
                + minScore + ", sumScore=" + sumScore + "]";
    }
}
