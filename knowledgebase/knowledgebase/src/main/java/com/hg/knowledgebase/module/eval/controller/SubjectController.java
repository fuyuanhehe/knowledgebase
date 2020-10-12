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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.VO.SubjectVO;
import com.hg.knowledgebase.module.eval.service.ISubjectService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 题目控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/eval/subject")
public class SubjectController
{

    private static Logger log = LoggerFactory
            .getLogger(SubjectController.class);

    @Autowired
    private ISubjectService subjectService;

    /**
     * 
     * @Description: 保存题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param subject
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveSubject")
    @SystemLog(value = "保存题目")
    public ResponseResultBean saveSubject(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Subject subject = JSONObject.parseObject(reqStr, Subject.class);
            flg = subjectService.addSubject(subject);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param subject
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifySubject")
    @SystemLog(value = "修改题目")
    public ResponseResultBean modifySubject(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Subject subject = JSONObject.parseObject(reqStr, Subject.class);
            flg = subjectService.modifySubject(subject);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeSubject")
    @SystemLog(value = "根据id移除题目")
    public ResponseResultBean removeSubjectById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = subjectService.removeSubjectById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id批量移除题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchSubject")
    @SystemLog(value = "根据id批量移除题目")
    public ResponseResultBean removeBatchSubjectById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = subjectService.removeBatchSubjectById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getSubject")
    @SystemLog(value = "根据id查询题目")
    public ResponseResultBean getSubjectById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            SubjectVO subject = subjectService.findSubjectById(id);
            flg = subject != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    subject);
        } catch (Exception e)
        {
            log.error("---根据id查询题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验题目名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkSubjectName")
    @SystemLog(value = "校验题目名是否存在")
    public ResponseResultBean checkSubjectName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Subject subject = subjectService.findSubjectByName(name);
            flg = subject != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验题目名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param subject
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllSubject")
    @SystemLog(value = "获取所有题目")
    public ResponseResultBean getAllSubject(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Subject subject = JSONObject.parseObject(reqStr, Subject.class);
            List<Subject> subjects = subjectService.findAllSubject(subject);
            flg = subjects != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    subjects);
        } catch (Exception e)
        {
            log.error("---获取所有题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param subject
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getSubjectPage")
    @SystemLog(value = "获取分页题目")
    public ResponseResultBean getSubjectPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Subject subject = JSONObject.parseObject(reqStr, Subject.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<SubjectVO> pageInfo = subjectService
                    .findSubjectPage(subject, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页题目异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 导入题目
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午4:41:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午4:41:42
     * @UpdateRemark: 修改内容
     * @param multipart
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/inputSubject")
    @SystemLog(value = "导入题目")
    public ResponseResultBean inputSubject(
            @RequestParam(GlobalConstant.FILE_NAME) MultipartFile multipart)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        if (multipart == null)
        {
            result = new ResponseResultBean(flg, "文件上传失败！");
        } else
        {
            try
            {
                flg = subjectService.inputSubject(multipart.getInputStream());
                result = new ResponseResultBean(flg,
                        flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
            } catch (Exception e)
            {
                log.error("---导入题目异常---", e.getMessage());
                result = new ResponseResultBean(flg, GlobalConstant.RULE_FAIL);
            }
        }
        return result;
    }

    /**
     * 
     * @Description: 导出题目
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午4:41:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午4:41:42
     * @UpdateRemark: 修改内容
     * @param multipart
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @GetMapping(value = "/outputSubject")
    @SystemLog(value = "导出题目")
    public void outputSubject(Subject subject)
    {
        try
        {
            subjectService.outputSubject(subject);
        } catch (Exception e)
        {
            log.error("---导出题目异常---", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 导出题目模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午4:41:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午4:41:42
     * @UpdateRemark: 修改内容
     * @param multipart
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @GetMapping(value = "/outputSubjectTemplete")
    @SystemLog(value = "导出题目模板")
    public void outputSubjectTemplete()
    {
        try
        {
            subjectService.outputSubjectTemplete();
        } catch (Exception e)
        {
            log.error("---导出题目模板异常---", e.getMessage());
        }
    }
}
