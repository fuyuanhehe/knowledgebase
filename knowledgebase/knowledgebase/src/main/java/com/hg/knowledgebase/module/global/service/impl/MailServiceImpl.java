package com.hg.knowledgebase.module.global.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hg.knowledgebase.constant.MsgConstant;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.global.service.IMsgService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 邮件业务实现层
 * @Author: 况克冬
 * @CreateDate: 2020年3月6日下午7:13:03
 * @Version: 1.0.0
 */
@Service("mailServiceImpl")
public class MailServiceImpl implements IMsgService
{

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送人
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 消息验证码常量
     */
    public static final String MSG_CODE = "code";

    /**
     * 验证码模版
     */
    private static final String VERIFICATION_CODE_FORMAT = "验证码【%s】";

    @Autowired
    private CacheManager cacheManager;

    /**
     * 
     * @Description: 发送邮件
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:06:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:06:42
     * @UpdateRemark: 修改内容
     * @param content
     *            邮件类容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean sendMsg(String targetName, String content)
            throws GlobalException
    {
        return sendMail(targetName, "问题反馈", content);
    }

    /**
     * 
     * @Description: 发送邮件编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:09:14
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:09:14
     * @UpdateRemark: 修改内容
     * @param targetName
     *            邮件发送目标人
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    @CachePut(cacheNames = {
            MsgConstant.VERIFICATION_CODE }, key = "#targetName")
    @Override
    public String sendCoder(String targetName) throws GlobalException
    {
        String coder = StringUtil.getRandom();
        sendMail(targetName, "验证码",
                String.format(VERIFICATION_CODE_FORMAT, coder));
        return coder;
    }

    /**
     * 
     * @Description: 查找邮件编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:11:23
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:11:23
     * @UpdateRemark: 修改内容
     * @param targetName
     *            查询邮件
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    @Cacheable(cacheNames = {
            MsgConstant.VERIFICATION_CODE }, key = "#targetName")
    @Override
    public String findCoder(String targetName) throws GlobalException
    {
        return null;
    }

    /**
     * 
     * @Description: 校验验证码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午8:12:29
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午8:12:29
     * @UpdateRemark: 修改内容
     * @param targetName
     * @param coder
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean checkCoder(String targetName, String coder, String msgId)
            throws GlobalException
    {
        boolean flg = false;
        if (StringUtils.isEmpty(targetName) || StringUtils.isEmpty(coder))
        {
            throw new GlobalException("校验验证码输入参数不能为空，入参【验证人：{}，校验码：{}】",
                    targetName, coder);
        }
        Cache cache = cacheManager.getCache(MsgConstant.VERIFICATION_CODE);
        ValueWrapper valueWrapper = cache.get(targetName);
        if (valueWrapper != null)
        {
            Object obj = valueWrapper.get();
            if (coder.equals(obj))
            {
                // 编码只能使用一次，使用后清除
                cache.evictIfPresent(targetName);
                flg = true;
            }
        }
        return flg;
    }

    /**
     * 
     * @Description: 发送邮件
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:48:27
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:48:27
     * @UpdateRemark: 修改内容
     * @param targetName
     * @param subject
     * @param content
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    public boolean sendMail(String targetName, String subject, String content)
            throws GlobalException
    {
        if (StringUtils.isEmpty(targetName) || StringUtils.isEmpty(subject)
                || StringUtils.isEmpty(content))
        {
            throw new GlobalException("发送邮件参数不能为空，入参【接受人：{}，主题：{}，内容：{}】",
                    targetName, subject, content);
        }
        SimpleMailMessage smm = new SimpleMailMessage();
        // 邮件发送者
        smm.setFrom(username);
        // 邮件接受者
        smm.setTo(targetName);
        // 主题
        smm.setSubject(subject);
        // 内容
        smm.setText(content);
        mailSender.send(smm);
        return true;
    }
}
