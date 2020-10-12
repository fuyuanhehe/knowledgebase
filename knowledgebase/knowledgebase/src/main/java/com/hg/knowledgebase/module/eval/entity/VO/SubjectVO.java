package com.hg.knowledgebase.module.eval.entity.VO;

import com.hg.knowledgebase.module.eval.entity.Subject;

/**
 * 
 * @Description: 题目扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class SubjectVO extends Subject
{

    /**
     * 
     */
    private static final long serialVersionUID = -8327659230789921633L;

    /**
     * 题目难度名称
     */
    private String difficultyName;

    public String getDifficultyName()
    {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName)
    {
        this.difficultyName = difficultyName;
    }
}
