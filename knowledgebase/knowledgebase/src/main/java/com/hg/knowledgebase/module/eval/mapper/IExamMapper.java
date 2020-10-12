package com.hg.knowledgebase.module.eval.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.eval.entity.Exam;
import com.hg.knowledgebase.module.eval.entity.UserExam;
import com.hg.knowledgebase.module.eval.entity.VO.ExamVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserExamVO;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 考试数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IExamMapper
{
    /**
     * 
     * @Description: 插入考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertExam(Exam exam) throws SQLException;

    /**
     * 
     * @Description: 插入批量考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param exams
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchExam(@Param("exams") List<Exam> exams) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteExamById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchExamById(@Param("ids") List<String> ids) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateExam(Exam exam) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Exam
     * @Version: 1.0.0
     */
    ExamVO selectExamById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Exam
     * @Version: 1.0.0
     */
    Exam selectExamByName(@Param("name") String name) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Exam>
     * @Version: 1.0.0
     */
    List<ExamVO> selectExamList(Exam exam) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param exam
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Exam>
     * @Version: 1.0.0
     */
    List<ExamVO> selectBaseExamList(Exam exam) throws SQLException;

    /**
     * 
     * @Description: 查询考试总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午5:00:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午5:00:09
     * @UpdateRemark: 修改内容
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectExamCount(Exam exam) throws SQLException;

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
    ExamVO selectExamCountMsg(@Param("id") String id) throws SQLException;

    ////////////////////////////////// 用户考试关系////////////////////////////////////////////
    /**
     * 
     * @Description: 插入用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:04:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:04:30
     * @UpdateRemark: 修改内容
     * @param examId
     * @param userIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertUserExam(@Param("examId") String examId,
            @Param("userIds") Set<String> userIds) throws SQLException;

    /**
     * 
     * @Description: 删除用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteUserExam(UserExam userExam) throws SQLException;

    /**
     * 
     * @Description: 根据考试id批量删除用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param examId
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchUserExamByExamId(@Param("examIds") List<String> examIds)
            throws SQLException;

    /**
     * 
     * @Description: 根据用户id批量删除用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param userIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchUserExamByUserId(@Param("userIds") List<String> userIds)
            throws SQLException;

    /**
     * 
     * @Description: 修改用户考试
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午12:51:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午12:51:34
     * @UpdateRemark: 修改内容
     * @param userExam
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int updateUserExam(UserExam userExam) throws SQLException;

    /**
     * 
     * @Description: 查询用户考试信息
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午1:40:41
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午1:40:41
     * @UpdateRemark: 修改内容
     * @param userId
     *            用户id
     * @param examId
     *            考试id
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             UserExamVO
     * @Version: 1.0.0
     */
    UserExamVO selectUserExam(@Param("userId") String userId,
            @Param("examId") String examId) throws SQLException;

    /**
     * 
     * @Description: 查询用户考试信息列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午6:18:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午6:18:19
     * @UpdateRemark: 修改内容
     * @param userExamVO
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<UserExamVO>
     * @Version: 1.0.0
     */
    List<UserExamVO> selectUserExamList(UserExamVO userExamVO)
            throws SQLException;

    /**
     * 
     * @Description: 查询用户考试信息统计列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午6:18:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午6:18:19
     * @UpdateRemark: 修改内容
     * @param StatisticsCondition
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<UserExam>
     * @Version: 1.0.0
     */
    List<StatisticsCondition> selectUserExamStatList(
            StatisticsCondition statisticsCondition) throws SQLException;
}
