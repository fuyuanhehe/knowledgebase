package com.hg.knowledgebase.module.eval.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.eval.service.ITestPaperService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 试卷控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/eval/testPaper")
public class TestPaperController
{

    private static Logger log = LoggerFactory
            .getLogger(TestPaperController.class);

    @Autowired
    private ITestPaperService testPaperService;

    /**
     * 
     * @Description: 保存试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveTestPaper")
    @SystemLog(value = "保存试卷")
    public ResponseResultBean saveTestPaper(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            TestPaper testPaper = JSONObject.parseObject(reqStr,
                    TestPaper.class);
            flg = testPaperService.addTestPaper(testPaper);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    testPaper.getId());
        } catch (Exception e)
        {
            log.error("---添加试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyTestPaper")
    @SystemLog(value = "修改试卷")
    public ResponseResultBean modifyTestPaper(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            TestPaper testPaper = JSONObject.parseObject(reqStr,
                    TestPaper.class);
            flg = testPaperService.modifyTestPaper(testPaper);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeTestPaper")
    @SystemLog(value = "根据id移除试卷")
    public ResponseResultBean removeTestPaperById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = testPaperService.removeTestPaperById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id批量移除试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchTestPaper")
    @SystemLog(value = "根据id批量移除试卷")
    public ResponseResultBean removeBatchTestPaperById(
            @RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = testPaperService.removeBatchTestPaperById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getTestPaper")
    @SystemLog(value = "根据id查询试卷")
    public ResponseResultBean getTestPaperById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            TestPaper testPaper = testPaperService.findTestPaperById(id);
            flg = testPaper != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    testPaper);
        } catch (Exception e)
        {
            log.error("---根据id查询试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验试卷名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkTestPaperName")
    @SystemLog(value = "校验试卷名是否存在")
    public ResponseResultBean checkTestPaperName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            TestPaper testPaper = testPaperService.findTestPaperByName(name);
            flg = testPaper != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验试卷名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllTestPaper")
    @SystemLog(value = "获取所有试卷")
    public ResponseResultBean getAllTestPaper(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            TestPaper testPaper = JSONObject.parseObject(reqStr,
                    TestPaper.class);
            List<TestPaper> testPapers = testPaperService
                    .findAllTestPaper(testPaper);
            flg = testPapers != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    testPapers);
        } catch (Exception e)
        {
            log.error("---获取所有试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getTestPaperPage")
    @SystemLog(value = "获取分页试卷")
    public ResponseResultBean getTestPaperPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            TestPaper testPaper = JSONObject.parseObject(reqStr,
                    TestPaper.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<TestPaper> pageInfo = testPaperService
                    .findTestPaperPage(testPaper, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页试卷异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 导出试卷（后期做）
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @GetMapping(value = "/outputTestPaper")
    @SystemLog(value = "导出试卷")
    public void outputTestPaper(@RequestParam(GlobalConstant.ID) String id)
    {
        try
        {
            testPaperService.outputTestPaper(id);
        } catch (Exception e)
        {
            log.error("---导出试卷异常---", e.getMessage());
        }
    }
}
