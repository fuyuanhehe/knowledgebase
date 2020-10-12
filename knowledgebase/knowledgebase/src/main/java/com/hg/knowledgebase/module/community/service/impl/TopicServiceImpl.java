package com.hg.knowledgebase.module.community.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.hg.knowledgebase.module.community.entity.Topic;
import com.hg.knowledgebase.module.community.entity.VO.TopicVO;
import com.hg.knowledgebase.module.community.mapper.ITopicMapper;
import com.hg.knowledgebase.module.community.service.ICommentService;
import com.hg.knowledgebase.module.community.service.ITopicService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 话题业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class TopicServiceImpl implements ITopicService
{

    @Autowired
    private ITopicMapper topicMapper;

    @Autowired
    private ICommentService commentService;

    /**
     * 
     * @Description: 添加话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean addTopic(Topic topic) throws GlobalException
    {
        if (topic == null)
        {
            throw new GlobalException("添加话题信息不能为空，入参【话题信息：{}】", topic);
        }
        // 执行条数
        int exec = 0;
        topic.setId(StringUtil.getUUID());
        topic.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = topicMapper.insertTopic(topic);
        } catch (SQLException e)
        {
            throw new GlobalException("添加话题SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean removeTopicById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除话题信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = topicMapper.deleteTopicById(id);
            // 删除对应评论
            Comment comment = new Comment();
            comment.setTopicId(id);
            commentService.removeComment(comment);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除话题SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    public boolean removeBatchTopicById(List<String> ids) throws GlobalException
    {
        Comment comment = null;
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量话题话题信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = topicMapper.deleteBatchTopicById(ids);
            // 批量删除评论
            List<Comment> comments = new ArrayList<>();
            for (String id : ids)
            {
                comment = new Comment();
                comment.setTopicId(id);
                comments.add(comment);
            }
            commentService.removeBatchComment(comments);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除话题SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyTopic(Topic topic) throws GlobalException
    {
        if (topic == null || StringUtils.isEmpty(topic.getId()))
        {
            throw new GlobalException("修改话题信息主键不能为空，入参【话题信息：{}】", topic);
        }
        // 执行条数
        int exec = 0;
        topic.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        topic.setUpdateTime(new Date());
        try
        {
            exec = topicMapper.updateTopic(topic);
        } catch (SQLException e)
        {
            throw new GlobalException("修改话题SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改基础话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBaseTopic(Topic topic) throws GlobalException
    {
        if (topic == null || StringUtils.isEmpty(topic.getId()))
        {
            throw new GlobalException("修改话题信息主键不能为空，入参【话题信息：{}】", topic);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = topicMapper.updateTopic(topic);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础话题SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Topic
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Topic findTopicById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询话题信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return topicMapper.selectTopicById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询话题SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Topic findTopicByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据话题名查询话题信息不能为空，入参【话题名称：{}】", name);
        }
        try
        {
            return topicMapper.selectTopicByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询话题SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Topic> findAllTopic(Topic topic) throws GlobalException
    {
        try
        {
            return topicMapper.selectBaseTopicList(topic);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有话题SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询话题评论分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<TopicVO> findTopicCommentPage(Topic topic, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<TopicVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询话题评论分页信息条数不能为空，入参【页码：{}，条数：{}】",
                    pageNum, pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<TopicVO> topicList = topicMapper.selectTopicCommentList(topic);
            pageInfo = new PageInfo<>(topicList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询话题评论分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询话题分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param topic
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<TopicVO> findTopicPage(Topic topic, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<TopicVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询话题分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<TopicVO> topicList = topicMapper.selectTopicList(topic);
            pageInfo = new PageInfo<>(topicList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询话题分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询话题总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findTopicCount(Topic topic) throws GlobalException
    {
        try
        {
            return topicMapper.selectTopicCount(topic);
        } catch (SQLException e)
        {
            throw new GlobalException("查询话题总数SQL异常：{}", e.getMessage());
        }
    }
}
