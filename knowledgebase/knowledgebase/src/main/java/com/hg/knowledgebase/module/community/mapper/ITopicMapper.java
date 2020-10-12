package com.hg.knowledgebase.module.community.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.community.entity.Topic;
import com.hg.knowledgebase.module.community.entity.VO.TopicVO;

/**
 * 
 * @Description: 话题数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ITopicMapper
{
    /**
     * 
     * @Description: 插入话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertTopic(Topic topic) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteTopicById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchTopicById(@Param("ids") List<String> ids)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateTopic(Topic topic) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Topic
     * @Version: 1.0.0
     */
    Topic selectTopicById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据登录名查询话题
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Topic
     * @Version: 1.0.0
     */
    Topic selectTopicByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 查询话题列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    List<TopicVO> selectTopicList(Topic topic) throws SQLException;

    /**
     * 
     * @Description: 查询话题评论列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateTopic: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param topic
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    List<TopicVO> selectTopicCommentList(Topic topic) throws SQLException;

    /**
     * 
     * @Description: 查询基础话题列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Topic>
     * @Version: 1.0.0
     */
    List<Topic> selectBaseTopicList(Topic topic) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectTopicCount(Topic topic) throws SQLException;
}
