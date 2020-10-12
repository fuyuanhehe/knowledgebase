package com.hg.knowledgebase.module.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Journalism;
import com.hg.knowledgebase.module.sys.entity.VO.JournalismVO;
import com.hg.knowledgebase.module.sys.mapper.IJournalismMapper;
import com.hg.knowledgebase.module.sys.service.IJournalismService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 新闻业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class JournalismServiceImpl implements IJournalismService
{

    @Autowired
    private IJournalismMapper journalismMapper;

    /**
     * 
     * @Description: 添加新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addJournalism(Journalism journalism) throws GlobalException
    {
        if (journalism == null)
        {
            throw new GlobalException("添加新闻信息不能为空，入参【新闻信息：{}】", journalism);
        }
        // 执行条数
        int exec = 0;
        journalism.setId(StringUtil.getUUID());
        journalism.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = journalismMapper.insertJournalism(journalism);
        } catch (SQLException e)
        {
            throw new GlobalException("添加新闻SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeJournalismById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除新闻信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = journalismMapper.deleteJournalismById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除新闻SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id批量删除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeBatchJournalismById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id批量删除新闻信息不能为空，入参【主键：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = journalismMapper.deleteBatchJournalismById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除新闻SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyJournalism(Journalism journalism)
            throws GlobalException
    {
        if (journalism == null || StringUtils.isEmpty(journalism.getId()))
        {
            throw new GlobalException("修改新闻信息主键不能为空，入参【新闻信息：{}】", journalism);
        }
        // 执行条数
        int exec = 0;
        journalism.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        journalism.setUpdateTime(new Date());
        try
        {
            exec = journalismMapper.updateJournalism(journalism);
        } catch (SQLException e)
        {
            throw new GlobalException("修改新闻SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Journalism findJournalismById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询新闻信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return journalismMapper.selectJournalismById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询新闻SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Journalism findJournalismByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询新闻信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return journalismMapper.selectJournalismByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询新闻SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询新闻分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param journalism
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Journalism>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<JournalismVO> findJournalismPage(Journalism journalism,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<JournalismVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询新闻分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<JournalismVO> journalismVOList = journalismMapper
                    .selectJournalismList(journalism);
            pageInfo = new PageInfo<>(journalismVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询新闻分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
