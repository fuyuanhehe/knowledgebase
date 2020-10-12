package com.hg.knowledgebase.module.sys.entity.VO;

import com.hg.knowledgebase.module.sys.entity.Menu;

/**
 * 
 * @Description: 权限扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午1:09:31
 * @Version: 1.0.0
 */
public class PowerVO extends Menu
{

    /**
     * 
     */
    private static final long serialVersionUID = 3047272593607854056L;

    /**
     * 菜单名称
     */
    private String menuName;

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    @Override
    public String toString()
    {
        return "PowerVO [menuName=" + menuName + "]";
    }
}
