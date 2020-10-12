package com.hg.knowledgebase.module.eval.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.customenum.StatTypeEnum;
import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.entity.UserCurriculum;
import com.hg.knowledgebase.module.eval.entity.VO.CurriculumVO;
import com.hg.knowledgebase.module.eval.entity.VO.UserCurriculumVO;
import com.hg.knowledgebase.module.eval.mapper.ICurriculumMapper;
import com.hg.knowledgebase.module.eval.service.ICurriculumService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;
import com.hg.knowledgebase.module.sys.mapper.IUserMapper;
import com.hg.knowledgebase.util.StatUtil;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 课程业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class CurriculumServiceImpl implements ICurriculumService
{

    @Autowired
    private ICurriculumMapper curriculumMapper;

    @Autowired
    private IUserMapper userMapper;

    /**
     * 
     * @Description: 添加课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean addCurriculum(CurriculumVO curriculumVO)
            throws GlobalException
    {
        if (curriculumVO == null)
        {
            throw new GlobalException("添加课程信息不能为空，入参【课程信息：{}】", curriculumVO);
        }
        // 执行条数
        int exec = 0;
        String curriculumId = StringUtil.getUUID();
        curriculumVO.setId(curriculumId);
        curriculumVO.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = curriculumMapper.insertCurriculum(curriculumVO);
            // 添加用户课程
            Set<String> userIdSet = curriculumVO.getUserIds();
            // 如果课程表中有用户，则添加课程用户关系
            if (userIdSet != null && userIdSet.size() > 0)
            {
                curriculumMapper.insertUserCurriculum(curriculumId, userIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("添加课程SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeCurriculumById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除课程信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = curriculumMapper.deleteCurriculumById(id);
            // 删除用户课程
            UserCurriculum userCurriculum = new UserCurriculum();
            userCurriculum.setCurriculumId(id);
            curriculumMapper.deleteUserCurriculum(userCurriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除课程SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchCurriculumById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量课程课程信息不能为空，入参【主键集合：{}】", ids);
        }
        int exec;
        try
        {
            exec = curriculumMapper.deleteBatchCurriculumById(ids);
            // 删除用户课程
            curriculumMapper.deleteBatchCurriculumById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量课程SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyCurriculum(CurriculumVO curriculumVO)
            throws GlobalException
    {
        String curriculumId = curriculumVO.getId();
        if (curriculumVO == null || StringUtils.isEmpty(curriculumId))
        {
            throw new GlobalException("修改课程信息主键不能为空，入参【课程信息：{}】", curriculumVO);
        }
        // 执行条数
        int exec = 0;
        curriculumVO.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        curriculumVO.setUpdateTime(new Date());
        try
        {
            exec = curriculumMapper.updateCurriculum(curriculumVO);
            // 删除用户课程
            UserCurriculum userCurriculum = new UserCurriculum();
            userCurriculum.setCurriculumId(curriculumId);
            curriculumMapper.deleteUserCurriculum(userCurriculum);
            // 添加用户课程
            Set<String> userIdSet = curriculumVO.getUserIds();
            // 如果课程中存在用户，则添加课程用户关系
            if (userIdSet != null && userIdSet.size() > 0)
            {
                curriculumMapper.insertUserCurriculum(curriculumId, userIdSet);
            }
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量课程SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 批量修改学习课程状态
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午3:40:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午3:40:35
     * @UpdateRemark: 修改内容
     * @param ids
     * @param status
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBatchCurriculumStatus(List<String> ids, Integer status)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0 || status == null)
        {
            throw new GlobalException("批量修改学习课程状态信息不能为空，入参【课程ids信息：{},状态：{}】",
                    ids, status);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = curriculumMapper.updateBatchCurriculumStatus(ids, status);
        } catch (SQLException e)
        {
            throw new GlobalException("批量修改学习课程状态SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public CurriculumVO findCurriculumById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询课程信息主键不能为空，入参【主键：{}】", id);
        }

        try
        {
            return curriculumMapper.selectCurriculumById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Curriculum
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Curriculum findCurriculumByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询课程信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return curriculumMapper.selectCurriculumByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据名称查询课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Curriculum> findAllCurriculum(Curriculum curriculum)
            throws GlobalException
    {
        try
        {
            return curriculumMapper.selectBaseCurriculumList(curriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有学习课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @return
     * @throws Exception
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<String> findAllLearnCurriculum(Curriculum curriculum)
            throws GlobalException
    {
        if (curriculum == null || curriculum.getStatus() == null)
        {
            throw new GlobalException("查询所有学习课程信息名称不能为空，入参【课程状态信息：{}】",
                    curriculum);
        }
        try
        {
            return curriculumMapper.selectBaseLearnCurriculumList(curriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有学习课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询课程分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCurriculum: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param curriculum
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Curriculum>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Curriculum> findCurriculumPage(Curriculum curriculum,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<Curriculum> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询课程分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<Curriculum> curriculumList = curriculumMapper
                    .selectCurriculumList(curriculum);
            pageInfo = new PageInfo<>(curriculumList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询课程分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;
    }

    /**
     * 
     * @Description: 查询课程总数量
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午5:04:20
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午5:04:20
     * @UpdateRemark: 修改内容
     * @return
     * @throws Exception
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findCurriculumCount(Curriculum curriculum) throws GlobalException
    {
        try
        {
            return curriculumMapper.selectCurriculumCount(curriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("查询课程总数量SQL异常：{}", e.getMessage());
        }
    }

    /////////////////////////////// 用户课程关系//////////////////////////////////////////////////////
    /**
     * 
     * @Description: 查询用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日下午12:02:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日下午12:02:15
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             UserCurriculumVO
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserCurriculumVO findUserCurriculum(UserCurriculum userCurriculum)
            throws GlobalException
    {
        if (userCurriculum == null
                || StringUtils.isEmpty(userCurriculum.getCurriculumId())
                || StringUtils.isEmpty(userCurriculum.getUserId()))
        {
            throw new GlobalException("查询用户课程信息必要条数不能为空，入参【用户课程：{}】",
                    userCurriculum);
        }
        try
        {
            return curriculumMapper.selectUserCurriculum(userCurriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("查询用户课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户的用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日下午12:02:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日下午12:02:15
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws Exception
     *             UserCurriculumVO
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserCurriculumVO findCurrentUserCurriculum(
            UserCurriculum userCurriculum) throws GlobalException
    {
        if (userCurriculum == null
                || StringUtils.isEmpty(userCurriculum.getCurriculumId()))
        {
            throw new GlobalException("查询当前用户的用户课程信息必要条数不能为空，入参【用户课程：{}】",
                    userCurriculum);
        }
        String userName = SecurityUtils.getCurrentUserLogin().get();
        UserVO userVO;
        try
        {
            userVO = userMapper.selectUserByName(userName);
            // 如果用户不存在，则抛出异常
            if (userVO == null)
            {
                throw new GlobalException("查询当前用户不存在，入参【用户名：{}】", userName);
            }
            userCurriculum = userCurriculum == null ? new UserCurriculum()
                    : userCurriculum;
            userCurriculum.setUserId(userVO.getId());
            return curriculumMapper.selectUserCurriculum(userCurriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户的用户课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 修改用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年4月15日上午9:13:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月15日上午9:13:09
     * @UpdateRemark: 修改内容
     * @param userCurriculum
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyUserCurriculum(UserCurriculum userCurriculum)
            throws GlobalException
    {
        if (userCurriculum == null
                || StringUtils.isEmpty(userCurriculum.getCurriculumId())
                || StringUtils.isEmpty(userCurriculum.getUserId()))
        {
            throw new GlobalException("修改用户课程信息必要条数不能为空，入参【用户课程：{}】",
                    userCurriculum);
        }
        // 执行条数
        int exec = 0;
        // 课程资源条数
        int resourcesNum = 0;
        try
        {
            // 查询课程课件信息
            CurriculumVO curriculumVO = curriculumMapper
                    .selectCurriculumById(userCurriculum.getCurriculumId());
            // 如果课程中存在课件，则解析课件
            if (curriculumVO != null
                    && !StringUtils.isEmpty(curriculumVO.getResources()))
            {
                JSONArray jsonArr = JSONArray
                        .parseArray(curriculumVO.getResources());
                resourcesNum = jsonArr.size();
            }
            String learnRecord = userCurriculum.getLearnRecord();
            // 如果有学习记录，则解析学习记录条数
            if (!StringUtils.isEmpty(learnRecord))
            {
                // 获取学习进度课程下标集合
                List<Integer> learnRecordList = JSONArray
                        .parseArray(learnRecord, Integer.class);
                if (resourcesNum > 0)
                {
                    // 转换保留两位小数
                    String progressStr = StringUtil.df.format(
                            learnRecordList.size() / resourcesNum * 100);
                    Double progress = Double.valueOf(progressStr);
                    userCurriculum.setProgress(progress);
                }
                // 设置已经学习完成
                if (learnRecordList.size() >= resourcesNum)
                {
                    userCurriculum.setCompleteStatus(1);
                }
            }
            userCurriculum.setLearnTime(new Date());
            exec = curriculumMapper.updateUserCurriculum(userCurriculum);
        } catch (SQLException e)
        {
            throw new GlobalException("修改用户课程SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 查询当前用户所有用户课程
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateExam: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @return
     * @throws Exception
     *             List<Exam>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserCurriculumVO> findAllCurrentUserCurriculum(
            UserCurriculumVO userCurriculumVO) throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        userCurriculumVO = userCurriculumVO == null ? new UserCurriculumVO()
                : userCurriculumVO;
        userCurriculumVO.setUserName(userName);
        try
        {
            return curriculumMapper.selectUserCurriculumList(userCurriculumVO);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户所有用户课程SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户课程分页
     * @Author: 况克冬
     * @CreateDate: 2020年4月27日下午2:43:53
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月27日下午2:43:53
     * @UpdateRemark: 修改内容
     * @param userCurriculumVO
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     *             PageInfo<Curriculum>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserCurriculumVO> findCurrentUserCurriculumPage(
            UserCurriculumVO userCurriculumVO, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<UserCurriculumVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询课程分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            String userName = SecurityUtils.getCurrentUserLogin().get();
            userCurriculumVO = userCurriculumVO == null ? new UserCurriculumVO()
                    : userCurriculumVO;
            userCurriculumVO.setUserName(userName);
            PageHelper.startPage(pageNum, pageSize);
            List<UserCurriculumVO> curriculumList = curriculumMapper
                    .selectUserCurriculumList(userCurriculumVO);
            pageInfo = new PageInfo<>(curriculumList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户课程分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 获取用户已经学习的统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月13日下午5:53:55
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月13日下午5:53:55
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     * @return
     * @throws Exception
     *             SimpleStatisticsResult
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public SimpleStatisticsResult findUserLearnedStat(
            StatisticsCondition statisticsCondition) throws GlobalException
    {
        boolean flg = statisticsCondition == null
                || StringUtils.isEmpty(statisticsCondition.getType())
                || StringUtils.isEmpty(statisticsCondition.getBeginTime())
                || StringUtils.isEmpty(statisticsCondition.getEndTime());
        if (flg)
        {
            throw new GlobalException("查询题目统计信息不能为空，入参【统计信息：{}】",
                    statisticsCondition);
        }
        String[] datas = statisticsCondition.getBeginTime()
                .split(StatUtil.SPLITTER);
        statisticsCondition.setCustomType(datas.length);
        try
        {
            List<StatisticsCondition> statList = curriculumMapper
                    .selectUserLearnedStatList(statisticsCondition);
            StatTypeEnum statTypeEnum = StatTypeEnum
                    .getStatTypeEnum(statisticsCondition.getType());
            if (statList == null || statTypeEnum == null)
            {
                throw new GlobalException(
                        "查询题目统计信息结果或类型不能为空，入参【统计结果信息：{}，类型：{}】", statList,
                        statTypeEnum);
            }
            try
            {
                return StatUtil.combinationStat(statisticsCondition,
                        statTypeEnum, datas, statList);
            } catch (ParseException e)
            {
                throw new GlobalException("组合统计异常：{}", e.getMessage());
            }
        } catch (SQLException e)
        {
            throw new GlobalException("获取用户已经学习的统计SQL异常：{}", e.getMessage());
        }
    }
}
