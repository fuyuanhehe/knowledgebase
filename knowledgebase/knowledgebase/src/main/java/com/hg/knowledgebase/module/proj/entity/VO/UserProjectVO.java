package com.hg.knowledgebase.module.proj.entity.VO;

import com.hg.knowledgebase.module.proj.entity.UserProject;

public class UserProjectVO extends UserProject
{

    /**
     * 
     */
    private static final long serialVersionUID = -3110774158158370615L;
    /**
     * 项目名称
     */
    private String projectName;

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    @Override
    public String toString()
    {
        return "UserProjectVO [projectName=" + projectName + "]";
    }
}
