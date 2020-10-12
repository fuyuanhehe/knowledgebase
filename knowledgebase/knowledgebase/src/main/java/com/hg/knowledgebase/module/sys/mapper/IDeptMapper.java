package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.VO.DeptVO;

/**
 * 
 * @Description: 部门数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IDeptMapper
{
    /**
     * 
     * @Description: 插入部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertDept(Dept dept) throws SQLException;

    /**
     * 
     * @Description: 插入批量部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param depts
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchDept(@Param("depts") List<Dept> depts) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteDeptById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchDeptById(@Param("ids") List<String> ids) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateDept(Dept dept) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    DeptVO selectDeptById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据用户查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年3月17日上午9:39:31
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月17日上午9:39:31
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    Dept selectDeptByUser(@Param("userName") String userName)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    Dept selectDeptByName(@Param("name") String name) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Dept>
     * @Version: 1.0.0
     */
    List<DeptVO> selectDeptList(Dept dept) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Dept>
     * @Version: 1.0.0
     */
    List<Dept> selectBaseDeptList(Dept dept) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectDeptCount(Dept dept) throws SQLException;

    /**
     * 
     * @Description: 查询部门分组
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午4:43:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午4:43:24
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<DeptVO>
     * @Version: 1.0.0
     */
    List<DeptVO> selectDeptGroup(Dept dept) throws SQLException;
}
