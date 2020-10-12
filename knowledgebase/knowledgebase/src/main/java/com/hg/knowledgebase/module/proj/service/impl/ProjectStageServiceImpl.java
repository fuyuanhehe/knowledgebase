package com.hg.knowledgebase.module.proj.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.proj.entity.ProjectStage;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectStageVO;
import com.hg.knowledgebase.module.proj.mapper.IProjectStageMapper;
import com.hg.knowledgebase.module.proj.service.IProjectStageService;
import com.hg.knowledgebase.util.StringUtil;

/**
 *
* @Description: 项目阶段业务实现类
* @Author: 符原
* @CreateDate: 2020年2月26日下午4:46:29
* @Version: 1.0.0
 */
@Service
public class ProjectStageServiceImpl implements IProjectStageService
{

    private static Logger log = LoggerFactory.getLogger(ProjectStageServiceImpl.class);

    @Autowired
    private IProjectStageMapper projectStageMapper;

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
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    @Override
    @Transactional
    public boolean addProjectStage(ProjectStage projectStageVO) throws SQLException
    {
        if (projectStageVO == null)
        {
            log.error("---添加项目阶段信息不能为空，入参【项目阶段信息：{}】---", projectStageVO);
            return false;
        }
        String projectStageId = StringUtil.getUUID();
        projectStageVO.setId(projectStageId);
        projectStageVO.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        int exec = projectStageMapper.insertSelective(projectStageVO);
        return exec > 0;
    }

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
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    @Override
    @Transactional
    public boolean deleteProjectStage(ProjectStage projectStage) throws SQLException
    {
        if (projectStage == null)
        {
            log.error("---根据id删除项目阶段信息不能为空 "+ projectStage);
            return false;
        }
        int exec = projectStageMapper.deleteProjectStage(projectStage);
        return exec > 0;
    }

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
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    @Transactional
    public boolean removeBatchProjectStageById(List<String> ids) throws SQLException
    {
        if (ids == null || ids.size() == 0)
        {
            log.error("---根据id删除批量项目阶段项目阶段信息不能为空，入参【主键集合：{}】---", ids);
            return false;
        }
        int exec = projectStageMapper.deleteBatchProjectStageById(ids);
        return exec > 0;
    }

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
    @Override
    @Transactional
    public boolean modifyProjectStage(ProjectStage projectStageVO) throws SQLException
    {
        if (projectStageVO == null || StringUtils.isEmpty(projectStageVO.getId()))
        {
            log.error("---修改项目阶段信息主键不能为空，入参【项目阶段信息：{}】---", projectStageVO);
            return false;
        }
        projectStageVO.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        projectStageVO.setUpdateTime(new Date());
        int exec = projectStageMapper.updateByPrimaryKeySelective(projectStageVO);
        return exec > 0;
    }





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
    * @throws SQLException
    * List<ProjectStage>
    * @Version: 1.0.0
     */
    @Override
    public PageInfo<ProjectStageVO> findProjectStagePage(ProjectStage projectStage, Integer pageNum, Integer pageSize) throws SQLException
    {
        PageInfo<ProjectStageVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            log.error("---查询项目阶段分页信息条数不能为空，入参【页码：{}，条数：{}】---", pageNum, pageSize);
            return pageInfo;
        }
        try
        {
            PageHelper.startPage(pageNum,pageSize);
            List<ProjectStageVO> projectStageList = projectStageMapper.getAllProjectStage(projectStage);
            pageInfo = new PageInfo<>(projectStageList);
        }
        finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;
    }
    /**
     * 功能描述: 单个查询
     * @auther: fuyuan
     * @date: 2020/4/2 0002 9:51
     */
    @Override
    public ProjectStage selectByPrimaryKey(String id) {
        if(org.apache.commons.lang3.StringUtils.isBlank(id)){
            return null;
        }
        return projectStageMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProjectStage selectByName(String name) {

        return projectStageMapper.selectByName(name);
    }


}
