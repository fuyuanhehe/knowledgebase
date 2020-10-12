package com.hg.knowledgebase.module.repo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.repo.entity.BusDocumentRecord;
import com.hg.knowledgebase.module.repo.mapper.BusDocumentRecordMapper;
import com.hg.knowledgebase.module.repo.service.BusDocumentRecordService;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import com.hg.knowledgebase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/** 知识文档记录serviceImpl
 * 功能描述:
 * @auther: fuyuan
 * @date: 2020/3/31 0031 15:25
 */
@Service
public class BusDocumentRecordServiceImpl implements BusDocumentRecordService
{

    @Autowired
    private IDeptService deptService;
    @Autowired
    private BusDocumentRecordMapper busDocumentRecordMapper;
    /**
     * 功能描述: 知识分类分页
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    public PageInfo<BusDocumentRecord> findBusDocumentRecord(BusDocumentRecord busDocumentRecord, Integer pageSize, Integer pageNum) {
        PageInfo<BusDocumentRecord> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            return pageInfo;
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<BusDocumentRecord> data = busDocumentRecordMapper.getBusDocumentRecordPage(busDocumentRecord);
            pageInfo = new PageInfo<>(data);
        } finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;


    }
    /**
     * 功能描述: 知识分类新增
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean addBusDocumentRecord(BusDocumentRecord BusDocumentRecord)throws Exception  {
        if(BusDocumentRecord == null ){
            return false;
        }
        Dept dept = deptService.findCurrentDept();
        if(dept != null){
            BusDocumentRecord.setDeptId(dept.getId());
        }
        String ids = StringUtil.getUUID();
        BusDocumentRecord.setId(ids);
        BusDocumentRecord.setCreateTime(new Date());
        BusDocumentRecord.setCreateUser(SecurityUtils.getCurrentUserLogin().get());

        int result = busDocumentRecordMapper.insertSelective(BusDocumentRecord);

        return result > 0;
    }
    /**
     * 功能描述: 知识分类修改
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean modifyBusDocumentRecord(BusDocumentRecord busDocumentRecord) {
        if(busDocumentRecord == null){
            return false;
        }
        return busDocumentRecordMapper.updateByPrimaryKeySelective(busDocumentRecord) > 0;
    }
    /**
     * 功能描述: 知识分类删除
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean removeBusDocumentRecord(BusDocumentRecord busDocumentRecord) {
        if(busDocumentRecord == null){
            return false;
        }
        return busDocumentRecordMapper.deleteByPrimaryKey(busDocumentRecord.getId()) > 0;
    }
    /**
     * 功能描述: 单个查询
     * @auther: fuyuan
     * @date: 2020/3/24 0024 14:17
     */
    @Override
    public BusDocumentRecord getBusDocumentRecord(BusDocumentRecord busDocumentRecord) {
        if(busDocumentRecord == null){
            return null;
        }
        return busDocumentRecordMapper.selectByPrimaryKey(busDocumentRecord.getId());
    }
}
