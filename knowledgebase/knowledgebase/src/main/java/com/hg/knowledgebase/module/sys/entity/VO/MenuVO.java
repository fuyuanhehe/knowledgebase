package com.hg.knowledgebase.module.sys.entity.VO;

import java.util.List;

import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.Power;

/**
 * 
 * @Description: 菜单扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午1:09:31
 * @Version: 1.0.0
 */
public class MenuVO extends Menu
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
     * 权限集合
     */
    private List<Power> powers;

    public List<Power> getPowers()
    {
        return powers;
    }

    public void setPowers(List<Power> powers)
    {
        this.powers = powers;
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
        return "MenuVO [pname=" + pname + ", powers=" + powers + "]";
    }
}
