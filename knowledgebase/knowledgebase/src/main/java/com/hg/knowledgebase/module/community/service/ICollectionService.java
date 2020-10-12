package com.hg.knowledgebase.module.community.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.community.entity.Collections;
import com.hg.knowledgebase.module.community.entity.VO.CollectionVO;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 收藏服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface ICollectionService
{

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addCollection(Collections collection) throws GlobalException;

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
    boolean removeCollection(Collections collection) throws GlobalException;

    /**
     * 
     * @Description: 删除批量收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchCollection(List<Collections> collections)
            throws GlobalException;

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
    Collections findCollectionById(String id) throws GlobalException;

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
    List<CollectionVO> findAllCollection(Collections collection)
            throws GlobalException;

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
    List<Collections> findCurrentAllCollection(Collections collection)
            throws GlobalException;

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
    PageInfo<CollectionVO> findCollectionPage(Collections collection,
            Integer pageNum, Integer pageSize) throws GlobalException;

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
     *             List<BaseDocumentVo>
     * @Version: 1.0.0
     */
    PageInfo<BaseDocumentVO> findCurrentCollectionDocPage(
            Collections collection, Integer pageNum, Integer pageSize)
            throws GlobalException;

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
    int findCollectionCount(Collections collection) throws GlobalException;
}
