package com.hg.knowledgebase.module.log.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.log.entity.SystemLogDO;
import com.hg.knowledgebase.module.log.entity.SystemLogVO;
import com.hg.knowledgebase.module.log.handler.SysLogDatahandler;
import com.hg.knowledgebase.module.log.mapper.SysLogMapper;

/**
 * @TODO
 * @author Ye.Qiang
 * @date 2020年3月17日
 */
@Service
public class SysLogService
{

    @Autowired
    SysLogMapper sysLogMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 
     * @Title: 分页查询操作日志
     * @author Ye.Qiang
     * @date 2020年3月17日
     */
    public PageInfo<SystemLogDO> querySystemLog(SystemLogVO systemLogVO)
    {
        PageInfo<SystemLogDO> pageResult = null;
        // 查询参数初始化
        int pageNum = Integer.parseInt(systemLogVO.getPageNum());
        int pageSize = Integer.parseInt(systemLogVO.getPageSize());
        String startTime = systemLogVO.getLogStartTime();
        String endTime = systemLogVO.getLogEndTime();
        String operationType = systemLogVO.getOperationMsg();
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<SystemLogDO> resultList = sysLogMapper.selectSystemLog(startTime, endTime, operationType);
            pageResult = new PageInfo<>(resultList);
        } catch (RuntimeException e)
        {
            logger.error("error with querySystemLog: " + e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }

        return pageResult;
    }

    /**
     * 
     * @Title: 新增操作日志
     * @author Ye.Qiang
     * @date 2020年3月17日
     */
    public void addSysLog(SystemLogDO sysLog)
    {
        // 数据校验
        if (SysLogDatahandler.checkSysLogDO(sysLog))
        {
            sysLogMapper.insertSysLog(sysLog);
        }

    }

}
