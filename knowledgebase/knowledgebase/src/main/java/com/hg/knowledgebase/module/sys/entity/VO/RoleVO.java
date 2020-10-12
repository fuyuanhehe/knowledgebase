package com.hg.knowledgebase.module.sys.entity.VO;

import java.util.Set;

import com.hg.knowledgebase.module.sys.entity.Role;

/**
 * 
 * @Description: 角色扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月2日上午10:00:26
 * @Version: 1.0.0
 */
public class RoleVO extends Role
{

    /**
     * 
     */
    private static final long serialVersionUID = 5925298108205184520L;

    /**
     * 菜单id集合
     */
    private Set<String> menuIds;

    /**
     * 权限集合
     */
    private Set<String> powerIds;

    /**
     * 用户数量
     */
    private Integer userCount;

    public Integer getUserCount()
    {
        return userCount;
    }

    public void setUserCount(Integer userCount)
    {
        this.userCount = userCount;
    }

    public Set<String> getMenuIds()
    {
        return menuIds;
    }

    public void setMenuIds(Set<String> menuIds)
    {
        this.menuIds = menuIds;
    }

    public Set<String> getPowerIds()
    {
        return powerIds;
    }

    public void setPowerIds(Set<String> powerIds)
    {
        this.powerIds = powerIds;
    }

    @Override
    public String toString()
    {
        return "RoleVO [menuIds=" + menuIds + ", powerIds=" + powerIds
                + ", userCount=" + userCount + "]";
    }
}
