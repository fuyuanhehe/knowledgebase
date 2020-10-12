package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.Notice;
import com.hg.knowledgebase.module.sys.entity.VO.NoticeVO;

/**
 * 
 * @Description: 通知公告数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface INoticeMapper
{
    /**
     * 
     * @Description: 插入通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertNotice(Notice notice) throws SQLException;

    /**
     * 
     * @Description: 插入批量通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param notices
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchNotice(@Param("notices") List<Notice> notices)
            throws SQLException;

    /**
     * 
     * @Description: 根据id删除通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteNoticeById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchNoticeById(@Param("ids") List<String> ids)
            throws SQLException;

    /**
     * 
     * @Description: 修改通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateNotice(Notice notice) throws SQLException;

    /**
     * 
     * @Description: 根据id查询通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    Notice selectNoticeById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 根据名称查询通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    Notice selectNoticeByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 查询所有通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<Notice>
     * @Version: 1.0.0
     */
    List<NoticeVO> selectNoticeList(Notice notice) throws SQLException;
}
