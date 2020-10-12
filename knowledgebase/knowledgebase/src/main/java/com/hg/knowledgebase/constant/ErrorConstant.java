package com.hg.knowledgebase.constant;

/**
 * 
 * @Description: 错误常量类
 * @Author: 况克冬
 * @CreateDate: 2020年4月27日下午3:41:40
 * @Version: 1.0.0
 */
public class ErrorConstant
{

    private ErrorConstant()
    {
    }

    ////////////////////////////////////// 验证////////////////////////////////////
    /**
     * 错误-验证码错误
     */
    public static final String ERROR_VERIFICATION_INVALID = "验证码错误";

    //////////////////////////////////// 文件////////////////////////////////////
    /**
     * 错误-文件上传失败
     */
    public static final String ERROR_FILE_UPLOAD_FAIL = "文件上传失败";

    //////////////////////////////////// 用户//////////////////////////////////////
    /**
     * 错误-邮件不存在
     */
    public static final String ERROR_EMAIL_NOTHINGNESS = "邮件用户不存在";

    /**
     * 错误-账户或者密码错误
     */
    public static final String ERROR_ACCOUNT_OR_PASSWORD_INVALID = "用户名或者密码错误";

    /**
     * 错误-禁止的账户
     */
    public static final String ERROR_ACCOUNT_OF_FORBIDDEN = "用户被禁用";

    /**
     * 错误-禁止的角色
     */
    public static final String ERROR_ROLE_OF_FORBIDDEN = "用户角色被禁用";
}
