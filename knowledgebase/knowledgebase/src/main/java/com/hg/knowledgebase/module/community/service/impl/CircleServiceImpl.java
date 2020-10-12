package com.hg.knowledgebase.module.community.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.constant.ModuleConstant;
import com.hg.knowledgebase.module.community.entity.Circle;
import com.hg.knowledgebase.module.community.mapper.ICircleMapper;
import com.hg.knowledgebase.module.community.service.ICircleService;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.mapper.IMenuMapper;
import com.hg.knowledgebase.module.sys.service.IMenuService;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 圈子业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class CircleServiceImpl implements ICircleService
{

    @Autowired
    private ICircleMapper circleMapper;

    @Autowired
    private IMenuMapper menuMapper;

    @Autowired
    private IMenuService menuService;

    /**
     * 默认菜单父级id
     */
    private static final String DEFULT_MENU_PARENT_ID = "50";

    /**
     * 默认菜单父级地址
     */
    private static final String DEFULT_MENU_PARENT_URL = "/community/topicCircle/";

    /**
     * 
     * @Description: 添加圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean addCircle(Circle circle) throws GlobalException
    {
        if (circle == null)
        {
            throw new GlobalException("添加圈子信息不能为空，入参【圈子信息：{}】", circle);
        }
        // 执行条数
        int exec = 0;
        String id = StringUtil.getUUID();
        String userName = SecurityUtils.getCurrentUserLogin().get();
        circle.setId(id);
        circle.setCreateUser(userName);
        try
        {
            exec = circleMapper.insertCircle(circle);
            // 添加菜单
            Menu menu = new Menu();
            menu.setId(id);
            // 父集菜单id(50)
            menu.setPid(DEFULT_MENU_PARENT_ID);
            menu.setName(circle.getName());
            menu.setCoder(id);
            // 父集菜单组(community)
            menu.setMenuGroup(ModuleConstant.COMMUNITY);
            menu.setReserve(ModuleConstant.COMMUNITY);
            // 父集菜单url(/community/communityMgt/circleTypeMgt)
            menu.setUrl(DEFULT_MENU_PARENT_URL + id);
            menu.setCreateUser(userName);
            menuMapper.insertMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("添加圈子SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean removeCircleById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除圈子信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = circleMapper.deleteCircleById(id);
            // 删除菜单
            menuService.removeMenuById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除圈子SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id删除批量圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    public boolean removeBatchCircleById(List<String> ids)
            throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量圈子圈子信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = circleMapper.deleteBatchCircleById(ids);
            // 删除菜单
            menuService.removeBatchMenuById(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量圈子SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyCircle(Circle circle) throws GlobalException
    {
        if (circle == null || StringUtils.isEmpty(circle.getId()))
        {
            throw new GlobalException("修改圈子信息主键不能为空，入参【圈子信息：{}】", circle);
        }
        // 执行条数
        int exec = 0;
        circle.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        circle.setUpdateTime(new Date());
        try
        {
            exec = circleMapper.updateCircle(circle);
            Menu menu = new Menu();
            menu.setId(circle.getId());
            menu.setName(circle.getName());
            menu.setUpdateTime(new Date());
            menu.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
            menuMapper.updateMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("修改圈子SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 修改基础圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyBaseCircle(Circle circle) throws GlobalException
    {
        if (circle == null || StringUtils.isEmpty(circle.getId()))
        {
            throw new GlobalException("修改圈子信息主键不能为空，入参【圈子信息：{}】", circle);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = circleMapper.updateCircle(circle);
            Menu menu = new Menu();
            menu.setId(circle.getId());
            menu.setUpdateTime(new Date());
            menu.setName(circle.getName());
            menu.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
            menuMapper.updateMenu(menu);
        } catch (SQLException e)
        {
            throw new GlobalException("修改基础圈子SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

    /**
     * 
     * @Description: 根据id查询圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Circle findCircleById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询圈子信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return circleMapper.selectCircleById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询圈子SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 根据名称查询圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             Circle
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public Circle findCircleByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据名称查询圈子信息名称不能为空，入参【名称：{}】", name);
        }
        try
        {
            return circleMapper.selectCircleByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询圈子SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Circle> findAllCircle(Circle circle) throws GlobalException
    {
        try
        {
            return circleMapper.selectCircleList(circle);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有圈子SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询所有圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<Circle> findAllBaseCircle(Circle circle) throws GlobalException
    {
        try
        {
            return circleMapper.selectBaseCircleList(circle);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有圈子SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询圈子分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param circle
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<Circle>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Circle> findCirclePage(Circle circle, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<Circle> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询圈子分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<Circle> circleList = circleMapper.selectCircleList(circle);
            pageInfo = new PageInfo<>(circleList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询圈子分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 
     * @Description: 查询圈子总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param circle
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public int findCircleCount(Circle circle) throws GlobalException
    {
        try
        {
            return circleMapper.selectCircleCount(circle);
        } catch (SQLException e)
        {
            throw new GlobalException("查询圈子总数SQL异常：{}", e.getMessage());
        }
    }
}
