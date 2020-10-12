package com.hg.knowledgebase.module.stat.bean;

import java.util.Map;

/**
 * 
* @Description: 统计条件
* @Author: 况克冬
* @CreateString: 2020年3月5日下午4:48:15
* @Version: 1.0.0
 */
public class StatisticsCondition
{

    /**
     * 统计类型（参考com.hg.knowledgebase.customenum.StatTypeEnum）
     */
    private String type;
    
    /**
     * 自定义类型（type值为custom时有效，取值类型（1：年，2：月，3：日））
     */
    private Integer customType;
    
    /**
     * 统计开始时间
     */
    private String beginTime;
    
    /**
     * 统计结束时间
     */
    private String endTime;

    /**
     * 其他条件
     */
    private Map<String, Object> otherCondition;
    
    /**
     * 统计结果日期
     */
    private String data;
    
    /**
     * 统计结果数
     */
    private Integer count;
    
    public Integer getCustomType()
    {
        return customType;
    }

    public void setCustomType(Integer customType)
    {
        this.customType = customType;
    }

    public Map<String, Object> getOtherCondition()
    {
        return otherCondition;
    }

    public void setOtherCondition(Map<String, Object> otherCondition)
    {
        this.otherCondition = otherCondition;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(String beginTime)
    {
        this.beginTime = beginTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    @Override
    public String toString()
    {
        return "StatisticsCondition [type=" + type + ", customType=" + customType + ", beginTime=" + beginTime
                + ", endTime=" + endTime + ", otherCondition=" + otherCondition + ", data=" + data + ", count=" + count
                + "]";
    }
}
