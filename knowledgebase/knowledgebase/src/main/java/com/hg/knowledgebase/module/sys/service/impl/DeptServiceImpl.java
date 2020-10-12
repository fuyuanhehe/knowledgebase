package com.hg.knowledgebase.module.sys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.VO.DeptVO;
import com.hg.knowledgebase.module.sys.mapper.IDeptMapper;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 部门业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class DeptServiceImpl implements IDeptService
{

    @Autowired
    private IDeptMapper deptMapper;

    /**
     * 缓存-查询部门统计常量
     */
    private static final String DEPT_STAT_CACHE = "findDeptStat";

    /**
     * 缓存-查询部门总数常量
     */
    private static final String DEPT_COUNT_CACHE = "findDeptCount";
    
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
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = {DEPT_STAT_CACHE, DEPT_COUNT_CACHE}, allEntries = true)
    @Override
    public boolean addDept(Dept dept) throws GlobalException
    {
        if (dept == null)
        {
            throw new GlobalException("添加部门信息不能为空，入参【部门信息：{}】", dept);
        }
        // 执行条数
        int exec = 0;
        dept.setId(StringUtil.getUUID());
        dept.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = deptMapper.insertDept(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("添加部门添加部门SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = {DEPT_STAT_CACHE, DEPT_COUNT_CACHE}, allEntries = true)
    @Override
    public boolean removeDeptById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除部门信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = deptMapper.deleteDeptById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除部门SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = {DEPT_STAT_CACHE, DEPT_COUNT_CACHE}, allEntries = true)
    @Override
    public boolean removeBatchDeptById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量部门部门信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = deptMapper.deleteBatchDeptById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量部门SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @CacheEvict(cacheNames = {DEPT_STAT_CACHE, DEPT_COUNT_CACHE}, allEntries = true)
    @Override
    public boolean modifyDept(Dept dept) throws GlobalException
    {
        if (dept == null || StringUtils.isEmpty(dept.getId()))
        {
            throw new GlobalException("修改部门信息主键不能为空，入参【部门信息：{}】", dept);
        }
        // 执行条数
        int exec = 0;
        dept.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        dept.setUpdateTime(new Date());
        try
        {
            exec = deptMapper.updateDept(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("修改部门SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @CacheEvict(cacheNames = {DEPT_STAT_CACHE, DEPT_COUNT_CACHE}, allEntries = true)
    @Override
    public boolean modifyBaseDept(Dept dept) throws GlobalException
    {
        if (dept == null || StringUtils.isEmpty(dept.getId()))
        {
            throw new GlobalException("修改部门信息主键不能为空，入参【部门信息：{}】", dept);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = deptMapper.updateDept(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础部门SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public DeptVO findDeptById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询部门信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return deptMapper.selectDeptById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询部门SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 获取当前用户部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Dept findCurrentDept() throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        try
        {
            return deptMapper.selectDeptByUser(userName);
        } catch (SQLException e)
        {
            throw new GlobalException("获取当前用户部门SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询部门
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDept: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param id
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dept
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据登录名查询部门信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return deptMapper.selectDeptByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询部门SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public List<DeptVO> findAllDept(Dept dept) throws GlobalException
    {
        try
        {
            return deptMapper.selectDeptList(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有部门SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public List<Dept> findAllBaseDept(Dept dept) throws GlobalException
    {
        try
        {
            return deptMapper.selectBaseDeptList(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有部门SQL异常：{}", e.getMessage());
        }
    }

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
    @Transactional(readOnly = true)
    @Override
    public PageInfo<DeptVO> findDeptPage(Dept dept, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<DeptVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询部门分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<DeptVO> deptVOList = deptMapper.selectDeptList(dept);
            pageInfo = new PageInfo<>(deptVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询部门分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Cacheable(cacheNames = {DEPT_COUNT_CACHE}, key = "#dept.pid + '-' + #dept.beginCreateTime + '-' "
            + "+ #dept.endCreateTime", condition = "#dept != null")
    @Transactional(readOnly = true)
    @Override
    public int findDeptCount(Dept dept) throws GlobalException
    {
        try
        {
            return deptMapper.selectDeptCount(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("查询部门总数SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询部门统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param dept
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatistics
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    @Cacheable(cacheNames = {DEPT_STAT_CACHE}, key = "#dept.pid + '-' + #dept.beginCreateTime + '-' "
            + "+ #dept.endCreateTime", condition = "#dept != null")
    public SimpleStatisticsResult findDeptStat(Dept dept) throws GlobalException
    {
        List<DeptVO> deptVOList = null;
        // 统计结果对象
        SimpleStatisticsResult stat = null;
        try
        {
            deptVOList = deptMapper.selectDeptGroup(dept);
        } catch (SQLException e)
        {
            throw new GlobalException("查询部门分组SQL异常：{}", e.getMessage());
        }
        //如果查询的部门有数据，则执行组合统计结果对象
        if (deptVOList != null && deptVOList.size() > 0)
        {
            stat = new SimpleStatisticsResult();
            // x轴数据集合
            List<String> dataAxis = new ArrayList<String>(deptVOList.size());
            // 数据值集合
            List<KeyValueBean> dataSeries = new ArrayList<KeyValueBean>(
                    deptVOList.size());
            for (DeptVO deptVO : deptVOList)
            {
                dataAxis.add(deptVO.getName());
                dataSeries.add(new KeyValueBean(deptVO.getName(),
                        deptVO.getCount() == null ? 0 : deptVO.getCount()));
            }
            stat.setDataAxis(dataAxis);
            stat.setDataSeries(dataSeries);
        }
        return stat;
    }
}
