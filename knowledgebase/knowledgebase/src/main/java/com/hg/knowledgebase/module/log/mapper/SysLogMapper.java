/**
 * 
 */
package com.hg.knowledgebase.module.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.log.entity.SystemLogDO;

/**
 * @TODO
 * @author Ye.Qiang
 * @date 2020年3月17日
 */
@Mapper
public interface SysLogMapper
{

    int insertSysLog(SystemLogDO logDO);

    List<SystemLogDO> selectSystemLog(@Param("startAt") String startAt, @Param("endAt") String endAt,
            @Param("operationType") String operationType);
}
