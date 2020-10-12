package com.hg.knowledgebase.module.repo.entity;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 文档类别
 * @auther: fuyuan
 * @date: 2020/3/19 0019 16:10
 */
public class BaseDocumentCategory
{
    /**
     * 文档类别id
     */
    private String id;

    /**
     * 文档类别名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 所属部门id
     */
    private String deptId;

    /**
     * 审批时间
     */
    private Date addrovalTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 审批状态 0:未审批   1：通过   2：驳回  3：不通过
     */
    private Short status;

    // 审批状态入参
    private List<Integer> list;

    /**
     * 审批意见
     */
    private String approvalOpinion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Date getAddrovalTime() {
        return addrovalTime;
    }

    public void setAddrovalTime(Date addrovalTime) {
        this.addrovalTime = addrovalTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}