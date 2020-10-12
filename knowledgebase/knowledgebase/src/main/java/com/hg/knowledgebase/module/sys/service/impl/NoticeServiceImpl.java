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
import com.hg.knowledgebase.module.sys.entity.Notice;
import com.hg.knowledgebase.module.sys.entity.VO.NoticeVO;
import com.hg.knowledgebase.module.sys.mapper.INoticeMapper;
import com.hg.knowledgebase.module.sys.service.INoticeService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 通知公告业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class NoticeServiceImpl implements INoticeService
{

    @Autowired
    private INoticeMapper noticeMapper;

    /**
     * 
     * @Description: 添加通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addNotice(Notice notice) throws GlobalException
    {
        if (notice == null)
        {
            throw new GlobalException("添加通知公告信息不能为空，入参【通知公告信息：{}】", notice);
        }
        // 执行条数
        int exec = 0;
        notice.setId(StringUtil.getUUID());
        notice.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = noticeMapper.insertNotice(notice);
        } catch (SQLException e)
        {
            throw new GlobalException("添加通知公告SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeNoticeById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除通知公告信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = noticeMapper.deleteNoticeById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除通知公告SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id批量删除通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeBatchNoticeById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id批量删除通知公告信息不能为空，入参【主键：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = noticeMapper.deleteBatchNoticeById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id批量删除通知公告SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyNotice(Notice notice) throws GlobalException
    {
        if (notice == null || StringUtils.isEmpty(notice.getId()))
        {
            throw new GlobalException("修改通知公告信息主键不能为空，入参【通知公告信息：{}】", notice);
        }
        // 执行条数
        int exec = 0;
        notice.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        notice.setUpdateTime(new Date());
        try
        {
            exec = noticeMapper.updateNotice(notice);
        } catch (SQLException e)
        {
            throw new GlobalException("修改通知公告SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询通知公告信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return noticeMapper.selectNoticeById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询通知公告SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询通知公告信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return noticeMapper.selectNoticeByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询通知公告SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询通知公告分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param notice
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Notice>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<NoticeVO> findNoticePage(Notice notice, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<NoticeVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询通知公告分页信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<NoticeVO> noticeVOList = noticeMapper.selectNoticeList(notice);
            pageInfo = new PageInfo<>(noticeVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询通知公告分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
