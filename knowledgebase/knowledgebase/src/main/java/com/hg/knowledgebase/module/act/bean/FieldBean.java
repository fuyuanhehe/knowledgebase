package com.hg.knowledgebase.module.act.bean;

import java.io.Serializable;

/**
 * 
* @Description: 字典bean
* @Author: 况克冬
* @CreateDate: 2020年6月3日上午11:14:10
* @Version: 1.0.0
 */
public class FieldBean implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -3074059339435969582L;

    /**
     * 字段id
     */
    private String id;
    
    /**
     * 字段名称
     */
    private String name;
    
    /**
     * 字段值
     */
    private Object value;
    
    /**
     * 字段属性
     */
    private String type;
    
    /**
     * 字段是否只读
     */
    private boolean readable;
    
    /**
     * 字段是否必填
     */
    private boolean required;
    
    /**
     * 字段是否可写入
     */
    private boolean writable;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public boolean getReadable()
    {
        return readable;
    }

    public void setReadable(boolean readable)
    {
        this.readable = readable;
    }

    public boolean getRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required = required;
    }

    public boolean getWritable()
    {
        return writable;
    }

    public void setWritable(boolean writable)
    {
        this.writable = writable;
    }

    @Override
    public String toString()
    {
        return "FieldBean [id=" + id + ", name=" + name + ", value=" + value
                + ", type=" + type + ", readable=" + readable + ", required="
                + required + ", writable=" + writable + "]";
    }
}
