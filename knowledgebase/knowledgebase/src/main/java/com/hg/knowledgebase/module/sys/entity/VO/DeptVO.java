package com.hg.knowledgebase.module.sys.entity.VO;

import com.hg.knowledgebase.module.sys.entity.Dept;

/**
 * 
 * @Description: 部门扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午1:09:31
 * @Version: 1.0.0
 */
public class DeptVO extends Dept
{

    /**
     * 
     */
    private static final long serialVersionUID = 3047272593607854056L;

    /**
     * 父名称
     */
    private String pname;

    /**
     * 统计数量
     */
    private Integer count;

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    @Override
    public String toString()
    {
        return "DeptVO [pname=" + pname + ", count=" + count + "]";
    }
}
