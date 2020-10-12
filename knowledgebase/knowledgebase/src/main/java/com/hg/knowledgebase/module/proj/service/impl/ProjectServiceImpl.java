package com.hg.knowledgebase.module.proj.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.proj.entity.Project;
import com.hg.knowledgebase.module.proj.entity.ProjectStage;
import com.hg.knowledgebase.module.proj.entity.UserProject;
import com.hg.knowledgebase.module.proj.entity.VO.ProjectVO;
import com.hg.knowledgebase.module.proj.entity.VO.UserProjectVO;
import com.hg.knowledgebase.module.proj.mapper.IProjectMapper;
import com.hg.knowledgebase.module.proj.mapper.IProjectStageMapper;
import com.hg.knowledgebase.module.proj.service.IProjectService;
import com.hg.knowledgebase.util.StringUtil;

/**
 *
* @Description: 项目业务实现类
* @Author: 符源
* @CreateDate: 2020年4月14日日下午4:46:29
* @Version: 1.0.0
 */
@Service
public class ProjectServiceImpl implements IProjectService
{

    private static Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private IProjectMapper projectMapper;

    @Autowired
    private IProjectStageMapper projectStageMapper;

    /**
     *
    * @Description: 添加项目
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
    @Override
    @Transactional
    public boolean addProject(ProjectVO projectVO) throws SQLException
    {
        if (projectVO == null)
        {
            log.error("---添加项目信息不能为空，入参【项目信息：{}】---", projectVO);
            return false;
        }
        String projectId = StringUtil.getUUID();
        projectVO.setId(projectId);
        projectVO.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        int exec = projectMapper.insertProject(projectVO);
        //获取项目参与人
        Set<String> participationUserList = projectVO.getParticipationUsers();
        if (participationUserList != null && participationUserList.size() > 0)
        {
            projectMapper.insertUserProject(projectId, 0, participationUserList);
        }
        //获取项目抄送人
        Set<String> duplicateUserList = projectVO.getDuplicateUsers();
        if (duplicateUserList != null && duplicateUserList.size() > 0)
        {
            projectMapper.insertUserProject(projectId, 1, duplicateUserList);
        }
        return exec > 0;
    }

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
    @Override
    @Transactional
        public boolean removeProjectById(String id) throws SQLException
    {
        if (StringUtils.isEmpty(id))
        {
            log.error("---根据id删除项目信息不能为空，入参【主键：{}】---", id);
            return false;
        }
        int exec = projectMapper.deleteProjectById(id);
        //删除项目阶段信息
        ProjectStage projectStage = new ProjectStage();
        projectStage.setProjectId(id);
        projectStageMapper.deleteProjectStage(projectStage);
        return exec > 0;
    }

    /**
     *
    * @Description: 根据id删除批量项目
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午5:30:50
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午5:30:50
    * @UpdateRemark: 修改内容
    * @param uuids
    * @return
    * @throws SQLException
    * boolean
    * @Version: 1.0.0
     */
    @Transactional
    public boolean removeBatchProjectById(List<String> ids) throws SQLException
    {
        if (ids == null || ids.size() == 0)
        {
            log.error("---根据id删除批量项目项目信息不能为空，入参【主键集合：{}】---", ids);
            return false;
        }
        int exec = projectMapper.deleteBatchProjectById(ids);
        //删除项目阶段信息
        projectStageMapper.deleteBatchProjectStageByProjectId(ids);
        return exec > 0;
    }

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
    @Override
    @Transactional
    public boolean modifyProject(ProjectVO projectVO) throws SQLException
    {
        if (projectVO == null || StringUtils.isEmpty(projectVO.getId()))
        {
            log.error("---修改项目信息主键不能为空，入参【项目信息：{}】---", projectVO);
            return false;
        }
        projectVO.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        projectVO.setUpdateTime(new Date());
        int exec = projectMapper.updateProject(projectVO);
        String projectId = projectVO.getId();
        //删除用户项目
        UserProject userProject = new UserProject();
        userProject.setProjectId(projectId);
        projectMapper.deleteUserProject(userProject);
        //获取项目参与人
        Set<String> participationUserList = projectVO.getParticipationUsers();
        if (participationUserList != null && participationUserList.size() > 0)
        {
            projectMapper.insertUserProject(projectId, 0, participationUserList);
        }
        //获取项目抄送人
        Set<String> duplicateUserList = projectVO.getDuplicateUsers();
        if (duplicateUserList != null && duplicateUserList.size() > 0)
        {
            projectMapper.insertUserProject(projectId, 1, duplicateUserList);
        }
        return exec > 0;
    }

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
    @Override
    public ProjectVO findProjectById(String id) throws SQLException
    {
        if (StringUtils.isEmpty(id))
        {
            log.error("---根据id查询项目信息主键不能为空，入参【主键：{}】---", id);
            return null;
        }
        return projectMapper.selectProjectById(id);
    }

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
    @Override
    public Project findProjectByName(String name) throws SQLException
    {
        if (StringUtils.isEmpty(name))
        {
            log.error("---根据登录名查询项目信息账号不能为空，入参【账号：{}】---", name);
            return null;
        }
        return projectMapper.selectProjectByName(name);
    }

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
    @Override
    public List<Project> findAllProject(Project project) throws SQLException
    {
        return projectMapper.selectBaseProjectList(project);
    }

    /**
     *
    * @Description: 查询项目分页
    * @Author: 符源
    * @CreateDate: 2020年4月14日日下午4:34:28
    * @UpdateProject: 符源
    * @UpdateDate: 2020年4月14日日下午4:34:28
    * @UpdateRemark: 修改内容
    * @param project
    * @param pageNum
    * @param pageSize
    * @return
    * @throws SQLException
    * List<Project>
    * @Version: 1.0.0
     */
    @Override
    public PageInfo<ProjectVO> findProjectPage(Project project, Integer pageNum, Integer pageSize) throws SQLException
    {
        PageInfo<ProjectVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            log.error("---查询项目分页信息条数不能为空，入参【页码：{}，条数：{}】---", pageNum, pageSize);
            return pageInfo;
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectVO> projectList = projectMapper.selectProjectList(project);
            pageInfo = new PageInfo<>(projectList);
        }
        finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;
    }

    //////////////////////////////////////////////用户项目关系////////////////////////////////////////////////
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
    @Override
    public List<UserProjectVO> findUserProjectByUserId(List<String> userIds) throws SQLException
    {
        return projectMapper.selectUserProjectByUserId(userIds);
    }
}
