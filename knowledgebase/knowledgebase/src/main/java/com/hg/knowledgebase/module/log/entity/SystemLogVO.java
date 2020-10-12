/**
 * 
 */
package com.hg.knowledgebase.module.log.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @TODO SystemLogVO
 * @author Ye.Qiang
 * @date 2020年3月17日
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SystemLogVO extends SystemLogDO
{
    /** 每页数据条数 **/
    private String pageSize;
    /** 页码号 **/
    private String pageNum;
    /** 查询日志开始时间 **/
    
    private String logStartTime;
    /** 查询日志结束时间 **/
    private String logEndTime;

    public String getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(String pageNum)
    {
        this.pageNum = pageNum;
    }

    public String getLogStartTime()
    {
        return logStartTime;
    }

    public void setLogStartTime(String logStartTime)
    {
        this.logStartTime = logStartTime;
    }

    public String getLogEndTime()
    {
        return logEndTime;
    }

    public void setLogEndTime(String logEndTime)
    {
        this.logEndTime = logEndTime;
    }

    @Override
    public String toString()
    {
        return "SystemLogVO [pageSize=" + pageSize + ", pageNum=" + pageNum + ", logStartTime=" + logStartTime
                + ", logEndTime=" + logEndTime + ", getPageSize()=" + getPageSize() + ", getPageNum()=" + getPageNum()
                + ", getLogStartTime()=" + getLogStartTime() + ", getLogEndTime()=" + getLogEndTime() + ", getId()="
                + getId() + ", getCoder()=" + getCoder() + ", getOperationUser()=" + getOperationUser()
                + ", getOperationUserDept()=" + getOperationUserDept() + ", getOperationUserIp()="
                + getOperationUserIp() + ", getOperationTime()=" + getOperationTime() + ", getOperationMsg()="
                + getOperationMsg() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}
