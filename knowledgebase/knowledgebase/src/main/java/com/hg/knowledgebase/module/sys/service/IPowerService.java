package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Power;
import com.hg.knowledgebase.module.sys.entity.VO.PowerVO;

/**
 * 
 * @Description: 权限服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IPowerService
{

    /**
     * 
     * @Description: 添加权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addPower(Power dept) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removePowerById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchPowerById(List<String> ids) throws GlobalException;

    /**
     * 
     * @Description: 修改权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyPower(Power dept) throws GlobalException;

    /**
     * 
     * @Description: 修改基础权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBasePower(Power dept) throws GlobalException;

    /**
     * 
     * @Description: 根据id查询权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Power
     * @Version: 1.0.0
     */
    PowerVO findPowerById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Power
     * @Version: 1.0.0
     */
    Power findPowerByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 查询所有权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    List<Power> findAllPower(Power dept) throws GlobalException;

    /**
     * 
     * @Description: 查询所有当前权限coder
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    List<String> findAllCurrentUserPower() throws GlobalException;

    /**
     * 
     * @Description: 查询权限分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param dept
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    PageInfo<PowerVO> findPowerPage(Power dept, Integer pageNum,
            Integer pageSize) throws GlobalException;
}
