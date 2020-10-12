package com.hg.knowledgebase.module.eval.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
* @Description: 试卷服务层
* @Author: 况克冬
* @CreateDate: 2020年2月26日下午4:21:35
* @Version: 1.0.0
 */
public interface ITestPaperService
{

    /**
     * 
     * @Description: 添加试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addTestPaper(TestPaper dept) throws GlobalException;
    
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
    * @throws GlobalException 业务异常时候出现的异常
    * boolean
    * @Version: 1.0.0
     */
    boolean removeTestPaperById(String id) throws GlobalException;
    
    /**
     * 
    * @Description: 根据id删除批量试卷
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午5:30:50
    * @UpdateTestPaper: 况克冬
    * @UpdateDate: 2020年2月26日下午5:30:50
    * @UpdateRemark: 修改内容
    * @param uuids
    * @return
    * @throws GlobalException 业务异常时候出现的异常
    * boolean
    * @Version: 1.0.0
     */
    boolean removeBatchTestPaperById(List<String> ids) throws GlobalException;
    
    /**
     * 
    * @Description: 修改试卷
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:26:15
    * @UpdateTestPaper: 况克冬
    * @UpdateDate: 2020年2月26日下午4:26:15
    * @UpdateRemark: 修改内容
    * @param dept
    * @return
    * @throws GlobalException 业务异常时候出现的异常
    * boolean
    * @Version: 1.0.0
     */
    boolean modifyTestPaper(TestPaper dept) throws GlobalException;
    
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
    * @throws GlobalException 业务异常时候出现的异常
    * TestPaper
    * @Version: 1.0.0
     */
    TestPaper findTestPaperById(String id) throws GlobalException;
    
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
    * @throws GlobalException 业务异常时候出现的异常
    * TestPaper
    * @Version: 1.0.0
     */
    TestPaper findTestPaperByName(String name) throws GlobalException;
    
    /**
     * 
    * @Description: 查询所有试卷
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:32:07
    * @UpdateTestPaper: 况克冬
    * @UpdateDate: 2020年2月26日下午4:32:07
    * @UpdateRemark: 修改内容
    * @param dept
    * @return
    * @throws GlobalException 业务异常时候出现的异常
    * List<TestPaper>
    * @Version: 1.0.0
     */
    List<TestPaper> findAllTestPaper(TestPaper dept) throws GlobalException;
    
    /**
     * 
    * @Description: 查询试卷分页
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:34:28
    * @UpdateTestPaper: 况克冬
    * @UpdateDate: 2020年2月26日下午4:34:28
    * @UpdateRemark: 修改内容
    * @param dept
    * @param pageNum
    * @param pageSize
    * @return
    * @throws GlobalException 业务异常时候出现的异常
    * List<TestPaper>
    * @Version: 1.0.0
     */
    PageInfo<TestPaper> findTestPaperPage(TestPaper dept, Integer pageNum,Integer pageSize) throws GlobalException;
    
    /**
     * 
    * @Description: 导出试卷
    * @Author: 况克冬
    * @CreateDate: 2020年3月11日下午4:15:38
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月11日下午4:15:38
    * @UpdateRemark: 修改内容
    * @param id
    * @throws GlobalException 业务异常时候出现的异常
    * void
    * @Version: 1.0.0
     */
    void outputTestPaper(String id) throws GlobalException;
    
    /**
     * 
    * @Description: 查询试卷总数量
    * @Author: 况克冬
    * @CreateDate: 2020年3月3日下午4:57:41
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月3日下午4:57:41
    * @UpdateRemark: 修改内容
    * @return
    * @throws GlobalException 业务异常时候出现的异常
    * int
    * @Version: 1.0.0
     */
    int findTestPaperCount(TestPaper testPaper) throws GlobalException;
}
