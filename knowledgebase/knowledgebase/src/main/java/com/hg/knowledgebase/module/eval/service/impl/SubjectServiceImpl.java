package com.hg.knowledgebase.module.eval.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.customenum.StatTypeEnum;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.VO.SubjectVO;
import com.hg.knowledgebase.module.eval.mapper.ISubjectMapper;
import com.hg.knowledgebase.module.eval.service.ISubjectService;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;
import com.hg.knowledgebase.module.sys.entity.Dict;
import com.hg.knowledgebase.module.sys.service.IDictService;
import com.hg.knowledgebase.util.StatUtil;
import com.hg.knowledgebase.util.StringUtil;
import com.hg.knowledgebase.util.SubjectExcelHanderUtil;

/**
 * 
 * @Description: 题目业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class SubjectServiceImpl implements ISubjectService
{

    @Autowired
    private ISubjectMapper subjectMapper;

    @Autowired
    private IDictService dictService;

    /**
     * 题目字典难度组coder常量
     */
    private static final String SUBJECT_DIFFICULTY_GROUP_CODER = "subject_type";

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
    @Override
    public boolean addSubject(Subject subject) throws GlobalException
    {
        if (subject == null)
        {
            throw new GlobalException("添加题目信息不能为空，入参【题目信息：{}】", subject);
        }
        // 执行条数
        int exec = 0;
        subject.setId(StringUtil.getUUID());
        subject.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = subjectMapper.insertSubject(subject);
        } catch (SQLException e)
        {
            throw new GlobalException("添加题目SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean removeSubjectById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除题目信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = subjectMapper.deleteSubjectById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除题目SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    public boolean removeBatchSubjectById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量题目题目信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = subjectMapper.deleteBatchSubjectById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量题目SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean modifySubject(Subject subject) throws GlobalException
    {
        if (subject == null || StringUtils.isEmpty(subject.getId()))
        {
            throw new GlobalException("修改题目信息主键不能为空，入参【题目信息：{}】", subject);
        }
        // 执行条数
        int exec = 0;
        subject.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        subject.setUpdateTime(new Date());
        try
        {
            exec = subjectMapper.updateSubject(subject);
        } catch (SQLException e)
        {
            throw new GlobalException("修改题目SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Transactional(readOnly = true)
    @Override
    public SubjectVO findSubjectById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询题目信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return subjectMapper.selectSubjectById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询题目SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询题目
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
    @Transactional(readOnly = true)
    @Override
    public Subject findSubjectByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询题目信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return subjectMapper.selectSubjectByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询题目SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public List<Subject> findAllSubject(Subject subject) throws GlobalException
    {
        try
        {
            return subjectMapper.selectBaseSubjectList(subject);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有题目SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public PageInfo<SubjectVO> findSubjectPage(Subject subject, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<SubjectVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询题目分页信息条数不能为空，入参【页码：{}，条数：{}】---",
                    pageNum, pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<SubjectVO> subjectList = subjectMapper
                    .selectSubjectList(subject);
            pageInfo = new PageInfo<>(subjectList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询题目分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 导入题目
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param subjectFile
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean inputSubject(InputStream in) throws GlobalException
    {
        if (in == null)
        {
            throw new GlobalException("导入题目文件为空");
        }
        // 执行条数
        int exec = 0;
        try
        {
            Dict dict = new Dict();
            dict.setGroupCoder(SUBJECT_DIFFICULTY_GROUP_CODER);
            Map<String, String> subjectDifficultyMap = dictService
                    .findAllDictOfNameAndCoderMap(dict);
            List<Subject> subjectList = SubjectExcelHanderUtil.analysisExcel(in,
                    subjectDifficultyMap);
            if (subjectList == null || subjectList.size() == 0)
            {
                throw new GlobalException("导入题目信息解析为空");
            }
            exec = subjectMapper.insertBatchSubject(subjectList);
        } catch (Exception e)
        {
            throw new GlobalException("导入题目异常：{}", e.getMessage());
        } finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.getMessage();
            }
        }
        return exec > 0;
    }

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
     * @throws Exception
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findSubjectCount(Subject subject) throws GlobalException
    {
        try
        {
            return subjectMapper.selectSubjectCount(subject);
        } catch (SQLException e)
        {
            throw new GlobalException("查询题目总数量SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public void outputSubject(Subject subject) throws GlobalException
    {
        OutputStream out = null;// 输出流
        try
        {
            List<SubjectVO> subjectVOList = null;
            try
            {
                subjectVOList = subjectMapper.selectSubjectList(subject);
            } catch (SQLException se)
            {
                throw new GlobalException("查询题目列表SQL异常：{}", se.getMessage());
            }
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("题目表.xlsx",
                            GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            SubjectExcelHanderUtil.exportSimpleExcel(
                    subjectVOList == null ? new ArrayList<>() : subjectVOList,
                    out, "标题", 0);
        } catch (Exception e)
        {
            throw new GlobalException("导出题目异常：{}", e.getMessage());
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
     * @Description: 导出题目模板
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateSubject: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public void outputSubjectTemplete() throws GlobalException
    {
        OutputStream out = null;// 输出流
        try
        {
            List<SubjectVO> subjectVOList = new ArrayList<>();
            subjectVOList.add(new SubjectVO());
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("题目表模板.xlsx",
                            GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            SubjectExcelHanderUtil.exportSimpleExcel(subjectVOList, out, null,
                    0);
        } catch (Exception e)
        {
            throw new GlobalException("导出题目模板异常：{}", e.getMessage());
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
    @Transactional(readOnly = true)
    @Override
    public SimpleStatisticsResult findSubjectTypeStat(
            StatisticsCondition statisticsCondition) throws GlobalException
    {
        SimpleStatisticsResult stat = null;
        try
        {
            Map<String, Integer> dataMap = subjectMapper
                    .selectSubjectTypeGroup(statisticsCondition);
            // 如果查询存在数据，则组合统计数据
            if (dataMap != null && dataMap.size() > 0)
            {
                stat = new SimpleStatisticsResult();
                // x轴数据集合
                List<String> dataAxis = new ArrayList<String>(dataMap.size());
                // 数据值集合
                List<KeyValueBean> dataSeries = new ArrayList<KeyValueBean>(
                        dataMap.size());
                for (Map.Entry<String, Integer> entry : dataMap.entrySet())
                {
                    dataAxis.add(entry.getKey());
                    dataSeries.add(
                            new KeyValueBean(entry.getKey(), entry.getValue()));
                }
                stat.setDataAxis(dataAxis);
                stat.setDataSeries(dataSeries);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("查询题目类型统计SQL异常：{}", e.getMessage());
        }
        return stat;
    }

    /**
     * 
     * @Description: 查询题目统计
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
    @Transactional(readOnly = true)
    @Override
    public SimpleStatisticsResult findSubjectStat(
            StatisticsCondition statisticsCondition) throws GlobalException
    {
        boolean flg = statisticsCondition == null
                || StringUtils.isEmpty(statisticsCondition.getType())
                || StringUtils.isEmpty(statisticsCondition.getBeginTime())
                || StringUtils.isEmpty(statisticsCondition.getEndTime());
        if (flg)
        {
            throw new GlobalException("查询题目统计信息不能为空，入参【统计信息：{}】",
                    statisticsCondition);
        }
        String[] datas = statisticsCondition.getBeginTime()
                .split(StatUtil.SPLITTER);
        statisticsCondition.setCustomType(datas.length);
        List<StatisticsCondition> statList;
        try
        {
            statList = subjectMapper.selectSubjectStatList(statisticsCondition);
        } catch (SQLException e)
        {
            throw new GlobalException("查询题目统计SQL异常：{}", e.getMessage());
        }
        StatTypeEnum statTypeEnum = StatTypeEnum
                .getStatTypeEnum(statisticsCondition.getType());
        // 如果查询统计条数或统计类型为空，则抛出异常
        if (statList == null || statTypeEnum == null)
        {
            throw new GlobalException("查询题目统计信息结果或类型不能为空，入参【统计结果信息：{}，类型：{}】",
                    statList, statTypeEnum);
        }
        try
        {
            return StatUtil.combinationStat(statisticsCondition, statTypeEnum,
                    datas, statList);
        } catch (ParseException e)
        {
            throw new GlobalException("组合统计异常：{}", e.getMessage());
        }
    }
}
