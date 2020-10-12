package com.hg.knowledgebase.module.community.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.community.entity.Comment;
import com.hg.knowledgebase.module.community.entity.VO.CommentVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 评论服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ICommentService
{

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
     * @Version: 1.0.0
     */
    boolean addComment(Comment comment) throws GlobalException;

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
    boolean removeComment(Comment comment) throws GlobalException;

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
    boolean removeRecursionDeleteCommentById(String id) throws GlobalException;

    /**
     * 
     * @Description: 删除批量评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchComment(List<Comment> comments) throws GlobalException;

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
    boolean modifyComment(Comment comment) throws GlobalException;

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
    boolean modifyBaseComment(Comment comment) throws GlobalException;

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
    Comment findCommentById(String id) throws GlobalException;

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
    List<CommentVO> findAllComment(Comment comment) throws GlobalException;

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
    PageInfo<CommentVO> findCommentPage(Comment comment, Integer pageNum,
            Integer pageSize) throws GlobalException;

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
     *             List<BaseDocumentVo>
     * @Version: 1.0.0
     */
    PageInfo<BaseDocumentVO> findCurrentCommentDocPage(Comment comment,
            Integer pageNum, Integer pageSize) throws GlobalException;

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
    int findCommentCount(Comment comment) throws GlobalException;
}
