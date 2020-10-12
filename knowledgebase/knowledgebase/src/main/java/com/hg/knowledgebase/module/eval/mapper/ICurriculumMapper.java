package com.hg.knowledgebase.module.eval.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.entity.UserCurriculum;
import com.hg.knowledgebase.module.eval.entity.VO.CurriculumVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserCurriculumVO;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 课程数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ICurriculumMapper
{
    /**
     * 
     * @Description: 插入课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertCurriculum(Curriculum curriculum) throws SQLException;

    /**
     * 
     * @Description: 插入批量课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param curriculums
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchCurriculum(
            @Param("curriculums") List<Curriculum> curriculums)
            throws SQLException;

    /**
     * 
     * @Description: 根据id删除课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteCurriculumById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchCurriculumById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateCurriculum(Curriculum curriculum) throws SQLException;

    /**
     * 
     * @Description: 批量更新课程状态
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午3:34:01
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午3:34:01
     * @UpdateRemark: 修改内容
     * @param ids
     * @param status
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int updateBatchCurriculumStatus(@Param("ids") List<String> ids,
            @Param("status") int status) throws SQLException;

    /**
     * 
     * @Description: 根据id查询课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    CurriculumVO selectCurriculumById(@Param("id") String id)
            throws SQLException;

    /**
     * 
     * @Description: 根据登录名查询课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    Curriculum selectCurriculumByName(@Param("name") String name)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    List<Curriculum> selectCurriculumList(Curriculum curriculum)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有基础课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    List<Curriculum> selectBaseCurriculumList(Curriculum curriculum)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有基础学习课程
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午3:29:49
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午3:29:49
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<String>
     * @Version: 1.0.0
     */
    List<String> selectBaseLearnCurriculumList(Curriculum curriculum)
            throws SQLException;

    /**
     * 
     * @Description: 查询课程总数量
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
    int selectCurriculumCount(Curriculum curriculum) throws SQLException;

    ////////////////////////////////// 用户课程关系////////////////////////////////////////////
    /**
     * 
     * @Description: 插入用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:04:30
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:04:30
     * @UpdateRemark: 修改内容
     * @param curriculumId
     * @param userIds
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertUserCurriculum(@Param("curriculumId") String curriculumId,
            @Param("userIds") Set<String> userIds) throws SQLException;

    /**
     * 
     * @Description: 删除用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午3:05:38
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午3:05:38
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteUserCurriculum(UserCurriculum userCurriculum) throws SQLException;

    /**
     * 
     * @Description: 根据课程id批量删除用户课程
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
    int deleteBatchUserCurriculumByCurriculumId(
            @Param("curriculumIds") Set<String> curriculumIds)
            throws SQLException;

    /**
     * 
     * @Description: 更新用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日上午10:32:49
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日上午10:32:49
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int updateUserCurriculum(UserCurriculum userCurriculum) throws SQLException;

    /**
     * 
     * @Description: 根据用户id批量删除用户课程
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
    int deleteBatchUserCurriculumByUserId(
            @Param("userIds") List<String> userIds) throws SQLException;

    /**
     * 
     * @Description: 查询用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日下午12:06:01
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日下午12:06:01
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             UserCurriculumVO
     * @Version: 1.0.0
     */
    UserCurriculumVO selectUserCurriculum(UserCurriculum userCurriculum)
            throws SQLException;

    /**
     * 
     * @Description: 查询用户课程信息列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午6:18:19
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午6:18:19
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<UserCurriculumVO>
     * @Version: 1.0.0
     */
    List<UserCurriculumVO> selectUserCurriculumList(
            UserCurriculumVO userCurriculumVO) throws SQLException;

    /**
     * 
     * @Description: 查询用户已经学习的统计列表
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
    List<StatisticsCondition> selectUserLearnedStatList(
            StatisticsCondition statisticsCondition) throws SQLException;
}
