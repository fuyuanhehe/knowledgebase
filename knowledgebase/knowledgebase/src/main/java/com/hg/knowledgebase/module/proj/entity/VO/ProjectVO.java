package com.hg.knowledgebase.module.proj.entity.VO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hg.knowledgebase.module.proj.entity.Project;

/**
 * 
* @Description:项目扩展类
* @Author: 况克冬
* @CreateDate: 2020年2月28日下午9:33:53
* @Version: 1.0.0
 */
public class ProjectVO extends Project
{

    /**
     * 
     */
    private static final long serialVersionUID = -3074074272998657285L;
    /**
     * 创建人昵称
     */
    private String createUserNick;
    
    /**
     * 参与用户 0
     */
    private Set<String> participationUsers;

    /**
     * 抄送用户 1
     */
    private Set<String> duplicateUsers;


    // 参与用户
    private List<Map<String,Object>> participatePerson;

   //抄送用户
    private List<Map<String,Object>> duplicatePerson;


    public List<Map<String, Object>> getParticipatePerson() {
        return participatePerson;
    }

    public void setParticipatePerson(List<Map<String, Object>> participatePerson) {
        this.participatePerson = participatePerson;
    }

    public List<Map<String, Object>> getDuplicatePerson() {
        return duplicatePerson;
    }

    public void setDuplicatePerson(List<Map<String, Object>> duplicatePerson) {
        this.duplicatePerson = duplicatePerson;
    }

    public String getCreateUserNick() {
        return createUserNick;
    }

    public void setCreateUserNick(String createUserNick) {
        this.createUserNick = createUserNick;
    }

    public Set<String> getParticipationUsers()
    {
        return participationUsers;
    }

    public void setParticipationUsers(Set<String> participationUsers)
    {
        this.participationUsers = participationUsers;
    }

    public Set<String> getDuplicateUsers()
    {
        return duplicateUsers;
    }

    public void setDuplicateUsers(Set<String> duplicateUsers)
    {
        this.duplicateUsers = duplicateUsers;
    }




}
