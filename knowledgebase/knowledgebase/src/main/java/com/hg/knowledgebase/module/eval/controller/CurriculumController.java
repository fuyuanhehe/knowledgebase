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
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.entity.UserCurriculum;
import com.hg.knowledgebase.module.eval.entity.VO.CurriculumVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserCurriculumVO;
import com.hg.knowledgebase.module.eval.service.ICurriculumService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 课程控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/eval/curriculum")
public class CurriculumController
{

    private static Logger log = LoggerFactory
            .getLogger(CurriculumController.class);

    @Autowired
    private ICurriculumService curriculumService;

    /**
     * 
     * @Description: 保存课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveCurriculum")
    @SystemLog(value = "保存课程")
    public ResponseResultBean saveCurriculum(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            CurriculumVO curriculumVO = JSONObject.parseObject(reqStr,
                    CurriculumVO.class);
            flg = curriculumService.addCurriculum(curriculumVO);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyCurriculum")
    @SystemLog(value = "修改课程")
    public ResponseResultBean modifyCurriculum(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            CurriculumVO curriculumVO = JSONObject.parseObject(reqStr,
                    CurriculumVO.class);
            flg = curriculumService.modifyCurriculum(curriculumVO);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id批量移除课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchCurriculum")
    @SystemLog(value = "根据id批量移除课程")
    public ResponseResultBean removeBatchCurriculum(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = curriculumService.removeBatchCurriculumById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeCurriculum")
    @SystemLog(value = "根据id移除课程")
    public ResponseResultBean removeCurriculumById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = curriculumService.removeCurriculumById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurriculum")
    @SystemLog(value = "根据id查询课程")
    public ResponseResultBean getCurriculumById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            CurriculumVO curriculumVO = curriculumService
                    .findCurriculumById(id);
            flg = curriculumVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    curriculumVO);
        } catch (Exception e)
        {
            log.error("---根据id查询课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验课程名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkCurriculumName")
    @SystemLog(value = "校验课程名是否存在")
    public ResponseResultBean checkCurriculumName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Curriculum curriculum = curriculumService
                    .findCurriculumByName(name);
            flg = curriculum != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验课程名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCurriculum")
    @SystemLog(value = "获取所有课程")
    public ResponseResultBean getAllCurriculum(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Curriculum curriculum = JSONObject.parseObject(reqStr,
                    Curriculum.class);
            List<Curriculum> curriculums = curriculumService
                    .findAllCurriculum(curriculum);
            flg = curriculums != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    curriculums);
        } catch (Exception e)
        {
            log.error("---获取所有课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurriculumPage")
    @SystemLog(value = "获取分页课程")
    public ResponseResultBean getCurriculumPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Curriculum curriculum = JSONObject.parseObject(reqStr,
                    Curriculum.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<Curriculum> pageInfo = curriculumService
                    .findCurriculumPage(curriculum, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    ///////////////////////////////////// 用户课程关系//////////////////////////////////////////////
    /**
     * 
     * @Description: 修改用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日上午9:10:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日上午9:10:38
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyUserCurriculum")
    @SystemLog(value = "修改用户课程")
    public ResponseResultBean modifyUserCurriculum(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserCurriculum userCurriculum = JSONObject.parseObject(reqStr,
                    UserCurriculum.class);
            flg = curriculumService.modifyUserCurriculum(userCurriculum);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userCurriculum);
        } catch (Exception e)
        {
            log.error("---修改用户课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/getCurrentUserCurriculum")
    @SystemLog(value = "获取当前用户的用户课程")
    public ResponseResultBean getCurrentUserCurriculum(
            @RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            UserCurriculum userCurriculum = JSONObject.parseObject(reqStr,
                    UserCurriculum.class);
            UserCurriculumVO userCurriculumVO = curriculumService
                    .findCurrentUserCurriculum(userCurriculum);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    userCurriculumVO);
        } catch (Exception e)
        {
            log.error("---获取当前用户的用户课程异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/getUserCurriculum")
    @SystemLog(value = "获取用户课程")
    public ResponseResultBean getUserCurriculum(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            UserCurriculum userCurriculum = JSONObject.parseObject(reqStr,
                    UserCurriculum.class);
            UserCurriculumVO userCurriculumVO = curriculumService
                    .findUserCurriculum(userCurriculum);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    userCurriculumVO);
        } catch (Exception e)
        {
            log.error("---获取用户课程异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户所有课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCurrentUserCurriculum")
    @SystemLog(value = "获取当前用户所有课程")
    public ResponseResultBean getAllCurrentUserCurriculum(
            @RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserCurriculumVO userCurriculumVO = JSONObject.parseObject(reqStr,
                    UserCurriculumVO.class);
            List<UserCurriculumVO> userCurriculumVOs = curriculumService
                    .findAllCurrentUserCurriculum(userCurriculumVO);
            flg = userCurriculumVOs != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    userCurriculumVOs);
        } catch (Exception e)
        {
            log.error("---获取当前用户所有课程异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户课程分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentUserCurriculumPage")
    @SystemLog(value = "获取当前用户课程分页")
    public ResponseResultBean getCurrentUserCurriculumPage(
            @RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserCurriculumVO userCurriculumVO = JSONObject.parseObject(reqStr,
                    UserCurriculumVO.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<UserCurriculumVO> pageInfo = curriculumService
                    .findCurrentUserCurriculumPage(userCurriculumVO, pageNum,
                            pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取当前用户课程分页异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
