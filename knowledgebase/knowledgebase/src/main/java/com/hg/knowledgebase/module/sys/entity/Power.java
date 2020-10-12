package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 权限实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Power extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 权限编号
     */
    private String coder;

    /**
     * 菜单id
     */
    private String menuId;

    public String getCoder()
    {
        return coder;
    }

    public void setCoder(String coder)
    {
        this.coder = coder;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    @Override
    public String toString()
    {
        return "Power [coder=" + coder + ", menuId=" + menuId + "]";
    }
}
