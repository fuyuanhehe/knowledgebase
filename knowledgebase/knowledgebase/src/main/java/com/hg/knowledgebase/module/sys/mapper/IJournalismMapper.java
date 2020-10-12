package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Journalism;
import com.hg.knowledgebase.module.sys.entity.VO.JournalismVO;

/**
 * 
 * @Description: 新闻数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IJournalismMapper
{
    /**
     * 
     * @Description: 插入新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertJournalism(Journalism journalism) throws SQLException;

    /**
     * 
     * @Description: 插入批量新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param journalisms
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchJournalism(
            @Param("journalisms") List<Journalism> journalisms)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteJournalismById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchJournalismById(@Param("ids") List<String> ids)
            throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateJournalism(Journalism journalism) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    Journalism selectJournalismById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据名称查询新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Journalism
     * @Version: 1.0.0
     */
    Journalism selectJournalismByName(@Param("name") String name)
            throws SQLException;

    /**
     * 
     * @Description: 查询所有新闻
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateJournalism: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param journalism
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Journalism>
     * @Version: 1.0.0
     */
    List<JournalismVO> selectJournalismList(Journalism journalism)
            throws SQLException;
}
