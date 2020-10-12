package com.hg.knowledgebase.module.global.bean;

import java.io.Serializable;

/**
 * @Description: 文件信息modle
 * @Author: 况克冬
 * @CreateDate: 2019/4/25 9:35
 * @Version: 1.0.0
 */
public class FileInfoBean implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2678371663674384398L;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件地址
     */
    private String addr;

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public FileInfoBean(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    public FileInfoBean(String name, String addr, String url)
    {
        super();
        this.name = name;
        this.addr = addr;
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "FileInfo{" + "name='" + name + '\'' + ", url='" + url + '\''
                + '}';
    }
}
