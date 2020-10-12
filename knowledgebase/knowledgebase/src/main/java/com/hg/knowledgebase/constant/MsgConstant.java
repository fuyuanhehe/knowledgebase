package com.hg.knowledgebase.constant;

/**
 * 
 * @Description: 信息常量类
 * @Author: 况克冬
 * @CreateDate: 2020年3月2日上午9:14:35
 * @Version: 1.0.0
 */
public class MsgConstant
{

    private MsgConstant()
    {
    }

    /**
     * 邮件类型
     */
    public static final String MAIL_TYPE = "mail";

    /**
     * 电话短信类型
     */
    public static final String PHONE_TYPE = "phone";

    /**
     * 消息目标用户常量
     */
    public static final String TO_USERNAME = "toUserName";

    /**
     * 消息目标常量
     */
    public static final String CONTENT = "content";

    /**
     * 验证码常量(接口使用)
     */
    public static final String CODE = "code";

    /**
     * 验证码常量(缓存使用)
     */
    public static final String VERIFICATION_CODE = "verificationCode";
}
