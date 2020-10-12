package com.hg.knowledgebase.module.global.service;

import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: 消息业务层
 * @Author: 况克冬
 * @CreateDate: 2020年3月6日下午7:03:53
 * @Version: 1.0.0
 */
public interface IMsgService
{
    /**
     * 消息类型常量
     */
    static final String MSG_TYPE = "msgType";

    /**
     * 
     * @Description: 发送消息
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:06:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:06:42
     * @UpdateRemark: 修改内容
     * @param targetName
     *            消息发送目标人
     * @param content
     *            消息类容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean sendMsg(String targetName, String content) throws GlobalException;

    /**
     * 
     * @Description: 发送消息编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:09:14
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:09:14
     * @UpdateRemark: 修改内容
     * @param targetName
     *            消息发送目标人
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    String sendCoder(String targetName) throws GlobalException;

    /**
     * 
     * @Description: 查找消息编码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:11:23
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:11:23
     * @UpdateRemark: 修改内容
     * @param targetName
     *            查询消息
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             String
     * @Version: 1.0.0
     */
    String findCoder(String targetName) throws GlobalException;

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
    boolean checkCoder(String targetName, String coder, String msgId)
            throws GlobalException;
}
