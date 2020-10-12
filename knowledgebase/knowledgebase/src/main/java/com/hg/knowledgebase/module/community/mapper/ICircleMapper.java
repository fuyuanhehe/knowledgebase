package com.hg.knowledgebase.module.community.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.community.entity.Circle;

/**
 * 
 * @Description: 圈子数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ICircleMapper
{
    /**
     * 
     * @Description: 插入圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertCircle(Circle circle) throws SQLException;

    /**
     * 
     * @Description: 插入批量圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param circles
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchCircle(@Param("circles") List<Circle> circles)
            throws SQLException;

    /**
     * 
     * @Description: 根据id删除圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteCircleById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchCircleById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateCircle(Circle circle) throws SQLException;

    /**
     * 
     * @Description: 根据id查询圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    Circle selectCircleById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据登录名查询圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    Circle selectCircleByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 查询所有圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    List<Circle> selectCircleList(Circle circle) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    List<Circle> selectBaseCircleList(Circle circle) throws SQLException;

    /**
     * 
     * @Description: 查询圈子总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectCircleCount(Circle circle) throws SQLException;
}
