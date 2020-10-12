package com.hg.knowledgebase.module.community.service.impl;

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
import com.hg.knowledgebase.module.community.entity.Comment;
import com.hg.knowledgebase.module.community.entity.VO.CommentVO;
import com.hg.knowledgebase.module.community.mapper.ICommentMapper;
import com.hg.knowledgebase.module.community.service.ICommentService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 评论业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class CommentServiceImpl implements ICommentService
{

    @Autowired
    private ICommentMapper commentMapper;

    /**
     * 
     * @Description: 添加评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean addComment(Comment comment) throws GlobalException
    {
        if (comment == null)
        {
            throw new GlobalException("添加评论信息不能为空，入参【评论信息：{}】", comment);
        }
        // 执行条数
        int exec = 0;
        comment.setId(StringUtil.getUUID());
        comment.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = commentMapper.insertComment(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("添加评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 删除评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeComment(Comment comment) throws GlobalException
    {
        if (comment == null)
        {
            throw new GlobalException("删除评论信息不能为空，入参【主键：{}】", comment);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = commentMapper.deleteComment(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("删除评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 递归删除评论
     * @Author: 况克冬
     * @CreateDate: 2020年3月19日下午2:18:39
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月19日下午2:18:39
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Override
    public boolean removeRecursionDeleteCommentById(String id)
            throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("递归删除评论信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = commentMapper.recursionDeleteCommentById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("递归删除评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 删除批量评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param comments
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchComment(List<Comment> comments)
            throws GlobalException
    {
        if (comments == null || comments.size() == 0)
        {
            throw new GlobalException("删除批量评论信息不能为空，入参【集合：{}】", comments);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = commentMapper.deleteBatchComment(comments);
        } catch (SQLException e)
        {
            throw new GlobalException("删除批量评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyComment(Comment comment) throws GlobalException
    {
        if (comment == null || StringUtils.isEmpty(comment.getId()))
        {
            throw new GlobalException("修改评论信息主键不能为空，入参【评论信息：{}】", comment);
        }
        // 执行条数
        int exec = 0;
        comment.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        comment.setUpdateTime(new Date());
        try
        {
            exec = commentMapper.updateComment(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("修改评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改基础评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBaseComment(Comment comment) throws GlobalException
    {
        if (comment == null || StringUtils.isEmpty(comment.getId()))
        {
            throw new GlobalException("修改评论信息主键不能为空，入参【评论信息：{}】", comment);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = commentMapper.updateComment(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础评论SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Comment
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Comment findCommentById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询评论信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return commentMapper.selectCommentById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询评论SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Comment>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<CommentVO> findAllComment(Comment comment)
            throws GlobalException
    {
        try
        {
            return commentMapper.selectCommentList(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有评论SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询评论分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param comment
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Comment>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<CommentVO> findCommentPage(Comment comment, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<CommentVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询评论分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<CommentVO> commentList = commentMapper
                    .selectCommentList(comment);
            pageInfo = new PageInfo<>(commentList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询评论分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询当前评论分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param comment
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<BaseDocumentVO>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<BaseDocumentVO> findCurrentCommentDocPage(Comment comment,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<BaseDocumentVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询评论分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            comment = comment == null ? new Comment() : comment;
            comment.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
            PageHelper.startPage(pageNum, pageSize);
            List<BaseDocumentVO> commentList = commentMapper
                    .selectCurrentCommentDocList(comment);
            pageInfo = new PageInfo<>(commentList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前评论分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询评论总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findCommentCount(Comment comment) throws GlobalException
    {
        try
        {
            return commentMapper.selectCommentCount(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("查询评论总数SQL异常：{}", e.getMessage());
        }
    }
}
