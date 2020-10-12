/**
 * 
 */
package com.hg.knowledgebase.module.log.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @TODO
 * @author Ye.Qiang
 * @date 2020年3月16日
 */
public class SystemLogDO
{

    /** 数据id **/
    private String id;
    /** 数据编码 **/
    private String coder;
    /** 操作用户姓名 **/
    private String operationUser;
    /** 操作用户所在部门 **/
    private String operationUserDept;
    /** 操作用户操作IP地址 **/
    private String operationUserIp;
    /** 操作时间 **/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy:MM:dd HH:mm:ss")
    private Date operationTime;
    /** 操作方式 **/
    private String operationMsg;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setOperationTime(Date operationTime)
    {
        this.operationTime = operationTime;
    }

    public String getCoder()
    {
        return coder;
    }

    public void setCoder(String coder)
    {
        this.coder = coder;
    }

    public String getOperationUser()
    {
        return operationUser;
    }

    public void setOperationUser(String operationUser)
    {
        this.operationUser = operationUser;
    }

    public String getOperationUserDept()
    {
        return operationUserDept;
    }

    public void setOperationUserDept(String operationUserDept)
    {
        this.operationUserDept = operationUserDept;
    }

    public String getOperationUserIp()
    {
        return operationUserIp;
    }

    public void setOperationUserIp(String operationUserIp)
    {
        this.operationUserIp = operationUserIp;
    }

    public Date getOperationTime()
    {
        return operationTime;
    }

    public String getOperationMsg()
    {
        return operationMsg;
    }

    public void setOperationMsg(String operationMsg)
    {
        this.operationMsg = operationMsg;
    }

}
