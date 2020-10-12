package com.hg.knowledgebase.module.eval.entity.VO;

import java.util.Date;

import com.hg.knowledgebase.module.eval.entity.UserCurriculum;

/**
 * 
 * @Description: 用户课程扩展类
 * @Author: 况克冬
 * @CreateDate: 2020年3月3日上午10:03:08
 * @Version: 1.0.0
 */
public class UserCurriculumVO extends UserCurriculum
{

    /**
     * 
     */
    private static final long serialVersionUID = -6214599260953914231L;
    
    /**
     * 课程名称
     */
    private String curriculumName;

    /**
     * 课程封面地址
     */
    private String curriculumCoverUrl;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 课程简介
     */
    private String curriculumIntroduction;

    /**
     * 课程资源
     */
    private String curriculumResources;

    /**
     * 用户人数
     */
    private Integer userCount;

    /**
     * 学习开始时间
     */
    private Date learnBeginTime;

    /**
     * 学习结束时间
     */
    private Date learnEndTime;

    /**
     * 课程状态（0：未学习；1：学习中；2：已结束）
     */
    private Integer curriculumStatus;

    public Integer getCurriculumStatus()
    {
        return curriculumStatus;
    }

    public void setCurriculumStatus(Integer curriculumStatus)
    {
        this.curriculumStatus = curriculumStatus;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCurriculumName()
    {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName)
    {
        this.curriculumName = curriculumName;
    }

    public String getCurriculumCoverUrl()
    {
        return curriculumCoverUrl;
    }

    public void setCurriculumCoverUrl(String curriculumCoverUrl)
    {
        this.curriculumCoverUrl = curriculumCoverUrl;
    }

    public String getCurriculumIntroduction()
    {
        return curriculumIntroduction;
    }

    public void setCurriculumIntroduction(String curriculumIntroduction)
    {
        this.curriculumIntroduction = curriculumIntroduction;
    }

    public String getCurriculumResources()
    {
        return curriculumResources;
    }

    public void setCurriculumResources(String curriculumResources)
    {
        this.curriculumResources = curriculumResources;
    }

    public Integer getUserCount()
    {
        return userCount;
    }

    public void setUserCount(Integer userCount)
    {
        this.userCount = userCount;
    }

    public Date getLearnBeginTime()
    {
        return learnBeginTime;
    }

    public void setLearnBeginTime(Date learnBeginTime)
    {
        this.learnBeginTime = learnBeginTime;
    }

    public Date getLearnEndTime()
    {
        return learnEndTime;
    }

    public void setLearnEndTime(Date learnEndTime)
    {
        this.learnEndTime = learnEndTime;
    }

    @Override
    public String toString()
    {
        return "UserCurriculumVO [curriculumName=" + curriculumName
                + ", curriculumCoverUrl=" + curriculumCoverUrl + ", userName="
                + userName + ", curriculumIntroduction="
                + curriculumIntroduction + ", curriculumResources="
                + curriculumResources + ", userCount=" + userCount
                + ", learnBeginTime=" + learnBeginTime + ", learnEndTime="
                + learnEndTime + "]";
    }
}
