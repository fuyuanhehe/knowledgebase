package com.hg.knowledgebase.module.global.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: 公共异常类
 * @Author: 况克冬
 * @CreateDate: 2020年5月27日上午9:16:49
 * @Version: 1.0.0
 */
public class GlobalException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -4767250972482601435L;

    /**
     * 替换符正则
     */
    private static final String REPLACER_REG = "\\{\\}";

    /**
     * 替换符
     */
    private static final String REPLACER = "{}";

    /**
     * 异常信息
     */
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public GlobalException()
    {
        super();
    }

    /**
     * 公共异常构造方法 例：new GlobalException("输入参数不能为空")
     * 
     * @param 错误信息
     */
    public GlobalException(String message)
    {
        super();
        this.message = message;
    }

    /**
     * 公共异常构造方法 例：new GlobalException("输入参数不能为空，入参【a={},b={}】", 1, 2)
     * 
     * @param formatMessages
     *            错误模板信息({}:模板替换符号)
     * @param parameters
     *            错误信息参数
     */
    public GlobalException(String formatMessages, Object... parameters)
    {
        super();
        this.message = format(formatMessages, parameters);
    }

    public GlobalException(String formatMessages, Object parameter)
    {
        super();
        this.message = formatMessages == null || parameter == null
                ? formatMessages
                : formatMessages.replace(REPLACER, parameter.toString());
    }

    @Override
    public String toString()
    {
        return "GlobalException [message=" + message + "]";
    }

    /**
     * 
     * @Description: 格式化信息
     * @Author: 况克冬
     * @CreateDate: 2020年5月27日下午2:47:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年5月27日下午2:47:30
     * @UpdateRemark: 修改内容
     * @param formatMessages
     *            模板信息
     * @param parameters
     *            参数
     * @return String
     * @Version: 1.0.0
     */
    private static String format(String formatMessages, Object... parameters)
    {
        // 检测是否有替换参数，如果没有返回原模板信息数据
        if (formatMessages == null || parameters == null
                || parameters.length == 0)
        {
            return formatMessages;
        }
        // 模板信息下标计数
        int i = 0;
        StringBuilder sb = new StringBuilder();
        //存放模板信息数据的数组
        String[] msgs = formatMessages.split(REPLACER_REG);
        //存储备份的模板信息数据的集合
        List<String> mesList = new ArrayList<>(msgs.length);
        // 备份模板信息数据数组到集合，方便后面做扩充数据
        for (String msg : msgs)
        {
            mesList.add(msg);
        }
        // 如果模板信息以“{}”符号结尾，则扩充一个空模板信息数据
        if (formatMessages.endsWith(REPLACER))
        {
            mesList.add("");
        }
        // 如果模板信息数据集合长度减1等于参数长度，则两者数据匹配执行替换
        if (mesList.size() - 1 == parameters.length)
        {
            sb.append(mesList.get(0));
            for (Object obj : parameters)
            {
                sb.append(obj);
                sb.append(mesList.get(++i));
            }
        } else
        {
            sb.append("数据不匹配内部错误！");
        }
        return sb.toString();
    }
}
