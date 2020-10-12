package com.hg.knowledgebase.module.eval.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.eval.entity.Exam;
import com.hg.knowledgebase.module.eval.entity.UserExam;
import com.hg.knowledgebase.module.eval.entity.VO.ExamVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserExamVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 考试服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IExamService
{

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
    boolean addExam(ExamVO examVO) throws GlobalException;

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
    boolean removeExamById(String id) throws GlobalException;

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
    boolean removeBatchExamById(List<String> ids) throws GlobalException;

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
    boolean modifyExam(ExamVO examVO) throws GlobalException;

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
    ExamVO findExamById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询考试
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
    Exam findExamByName(String name) throws GlobalException;

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
    List<ExamVO> findAllExam(Exam exam) throws GlobalException;

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
    PageInfo<ExamVO> findExamPage(Exam exam, Integer pageNum, Integer pageSize)
            throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int findExamCount(Exam exam) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             ExamVO
     * @Version: 1.0.0
     */
    ExamVO findExamCountMsg(String id) throws GlobalException;

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
    boolean modifyCurrentUserExam(UserExam userExam) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             UserExamVO
     * @Version: 1.0.0
     */
    UserExamVO findUserExam(UserExam userExam) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             UserExamVO
     * @Version: 1.0.0
     */
    UserExamVO findCurrentUserExam(UserExam userExam) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<UserExamVO>
     * @Version: 1.0.0
     */
    List<UserExamVO> findAllCurrentUserExam(UserExamVO userExamVO)
            throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             PageInfo<UserExamVO>
     * @Version: 1.0.0
     */
    PageInfo<UserExamVO> findUserExamPage(UserExamVO userExamVO,
            Integer pageNum, Integer pageSize) throws GlobalException;

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
    SimpleStatisticsResult findUserExamStat(
            StatisticsCondition statisticsCondition) throws GlobalException;
}
