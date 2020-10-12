package com.hg.knowledgebase.module.repo.entity;

import java.util.Date;

/**
 * 功能描述: 记录实体
 * @auther: fuyuan
 * @date: 2020/3/31 0031 15:12
 */
public class BusDocumentRecord
{
    /**
     * 文档记录ID
     */
    private String id;

    /**
     * 文档ID
     */
    private String documentId;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 记录类型（0：编辑；1：下载；2：纠错）
     */
    private Short type;

    /**
     * 状态（0：未确认；1：已确认）
     */
    private Short status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     *
     */
    private Date createTime;

    /**
     * 评论所属部门 sql用
     */
    private String reserve1;

    /**
     * 所属文件名称
     */
    private String reserve2;

    /**
     * 文件所属部门
     */
    private String reserve3;

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }
}