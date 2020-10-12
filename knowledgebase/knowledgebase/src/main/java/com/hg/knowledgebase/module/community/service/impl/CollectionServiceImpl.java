package com.hg.knowledgebase.module.community.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.community.entity.Collections;
import com.hg.knowledgebase.module.community.entity.VO.CollectionVO;
import com.hg.knowledgebase.module.community.mapper.ICollectionMapper;
import com.hg.knowledgebase.module.community.service.ICollectionService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 收藏业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class CollectionServiceImpl implements ICollectionService
{

    @Autowired
    private ICollectionMapper collectionMapper;

    /**
     * 
     * @Description: 添加收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean addCollection(Collections collection) throws GlobalException
    {
        if (collection == null)
        {
            throw new GlobalException("添加收藏信息不能为空，入参【收藏信息：{}】", collection);
        }
        // 执行条数
        int exec = 0;
        collection.setId(StringUtil.getUUID());
        collection.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            exec = collectionMapper.insertCollection(collection);
        } catch (SQLException e)
        {
            throw new GlobalException("添加收藏SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 删除收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean removeCollection(Collections collection)
            throws GlobalException
    {
        if (collection == null)
        {
            throw new GlobalException("删除收藏信息不能为空，入参【主键：{}】", collection);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = collectionMapper.deleteCollection(collection);
        } catch (SQLException e)
        {
            throw new GlobalException("删除收藏SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 删除批量收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param collections
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    public boolean removeBatchCollection(List<Collections> collections)
            throws GlobalException
    {
        if (collections == null || collections.size() == 0)
        {
            throw new GlobalException("删除批量收藏信息不能为空，入参【集合：{}】", collections);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = collectionMapper.deleteBatchCollection(collections);
        } catch (SQLException e)
        {
            throw new GlobalException("删除批量收藏SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Collection
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Collections findCollectionById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询收藏信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return collectionMapper.selectCollectionById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询收藏SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Collection>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<CollectionVO> findAllCollection(Collections collection)
            throws GlobalException
    {
        try
        {
            return collectionMapper.selectCollectionList(collection);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有收藏SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户所有收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Collection>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Collections> findCurrentAllCollection(Collections collection)
            throws GlobalException
    {
        collection = collection == null ? new Collections() : collection;
        collection.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            return collectionMapper.selectBaseCollectionList(collection);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户所有收藏SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询收藏分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param collection
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Collection>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<CollectionVO> findCollectionPage(Collections collection,
            Integer pageNum, Integer pageSize) throws GlobalException
    {
        PageInfo<CollectionVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询收藏分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<CollectionVO> collectionList = collectionMapper
                    .selectCollectionList(collection);
            pageInfo = new PageInfo<>(collectionList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询收藏分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询当前收藏分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param collection
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<BaseDocumentVO>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<BaseDocumentVO> findCurrentCollectionDocPage(
            Collections collection, Integer pageNum, Integer pageSize)
            throws GlobalException
    {
        PageInfo<BaseDocumentVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询收藏分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            collection = collection == null ? new Collections() : collection;
            collection.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
            PageHelper.startPage(pageNum, pageSize);
            List<BaseDocumentVO> collectionList = collectionMapper
                    .selectCurrentCollectionDocList(collection);
            pageInfo = new PageInfo<>(collectionList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前收藏分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询收藏总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findCollectionCount(Collections collection)
            throws GlobalException
    {
        try
        {
            return collectionMapper.selectCollectionCount(collection);
        } catch (SQLException e)
        {
            throw new GlobalException("查询收藏总数SQL异常：{}", e.getMessage());
        }
    }
}
