package com.hg.knowledgebase.module.global.bean;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * @Description: 接口返回结果类
 * @Author: 况克冬
 * @CreateDate: 2019/4/22 18:13
 * @Version: 1.0.0
 */
public class ResponseResultBean implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1892006308027540258L;

    /**
     * 执行状态
     */
    private boolean success;

    /**
     * 执行信息
     */
    private String message;

    /**
     * 执行码
     */
    private int coder;

    /**
     * 返回普通数据（如：int、string、list...）
     */
    private Object data;

    /**
     * 分页数据信息
     */
    private PageInfo<?> pageInfo;

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getCoder()
    {
        return coder;
    }

    public void setCoder(int coder)
    {
        this.coder = coder;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public PageInfo<?> getPageInfo()
    {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<?> pageInfo)
    {
        this.pageInfo = pageInfo;
    }

    public ResponseResultBean()
    {
    }

    public ResponseResultBean(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public ResponseResultBean(boolean success, String message, int coder,
            Object data)
    {
        this.success = success;
        this.message = message;
        this.coder = coder;
        this.data = data;
    }

    public ResponseResultBean(boolean success, String message, Object data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /*
     * public ResponseResult(boolean success, String message, PageInfo<?>
     * pageInfo) { this.success = success; this.message = message; this.pageInfo
     * = pageInfo; }
     */

    @Override
    public String toString()
    {
        return "Result{" + "success=" + success + ", message='" + message + '\''
                + ", coder=" + coder + ", data=" + data + '}';
    }
}
