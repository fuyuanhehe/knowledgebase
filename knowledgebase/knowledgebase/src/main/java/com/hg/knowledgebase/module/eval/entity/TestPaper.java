package com.hg.knowledgebase.module.eval.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 试卷实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class TestPaper extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = -1818544914632862510L;

    /**
     * 试卷题目（json）
     */
    private String subjects;

    /**
     * 题目总数量
     */
    private Integer totalNum;

    /**
     * 题目总分
     */
    private Double totalScore;

    public String getSubjects()
    {
        return subjects;
    }

    public void setSubjects(String subjects)
    {
        this.subjects = subjects;
    }

    public Integer getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum)
    {
        this.totalNum = totalNum;
    }

    public Double getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(Double totalScore)
    {
        this.totalScore = totalScore;
    }

    @Override
    public String toString()
    {
        return "TestPaper [subjects=" + subjects + ", totalNum=" + totalNum
                + ", totalScore=" + totalScore + "]";
    }
}
