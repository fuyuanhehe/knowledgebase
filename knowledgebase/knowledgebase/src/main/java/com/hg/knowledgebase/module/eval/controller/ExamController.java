package com.hg.knowledgebase.module.eval.controller;

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
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.Exam;
import com.hg.knowledgebase.module.eval.entity.UserExam;
import com.hg.knowledgebase.module.eval.entity.VO.ExamVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserExamVO;
import com.hg.knowledgebase.module.eval.service.IExamService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 考试控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/eval/exam")
public class ExamController
{

    private static Logger log = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private IExamService examService;

    /**
     * 
     * @Description: 保存考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveExam")
    @SystemLog(value = "保存考试")
    public ResponseResultBean saveExam(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            ExamVO examVO = JSONObject.parseObject(reqStr, ExamVO.class);
            flg = examService.addExam(examVO);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyExam")
    @SystemLog(value = "修改考试")
    public ResponseResultBean modifyExam(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            ExamVO examVO = JSONObject.parseObject(reqStr, ExamVO.class);
            flg = examService.modifyExam(examVO);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeExam")
    @SystemLog(value = "根据id移除考试")
    public ResponseResultBean removeExamById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = examService.removeExamById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id批量移除考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchExam")
    @SystemLog(value = "根据id批量移除考试")
    public ResponseResultBean removeBatchExamById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = examService.removeBatchExamById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getExam")
    @SystemLog(value = "根据id查询考试")
    public ResponseResultBean getExamById(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            ExamVO examVO = examService.findExamById(id);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    examVO);
        } catch (Exception e)
        {
            log.error("---根据id查询考试异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验考试名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkExamName")
    @SystemLog(value = "校验考试名是否存在")
    public ResponseResultBean checkExamName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Exam exam = examService.findExamByName(name);
            flg = exam != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验考试名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllExam")
    @SystemLog(value = "获取所有考试")
    public ResponseResultBean getAllExam(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Exam exam = JSONObject.parseObject(reqStr, Exam.class);
            List<ExamVO> exams = examService.findAllExam(exam);
            flg = exams != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, exams);
        } catch (Exception e)
        {
            log.error("---获取所有考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getExamPage")
    @SystemLog(value = "获取分页考试")
    public ResponseResultBean getExamPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Exam exam = JSONObject.parseObject(reqStr, Exam.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<ExamVO> pageInfo = examService.findExamPage(exam, pageNum,
                    pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/getExamCountMsg")
    @SystemLog(value = "获取考试统计信息")
    public ResponseResultBean getExamStatMsg(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            ExamVO examVO = examService.findExamCountMsg(id);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    examVO);
        } catch (Exception e)
        {
            log.error("---获取考试统计信息异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    ///////////////////////////////////// 用户考试关系//////////////////////////////////////////////
    /**
     * 
     * @Description: 保存当前用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveCurrentUserExam")
    @SystemLog(value = "保存当前用户考试")
    public ResponseResultBean saveCurrentUserExam(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        UserExamVO userExamVO = null;
        try
        {
            UserExam userExam = JSONObject.parseObject(reqStr, UserExam.class);
            boolean flg = examService.modifyCurrentUserExam(userExam);
            if (flg)
            {
                userExamVO = examService.findUserExam(userExam);
            }
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userExamVO);
        } catch (Exception e)
        {
            log.error("---保存当前用户考试异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserExam")
    @SystemLog(value = "获取用户考试")
    public ResponseResultBean getUserExam(@RequestBody UserExam userExam)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserExamVO userExamVO = examService.findUserExam(userExam);
            flg = userExamVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userExamVO);
        } catch (Exception e)
        {
            log.error("---获取用户考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentUserExam")
    @SystemLog(value = "获取当前用户用户考试")
    public ResponseResultBean getCurrentUserExam(@RequestBody UserExam userExam)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserExamVO userExamVO = examService.findCurrentUserExam(userExam);
            flg = userExamVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userExamVO);
        } catch (Exception e)
        {
            log.error("---获取当前用户用户考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户所有用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCurrentUserExam")
    @SystemLog(value = "获取当前用户所有用户考试")
    public ResponseResultBean getAllCurrentUserExam(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserExamVO userExamVO = JSONObject.parseObject(reqStr,
                    UserExamVO.class);
            List<UserExamVO> userExamVOs = examService
                    .findAllCurrentUserExam(userExamVO);
            flg = userExamVOs != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userExamVOs);
        } catch (Exception e)
        {
            log.error("---获取当前用户所有用户考试异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户用户考试分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentUserExamPage")
    @SystemLog(value = "获取当前用户用户考试分页")
    public ResponseResultBean getCurrentUserExamPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserExamVO userExamVO = JSONObject.parseObject(reqStr,
                    UserExamVO.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            String userName = SecurityUtils.getCurrentUserLogin().get();
            userExamVO.setUserName(userName);
            PageInfo<UserExamVO> pageInfo = examService
                    .findUserExamPage(userExamVO, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取当前用户用户考试分页异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取用户考试分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param exam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserExamPage")
    @SystemLog(value = "获取用户考试分页")
    public ResponseResultBean getUserExamPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserExamVO userExamVO = JSONObject.parseObject(reqStr,
                    UserExamVO.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<UserExamVO> pageInfo = examService
                    .findUserExamPage(userExamVO, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取用户考试分页异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
