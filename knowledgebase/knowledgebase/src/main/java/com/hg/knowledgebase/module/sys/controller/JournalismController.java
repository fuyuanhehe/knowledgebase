package com.hg.knowledgebase.module.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.sys.entity.Journalism;
import com.hg.knowledgebase.module.sys.entity.VO.JournalismVO;
import com.hg.knowledgebase.module.sys.service.IJournalismService;

/**
 * 
 * @Description: 新闻控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/journalism")
public class JournalismController
{

    private static Logger log = LoggerFactory
            .getLogger(JournalismController.class);

    @Autowired
    private IJournalismService journalismService;

    /**
     * 
     * @Description: 保存新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveJournalism")
    @SystemLog(value = "保存新闻")
    public ResponseResultBean saveJournalism(@RequestBody Journalism journalism)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = journalismService.addJournalism(journalism);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyJournalism")
    @SystemLog(value = "修改新闻")
    public ResponseResultBean modifyJournalism(
            @RequestBody Journalism journalism)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = journalismService.modifyJournalism(journalism);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeJournalism")
    @SystemLog(value = "根据id移除新闻")
    public ResponseResultBean removeJournalismById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = journalismService.removeJournalismById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据集合id批量移除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:16:17
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:16:17
     * @UpdateRemark: 修改内容
     * @param ids
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchJournalism")
    @SystemLog(value = "根据id移除新闻")
    public ResponseResultBean removeBatchUserById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = journalismService.removeBatchJournalismById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getJournalism")
    @SystemLog(value = "根据id查询新闻")
    public ResponseResultBean getJournalismById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            Journalism journalism = journalismService.findJournalismById(id);
            flg = journalism != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    journalism);
        } catch (Exception e)
        {
            log.error("---根据id查询新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验新闻名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkJournalismName")
    @SystemLog(value = "校验新闻名是否存在")
    public ResponseResultBean checkJournalismName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Journalism journalism = journalismService
                    .findJournalismByName(name);
            flg = journalism != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---根据账号查询新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getJournalismPage")
    @SystemLog(value = "获取分页新闻")
    public ResponseResultBean getJournalismPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Journalism journalism = JSONObject.parseObject(reqStr,
                    Journalism.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<JournalismVO> pageInfo = journalismService
                    .findJournalismPage(journalism, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页新闻异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
