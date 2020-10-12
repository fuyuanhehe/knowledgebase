package com.hg.knowledgebase.module.community.entity;

import com.hg.knowledgebase.module.global.bean.BaseBean;

/**
 * 
 * @Description: 话题实体类
 * @Author: 况克冬
 * @CreateDate: 2020年3月18日下午5:58:06
 * @Version: 1.0.0
 */
public class Topic extends BaseBean
{

    /**
     * 
     */
    private static final long serialVersionUID = -6403757174633382855L;

    /**
     * 圈子id
     */
    private String circleId;

    /**
     * 主题
     */
    private String theme;

    /**
     * 话题类容
     */
    private String content;

    public String getCircleId()
    {
        return circleId;
    }

    public void setCircleId(String circleId)
    {
        this.circleId = circleId;
    }

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Topic [circleId=" + circleId + ", theme=" + theme + ", content="
                + content + "]";
    }
}
