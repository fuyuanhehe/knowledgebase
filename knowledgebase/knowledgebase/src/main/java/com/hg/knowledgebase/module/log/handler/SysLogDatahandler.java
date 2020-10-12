/**
 * 
 */
package com.hg.knowledgebase.module.log.handler;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.hg.knowledgebase.module.log.entity.SystemLogDO;
import com.hg.knowledgebase.module.log.entity.SystemLogVO;

/**
 * @TODO 系统日志参数校验
 * @author Ye.Qiang
 * @date 2020年3月17日
 */
public class SysLogDatahandler
{
    /**
     * 
     * @Title: 系统日志参数校验
     * @author Ye.Qiang
     * @date 2020年3月17日
     */
    public static boolean checkSysLogDO(SystemLogDO logDO)
    {

        if (null == logDO)
        {
            return false;
        }

        /** 操作用户姓名 **/
        String operationUser = logDO.getOperationUser();

        if (StringUtils.isEmpty(operationUser))
        {
            return false;
        }

        /** 操作用户操作IP地址 **/
        String operationUserIp = logDO.getOperationUserIp();
        if (StringUtils.isEmpty(operationUserIp))
        {
            return false;
        }
        /** 操作时间 **/
        Date operationTime = logDO.getOperationTime();
        if (StringUtils.isEmpty(operationTime))
        {
            return false;
        }
        /** 操作方式 **/
        String operationMsg = logDO.getOperationMsg();
        if (StringUtils.isEmpty(operationMsg))
        {
            return false;
        }

        return true;

    }

    /**
     * 
     * @Title: 系统日志参数校验
     * @author Ye.Qiang
     * @date 2020年3月17日
     */
    public static boolean checkSysLogVO(SystemLogVO systemLogVO)
    {

        // 实体为空,直接返回
        if (null == systemLogVO)
        {
            return false;
        }
        // 每页数据条数不能为空
        String pageSize = systemLogVO.getPageSize();
        if (StringUtils.isEmpty(pageSize))
        {
            return false;
        }
        // 当前页数不能为空
        String pageNum = systemLogVO.getPageNum();
        if (StringUtils.isEmpty(pageNum))
        {
            return false;
        }

        return true;

    }

}
