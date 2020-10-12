package com.hg.knowledgebase.module.community.entity.VO;

import com.hg.knowledgebase.module.community.entity.Topic;

/**
 * 
 * @Description: 话题扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:59:34
 * @Version: 1.0.0
 */
public class TopicVO extends Topic
{

    /**
     * 
     */
    private static final long serialVersionUID = -4229275467324958295L;
    /**
     * 圈子名称
     */
    private String circleName;

    /**
     * 创建用户名
     */
    private String createUserName;

    /**
     * 评论次数
     */
    private Integer commentCount;

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }

    public Integer getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount)
    {
        this.commentCount = commentCount;
    }

    public String getCircleName()
    {
        return circleName;
    }

    public void setCircleName(String circleName)
    {
        this.circleName = circleName;
    }

    @Override
    public String toString()
    {
        return "TopicVO [circleName=" + circleName + ", createUserName="
                + createUserName + ", commentCount=" + commentCount + "]";
    }
}
