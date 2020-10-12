package com.hg.knowledgebase.module.eval.entity.VO;

import java.util.Set;

import com.hg.knowledgebase.module.eval.entity.Curriculum;

/**
 * 
 * @Description: 课程扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月3日上午10:06:44
 * @Version: 1.0.0
 */
public class CurriculumVO extends Curriculum
{

    /**
     * 
     */
    private static final long serialVersionUID = -7157246902913114561L;
    
    /**
     * 用户id集合
     */
    private Set<String> userIds;

    public Set<String> getUserIds()
    {
        return userIds;
    }

    public void setUserIds(Set<String> userIds)
    {
        this.userIds = userIds;
    }

    @Override
    public String toString()
    {
        return "CurriculumVO [userIds=" + userIds + "]";
    }
}
