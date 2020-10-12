package com.hg.knowledgebase.module.stat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.entity.Exam;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.eval.service.ICurriculumService;
import com.hg.knowledgebase.module.eval.service.IExamService;
import com.hg.knowledgebase.module.eval.service.ISubjectService;
import com.hg.knowledgebase.module.eval.service.ITestPaperService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
* @Description: 测评统计控制层
* @Author: 况克冬
* @CreateDate: 2020年3月3日下午4:53:06
* @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/stat/eval")
public class EvalStatController
{

    private static Logger log = LoggerFactory.getLogger(EvalStatController.class);
    
    @Autowired
    private ICurriculumService curriculumService;
    
    @Autowired
    private IExamService examService;
    
    @Autowired
    private ISubjectService subjectService;
    
    @Autowired
    private ITestPaperService testPaperService;
    
    /**
     * 
    * @Description: 获取课程总数（培训总数）
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurriculumCount")
    @SystemLog(value = "获取课程总数（培训总数）")
    public ResponseResultBean getCurriculumCount(@RequestBody Curriculum curriculum)
    {
        ResponseResultBean result = null;
        try
        {
            int count = curriculumService.findCurriculumCount(curriculum);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取课程总数（培训总数）异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取考试总数
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getExamCount")
    @SystemLog(value = "获取考试总数")
    public ResponseResultBean getExamCount(@RequestBody Exam exam)
    {
        ResponseResultBean result = null;
        try
        {
            int count = examService.findExamCount(exam);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取考试总数异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取题目总数（题库总数）
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getSubjectCount")
    @SystemLog(value = "获取题目总数（题库总数）")
    public ResponseResultBean getSubjectCount(@RequestBody Subject subject)
    {
        ResponseResultBean result = null;
        try
        {
            int count = subjectService.findSubjectCount(subject);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取题目总数异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取题目类型统计
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getSubjectTypeStat")
    @SystemLog(value = "获取题目类型统计")
    public ResponseResultBean getSubjectTypeStat(@RequestBody StatisticsCondition statisticsCondition)
    {
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = subjectService.findSubjectTypeStat(statisticsCondition);
            boolean flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("---获取题目类型统计异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取题目统计
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getSubjectStat")
    @SystemLog(value = "获取题目统计")
    public ResponseResultBean getSubjectStat(@RequestBody StatisticsCondition statisticsCondition)
    {
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = subjectService.findSubjectStat(statisticsCondition);
            boolean flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("---获取题目类型统计异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取试卷总数（考试总数）
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午1:49:47
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午1:49:47
    * @UpdateRemark: 修改内容
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getTestPaperCount")
    @SystemLog(value = "获取试卷总数（考试总数）")
    public ResponseResultBean getTestPaperCount(@RequestBody TestPaper testPaper)
    {
        ResponseResultBean result = null;
        try
        {
            int count = testPaperService.findTestPaperCount(testPaper);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, count);
        } 
        catch (Exception e)
        {
            log.error("---获取试卷总数（考试总数）异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取用户考试统计
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午2:29:33
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午2:29:33
    * @UpdateRemark: 修改内容
    * @param reqStr
    * @return
    * ResponseResult
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserExamStat")
    @SystemLog(value = "获取用户考试统计")
    public ResponseResultBean getUserExamStat(@RequestBody StatisticsCondition statisticsCondition)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = examService.findUserExamStat(statisticsCondition);
            flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("---获取用户考试统计异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Description: 获取用户已经学习的统计（学习统计）
    * @Author: 况克冬
    * @CreateDate: 2020年3月18日上午10:19:26
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月18日上午10:19:26
    * @UpdateRemark: 修改内容
    * @param statisticsCondition
    * @return
    * ResponseResultBean
    * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserLearnedStat")
    @SystemLog(value = "获取用户已经学习的统计（学习统计）")
    public ResponseResultBean getUserLearnedStat(@RequestBody StatisticsCondition statisticsCondition)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            SimpleStatisticsResult statistics = curriculumService.findUserLearnedStat(statisticsCondition);
            flg = statistics != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, statistics);
        } 
        catch (Exception e)
        {
            log.error("--获取用户已经学习的统计（学习统计）异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}
