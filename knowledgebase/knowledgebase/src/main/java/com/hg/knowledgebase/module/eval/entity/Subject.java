package com.hg.knowledgebase.module.eval.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 题目实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Subject extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 题目类型（0：单选；1：多选;2：判断）
     */
    private Integer type;

    /**
     * 题目难度
     */
    private String difficulty;

    /**
     * 题目分值
     */
    private Double score;

    /**
     * 题目解析
     */
    private String analysis;

    /**
     * 题选项（json）
     */
    private String options;

    /**
     * 答案（json）
     */
    private String answers;

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public String getAnalysis()
    {
        return analysis;
    }

    public void setAnalysis(String analysis)
    {
        this.analysis = analysis;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getAnswers()
    {
        return answers;
    }

    public void setAnswers(String answers)
    {
        this.answers = answers;
    }

    @Override
    public String toString()
    {
        return "Subject [type=" + type + ", difficulty=" + difficulty
                + ", score=" + score + ", analysis=" + analysis + ", options="
                + options + ", answers=" + answers + "]";
    }
}
