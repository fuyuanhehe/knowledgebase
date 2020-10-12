package com.hg.knowledgebase.module.sys.entity.VO;

import java.util.List;

import com.hg.knowledgebase.annotation.ExcelColumn;
import com.hg.knowledgebase.module.sys.entity.User;

/**
 * 
 * @Description: 用户实体扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午5:10:34
 * @Version: 1.0.0
 */
public class UserVO extends User
{

    /**
     * 
     */
    private static final long serialVersionUID = -2948492882903841492L;

    /**
     * 部门名称
     */
    @ExcelColumn(value = "部门", sort = 3)
    private String deptName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态（0：禁用；1：启用）
     */
    private Integer roleStatus;

    /**
     * 用户登录标识
     */
    private String token;

    /**
     * 用户ID集合（查询）
     */
    private List<String> ids;

    public List<String> getIds()
    {
        return ids;
    }

    public void setIds(List<String> ids)
    {
        this.ids = ids;
    }

    public Integer getRoleStatus()
    {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus)
    {
        this.roleStatus = roleStatus;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public String toString()
    {
        return "UserVO [deptName=" + deptName + ", roleName=" + roleName
                + ", token=" + token + "]";
    }
}
