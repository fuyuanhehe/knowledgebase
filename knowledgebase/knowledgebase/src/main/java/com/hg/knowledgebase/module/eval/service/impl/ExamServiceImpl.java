package com.hg.knowledgebase.module.eval.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.customenum.StatTypeEnum;
import com.hg.knowledgebase.module.eval.entity.Exam;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.eval.entity.UserExam;
import com.hg.knowledgebase.module.eval.entity.VO.ExamVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserExamVO;
import com.hg.knowledgebase.module.eval.mapper.IExamMapper;
import com.hg.knowledgebase.module.eval.mapper.ITestPaperMapper;
import com.hg.knowledgebase.module.eval.service.IExamService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;
import com.hg.knowledgebase.module.sys.mapper.IUserMapper;
import com.hg.knowledgebase.util.StatUtil;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 考试业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class ExamServiceImpl implements IExamService
{

    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private ITestPaperMapper testPaperMapper;

    /**
     * 
     * @Description: 添加考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addExam(ExamVO examVO) throws GlobalException
    {
        if (examVO == null)
        {
            throw new GlobalException("添加考试信息不能为空，入参【考试信息：{}】", examVO);
        }
        // 执行条数
        int exec = 0;
        String examId = StringUtil.getUUID();
        examVO.setId(examId);
        examVO.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = examMapper.insertExam(examVO);
            // 添加用户考试关系
            Set<String> userIdSet = examVO.getUsers();
            // 如果考试信息存在用户，则添加考试用户关系
            if (userIdSet != null && userIdSet.size() > 0)
            {
                examMapper.insertUserExam(examId, userIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("添加考试SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeExamById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除考试信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = examMapper.deleteExamById(id);
            // 删除用户考试
            UserExam userExam = new UserExam();
            userExam.setExamId(id);
            examMapper.deleteUserExam(userExam);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除考试SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchExamById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量考试考试信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = examMapper.deleteBatchExamById(ids);
            // 批量删除用户考试
            examMapper.deleteBatchUserExamByExamId(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量考试SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyExam(ExamVO examVO) throws GlobalException
    {
        String examId = examVO.getId();
        if (examVO == null || StringUtils.isEmpty(examId))
        {
            throw new GlobalException("修改考试信息主键不能为空，入参【考试信息：{}】", examVO);
        }
        // 执行条数
        int exec = 0;
        examVO.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        examVO.setUpdateTime(new Date());
        try
        {
            exec = examMapper.updateExam(examVO);
            // 删除用户考试
            UserExam userExam = new UserExam();
            userExam.setExamId(examId);
            examMapper.deleteUserExam(userExam);
            // 添加用户考试
            Set<String> userIdSet = examVO.getUsers();
            // 如果考试存在用户信息，则添加考试用户关系
            if (userIdSet != null && userIdSet.size() > 0)
            {
                examMapper.insertUserExam(examId, userIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("修改考试SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Exam
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public ExamVO findExamById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询考试信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return examMapper.selectExamById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Exam
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Exam findExamByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询考试信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return examMapper.selectExamByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Exam>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<ExamVO> findAllExam(Exam exam) throws GlobalException
    {
        try
        {
            return examMapper.selectBaseExamList(exam);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询考试分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param exam
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Exam>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<ExamVO> findExamPage(Exam exam, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<ExamVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("询考试分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<ExamVO> examList = examMapper.selectExamList(exam);
            pageInfo = new PageInfo<>(examList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询考试分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询考试总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午5:04:20
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午5:04:20
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws Exception
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findExamCount(Exam exam) throws GlobalException
    {
        try
        {
            return examMapper.selectExamCount(exam);
        } catch (SQLException e)
        {
            throw new GlobalException("查询考试总数量SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询考试统计信息
     * @Author: 况克冬
     * @CreateDate: 2020年4月14日下午4:49:21
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月14日下午4:49:21
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws Exception
     *             ExamVO
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public ExamVO findExamCountMsg(String id) throws GlobalException
    {
        ExamVO examVO = null;
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询查询考试统计信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            examVO = examMapper.selectExamCountMsg(id);
            // 如果查询有数据，则计数考试平均分
            if (examVO != null)
            {
                Integer totalSubjectCount = examVO.getTotalSubjectCount();
                examVO.setAverageScore(
                        totalSubjectCount == null || totalSubjectCount == 0 ? 0
                                : examVO.getSumScore() / totalSubjectCount);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("查询考试统计信息SQL异常：{}", e.getMessage());
        }
        return examVO;
    }

    /////////////////////////////// 用户考试关系//////////////////////////////////////////////////////
    /**
     * 
     * @Description: 更新当前用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyCurrentUserExam(UserExam userExam)
            throws GlobalException
    {
        // 源题答案集合
        List<String> sourceAnswersList = null;
        // 用户答案集合
        List<String> userAnswerList = null;
        // 参数校验
        if (userExam == null || StringUtils.isEmpty(userExam.getExamId()))
        {
            throw new GlobalException("更新当前用户考试的考试id不能为空，入参【用户考试信息：{}】",
                    userExam);
        }
        // 执行条数
        int exec = 0;
        // 考试id,前面校验了考试id因此后面不需要校验
        String examId = userExam.getExamId();
        try
        {
            // 获取考试信息
            ExamVO examVO = examMapper.selectExamById(examId);
            // 如果考试信息或者试卷id为空，则抛出异常
            if (examVO == null || StringUtils.isEmpty(examVO.getTestPaperId()))
            {
                throw new GlobalException("更新当前用户考试的考试信息不能为空，入参【考试信息：{}】",
                        examVO);
            }
            // 试卷id,前面校验了试卷id因此后面不需要校验
            String testPaperId = examVO.getTestPaperId();
            // 获取试卷信息
            TestPaper testPaper = testPaperMapper
                    .selectTestPaperById(testPaperId);
            // 如果试卷信息为空，则抛出异常
            if (testPaper == null)
            {
                throw new GlobalException("更新当前用户考试的试卷信息不能为空，入参【试卷信息：{}】",
                        testPaper);
            }
            // 获取当前登录用户名
            String userName = SecurityUtils.getCurrentUserLogin().get();
            // 获取当前用户信息
            UserVO userVO = userMapper.selectUserByName(userName);
            if (userVO == null || StringUtils.isEmpty(userVO.getId()))
            {
                throw new GlobalException("更新当前用户考试的用户信息不能为空，入参【用户信息：{}】",
                        userVO);
            }
            /**
             * 计算考试得分
             */
            // 试卷
            String subjectsJson = testPaper.getSubjects();
            // 考试分数
            double score = 0.0;
            // 正确条数
            int correctNum = 0;
            // 错误条数
            int errorNum = 0;
            // 用户考试提交答案
            String answersJson = userExam.getAnswers();
            // 如果用户考试答案和题目信息都存在，则执行解析及后续操作
            if (!StringUtils.isEmpty(answersJson)
                    && !StringUtils.isEmpty(subjectsJson))
            {
                // 用户总答案集合
                List<String> userTotalAnswerList = JSONObject
                        .parseArray(answersJson).toJavaList(String.class);
                // 解析试卷题
                List<Subject> subjectList = JSONObject.parseArray(subjectsJson)
                        .toJavaList(Subject.class);
                // 校验答题数量和考试数量是否匹配，不匹配则抛出异常
                if (userTotalAnswerList.size() != subjectList.size())
                {
                    throw new GlobalException("更新当前用户考试的用户考题和实际考题不匹配】");
                }
                for (int i = 0, len = subjectList.size(); i < len; i++)
                {
                    sourceAnswersList = JSONObject
                            .parseArray(subjectList.get(i).getAnswers())
                            .toJavaList(String.class);
                    userAnswerList = JSONObject
                            .parseArray(userTotalAnswerList.get(i))
                            .toJavaList(String.class);
                    // 校验用户答案和题目源答案是否相等
                    if (sourceAnswersList.size() == userAnswerList.size()
                            && sourceAnswersList.containsAll(userAnswerList))
                    {
                        score += subjectList.get(i).getScore();
                        correctNum += 1;
                    }
                }
                errorNum = subjectList.size() - correctNum;
            }
            // 添加用户考试信息
            userExam.setUserId(userVO.getId());
            userExam.setStatus(1);
            userExam.setScore(score);
            userExam.setSubmitTime(new Date());
            userExam.setErrorNum(errorNum);
            exec = examMapper.updateUserExam(userExam);
        } catch (SQLException e)
        {
            throw new GlobalException("更新当前用户考试SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 查询用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return
     * @throws Exception
     *             UserExamVO
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserExamVO findUserExam(UserExam userExam) throws GlobalException
    {
        if (userExam == null || StringUtils.isEmpty(userExam.getExamId())
                || StringUtils.isEmpty(userExam.getUserId()))
        {
            throw new GlobalException("查询用户考试信息不能为空，入参【考试信息：{}】", userExam);
        }
        try
        {
            return examMapper.selectUserExam(userExam.getUserId(),
                    userExam.getExamId());
        } catch (SQLException e)
        {
            throw new GlobalException("查询用户考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return
     * @throws Exception
     *             UserExamVO
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserExamVO findCurrentUserExam(UserExam userExam)
            throws GlobalException
    {
        if (userExam == null || StringUtils.isEmpty(userExam.getExamId()))
        {
            throw new GlobalException("查询当前用户用户考试信息不能为空，入参【考试信息：{}】", userExam);
        }
        // 获取当前登录用户名
        String userName = SecurityUtils.getCurrentUserLogin().get();
        try
        {
            // 获取当前用户信息
            UserVO userVO = userMapper.selectUserByName(userName);
            // 如果用户不存在，则抛出异常
            if (userVO == null || StringUtils.isEmpty(userVO.getId()))
            {
                throw new GlobalException("当前用户不存在，入参【用户：{}】", userName);
            }
            return examMapper.selectUserExam(userVO.getId(),
                    userExam.getExamId());
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户用户考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户所有用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userExamVO
     * @return
     * @throws Exception
     *             List<UserExamVO>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserExamVO> findAllCurrentUserExam(UserExamVO userExamVO)
            throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        userExamVO = userExamVO == null ? new UserExamVO() : userExamVO;
        userExamVO.setUserName(userName);
        try
        {
            return examMapper.selectUserExamList(userExamVO);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户所有用户考试SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 获取用户考试分页
     * @Author: 况克冬
     * @CreateDate: 2020年4月14日下午6:17:29
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月14日下午6:17:29
     * @UpdateRemark: 修改内容
     * @param userExamVO
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     *             PageInfo<UserExamVO>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserExamVO> findUserExamPage(UserExamVO userExamVO,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<UserExamVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("获取用户考试分页信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<UserExamVO> examList = examMapper
                    .selectUserExamList(userExamVO);
            pageInfo = new PageInfo<>(examList);
        } catch (SQLException e)
        {
            throw new GlobalException("获取用户考试分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    };

    /**
     * 
     * @Description: 获取用户考试统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月17日下午4:19:29
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月17日下午4:19:29
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatisticsResult
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public SimpleStatisticsResult findUserExamStat(
            StatisticsCondition statisticsCondition) throws GlobalException
    {
        boolean flg = statisticsCondition == null
                || StringUtils.isEmpty(statisticsCondition.getType())
                || StringUtils.isEmpty(statisticsCondition.getBeginTime())
                || StringUtils.isEmpty(statisticsCondition.getEndTime());
        if (flg)
        {
            throw new GlobalException("获取用户考试统计信息不能为空，入参【统计信息：{}】",
                    statisticsCondition);
        }
        String[] datas = statisticsCondition.getBeginTime()
                .split(StatUtil.SPLITTER);
        statisticsCondition.setCustomType(datas.length);
        try
        {
            List<StatisticsCondition> statList = examMapper
                    .selectUserExamStatList(statisticsCondition);
            StatTypeEnum statTypeEnum = StatTypeEnum
                    .getStatTypeEnum(statisticsCondition.getType());
            // 如果统计数据或统计类型为空，则抛出异常
            if (statList == null || statTypeEnum == null)
            {
                throw new GlobalException(
                        "获取用户考试统计信息结果或类型不能为空，入参【统计结果信息：{}，类型：{}】", statList,
                        statTypeEnum);
            }
            try
            {
                return StatUtil.combinationStat(statisticsCondition,
                        statTypeEnum, datas, statList);
            } catch (ParseException e)
            {
                throw new GlobalException("组合统计异常：{}", e.getMessage());
            }
        } catch (SQLException e)
        {
            throw new GlobalException("获取用户考试统计SQL异常：{}", e.getMessage());
        }
    }
}
