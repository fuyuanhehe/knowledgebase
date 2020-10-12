package com.hg.knowledgebase.module.sys.entity;

import com.hg.knowledgebase.annotation.ExcelColumn;
import com.hg.knowledgebase.customenum.SexEnum;
import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 用户实体类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:14:46
 * @Version: 1.0.0
 */
public class User extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = -4134486921633675468L;

    /**
     * 账号
     */
    @ExcelColumn(value = "工号", sort = 0)
    private String name;

    /**
     * 用户昵称
     */
    @ExcelColumn(value = "用户名", sort = 1)
    private String nickName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户电话
     */
    @ExcelColumn(value = "联系方式", sort = 5)
    private String phone;

    /**
     * 用户邮件
     */
    @ExcelColumn(value = "邮箱", sort = 4)
    private String mail;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 用户性别（0：男；1：女）
     */
    @ExcelColumn(value = "性别", sort = 2, isSex = true, sexs = { SexEnum.MAN,
            SexEnum.WOMAN })
    private Integer sex;

    /**
     * 用户状态（0：禁用；1：启用）
     */
    private Integer status;

    /**
     * 用户类型（0：自定义用户；1：系统用户）
     */
    private Integer type;

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDeptId()
    {
        return deptId;
    }

    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getPortrait()
    {
        return portrait;
    }

    public void setPortrait(String portrait)
    {
        this.portrait = portrait;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @Override
    public String toString()
    {
        return "User [name=" + name + ", nickName=" + nickName + ", password="
                + password + ", phone=" + phone + ", mail=" + mail + ", deptId="
                + deptId + ", roleId=" + roleId + ", portrait=" + portrait
                + ", sex=" + sex + ", status=" + status + ", type=" + type
                + "]";
    }
}
