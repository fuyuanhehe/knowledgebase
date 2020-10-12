package com.hg.knowledgebase.module.proj.service;

import java.sql.SQLException;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.proj.entity.Project;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectVO;
import com.hg.knowledgebase.module.proj.entity.VO.UserProjectVO;

/**
 * 
* @Description: 项目服务层
* @Author: 况克冬
* @CreateDate: 2020年2月26日下午4:21:35
* @Version: 1.0.0
 */
public interface IProjectService
{

    /**
     * 
    * @Description: 添加项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:23:09
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:23:09
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean addProject(ProjectVO projectVO) throws Exception;
    
    /**
     * 
    * @Description: 根据id删除项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:24:56
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:24:56
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean removeProjectById(String id) throws Exception;
    
    /**
     * 
    * @Description: 根据id删除批量项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午5:30:50
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午5:30:50
    * @UpdateRemark: 修改内容
    * @param uuids
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean removeBatchProjectById(List<String> ids) throws Exception;
    
    /**
     * 
    * @Description: 修改项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:26:15
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:26:15
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean modifyProject(ProjectVO projectVO) throws Exception;
    
    /**
     * 
    * @Description: 根据id查询项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:28:37
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:28:37
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws Exception
    * Project
    * @Version: 1.0.0
     */
    ProjectVO findProjectById(String id) throws Exception;
    
    /**
     * 
    * @Description: 根据登录名查询项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:28:37
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:28:37
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws Exception
    * Project
    * @Version: 1.0.0
     */
    Project findProjectByName(String name) throws Exception;
    
    /**
     * 
    * @Description: 查询所有项目
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:32:07
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:32:07
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws Exception
    * List<Project>
    * @Version: 1.0.0
     */
    List<Project> findAllProject(Project project) throws Exception;
    
    /**
     * 
    * @Description: 查询项目分页
    * @Author: 况克冬
    * @CreateDate: 2020年2月26日下午4:34:28
    * @UpdateProject: 况克冬
    * @UpdateDate: 2020年2月26日下午4:34:28
    * @UpdateRemark: 修改内容
    * @param project
    * @param pageNum
    * @param pageSize
    * @return
    * @throws Exception
    * List<Project>
    * @Version: 1.0.0
     */
    PageInfo<ProjectVO> findProjectPage(Project project, Integer pageNum,Integer pageSize) throws Exception;
    
    //////////////////////////////////////////用户项目关系////////////////////////////////////////////
    /**
     * 
    * @Description: 根据用户id查询用户项目列表
    * @Author: 况克冬
    * @CreateDate: 2020年3月5日上午11:50:16
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月5日上午11:50:16
    * @UpdateRemark: 修改内容
    * @param userIds
    * @return
    * @throws SQLException
    * List<UserProjectVO>
    * @Version: 1.0.0
     */
    List<UserProjectVO> findUserProjectByUserId(List<String> userIds) throws Exception;
}
