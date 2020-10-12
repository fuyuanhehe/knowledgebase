package com.hg.knowledgebase.module.global.bean;

import java.io.Serializable;

/**
 * 
 * @Description: 简单mapbean
 * @Author: 况克冬
 * @CreateDate: 2020年3月13日下午5:32:11
 * @Version: 1.0.0
 */
public class KeyValueBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 7712004597063419022L;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private Object value;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public KeyValueBean(String key, Object value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    public KeyValueBean()
    {
        super();
    }
}
