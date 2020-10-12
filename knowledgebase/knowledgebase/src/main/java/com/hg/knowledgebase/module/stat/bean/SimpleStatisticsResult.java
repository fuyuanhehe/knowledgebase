package com.hg.knowledgebase.module.stat.bean;

import java.io.Serializable;
import java.util.List;

import com.hg.knowledgebase.module.global.bean.KeyValueBean;

/**
 * 
* @Description: 简单统计结果实体
* @Author: 况克冬
* @CreateDate: 2020年3月3日下午2:13:35
* @Version: 1.0.0
 */
public class SimpleStatisticsResult implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1496668023527053536L;

    /**
     * 统计x轴数据
     */
    private List<String> dataAxis;
    
    /**
     * 统计数据值
     */
    private List<KeyValueBean> dataSeries;

    public List<String> getDataAxis()
    {
        return dataAxis;
    }

    public void setDataAxis(List<String> dataAxis)
    {
        this.dataAxis = dataAxis;
    }

    public List<KeyValueBean> getDataSeries()
    {
        return dataSeries;
    }

    public void setDataSeries(List<KeyValueBean> dataSeries)
    {
        this.dataSeries = dataSeries;
    }

    @Override
    public String toString()
    {
        return "SimpleStatistics [dataAxis=" + dataAxis + ", dataSeries=" + dataSeries + "]";
    }
}
