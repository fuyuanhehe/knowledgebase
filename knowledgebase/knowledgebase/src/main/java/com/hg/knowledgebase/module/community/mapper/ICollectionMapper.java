package com.hg.knowledgebase.module.community.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.community.entity.Collections;
import com.hg.knowledgebase.module.community.entity.VO.CollectionVO;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 收藏数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface ICollectionMapper
{
    /**
     * 
     * @Description: 插入收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertCollection(Collections collection) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteCollection(Collections collection) throws SQLException;

    /**
     * 
     * @Description: 删除批量收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param collections
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchCollection(
            @Param("collections") List<Collections> collections)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Collection
     * @Version: 1.0.0
     */
    Collections selectCollectionById(@Param("id") String id)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<Collection>
     * @Version: 1.0.0
     */
    List<CollectionVO> selectCollectionList(Collections collection)
            throws SQLException;

    /**
     * 
     * @Description: 查询基础收藏列表
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Collection>
     * @Version: 1.0.0
     */
    List<Collections> selectBaseCollectionList(Collections collection)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectCollectionCount(Collections collection) throws SQLException;

    //////////////////////////////////// 用户收藏///////////////////////////////////////////
    /**
     * 
     * @Description: 查询当前用户收藏文档列表
     * @Author: 况克冬
     * @CreateDate: 2020年3月19日下午5:04:06
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月19日下午5:04:06
     * @UpdateRemark: 修改内容
     * @param collection
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<BaseDocumentVO>
     * @Version: 1.0.0
     */
    List<BaseDocumentVO> selectCurrentCollectionDocList(Collections collection)
            throws SQLException;
}
