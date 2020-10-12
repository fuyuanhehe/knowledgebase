package com.hg.knowledgebase.module.sys.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Dict;

/**
 * 
 * @Description: 字典服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IDictService
{

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
     * @Version: 1.0.0
     */
    boolean addDict(Dict dict) throws GlobalException;

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
    boolean removeDictById(String id) throws GlobalException;

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
    boolean removeBatchDictById(List<String> ids) throws GlobalException;

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
    boolean modifyDict(Dict dict) throws GlobalException;

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
    boolean modifyBaseDict(Dict dict) throws GlobalException;

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
    Dict findDictById(String id) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Dict
     * @Version: 1.0.0
     */
    Dict findDictByName(String name) throws GlobalException;

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
    List<Dict> findAllDict(Dict dict) throws GlobalException;

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
    List<Dict> findAllBaseDict(Dict dict) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Map<String,String>
     * @Version: 1.0.0
     */
    Map<String, String> findAllDictOfCoderAndNameMap(Dict dict)
            throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Map<String,String>
     * @Version: 1.0.0
     */
    Map<String, String> findAllDictOfNameAndCoderMap(Dict dict)
            throws GlobalException;

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
    PageInfo<Dict> findDictPage(Dict dict, Integer pageNum, Integer pageSize)
            throws GlobalException;
}
