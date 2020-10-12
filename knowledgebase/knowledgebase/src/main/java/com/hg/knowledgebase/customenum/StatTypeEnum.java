package com.hg.knowledgebase.customenum;

import org.springframework.util.StringUtils;

/**
 * 
 * @Description: 统计类型枚举
 * @Author: 况克冬
 * @CreateDate: 2020年3月16日上午10:12:58
 * @Version: 1.0.0
 */
public enum StatTypeEnum
{
    /**
     * 统计年
     */
    YEAR,

    /**
     * 统计月
     */
    MONTH,

    /**
     * 日
     */
    DAY,
    /**
     * 半年
     */
    HALF_YEAR,
    /**
     * 季度
     */
    QUARTER,
    /**
     * 周
     */
    WEEK,
    /**
     * 自定义
     */
    CUSTOM;

    /**
     * 
     * @Description: 获取统计类型
     * @Author: 况克冬
     * @CreateDate: 2020年3月17日下午5:35:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月17日下午5:35:57
     * @UpdateRemark: 修改内容
     * @param type
     * @return StatTypeEnum
     * @Version: 1.0.0
     */
    public static StatTypeEnum getStatTypeEnum(String type)
    {
        StatTypeEnum statType = null;
        if (StringUtils.isEmpty(type))
        {
            return statType;
        }
        for (StatTypeEnum ste : StatTypeEnum.values())
        {
            if (ste.toString().equalsIgnoreCase(type))
            {
                statType = ste;
                break;
            }
        }
        return statType;
    }
}
