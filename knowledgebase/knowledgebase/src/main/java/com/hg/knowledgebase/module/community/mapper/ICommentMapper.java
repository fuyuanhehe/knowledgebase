package com.hg.knowledgebase.module.community.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.community.entity.Comment;
import com.hg.knowledgebase.module.community.entity.VO.CommentVO;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 评论数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ICommentMapper
{
    /**
     * 
     * @Description: 插入评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertComment(Comment comment) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteComment(Comment comment) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int recursionDeleteCommentById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param comments
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchComment(@Param("comments") List<Comment> comments)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateComment(Comment comment) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Comment
     * @Version: 1.0.0
     */
    Comment selectCommentById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Comment>
     * @Version: 1.0.0
     */
    List<CommentVO> selectCommentList(Comment comment) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectCommentCount(Comment comment) throws SQLException;

    //////////////////////////////////// 用户评论///////////////////////////////////////////
    /**
     * 
     * @Description: 查询当前用户评论文档列表
     * @Author: 况克冬
     * @CreateDate: 2020年3月19日下午5:04:06
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月19日下午5:04:06
     * @UpdateRemark: 修改内容
     * @param comment
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<BaseDocumentVO>
     * @Version: 1.0.0
     */
    List<BaseDocumentVO> selectCurrentCommentDocList(Comment comment)
            throws SQLException;
}
