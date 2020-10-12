package com.hg.knowledgebase.module.proj.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
* @Description: 项目实体类
* @Author: 况克冬
* @CreateDate: 2020年2月27日下午12:22:11
* @Version: 1.0.0
 */
public class Project extends BaseBean
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1036830042646811612L;

    /**
     * 项目类型（0：内部项目；1：外部项目）
     */
    private Integer type;
    
    /**
     * 项目总金额
     */
    private BigDecimal totalMoney;

    /**
     * 项目开始时间
     */
    private Date beginDate;
    
    /**
     * 项目结束时间
     */
    private Date endDate;

    /**
     * 撤销时间
     */
    private Date revokeTime;
    
    /**
     * 审核通过时间
     */
    private Date auditTime;
    
    /**
     * 项目期限（天）
     */
    private Integer term;

   // 审核状态 0: 通过  1：不通过   2：未审核
    private Integer examineStatus;

    // 审核信息
    private String examineMsg;


    private String reserved;

    private String reserved2;

    private String reserved3;


    /**
     * 项目状态（-1：回收站；0：等待开始；1：进行中；2：已完工      ）
     */
    private Integer status;

    /**
     * 项目阶段
     */
    private String stage;
    
    /**
     * 项目简介
     */
    private String introduction;
    
    /**
     * 客户名称
     */
    private String customer;
    
    /**
     * 客户对接人
     */
    private String conscientiousUser;
    
    /**
     * 客户对接人手机号
     */
    private String phone;
    
    /**
     * 客户对接人座机
     */
    private String landline;
    
    /**
     * 客户地址
     */
    private String customerAddr;
    
    /**
     * 项目附件（多个文件分号隔开）
     */
    private String enclosures;

    /**
     * 风险描述
     */
    private String riskDescription;
    
    /**
     * 流程id
     */
    private String procId;

    // 项目负责人
    private String chargePerson;

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getRiskDescription()
    {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription)
    {
        this.riskDescription = riskDescription;
    }

    public String getProcId()
    {
        return procId;
    }

    public void setProcId(String procId)
    {
        this.procId = procId;
    }

    public Date getRevokeTime()
    {
        return revokeTime;
    }

    public void setRevokeTime(Date revokeTime)
    {
        this.revokeTime = revokeTime;
    }

    public Date getAuditTime()
    {
        return auditTime;
    }

    public void setAuditTime(Date auditTime)
    {
        this.auditTime = auditTime;
    }

    public String getStage()
    {
        return stage;
    }

    public void setStage(String stage)
    {
        this.stage = stage;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public BigDecimal getTotalMoney()
    {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney)
    {
        this.totalMoney = totalMoney;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Integer getTerm()
    {
        return term;
    }

    public void setTerm(Integer term)
    {
        this.term = term;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getCustomer()
    {
        return customer;
    }

    public void setCustomer(String customer)
    {
        this.customer = customer;
    }

    public String getConscientiousUser()
    {
        return conscientiousUser;
    }

    public void setConscientiousUser(String conscientiousUser)
    {
        this.conscientiousUser = conscientiousUser;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getLandline()
    {
        return landline;
    }

    public void setLandline(String landline)
    {
        this.landline = landline;
    }

    public String getCustomerAddr()
    {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr)
    {
        this.customerAddr = customerAddr;
    }

    public String getEnclosures()
    {
        return enclosures;
    }

    public void setEnclosures(String enclosures)
    {
        this.enclosures = enclosures;
    }

    @Override
    public String toString()
    {
        return "Project [type=" + type + ", totalMoney=" + totalMoney + ", beginDate=" + beginDate + ", endDate="
                + endDate + ", revokeTime=" + revokeTime + ", auditTime=" + auditTime + ", term=" + term + ", status="
                + status + ", stage=" + stage + ", introduction=" + introduction + ", customer=" + customer
                + ", conscientiousUser=" + conscientiousUser + ", phone=" + phone + ", landline=" + landline
                + ", customerAddr=" + customerAddr + ", enclosures=" + enclosures + ", procId=" + procId + "]";
    }


    public String getExamineMsg() {
        return examineMsg;
    }

    public void setExamineMsg(String examineMsg) {
        this.examineMsg = examineMsg;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }
}
