package com.hg.knowledgebase.module.eval.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.eval.mapper.ITestPaperMapper;
import com.hg.knowledgebase.module.eval.service.ITestPaperService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.util.StringUtil;
import com.hg.knowledgebase.util.TestPaperWordHanderUtil;

/**
 * 
 * @Description: 试卷业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class TestPaperServiceImpl implements ITestPaperService
{

    @Autowired
    private ITestPaperMapper testPaperMapper;

    /**
     * 
     * @Description: 添加试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addTestPaper(TestPaper testPaper) throws GlobalException
    {
        if (testPaper == null)
        {
            throw new GlobalException("添加试卷信息不能为空，入参【试卷信息：{}】", testPaper);
        }
        // 执行条数
        int exec = 0;
        testPaper.setId(StringUtil.getUUID());
        testPaper.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        // 计算试卷总分数和总条数
        String subjectsJson = testPaper.getSubjects();
        // 如果试卷包含题目，则设置试卷题目总条数和总分数
        if (!StringUtils.isEmpty(subjectsJson))
        {
            List<Subject> subjectList = JSONObject.parseArray(subjectsJson,
                    Subject.class);
            testPaper.setTotalNum(subjectList.size());
            testPaper.setTotalScore(sumScore(subjectList));
        }
        try
        {
            exec = testPaperMapper.insertTestPaper(testPaper);
        } catch (SQLException e)
        {
            throw new GlobalException("添加试卷SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeTestPaperById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除试卷信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = testPaperMapper.deleteTestPaperById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除试卷SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchTestPaperById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量试卷试卷信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = testPaperMapper.deleteBatchTestPaperById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量试卷SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyTestPaper(TestPaper testPaper) throws GlobalException
    {
        if (testPaper == null || StringUtils.isEmpty(testPaper.getId()))
        {
            throw new GlobalException("修改试卷信息主键不能为空，入参【试卷信息：{}】", testPaper);
        }
        // 执行条数
        int exec = 0;
        testPaper.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        testPaper.setUpdateTime(new Date());
        // 计算试卷总分数和总条数
        String subjectsJson = testPaper.getSubjects();
        // 如果试卷包含题目，则设置试卷题目总条数和总分数
        if (!StringUtils.isEmpty(subjectsJson))
        {
            List<Subject> subjectList = JSONObject.parseArray(subjectsJson,
                    Subject.class);
            testPaper.setTotalNum(subjectList.size());
            testPaper.setTotalScore(sumScore(subjectList));
        }
        try
        {
            exec = testPaperMapper.updateTestPaper(testPaper);
        } catch (SQLException e)
        {
            throw new GlobalException("修改试卷SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     *             TestPaper
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public TestPaper findTestPaperById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询试卷信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return testPaperMapper.selectTestPaperById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询试卷SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询试卷
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             TestPaper
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public TestPaper findTestPaperByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询试卷信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return testPaperMapper.selectTestPaperByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询试卷SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<TestPaper>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<TestPaper> findAllTestPaper(TestPaper testPaper)
            throws GlobalException
    {
        try
        {
            return testPaperMapper.selectBaseTestPaperList(testPaper);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有试卷SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询试卷分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateTestPaper: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param testPaper
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<TestPaper>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<TestPaper> findTestPaperPage(TestPaper testPaper,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<TestPaper> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询试卷分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<TestPaper> testPaperList = testPaperMapper
                    .selectTestPaperList(testPaper);
            pageInfo = new PageInfo<>(testPaperList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询试卷分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询试卷总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午4:57:41
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午4:57:41
     * @UpdateRemark: 修改内容
     * @return
     * @throws Exception
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findTestPaperCount(TestPaper testPaper) throws GlobalException
    {
        try
        {
            return testPaperMapper.selectTestPaperCount(testPaper);
        } catch (SQLException e)
        {
            throw new GlobalException("查询试卷总数量SQL异常：{}", e.getMessage());
        }
    }

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
     *             void
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public void outputTestPaper(String id) throws GlobalException
    {
        // 输出流
        OutputStream out = null;
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("导出试卷参数不能为空，入参【试卷id：{}】", id);
        }
        TestPaper testPaper = null;
        try
        {
            testPaper = testPaperMapper.selectTestPaperById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("导出试卷SQL异常：{}", e.getMessage());
        }
        // 如果试卷信息为空，则抛出异常
        if (testPaper == null)
        {
            throw new GlobalException("导出试卷不存在，入参【试卷id：{}】", id);
        }
        try
        {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename="
                            + URLEncoder.encode(testPaper.getName() + ".docx",
                                    GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            TestPaperWordHanderUtil.exportTestPaperWord(testPaper, out);
        } catch (Exception e)
        {
            throw new GlobalException("导出试卷异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }

    /**
     * 
     * @Description: 求和分数
     * @Author: 况克冬
     * @CreateDate: 2020年4月13日下午5:04:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月13日下午5:04:12
     * @UpdateRemark: 修改内容
     * @param subjectList
     * @return double
     * @Version: 1.0.0
     */
    private double sumScore(List<Subject> subjectList)
    {
        double totalScore = 0.0;
        if (subjectList == null || subjectList.size() == 0)
        {
            return totalScore;
        }
        for (Subject subject : subjectList)
        {
            totalScore += subject.getScore() == null ? 0 : subject.getScore();
        }
        return totalScore;
    }
}
