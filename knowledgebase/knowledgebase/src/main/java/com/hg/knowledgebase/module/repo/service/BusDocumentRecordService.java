package com.hg.knowledgebase.module.repo.service;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.repo.entity.BusDocumentRecord;


/**
 * 功能描述: 知识文档记录service
 * @auther: fuyuan
 * @date: 2020/3/31 0031 15:24
 */
public interface BusDocumentRecordService
{
    /**
     * 功能描述: 知识分类分页
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    PageInfo<BusDocumentRecord> findBusDocumentRecord(BusDocumentRecord BusDocumentRecord, Integer pageSize, Integer pageNum);

    /**
     * 功能描述: 知识分类新增
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean addBusDocumentRecord(BusDocumentRecord BusDocumentRecord)throws Exception ;
    /**
     * 功能描述: 知识分类修改
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean modifyBusDocumentRecord(BusDocumentRecord BusDocumentRecord);
    /**
     * 功能描述: 知识分类删除
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean removeBusDocumentRecord(BusDocumentRecord BusDocumentRecord) ;

    /**
     * 功能描述: 单个查询
     * @auther: fuyuan
     * @date: 2020/3/24 0024 14:17
     */

    BusDocumentRecord getBusDocumentRecord(BusDocumentRecord BusDocumentRecord);
    
}
