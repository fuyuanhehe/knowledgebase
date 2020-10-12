package com.hg.knowledgebase.module.community.entity.VO;

import java.util.List;

import com.hg.knowledgebase.module.community.entity.Comment;

/**
 * 
 * @Description: 评论扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:59:34
 * @Version: 1.0.0
 */
public class CommentVO extends Comment
{

    /**
     * 
     */
    private static final long serialVersionUID = -6099248753192143361L;

    /**
     * 评论用户
     */
    private String commentUser;

    /**
     * 评论用户头像
     */
    private String commentUserPortrait;

    /**
     * 文档名称
     */
    private String documentName;

    /**
     * 文档类型名称
     */
    private String documentTypeName;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 子集评论（回复）
     */
    private List<CommentVO> childrenComment;

    public List<CommentVO> getChildrenComment()
    {
        return childrenComment;
    }

    public void setChildrenComment(List<CommentVO> childrenComment)
    {
        this.childrenComment = childrenComment;
    }

    public String getDocumentTypeName()
    {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName)
    {
        this.documentTypeName = documentTypeName;
    }

    public String getDocumentName()
    {
        return documentName;
    }

    public void setDocumentName(String documentName)
    {
        this.documentName = documentName;
    }

    public String getTopicName()
    {
        return topicName;
    }

    public void setTopicName(String topicName)
    {
        this.topicName = topicName;
    }

    public String getCommentUser()
    {
        return commentUser;
    }

    public void setCommentUser(String commentUser)
    {
        this.commentUser = commentUser;
    }

    public String getCommentUserPortrait()
    {
        return commentUserPortrait;
    }

    public void setCommentUserPortrait(String commentUserPortrait)
    {
        this.commentUserPortrait = commentUserPortrait;
    }

    @Override
    public String toString()
    {
        return "CommentVO [commentUser=" + commentUser
                + ", commentUserPortrait=" + commentUserPortrait
                + ", documentName=" + documentName + ", documentTypeName="
                + documentTypeName + ", topicName=" + topicName + "]";
    }
}
