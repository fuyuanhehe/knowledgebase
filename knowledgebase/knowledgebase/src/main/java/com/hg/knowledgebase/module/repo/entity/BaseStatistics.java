package com.hg.knowledgebase.module.repo.entity;

import java.util.Date;

/**
 * 功能描述: 文档统计实体
 * @auther: fuyuan
 * @date: 2020/4/30 0030 10:01
 */
public class BaseStatistics
{
    private String id;
      // 文档id
    private String documentId;
     //分类id
    private String categoryId;
    //类型0:下载  1：浏览
    private Integer type;
    // 创建时间
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId == null ? null : documentId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
