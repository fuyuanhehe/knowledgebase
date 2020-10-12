package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.VO.DeptVO;

/**
 * 
 * @Description: 部门服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IDeptService
{

    /**
     * 
     * @Description: 添加部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addDept(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeDeptById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchDeptById(List<String> ids) throws GlobalException;

    /**
     * 
     * @Description: 修改部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyDept(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 修改基础部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBaseDept(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 根据id查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    DeptVO findDeptById(String id) throws GlobalException;

    /**
     * 
     * @Description: 获取当前用户部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    Dept findCurrentDept() throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    Dept findDeptByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 查询所有部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dept>
     * @Version: 1.0.0
     */
    List<DeptVO> findAllDept(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 查询所有部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dept>
     * @Version: 1.0.0
     */
    List<Dept> findAllBaseDept(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 查询部门分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param dept
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dept>
     * @Version: 1.0.0
     */
    PageInfo<DeptVO> findDeptPage(Dept dept, Integer pageNum, Integer pageSize)
            throws GlobalException;

    /**
     * 
     * @Description: 查询部门总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws SQLGlobalException
     *             int
     * @Version: 1.0.0
     */
    int findDeptCount(Dept dept) throws GlobalException;

    /**
     * 
     * @Description: 获取部门统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午2:38:59
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午2:38:59
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatistics
     * @Version: 1.0.0
     */
    SimpleStatisticsResult findDeptStat(Dept dept) throws GlobalException;
}
