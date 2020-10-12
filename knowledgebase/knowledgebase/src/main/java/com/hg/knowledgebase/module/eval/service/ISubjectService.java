package com.hg.knowledgebase.module.eval.service;

import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.VO.SubjectVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 题目服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ISubjectService
{

    /**
     * 
     * @Description: 添加题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addSubject(Subject subject) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeSubjectById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchSubjectById(List<String> ids) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifySubject(Subject subject) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Subject
     * @Version: 1.0.0
     */
    SubjectVO findSubjectById(String id) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Subject
     * @Version: 1.0.0
     */
    Subject findSubjectByName(String name) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Subject>
     * @Version: 1.0.0
     */
    List<Subject> findAllSubject(Subject subject) throws GlobalException;

    /**
     * 
     * @Description: 查询题目分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param subject
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Subject>
     * @Version: 1.0.0
     */
    PageInfo<SubjectVO> findSubjectPage(Subject subject, Integer pageNum,
            Integer pageSize) throws GlobalException;

    /**
     * 
     * @Description: 导入题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param in
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean inputSubject(InputStream in) throws GlobalException;

    /**
     * 
     * @Description: 查询题目总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午5:04:20
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年3月3日下午5:04:20
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int findSubjectCount(Subject subject) throws GlobalException;

    /**
     * 
     * @Description: 导出题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    void outputSubject(Subject subject) throws GlobalException;

    /**
     * 
     * @Description: 导出题目模板
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param subject
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    void outputSubjectTemplete() throws GlobalException;

    /**
     * 
     * @Description: 查询题目类型统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月13日下午5:53:55
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年3月13日下午5:53:55
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatisticsResult
     * @Version: 1.0.0
     */
    SimpleStatisticsResult findSubjectTypeStat(
            StatisticsCondition statisticsCondition) throws GlobalException;

    /**
     * 
     * @Description: 查询获取题目统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月13日下午5:53:55
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年3月13日下午5:53:55
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatisticsResult
     * @Version: 1.0.0
     */
    SimpleStatisticsResult findSubjectStat(
            StatisticsCondition statisticsCondition) throws GlobalException;
}
