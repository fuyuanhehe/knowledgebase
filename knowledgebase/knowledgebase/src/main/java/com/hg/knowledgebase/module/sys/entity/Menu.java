package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 菜单实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午12:22:11
 * @Version: 1.0.0
 */
public class Menu extends BaseBean
{
    /**
     * 
     */
    private static final long serialVersionUID = -7606345628070725210L;

    /**
     * 菜单父id
     */
    private String pid;

    /**
     * 菜单编码
     */
    private String coder;

    /**
     * 菜单跳转地址
     */
    private String url;

    /**
     * 图标地址
     */
    private String iconUrl;

    /**
     * 菜单组
     */
    private String menuGroup;

    /**
     * 预留字段（备用）
     */
    private String reserve;

    /**
     * 菜单状态（0：禁用；1：启用）
     */
    private Integer status;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getMenuGroup()
    {
        return menuGroup;
    }

    public void setMenuGroup(String menuGroup)
    {
        this.menuGroup = menuGroup;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getCoder()
    {
        return coder;
    }

    public void setCoder(String coder)
    {
        this.coder = coder;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getReserve()
    {
        return reserve;
    }

    public void setReserve(String reserve)
    {
        this.reserve = reserve;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString()
    {
        return "Menu [pid=" + pid + ", coder=" + coder + ", url=" + url
                + ", iconUrl=" + iconUrl + ", menuGroup=" + menuGroup
                + ", reserve=" + reserve + "]";
    }
}
