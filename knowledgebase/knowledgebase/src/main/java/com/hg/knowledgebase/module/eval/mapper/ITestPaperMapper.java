package com.hg.knowledgebase.module.eval.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.eval.entity.TestPaper;

/**
 * 
 * @Description: 试卷数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ITestPaperMapper
{
    /**
     * 
     * @Description: 插入试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertTestPaper(TestPaper testPaper) throws SQLException;

    /**
     * 
     * @Description: 插入批量试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param testPapers
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchTestPaper(@Param("testPapers") List<TestPaper> testPapers)
            throws SQLException;

    /**
     * 
     * @Description: 根据id删除试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteTestPaperById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchTestPaperById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateTestPaper(TestPaper testPaper) throws SQLException;

    /**
     * 
     * @Description: 根据id查询试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             TestPaper
     * @Version: 1.0.0
     */
    TestPaper selectTestPaperById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据登录名查询试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             TestPaper
     * @Version: 1.0.0
     */
    TestPaper selectTestPaperByName(@Param("name") String name)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<TestPaper>
     * @Version: 1.0.0
     */
    List<TestPaper> selectTestPaperList(TestPaper testPaper)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有基础试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<TestPaper>
     * @Version: 1.0.0
     */
    List<TestPaper> selectBaseTestPaperList(TestPaper testPaper)
            throws SQLException;

    /**
     * 
     * @Description: 查询试卷总数量
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
    int selectTestPaperCount(TestPaper testPaper) throws SQLException;
}
