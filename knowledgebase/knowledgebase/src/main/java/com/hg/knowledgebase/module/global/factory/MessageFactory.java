package com.hg.knowledgebase.module.global.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hg.knowledgebase.constant.MsgConstant;
import com.hg.knowledgebase.module.global.service.IMsgService;
import com.hg.knowledgebase.module.global.service.impl.MailServiceImpl;
import com.hg.knowledgebase.module.global.service.impl.PhoneServiceImpl;

/**
 *
* @Description: 消息工厂
* @Author: 况克冬
* @CreateDate: 2020年3月10日上午11:35:17
* @Version: 1.0.0
 */
@Component
public class MessageFactory
{

    private MessageFactory() 
    {
    }
    
    /**
     * 默认短信类型
     */
    private static String type;
    
    /**
     * 邮件服务
     */
    private static MailServiceImpl mailService;
    
    /**
     * 电话短信服务
     */
    private static PhoneServiceImpl phoneService;
    
    @Autowired
    public void setType(PhoneServiceImpl phoneService)
    {
        MessageFactory.phoneService = phoneService;
    }
    
    @Autowired
    public void setType(MailServiceImpl mailService)
    {
        MessageFactory.mailService = mailService;
    }
    
    @Value("${custom.msg.type}")
    public void setType(String type)
    {
        MessageFactory.type = type;
    }
    
    public static String getType()
    {
        return type;
    }

    /**
     * 
    * @Description: 获取消息服务
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午1:55:37
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午1:55:37
    * @UpdateRemark: 修改内容
    * @return
    * IMsgService
    * @Version: 1.0.0
     */
    public static IMsgService getMessageService() 
    {
        return getMessageService(type);
    }
    
    /**
     * 
    * @Description: 获取消息服务
    * 注意：参数调用方验证
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午1:55:55
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午1:55:55
    * @UpdateRemark: 修改内容
    * @param type 消息类型
    * @return
    * IMsgService
    * @Version: 1.0.0
     */
    public static IMsgService getMessageService(String type) 
    {
        IMsgService msgService = null;
        if (type == null) 
        {
            return msgService;
        }
        switch (type)
        {
        case MsgConstant.MAIL_TYPE:
            //邮件类型
            msgService = mailService;
            break;
        case MsgConstant.PHONE_TYPE:
            //电话短信类型
            msgService = phoneService;
            break;
        default:
            //邮件类型
            msgService = mailService;
            break;
        }
        return msgService;
    }
}
