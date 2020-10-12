package com.hg.knowledgebase.module.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Dict;

/**
 * 
 * @Description: 字典数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IDictMapper
{
    /**
     * 
     * @Description: 插入字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException
     *             SQL错误时候出现的异常 boolean
     * @Version: 1.0.0
     */
    int insertDict(Dict dict) throws GlobalException;

    /**
     * 
     * @Description: 插入批量字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param dicts
     * @return
     * @throws GlobalException
     *             SQL错误时候出现的异常 int
     * @Version: 1.0.0
     */
    int insertBatchDict(@Param("dicts") List<Dict> dicts)
            throws GlobalException;

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
     * @throws GlobalException
     *             SQL错误时候出现的异常 boolean
     * @Version: 1.0.0
     */
    int deleteDictById(@Param("id") String id) throws GlobalException;

    /**
     * 
     * @Description: 删除批量字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException
     *             SQL错误时候出现的异常 int
     * @Version: 1.0.0
     */
    int deleteBatchDictById(@Param("ids") List<String> ids)
            throws GlobalException;

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
     * @throws GlobalException
     *             SQL错误时候出现的异常 boolean
     * @Version: 1.0.0
     */
    int updateDict(Dict dict) throws GlobalException;

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
     * @throws GlobalException
     *             SQL错误时候出现的异常 Dict
     * @Version: 1.0.0
     */
    Dict selectDictById(@Param("id") String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException
     *             SQL错误时候出现的异常 Dict
     * @Version: 1.0.0
     */
    Dict selectDictByName(@Param("name") String name) throws GlobalException;

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
     * @throws GlobalException
     *             SQL错误时候出现的异常 List<Dict>
     * @Version: 1.0.0
     */
    List<Dict> selectDictList(Dict dict) throws GlobalException;

    /**
     * 
     * @Description: 查询所有基础字典
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateDict: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param dict
     * @return
     * @throws GlobalException
     *             SQL错误时候出现的异常 List<Dict>
     * @Version: 1.0.0
     */
    List<Dict> selectBaseDictList(Dict dict) throws GlobalException;
}
