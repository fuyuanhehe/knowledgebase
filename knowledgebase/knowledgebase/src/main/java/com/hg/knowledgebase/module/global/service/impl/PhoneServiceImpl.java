package com.hg.knowledgebase.module.global.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.customenum.ParamTypeEnum;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.global.service.HttpApiService;
import com.hg.knowledgebase.module.global.service.IMsgService;

/**
 * 
 * @Description: 激光短信业务层接口
 * @Author: 况克冬
 * @CreateDate: 2020年3月10日下午1:46:39
 * @Version: 1.0.0
 */
@Service("phoneServiceImpl")
public class PhoneServiceImpl implements IMsgService
{

    /**
     * 验证码地址
     */
    @Value("${phone.message.url.codes}")
    private String codesUrl;

    /**
     * 单条消息地址
     */
    @Value("${phone.message.url.singleMsg}")
    private String singleUrl;

    /**
     * 验证码地址
     */
    @Value("${phone.message.url.bath}")
    private String bathUrl;

    /**
     * 短信appKeys
     */
    @Value("${phone.message.appKeys}")
    private String appKeys;

    /**
     * 短信masterSecret
     */
    @Value("${phone.message.masterSecret}")
    private String masterSecret;

    /**
     * 验证码模板ID
     */
    @Value("${phone.message.coderTempId}")
    private int coderTempId;

    /**
     * 消息模板ID
     */
    @Value("${phone.message.simpleTempId}")
    private int simpleTempId;

    @Autowired
    private HttpApiService httpApiService;

    /**
     * 激光手机号参数常量
     */
    private static final String MOBILE = "mobile";

    /**
     * 激光模板ID参数常量
     */
    private static final String TEMP_ID = "temp_id";

    /**
     * 激光消息ID参数常量
     */
    public static final String MSG_ID = "msgId";

    /**
     * 激光模板模板参数常量
     */
    private static final String TEMP_PARA = "temp_para";

    /**
     * 激光校验参数常量
     */
    private static final String IS_VALID = "is_valid";

    /**
     * 激光模板验证码参数常量
     */
    private static final String CODE = "code";

    /**
     * 校验验证码地址模板
     */
    private static final String CHEAK_CODER_URL_FOMAT = "%s/%s/valid";

    /**
     * 
     * @Description: 发送单条短信
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:06:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:06:42
     * @UpdateRemark: 修改内容
     * @param content
     *            短信替换类容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean sendMsg(String targetName, String content)
            throws GlobalException
    {
        if (StringUtils.isEmpty(targetName))
        {
            throw new GlobalException("发送单条短信输入参数不能为空，入参【手机号：{}】", targetName);
        }
        String authorization = getAuthorization();
        Map<String, Object> param = new HashMap<>();
        param.put(MOBILE, targetName);
        param.put(TEMP_ID, simpleTempId);
        if (!StringUtils.isEmpty(content))
        {
            param.put(TEMP_PARA, content);
        }
        Map<String, String> headerMap = httpApiService
                .getJsonHeaderMap(authorization);
        String msg = null;
        try
        {
            msg = httpApiService.httpPost(codesUrl, param, null,
                    ParamTypeEnum.JSON_STRING, headerMap);
        } catch (Exception e)
        {
            throw new GlobalException("发送单条短信http异常：{}", e.getMessage());
        }
        return msg != null;
    }

    /**
     * 
     * @Description: 发送短信编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:09:14
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:09:14
     * @UpdateRemark: 修改内容
     * @param targetName
     *            短信发送目标人
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    @Override
    public String sendCoder(String targetName) throws GlobalException
    {
        String msgId = null;
        if (StringUtils.isEmpty(targetName))
        {
            throw new GlobalException("发送短信编码输入参数不能为空，入参【手机号：{}】", targetName);
        }
        String authorization = getAuthorization();
        Map<String, Object> param = new HashMap<>();
        param.put(MOBILE, targetName);
        param.put(TEMP_ID, coderTempId);
        Map<String, String> headerMap = httpApiService
                .getJsonHeaderMap(authorization);
        String msg;
        try
        {
            msg = httpApiService.httpPost(codesUrl, param, null,
                    ParamTypeEnum.JSON_STRING, headerMap);
            // 如果返回信息不为空，则获取信息json中的验证码属性值
            if (!StringUtils.isEmpty(msg))
            {
                JSONObject jsonObj = JSONObject.parseObject(msg);
                msgId = jsonObj.getString(MSG_ID);
            }
        } catch (Exception e)
        {
            throw new GlobalException("发送短信编码http异常：{}", e.getMessage());
        }
        return msgId;
    }

    /**
     * 
     * @Description: 查找短信编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:11:23
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:11:23
     * @UpdateRemark: 修改内容
     * @param targetName
     *            查询短信
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
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
        if (StringUtils.isEmpty(targetName) || StringUtils.isEmpty(coder)
                || StringUtils.isEmpty(msgId))
        {
            throw new GlobalException("校验验证码输入参数不能为空，入参【手机号：{},验证码：{},消息ID：{}】",
                    targetName, coder, msgId);
        }
        String authorization = getAuthorization();
        Map<String, Object> param = new HashMap<>();
        param.put(CODE, coder);
        Map<String, String> headerMap = httpApiService
                .getJsonHeaderMap(authorization);
        String msg;
        try
        {
            msg = httpApiService.httpPost(
                    String.format(CHEAK_CODER_URL_FOMAT, codesUrl, msgId),
                    param, null, ParamTypeEnum.JSON_STRING, headerMap);
            // 如果返回信息不为空，则获取信息json中的有效属性值
            if (!StringUtils.isEmpty(msg))
            {
                flg = JSONObject.parseObject(msg).getBoolean(IS_VALID);
            }
        } catch (Exception e)
        {
            throw new GlobalException("校验验证码http异常：{}", e.getMessage());
        }
        return flg;
    }

    /**
     * 
     * @Description: 获取编码后的authorization
     * @Author: 况克冬
     * @CreateDate: 2020年3月10日下午3:35:53
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月10日下午3:35:53
     * @UpdateRemark: 修改内容
     * @return String
     * @Version: 1.0.0
     */
    private String getAuthorization()
    {
        String authorization = appKeys + ":" + masterSecret;
        return "Basic "
                + Base64.getEncoder().encodeToString(authorization.getBytes());
    }
}
