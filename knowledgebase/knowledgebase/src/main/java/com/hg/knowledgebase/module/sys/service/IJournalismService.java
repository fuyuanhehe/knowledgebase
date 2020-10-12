package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Journalism;
import com.hg.knowledgebase.module.sys.entity.VO.JournalismVO;

/**
 * 
 * @Description: 新闻服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IJournalismService
{

    /**
     * 
     * @Description: 添加新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addJournalism(Journalism journalism) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeJournalismById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id批量删除新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchJournalismById(List<String> ids) throws GlobalException;

    /**
     * 
     * @Description: 修改新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyJournalism(Journalism journalism) throws GlobalException;

    /**
     * 
     * @Description: 根据id查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    Journalism findJournalismById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    Journalism findJournalismByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 查询新闻分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param journalism
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Journalism>
     * @Version: 1.0.0
     */
    PageInfo<JournalismVO> findJournalismPage(Journalism journalism,
            Integer pageNum, Integer pageSize) throws GlobalException;
}
