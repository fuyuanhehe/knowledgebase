package com.hg.knowledgebase.module.community.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 收藏实体类 注意：由于当前Collection和java.util.Collection重名，因此修改为Collections
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:58:06
 * @Version: 1.0.0
 */
public class Collections extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = 5952265274775749409L;

    /**
     * 收藏类型（0：文档类型；1：话题类型）
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

    @Override
    public String toString()
    {
        return "Collection [type=" + type + ", documentId=" + documentId
                + ", topicId=" + topicId + "]";
    }
}
