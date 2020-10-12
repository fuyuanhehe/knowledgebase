package com.hg.knowledgebase.module.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Notice;
import com.hg.knowledgebase.module.sys.entity.VO.NoticeVO;

/**
 * 
 * @Description: 通知公告服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface INoticeService
{

    /**
     * 
     * @Description: 添加通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param notice
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addNotice(Notice notice) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeNoticeById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id批量删除通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchNoticeById(List<String> ids) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyNotice(Notice notice) throws GlobalException;

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
     * @throws GlobalException 业务异常时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    Notice findNoticeById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询通知公告
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Notice
     * @Version: 1.0.0
     */
    Notice findNoticeByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 查询通知公告分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateNotice: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param notice
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Notice>
     * @Version: 1.0.0
     */
    PageInfo<NoticeVO> findNoticePage(Notice notice, Integer pageNum,
            Integer pageSize) throws GlobalException;
}
