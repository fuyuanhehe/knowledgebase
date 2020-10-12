package com.hg.knowledgebase.module.proj.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.proj.entity.ProjectStage;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectStageVO;

/**
 *
* @Description: 项目阶段服务层
* @Author: 符原
* @CreateDate: 2020年2月26日下午4:21:35
* @Version: 1.0.0
 */
public interface IProjectStageService
{

    /**
     *
    * @Description: 添加项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:23:09
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:23:09
    * @UpdateRemark: 修改内容
    * @param projectStage
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean addProjectStage(ProjectStage projectStageVO) throws Exception;

    /**
     *
    * @Description: 根据id删除项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:24:56
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:24:56
    * @UpdateRemark: 修改内容
    * @param uuid
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean deleteProjectStage(ProjectStage projectStage) throws Exception;
    /**
     *
    * @Description: 根据id删除批量项目阶段
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午5:30:50
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午5:30:50
    * @UpdateRemark: 修改内容
    * @param uuids
    * @return
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean removeBatchProjectStageById(List<String> ids) throws Exception;

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
    * @throws Exception
    * boolean
    * @Version: 1.0.0
     */
    boolean modifyProjectStage(ProjectStage projectStageVO) throws Exception;

    /**
     *
    * @Description: 查询项目阶段分页
    * @Author: 符原
    * @CreateDate: 2020年2月26日下午4:34:28
    * @UpdateProjectStage: 符原
    * @UpdateDate: 2020年2月26日下午4:34:28
    * @UpdateRemark: 修改内容
    * @param projectStage
    * @param pageNum
    * @param pageSize
    * @return
    * @throws Exception
    * List<ProjectStage>
    * @Version: 1.0.0
     */
    PageInfo<ProjectStageVO> findProjectStagePage(ProjectStage projectStage, Integer pageNum,Integer pageSize) throws Exception;


    /**
     * 功能描述: 主键查询
     * @auther: fuyuan
     * @date: 2020/4/1 0001 17:59
     */
    ProjectStage  selectByPrimaryKey(String id);

    /**
     * 功能描述: 查重
     * @auther: fuyuan
     * @date: 2020/4/1 0001 17:59
     */
    ProjectStage  selectByName(String name);

}
