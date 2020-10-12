package com.hg.knowledgebase.module.eval.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.VO.SubjectVO;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 题目数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ISubjectMapper
{
    /**
     * 
     * @Description: 插入题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertSubject(Subject subject) throws SQLException;

    /**
     * 
     * @Description: 插入批量题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param subjects
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchSubject(@Param("subjects") List<Subject> subjects)
            throws SQLException;

    /**
     * 
     * @Description: 根据id删除题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteSubjectById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchSubjectById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateSubject(Subject subject) throws SQLException;

    /**
     * 
     * @Description: 根据id查询题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Subject
     * @Version: 1.0.0
     */
    SubjectVO selectSubjectById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据登录名查询题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Subject
     * @Version: 1.0.0
     */
    Subject selectSubjectByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 查询所有题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Subject>
     * @Version: 1.0.0
     */
    List<SubjectVO> selectSubjectList(Subject subject) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Subject>
     * @Version: 1.0.0
     */
    List<Subject> selectBaseSubjectList(Subject subject) throws SQLException;

    /**
     * 
     * @Description: 查询题目总数量
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
    int selectSubjectCount(Subject subject) throws SQLException;

    /**
     * 
     * @Description: 获取题目类型分组
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午2:38:59
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午2:38:59
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws Exception
     *             Map<String,Integer> key:性别名称 value:人数
     * @Version: 1.0.0
     */
    Map<String, Integer> selectSubjectTypeGroup(
            StatisticsCondition statisticsCondition) throws SQLException;

    /**
     * 
     * @Description: 获取题目统计列表
     * @Author: 况克冬
     * @CreateDate: 2020年3月18日上午10:01:00
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月18日上午10:01:00
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<StatisticsCondition>
     * @Version: 1.0.0
     */
    List<StatisticsCondition> selectSubjectStatList(
            StatisticsCondition statisticsCondition) throws SQLException;
}
