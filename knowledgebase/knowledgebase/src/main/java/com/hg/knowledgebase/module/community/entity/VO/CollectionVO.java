package com.hg.knowledgebase.module.community.entity.VO;

import com.hg.knowledgebase.module.community.entity.Collections;

/**
 * 
 * @Description: 收藏扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:59:34
 * @Version: 1.0.0
 */
public class CollectionVO extends Collections
{

    /**
     * 
     */
    private static final long serialVersionUID = 4706804356105191072L;

    /**
     * 文档名称
     */
    private String documentName;

    /**
     * 话题名称
     */
    private String topicName;

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

    @Override
    public String toString()
    {
        return "CollectionVO [documentName=" + documentName + ", topicName="
                + topicName + "]";
    }
}
