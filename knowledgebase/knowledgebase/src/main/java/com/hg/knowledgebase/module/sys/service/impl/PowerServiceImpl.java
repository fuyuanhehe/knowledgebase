package com.hg.knowledgebase.module.sys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Power;
import com.hg.knowledgebase.module.sys.entity.VO.PowerVO;
import com.hg.knowledgebase.module.sys.mapper.IPowerMapper;
import com.hg.knowledgebase.module.sys.service.IPowerService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 权限业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class PowerServiceImpl implements IPowerService
{

    @Autowired
    private IPowerMapper powerMapper;

    /**
     * 
     * @Description: 添加权限
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addPower(Power power) throws GlobalException
    {
        if (power == null)
        {
            throw new GlobalException("添加权限信息不能为空，入参【权限信息：{}】", power);
        }
        // 执行条数
        int exec = 0;
        power.setId(StringUtil.getUUID());
        power.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = powerMapper.insertPower(power);
        } catch (SQLException e)
        {
            throw new GlobalException("添加权限SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Override
    public boolean removePowerById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除权限信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = powerMapper.deletePowerById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除权限SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    public boolean removeBatchPowerById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量权限权限信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = powerMapper.deleteBatchPowerById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除权限SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyPower(Power power) throws GlobalException
    {
        if (power == null || StringUtils.isEmpty(power.getId()))
        {
            throw new GlobalException("修改权限信息主键不能为空，入参【权限信息：{}】", power);
        }
        // 执行条数
        int exec = 0;
        power.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        power.setUpdateTime(new Date());
        try
        {
            exec = powerMapper.updatePower(power);
        } catch (SQLException e)
        {
            throw new GlobalException("修改权限SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBasePower(Power power) throws GlobalException
    {
        if (power == null || StringUtils.isEmpty(power.getId()))
        {
            throw new GlobalException("修改权限信息主键不能为空，入参【权限信息：{}】", power);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = powerMapper.updatePower(power);
        } catch (SQLException e)
        {
            throw new GlobalException("修改权限SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
    @Transactional(readOnly = true)
    @Override
    public PowerVO findPowerById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询权限信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return powerMapper.selectPowerById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询权限SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询权限
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
    @Transactional(readOnly = true)
    @Override
    public Power findPowerByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询权限信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return powerMapper.selectPowerByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询权限SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Power> findAllPower(Power power) throws GlobalException
    {
        try
        {
            return powerMapper.selectBasePowerList(power);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有权限SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有当前用户权限coder
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param power
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findAllCurrentUserPower() throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        try
        {
            return powerMapper.selectUserPowerList(userName);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有当前用户权限coder SQL异常：{}",
                    e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询权限分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdatePower: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param power
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Power>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<PowerVO> findPowerPage(Power power, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<PowerVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询权限分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<PowerVO> powerVOList = powerMapper.selectPowerList(power);
            pageInfo = new PageInfo<>(powerVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询权限分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
