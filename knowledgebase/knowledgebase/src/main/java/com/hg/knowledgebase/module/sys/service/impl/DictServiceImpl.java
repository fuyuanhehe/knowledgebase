package com.hg.knowledgebase.module.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Dict;
import com.hg.knowledgebase.module.sys.mapper.IDictMapper;
import com.hg.knowledgebase.module.sys.service.IDictService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 字典业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class DictServiceImpl implements IDictService
{

    @Autowired
    private IDictMapper dictMapper;

    /**
     * 
     * @Description: 添加字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean addDict(Dict dict) throws GlobalException
    {
        if (dict == null)
        {
            throw new GlobalException("添加字典信息不能为空，入参【字典信息：{}】", dict);
        }
        // 执行条数
        int exec = 0;
        dict.setId(StringUtil.getUUID());
        dict.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = dictMapper.insertDict(dict);
        } catch (GlobalException e)
        {
            throw new GlobalException("添加字典SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeDictById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除字典信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = dictMapper.deleteDictById(id);
        } catch (GlobalException e)
        {
            throw new GlobalException("根据id删除字典SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchDictById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量字典字典信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = dictMapper.deleteBatchDictById(ids);
        } catch (GlobalException e)
        {
            throw new GlobalException("根据id删除批量字典SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyDict(Dict dict) throws GlobalException
    {
        if (dict == null || StringUtils.isEmpty(dict.getId()))
        {
            throw new GlobalException("修改字典信息主键不能为空，入参【字典信息：{}】", dict);
        }
        // 执行条数
        int exec = 0;
        dict.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        dict.setUpdateTime(new Date());
        try
        {
            exec = dictMapper.updateDict(dict);
        } catch (GlobalException e)
        {
            throw new GlobalException("修改字典SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改基础字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBaseDict(Dict dict) throws GlobalException
    {
        if (dict == null || StringUtils.isEmpty(dict.getId()))
        {
            throw new GlobalException("修改字典信息主键不能为空，入参【字典信息：{}】", dict);
        }
        int exec;
        try
        {
            exec = dictMapper.updateDict(dict);
        } catch (GlobalException e)
        {
            throw new GlobalException("修改基础字典SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dict
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Dict findDictById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询字典信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return dictMapper.selectDictById(id);
        } catch (GlobalException e)
        {
            throw new GlobalException("根据id查询字典SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Dict
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Dict findDictByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询字典信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return dictMapper.selectDictByName(name);
        } catch (GlobalException e)
        {
            throw new GlobalException("根据名称查询字典SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dict>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Dict> findAllDict(Dict dict) throws GlobalException
    {
        try
        {
            return dictMapper.selectDictList(dict);
        } catch (GlobalException e)
        {
            throw new GlobalException("查询所有字典SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dict>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Dict> findAllBaseDict(Dict dict) throws GlobalException
    {
        try
        {
            return dictMapper.selectBaseDictList(dict);
        } catch (GlobalException e)
        {
            throw new GlobalException("查询所有字典SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 获取所有字典的map集合（key:coder;value:name）
     * @Author: 况克冬
     * @CreateDate: 2020年4月9日上午10:16:14
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月9日上午10:16:14
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws Exception
     *             Map<String,String>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, String> findAllDictOfCoderAndNameMap(Dict dict)
            throws GlobalException
    {
        Map<String, String> dictMap = new HashMap<String, String>(1);
        List<Dict> dictList = findAllBaseDict(dict);
        if (dictList != null && dictList.size() > 0)
        {
            for (Dict dic : dictList)
            {
                dictMap.put(dic.getCoder(), dic.getName());
            }
        }
        return dictMap;
    }

    /**
     * 
     * @Description: 获取所有字典的map集合（key:name;value:coder）
     * @Author: 况克冬
     * @CreateDate: 2020年4月9日上午10:16:14
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月9日上午10:16:14
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws Exception
     *             Map<String,String>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, String> findAllDictOfNameAndCoderMap(Dict dict)
            throws GlobalException
    {
        Map<String, String> dictMap = new HashMap<String, String>(1);
        List<Dict> dictList = findAllBaseDict(dict);
        if (dictList != null && dictList.size() > 0)
        {
            for (Dict dic : dictList)
            {
                dictMap.put(dic.getName(), dic.getCoder());
            }
        }
        return dictMap;
    }

    /**
     * 
     * @Description: 查询字典分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param dict
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Dict>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Dict> findDictPage(Dict dict, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<Dict> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询字典分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<Dict> dictList = dictMapper.selectDictList(dict);
            pageInfo = new PageInfo<>(dictList);
        } catch (GlobalException e)
        {
            throw new GlobalException("查询字典分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}
