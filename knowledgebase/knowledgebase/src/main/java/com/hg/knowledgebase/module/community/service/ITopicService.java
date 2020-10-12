package com.hg.knowledgebase.module.community.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.community.entity.Topic;
import com.hg.knowledgebase.module.community.entity.VO.TopicVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: 话题服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ITopicService
{

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
     * @Version: 1.0.0
     */
    boolean addTopic(Topic topic) throws GlobalException;

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
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeTopicById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchTopicById(List<String> ids) throws GlobalException;

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
    boolean modifyTopic(Topic topic) throws GlobalException;

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
    boolean modifyBaseTopic(Topic topic) throws GlobalException;

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
    Topic findTopicById(String id) throws GlobalException;

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
     *             Topic
     * @Version: 1.0.0
     */
    Topic findTopicByName(String name) throws GlobalException;

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
    List<Topic> findAllTopic(Topic topic) throws GlobalException;

    /**
     * 
     * @Description: 查询所有话题评论
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    PageInfo<TopicVO> findTopicCommentPage(Topic topic, Integer pageNum,
            Integer pageSize) throws GlobalException;

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
    PageInfo<TopicVO> findTopicPage(Topic topic, Integer pageNum,
            Integer pageSize) throws GlobalException;

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
    int findTopicCount(Topic topic) throws GlobalException;
}
