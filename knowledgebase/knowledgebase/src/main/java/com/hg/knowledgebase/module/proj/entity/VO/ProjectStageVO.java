package com.hg.knowledgebase.module.proj.entity.VO;

import com.hg.knowledgebase.module.proj.entity.ProjectStage;

/**
 * 
* @Description: 项目阶段扩展类
* @Author: 况克冬
* @CreateDate: 2020年4月1日下午4:41:43
* @Version: 1.0.0
 */
public class ProjectStageVO extends ProjectStage
{

    /**
     * 
     */
    private static final long serialVersionUID = 7532020320691104685L;

    /**
     * 责任人名称
     */
    private String blameUser;
    
    /**
     * 项目名称
     */
    private String projectName;
    
    /**
     * 进度名称
     */
    private String progressName;

    public String getBlameUser()
    {
        return blameUser;
    }

    public void setBlameUser(String blameUser)
    {
        this.blameUser = blameUser;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProgressName()
    {
        return progressName;
    }

    public void setProgressName(String progressName)
    {
        this.progressName = progressName;
    }

    @Override
    public String toString()
    {
        return "ProjectStageVO [blameUser=" + blameUser + ", projectName=" + projectName + ", progressName="
                + progressName + "]";
    }
}
