package com.hg.knowledgebase.module.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.log.entity.SystemLogVO;
import com.hg.knowledgebase.module.log.service.SysLogService;

/**
 * @TODO 日志查询接口
 * @author Ye.Qiang
 * @date 2020年3月16日
 */
@RestController
@RequestMapping(value = "/api/sys/log")
public class LogController
{
    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    SysLogService sysLogService;

    @SystemLog("查询系统日志")
    @RequestMapping(value = "/querySystemLog", method = RequestMethod.POST)
    public ResponseResultBean queryLog(@RequestBody SystemLogVO systemLogVO)
    {
        logger.info("queryLog: " + systemLogVO.toString());

        ResponseResultBean response = new ResponseResultBean(GlobalConstant.FALSE, "Error with Params");
        // 必传参数校验
        if (null != systemLogVO && null != systemLogVO.getPageNum() && null != systemLogVO.getPageSize())
        {
            response = new ResponseResultBean(GlobalConstant.TRUE, GlobalConstant.SUCCESS,
                    sysLogService.querySystemLog(systemLogVO));

            return response;
        }

        return response;

    }

}
