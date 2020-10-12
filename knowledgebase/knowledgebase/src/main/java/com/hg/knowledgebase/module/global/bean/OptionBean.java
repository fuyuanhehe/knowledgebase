package com.hg.knowledgebase.module.global.bean;

/**
 * 
 * @Description: 选项bean
 * @Author: 况克冬
 * @CreateDate: 2020年3月4日上午10:21:46
 * @Version: 1.0.0
 */
public class OptionBean
{

    /**
     * 选项键
     */
    private String key;

    /**
     * 选项值
     */
    private String value;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "OptionBean [key=" + key + ", value=" + value + "]";
    }
}
