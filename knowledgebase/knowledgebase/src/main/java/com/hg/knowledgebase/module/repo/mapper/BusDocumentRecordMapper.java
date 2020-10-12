package com.hg.knowledgebase.module.repo.mapper;

import com.hg.knowledgebase.module.repo.entity.BusDocumentRecord;

import java.util.List;
/**
 * 功能描述: 知识文档记录mapper
 * @auther: fuyuan
 * @date: 2020/3/31 0031 15:24
 */
public interface BusDocumentRecordMapper
{
    int deleteByPrimaryKey(String id);

    int insert(BusDocumentRecord record);

    int insertSelective(BusDocumentRecord record);

    BusDocumentRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusDocumentRecord record);

    int updateByPrimaryKey(BusDocumentRecord record);
    /**
     * 功能描述: 知识文档分页查询
     * @auther: fuyuan
     * @date: 2020/3/31 0031 15:23
     */
    List<BusDocumentRecord> getBusDocumentRecordPage(BusDocumentRecord busDocumentRecord);

}