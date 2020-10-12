package com.hg.knowledgebase.module.repo.entity.VO;

import java.util.Date;
import java.util.List;

import com.hg.knowledgebase.module.repo.entity.BaseDocument;

/**
 * 功能描述: 文档管理vo
 * @auther: fuyuan
 * @date: 2020/3/16 0016 11:53
 */
public class BaseDocumentVO extends BaseDocument
{
    private List<BaseDocument> children;

    /**
     * 创建人名称
     */
    private String createUserName;
    
    /**
     * 文档类别名称
     */
    private String categoryName;

     // 部门名称
    private String depName;
     //sql 排序用
    private int number;

    // 开始时间
    private Date beginCreateTime;
    // 结束时间
    private Date endCreateTime;

    public Date getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(Date beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public BaseDocumentVO(){}

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public List<BaseDocument> getChildren() {
        return children;
    }

    public void setChildren(List<BaseDocument> children) {
        this.children = children;
    }
}
