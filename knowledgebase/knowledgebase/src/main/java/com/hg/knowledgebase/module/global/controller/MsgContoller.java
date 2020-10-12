package com.hg.knowledgebase.module.global.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.constant.MsgConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.global.factory.MessageFactory;
import com.hg.knowledgebase.module.global.service.IMsgService;
import com.hg.knowledgebase.module.global.service.impl.MailServiceImpl;
import com.hg.knowledgebase.module.global.service.impl.PhoneServiceImpl;

/**
 * 
 * @Description: 消息控制层
 * @Author: 况克冬
 * @CreateDate: 2019年8月16日上午9:14:15
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/global/msg")
public class MsgContoller
{

    private static Logger log = LoggerFactory.getLogger(MsgContoller.class);

    /**
     * 
     * @Description: 普通消息发送
     * @Author: 况克冬
     * @CreateDate: 2019年8月16日上午9:33:54
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年8月16日上午9:33:54
     * @UpdateRemark: 修改内容
     * @param reqData
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/sendSimpleMsg")
    @SystemLog(value = "普通消息发送")
    public ResponseResultBean sendSimpleMsg(@RequestBody String reqData)
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqData);
            String content = jsonObj.getString(MsgConstant.CONTENT);
            String toUserName = jsonObj.getString(MsgConstant.TO_USERNAME);
            boolean flg = MessageFactory.getMessageService().sendMsg(toUserName,
                    content);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---普通消息发送异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 发送验证码消息
     * @Author: 况克冬
     * @CreateDate: 2020年3月10日下午2:24:58
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月10日下午2:24:58
     * @UpdateRemark: 修改内容
     * @param reqData
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/sendCodeMsg")
    @SystemLog(value = "发送验证码消息")
    public ResponseResultBean sendCodeMsg(@RequestBody String reqData)
    {
        Map<String, String> dataMap = null;
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqData);
            String toUserName = jsonObj.getString(MsgConstant.TO_USERNAME);
            String coder = MessageFactory.getMessageService()
                    .sendCoder(toUserName);
            String type = MessageFactory.getType();
            boolean flg = coder != null;
            if (flg && type != null)
            {
                dataMap = new HashMap<>();
                switch (type)
                {
                case MsgConstant.MAIL_TYPE:
                    // 邮件类型
                    dataMap.put(MailServiceImpl.MSG_CODE, coder);
                    break;
                case MsgConstant.PHONE_TYPE:
                    // 电话短信类型
                    dataMap.put(PhoneServiceImpl.MSG_ID, coder);
                    break;
                default:
                    break;
                }
                dataMap.put(IMsgService.MSG_TYPE, type);
            }
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    dataMap);
        } catch (Exception e)
        {
            log.error("---发送验证码消息异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 校验验证码
     * @Author: 况克冬
     * @CreateDate: 2020年3月10日下午2:24:58
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月10日下午2:24:58
     * @UpdateRemark: 修改内容
     * @param reqData
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/cheakCode")
    @SystemLog(value = "校验验证码")
    public ResponseResultBean cheakCode(@RequestBody String reqData)
    {
        ResponseResultBean responseResult = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqData);
            String toUserName = jsonObj.getString(MsgConstant.TO_USERNAME);
            String coder = jsonObj.getString(MsgConstant.CODE);
            String msgId = jsonObj.getString(PhoneServiceImpl.MSG_ID);
            boolean flg = MessageFactory.getMessageService()
                    .checkCoder(toUserName, coder, msgId);
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---校验验证码异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, e.getMessage());
        }
        return responseResult;
    }
}
