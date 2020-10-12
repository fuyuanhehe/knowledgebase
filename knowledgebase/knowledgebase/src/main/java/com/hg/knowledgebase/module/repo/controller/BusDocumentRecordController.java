package com.hg.knowledgebase.module.repo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.repo.entity.BusDocumentRecord;
import com.hg.knowledgebase.module.repo.service.BusDocumentRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/** 知识文档记录controller
 * 功能描述:
 * @auther: fuyuan
 * @date: 2020/3/31 0031 15:25
 */
@RestController
@RequestMapping(value = "/api/repo/busDocumentRecord")
public class BusDocumentRecordController 
{

    private static Logger log = LoggerFactory.getLogger(BusDocumentRecordController.class);

    @Resource
    private BusDocumentRecordService busDocumentRecordService;

    /**
     * 功能描述: add 知识文档记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/saveBusDocumentRecord")
    @SystemLog(value = "新增知识文档记录")
    public ResponseResultBean saveBusDocumentRecord(@RequestBody BusDocumentRecord BusDocumentRecord )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            flg = busDocumentRecordService.addBusDocumentRecord(BusDocumentRecord);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---添加知识文档记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 修改 知识文档记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/modifyBusDocumentRecord")
    @SystemLog(value = "修改知识文档记录")
    public ResponseResultBean modifyBusDocumentRecord(@RequestBody BusDocumentRecord busDocumentRecord)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            flg = busDocumentRecordService.modifyBusDocumentRecord(busDocumentRecord);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---修改知识文档记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  知识文档记录删除
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/removeBusDocumentRecord")
    @SystemLog(value = "删除知识文档记录")
    public ResponseResultBean removeBusDocumentRecordById(@RequestBody BusDocumentRecord BusDocumentRecord)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = busDocumentRecordService.removeBusDocumentRecord(BusDocumentRecord);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---根据id删除知识文档记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  知识文档记录分页
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getAllBusDocumentRecord")
    @SystemLog(value = "查询所有知识文档记录")
    public ResponseResultBean getAllBusDocumentRecord(@RequestBody String str)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject json = JSONObject.parseObject(str);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            BusDocumentRecord busDocumentRecord = json.toJavaObject(BusDocumentRecord.class);

            PageInfo<BusDocumentRecord> data = busDocumentRecordService.findBusDocumentRecord(busDocumentRecord, pageSize, pageNum);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        }
        catch (Exception e)
        {
            log.error("---获取知识文档记录异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 单个查询 知识文档记录
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getBusDocumentRecord")
    @SystemLog(value = "单个查询知识文档记录")
    public ResponseResultBean getBusDocumentRecord(@RequestBody BusDocumentRecord BusDocumentRecord )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            BusDocumentRecord data = busDocumentRecordService.getBusDocumentRecord(BusDocumentRecord);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---单个查询知识文档记录---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }
    

}
