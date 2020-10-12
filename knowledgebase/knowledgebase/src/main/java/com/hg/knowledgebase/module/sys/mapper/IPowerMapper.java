package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Power;
import com.hg.knowledgebase.module.sys.entity.VO.PowerVO;

/**
 * 
 * @Description: 权限数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IPowerMapper
{
    /**
     * 
     * @Description: 插入权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertPower(Power power) throws SQLException;

    /**
     * 
     * @Description: 插入批量权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param powers
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchPower(@Param("powers") List<Power> powers)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deletePowerById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchPowerById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updatePower(Power power) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Power
     * @Version: 1.0.0
     */
    PowerVO selectPowerById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Power
     * @Version: 1.0.0
     */
    Power selectPowerByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 查询所有权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    List<PowerVO> selectPowerList(Power power) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    List<Power> selectBasePowerList(Power power) throws SQLException;

    /**
     * 
     * @Description: 查询用户权限coder
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateMenu: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userName
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    List<String> selectUserPowerList(@Param("userName") String userName)
            throws SQLException;
}
