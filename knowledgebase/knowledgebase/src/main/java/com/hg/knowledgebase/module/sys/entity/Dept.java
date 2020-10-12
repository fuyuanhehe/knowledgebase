package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 部门实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Dept extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 部门父id
     */
    private String pid;

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    @Override
    public String toString()
    {
        return "Dept [pid=" + pid + "]";
    }
}
