package com.hg.knowledgebase.customenum;

import org.springframework.util.StringUtils;

/**
 * 
 * @Description: 性别枚举
 * @Author: 况克冬
 * @CreateDate: 2020年2月28日下午1:33:55
 * @Version: 1.0.0
 */
public enum SexEnum
{

    /**
     * 男
     */
    MAN(0, "男"),
    /**
     * 女
     */
    WOMAN(1, "女");

    /**
     * 键
     */
    private Integer key;

    /**
     * 值
     */
    private String value;

    public int getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    private SexEnum(Integer key, String value)
    {
        this.key = key;
        this.value = value;
    }

    private SexEnum(String value, Integer key)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * 
     * @Description: 根据值获取键
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午1:46:04
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午1:46:04
     * @UpdateRemark: 修改内容
     * @param value
     * @return String
     * @Version: 1.0.0
     */
    public Integer getKeyByValue(String value)
    {
        Integer key = null;
        if (!StringUtils.isEmpty(value))
        {
            for (SexEnum sexEnum : SexEnum.values())
            {
                if (value.equals(sexEnum.value))
                {
                    key = sexEnum.key;
                    break;
                }
            }
        }
        return key;
    }
}
