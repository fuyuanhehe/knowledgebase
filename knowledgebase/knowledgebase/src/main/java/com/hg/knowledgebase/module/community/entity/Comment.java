package com.hg.knowledgebase.module.community.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 评论实体类
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:58:06
 * @Version: 1.0.0
 */
public class Comment extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = 2733403485070961014L;

    /**
     * 评论父id
     */
    private String pid;

    /**
     * 评论类型（0：文档类型；1：话题类型）
     */
    private Integer type;

    /**
     * 文档ID
     */
    private String documentId;

    /**
     * 话题ID
     */
    private String topicId;

    /**
     * 评论类容
     */
    private String content;

    /**
     * 回复用户
     */
    private String replyUser;

    /**
     * 评分
     */
    private Double score;

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }

    public String getReplyUser()
    {
        return replyUser;
    }

    public void setReplyUser(String replyUser)
    {
        this.replyUser = replyUser;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getDocumentId()
    {
        return documentId;
    }

    public void setDocumentId(String documentId)
    {
        this.documentId = documentId;
    }

    public String getTopicId()
    {
        return topicId;
    }

    public void setTopicId(String topicId)
    {
        this.topicId = topicId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Comment [pid=" + pid + ", type=" + type + ", documentId="
                + documentId + ", topicId=" + topicId + ", content=" + content
                + ", replyUser=" + replyUser + "]";
    }
}
