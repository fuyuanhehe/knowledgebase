package com.hg.knowledgebase.module.sys.entity.VO;

import com.hg.knowledgebase.module.sys.entity.Journalism;

/**
 * 
 * @Description: 新闻实体扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午5:10:34
 * @Version: 1.0.0
 */
public class JournalismVO extends Journalism
{

    /**
     * 
     */
    private static final long serialVersionUID = -4057594646292383031L;

    /**
     * 用户名称
     */
    private String userName;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        return "JournalismVO [userName=" + userName + "]";
    }
}
