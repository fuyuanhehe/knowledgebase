package com.hg.knowledgebase.module.proj.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.proj.entity.ProjectStage;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectStageVO;

/**
 *
* @Description: 项目阶段数据层
* @Author: 符原
* @CreateDate: 2020年2月26日下午4:38:26
* @Version: 1.0.0
 */
public interface IProjectStageMapper
{
    /**
     *
    * @Description: 插入项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:23:09
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:23:09
    * @UpdateRemark: 修改内容
    * @param projectStage
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int insertSelective(ProjectStage projectStage) throws SQLException;


    /**
     *
    * @Description: 根据删除项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:24:56
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:24:56
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int deleteProjectStage(ProjectStage projectStage) throws SQLException;

    /**
     *
    * @Description: 删除批量项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午5:41:51
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午5:41:51
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
     */
    int deleteBatchProjectStageById(@Param("ids")List<String> ids) throws SQLException;

    /**
     *
    * @Description: 删除批量项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午5:41:51
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午5:41:51
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws SQLException
    * int
    * @Version: 1.0.0
     */
    int deleteBatchProjectStageByProjectId(@Param("projectIds")List<String> projectIds) throws SQLException;

    /**
     *
    * @Description: 修改项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:26:15
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:26:15
    * @UpdateRemark: 修改内容
    * @param projectStage
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    int updateByPrimaryKeySelective(ProjectStage projectStage) throws SQLException;


    /**
     * 功能描述: 主键查询
     * @auther: fuyuan
     * @date: 2020/4/1 0001 17:59
     */
    ProjectStage  selectByPrimaryKey(String id);


    /**
     * 功能描述: 主键查询
     * @auther: fuyuan
     * @date: 2020/4/1 0001 17:59
     */
    List<ProjectStageVO>  getAllProjectStage(ProjectStage projectStage);

   /**
    * 功能描述: 查重
    * @auther: fuyuan
    * @date: 2020/4/26 0026 15:53
    */

   ProjectStage selectByName(String name);
}
