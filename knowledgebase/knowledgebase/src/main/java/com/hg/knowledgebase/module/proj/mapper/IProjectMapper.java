package com.hg.knowledgebase.module.proj.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.proj.entity.Project;
import com.hg.knowledgebase.module.proj.entity.UserProject;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectVO;
import com.hg.knowledgebase.module.proj.entity.VO.UserProjectVO;

/**
 *
* @Description: 项目数据层
* @Author: 符源
* @CreateDate: 2020年4月14日日下午4:38:26
* @Version: 1.0.0
 */
public interface IProjectMapper
{
    /**
     *
    * @Description: 插入项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:23:09
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:23:09
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int insertProject(Project project) throws SQLException;

    /**
     *
    * @Description: 插入批量项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午5:41:15
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午5:41:15
    * @UpdateRemark: 修改内容
    * @param projects
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
     */
    int insertBatchProject(@Param("projects")List<Project> projects) throws SQLException;

    /**
     *
    * @Description: 根据id删除项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:24:56
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:24:56
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int deleteProjectById(@Param("id")String id) throws SQLException;

    /**
     *
    * @Description: 删除批量项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午5:41:51
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午5:41:51
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
     */
    int deleteBatchProjectById(@Param("ids")List<String> ids) throws SQLException;

    /**
     *
    * @Description: 修改项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:26:15
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:26:15
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int updateProject(Project project) throws SQLException;

    /**
     *
    * @Description: 根据id查询项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:28:37
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:28:37
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * Project
    * @Version: 1.0.0
     */
    ProjectVO selectProjectById(@Param("id")String id) throws SQLException;

    /**
     *
    * @Description: 根据登录名查询项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:28:37
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:28:37
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * Project
    * @Version: 1.0.0
     */
    Project selectProjectByName(@Param("name")String name) throws SQLException;

    /**
     *
    * @Description: 查询所有项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:32:07
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:32:07
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws SQLException
    * List<Project>
    * @Version: 1.0.0
     */
    List<ProjectVO> selectProjectList(Project project) throws SQLException;

    /**
     *
    * @Description: 查询所有基础项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:32:07
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:32:07
    * @UpdateRemark: 修改内容
    * @param project
    * @return
    * @throws SQLException
    * List<Project>
    * @Version: 1.0.0
     */
    List<Project> selectBaseProjectList(Project project) throws SQLException;

    //////////////////////////////////用户项目关系////////////////////////////////////////////
    /**
    *
    * @Description: 插入用户项目
    * @Author: 符源
    * @CreateDate: 2020年2月27日下午3:04:30
    * @UpdateUser: 符源
    * @UpdateDate: 2020年2月27日下午3:04:30
    * @UpdateRemark: 修改内容
    * @param projectId
    * @param type
    * @param userIds
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
    */
    int insertUserProject(@Param("projectId")String projectId, @Param("type")int type,
            @Param("userIds") Set<String> userIds) throws SQLException;

    /**
    *
    * @Description: 删除用户项目
    * @Author: 符源
    * @CreateDate: 2020年2月27日下午3:05:38
    * @UpdateUser: 符源
    * @UpdateDate: 2020年2月27日下午3:05:38
    * @UpdateRemark: 修改内容
    * @param userProject
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
    */
    int deleteUserProject(UserProject userProject) throws SQLException;

    /**
    *
    * @Description: 根据项目id批量删除用户项目
    * @Author: 符源
    * @CreateDate: 2020年2月27日下午3:05:38
    * @UpdateUser: 符源
    * @UpdateDate: 2020年2月27日下午3:05:38
    * @UpdateRemark: 修改内容
    * @param projectId
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
    */
    int deleteBatchUserProjectByProjectId(@Param("projectIds")List<String> projectIds) throws SQLException;

    /**
    *
    * @Description: 根据用户id批量删除用户项目
    * @Author: 符源
    * @CreateDate: 2020年2月27日下午3:05:38
    * @UpdateUser: 符源
    * @UpdateDate: 2020年2月27日下午3:05:38
    * @UpdateRemark: 修改内容
    * @param userIds
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
    */
    int deleteBatchUserProjectByUserId(@Param("userIds")List<String> userIds) throws SQLException;

    /**
     *
    * @Description: 根据用户id查询用户项目列表
    * @Author: 符源
    * @CreateDate: 2020年3月5日上午11:50:16
    * @UpdateUser: 符源
    * @UpdateDate: 2020年3月5日上午11:50:16
    * @UpdateRemark: 修改内容
    * @param userIds
    * @return
    * @throws SQLException
    * List<UserProjectVO>
    * @Version: 1.0.0
     */
    List<UserProjectVO> selectUserProjectByUserId(@Param("userIds")List<String> userIds) throws SQLException;

    /**
    *
    * @Description: 查询用户项目信息
    * @Author: 符源
    * @CreateDate: 2020年3月4日下午1:40:41
    * @UpdateUser: 符源
    * @UpdateDate: 2020年3月4日下午1:40:41
    * @UpdateRemark: 修改内容
    * @param userId 用户id
    * @param projectId 考试id
    * @return
    * @throws SQLException
    * UserProjectVO
    * @Version: 1.0.0
    */
    //UserProjectVO selectUserProject(@Param("userId")String userId, @Param("projectId")String projectId) throws SQLException;

    /**
    *
    * @Description: 查询用户项目信息列表
    * @Author: 符源
    * @CreateDate: 2020年2月28日下午6:18:19
    * @UpdateUser: 符源
    * @UpdateDate: 2020年2月28日下午6:18:19
    * @UpdateRemark: 修改内容
    * @param userProjectVO
    * @return
    * @throws SQLException
    * List<UserProjectVO>
    * @Version: 1.0.0
    */
    //List<UserProjectVO> selectUserProjectList(UserProjectVO userProjectVO) throws SQLException;

    List<Map<String,Object>>  selectUserProjectByProjectId(@Param(value = "id") String id,@Param(value = "type")Integer type );

}
