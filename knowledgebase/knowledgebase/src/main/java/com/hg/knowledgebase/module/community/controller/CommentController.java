package com.hg.knowledgebase.module.community.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.community.entity.Comment;
import com.hg.knowledgebase.module.community.entity.VO.CommentVO;
import com.hg.knowledgebase.module.community.service.ICommentService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 评论控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */

@RestController
@RequestMapping(value = "/api/community/comment")
public class CommentController
{

    private static Logger log = LoggerFactory
            .getLogger(CommentController.class);

    @Autowired
    private ICommentService commentService;

    /**
     * 
     * @Description: 保存评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveComment")
    @SystemLog(value = "保存评论")
    public ResponseResultBean saveComment(@RequestBody Comment comment)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = commentService.addComment(comment);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyComment")
    @SystemLog(value = "修改评论")
    public ResponseResultBean modifyComment(@RequestBody Comment comment)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = commentService.modifyComment(comment);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeComment")
    @SystemLog(value = "根据id移除评论")
    public ResponseResultBean removeCommentById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = commentService.removeRecursionDeleteCommentById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getComment")
    @SystemLog(value = "根据id查询评论")
    public ResponseResultBean getCommentById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            Comment comment = commentService.findCommentById(id);
            flg = comment != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    comment);
        } catch (Exception e)
        {
            log.error("---根据id查询评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllComment")
    @SystemLog(value = "获取所有评论")
    public ResponseResultBean getAllComment(@RequestBody Comment comment)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<CommentVO> comments = commentService.findAllComment(comment);
            flg = comments != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    comments);
        } catch (Exception e)
        {
            log.error("---获取所有评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCommentPage")
    @SystemLog(value = "获取分页评论")
    public ResponseResultBean getCommentPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Comment comment = JSONObject.parseObject(reqStr, Comment.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<CommentVO> pageInfo = commentService
                    .findCommentPage(comment, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页评论异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前评论文档分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentCommentDocPage")
    @SystemLog(value = "获取当前评论文档分页")
    public ResponseResultBean getCurrentCommentPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Comment comment = JSONObject.parseObject(reqStr, Comment.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<BaseDocumentVO> pageInfo = commentService
                    .findCurrentCommentDocPage(comment, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取当前评论文档分页异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取评论条数
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateComment: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param comment
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCommentCount")
    @SystemLog(value = "获取评论条数")
    public ResponseResultBean getCommentCount(@RequestBody Comment comment)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            int count = commentService.findCommentCount(comment);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    count);
        } catch (Exception e)
        {
            log.error("---获取评论条数异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}