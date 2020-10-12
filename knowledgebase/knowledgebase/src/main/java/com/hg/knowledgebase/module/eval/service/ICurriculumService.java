package com.hg.knowledgebase.module.eval.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.entity.UserCurriculum;
import com.hg.knowledgebase.module.eval.entity.VO.CurriculumVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserCurriculumVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 课程服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ICurriculumService
{

    /**
     * 
     * @Description: 添加课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addCurriculum(CurriculumVO curriculumVO) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeCurriculumById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchCurriculumById(List<String> ids) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyCurriculum(CurriculumVO curriculumVO) throws GlobalException;

    /**
     * 
     * @Description: 批量修改学习课程状态
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午3:40:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午3:40:35
     * @UpdateRemark: 修改内容
     * @param ids
     * @param status
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBatchCurriculumStatus(List<String> ids, Integer status)
            throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    CurriculumVO findCurriculumById(String id) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    Curriculum findCurriculumByName(String name) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    List<Curriculum> findAllCurriculum(Curriculum curriculum)
            throws GlobalException;

    /**
     * 
     * @Description: 查询所有学习课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    List<String> findAllLearnCurriculum(Curriculum curriculum)
            throws GlobalException;

    /**
     * 
     * @Description: 查询课程分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    PageInfo<Curriculum> findCurriculumPage(Curriculum curriculum,
            Integer pageNum, Integer pageSize) throws GlobalException;

    /**
     * 
     * @Description: 查询课程总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午5:04:20
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午5:04:20
     * @UpdateRemark: 修改内容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int findCurriculumCount(Curriculum curriculum) throws GlobalException;

    /////////////////////////////// 用户课程关系//////////////////////////////////////////////////////
    /**
     * 
     * @Description: 查询用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日下午12:02:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日下午12:02:15
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             UserCurriculumVO
     * @Version: 1.0.0
     */
    UserCurriculumVO findUserCurriculum(UserCurriculum userCurriculum)
            throws GlobalException;

    /**
     * 
     * @Description: 查询当前用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日下午12:02:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日下午12:02:15
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             UserCurriculumVO
     * @Version: 1.0.0
     */
    UserCurriculumVO findCurrentUserCurriculum(UserCurriculum userCurriculum)
            throws GlobalException;

    /**
     * 
     * @Description: 修改用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日上午9:13:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日上午9:13:09
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyUserCurriculum(UserCurriculum userCurriculum)
            throws GlobalException;

    /**
     * 
     * @Description: 查询当前用户所有用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<UserCurriculumVO>
     * @Version: 1.0.0
     */
    List<UserCurriculumVO> findAllCurrentUserCurriculum(
            UserCurriculumVO userCurriculumVO) throws GlobalException;

    /**
     * 
     * @Description: 查询当前用户课程分页
     * @Author: 况克冬
     * @CreateDate: 2020年4月27日下午2:43:53
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月27日下午2:43:53
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             PageInfo<Curriculum>
     * @Version: 1.0.0
     */
    PageInfo<UserCurriculumVO> findCurrentUserCurriculumPage(
            UserCurriculumVO userCurriculumVO, Integer pageNum,
            Integer pageSize) throws GlobalException;

    /**
     * 
     * @Description: 获取用户已经学习的统计
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
    SimpleStatisticsResult findUserLearnedStat(
            StatisticsCondition statisticsCondition) throws GlobalException;
}
