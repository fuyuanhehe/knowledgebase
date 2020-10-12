package com.hg.knowledgebase.module.global.bean;

/**
 * 
 * @Description: 替换bean类
 * @Author: 况克冬
 * @CreateDate: 2020年2月28日下午1:10:10
 * @Version: 1.0.0
 */
public final class ReplaceBean
{
    /**
     * 源数据
     */
    private Object source;

    /**
     * 目标数据
     */
    private Object target;

    public Object getSource()
    {
        return source;
    }

    public void setSource(Object source)
    {
        this.source = source;
    }

    public Object getTarget()
    {
        return target;
    }

    public void setTarget(Object target)
    {
        this.target = target;
    }

    public ReplaceBean()
    {
        super();
    }

    /**
     * 
     * @param source
     *            源数据
     * @param target
     *            目标数据
     */
    public ReplaceBean(Object source, Object target)
    {
        super();
        this.source = source;
        this.target = target;
    }
}
