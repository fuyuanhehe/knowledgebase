package com.hg.knowledgebase.module.community.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.community.entity.Circle;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: 圈子服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ICircleService
{

    /**
     * 
     * @Description: 添加圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addCircle(Circle circle) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeCircleById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchCircleById(List<String> ids) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyCircle(Circle circle) throws GlobalException;

    /**
     * 
     * @Description: 修改基础圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBaseCircle(Circle circle) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    Circle findCircleById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据名称查询圈子
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
    Circle findCircleByName(String name) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    List<Circle> findAllCircle(Circle circle) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    List<Circle> findAllBaseCircle(Circle circle) throws GlobalException;

    /**
     * 
     * @Description: 查询圈子分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param circle
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    PageInfo<Circle> findCirclePage(Circle circle, Integer pageNum,
            Integer pageSize) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int findCircleCount(Circle circle) throws GlobalException;
}
