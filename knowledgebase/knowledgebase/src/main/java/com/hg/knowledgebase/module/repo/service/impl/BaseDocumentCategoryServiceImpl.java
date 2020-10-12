package com.hg.knowledgebase.module.repo.service.impl;

import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.repo.entity.BaseDocumentCategory;
import com.hg.knowledgebase.module.repo.mapper.BaseDocumentCategoryMapper;
import com.hg.knowledgebase.module.repo.mapper.BaseDocumentMapper;
import com.hg.knowledgebase.module.repo.service.BaseDocumentCategoryService;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.mapper.IMenuMapper;
import com.hg.knowledgebase.module.sys.service.IMenuService;
import com.hg.knowledgebase.util.FileUtil;
import com.hg.knowledgebase.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: 知识分类service
 * @auther: fuyuan
 * @date: 2020/3/18 0018 14:04
 */
@Service
public class BaseDocumentCategoryServiceImpl implements BaseDocumentCategoryService
{
    private static Logger log = LoggerFactory.getLogger(BaseDocumentCategoryServiceImpl.class);
    @Resource
    private BaseDocumentCategoryMapper baseDocumentCategoryMapper;

    @Resource
    private BaseDocumentMapper baseDocumentMapper;

    @Autowired
    private IMenuMapper menuMapper;

    @Autowired
    private IMenuService iMenuService;

    /**
     * 功能描述: 知识分类分页
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    public List<BaseDocumentCategory> findBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory) {

            List<BaseDocumentCategory> userVOList = baseDocumentCategoryMapper.selectBaseDocumentCategoryList(BaseDocumentCategory);

        return userVOList;
    }

    /**
     * 功能描述: 知识分类新增
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean addBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception {
        if(BaseDocumentCategory == null ){
            return false;
        }
        String ids = StringUtil.getUUID();
        BaseDocumentCategory.setId(ids);
        BaseDocumentCategory.setCreateTime(new Date());
        BaseDocumentCategory.setCreateUser(SecurityUtils.getCurrentUserLogin().get());

        int result = baseDocumentCategoryMapper.insertSelective(BaseDocumentCategory);

/*        BaseDocument baseDocument = new BaseDocument();// TODO
        baseDocument.setId(ids);
        baseDocument.setPid(ids);
        baseDocument.setUrl(BaseDocumentCategory.getUrl());
        baseDocument.setDeptId(BaseDocumentCategory.getDeptId());
        baseDocument.setType(new Short("1"));
        baseDocument.setWeight(new Short("3"));
        baseDocument.setCategoryId(ids);
        baseDocument.setName(BaseDocumentCategory.getName());
        baseDocument.setCreateTime(new Date());
        baseDocument.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        baseDocumentMapper.insertSelective(baseDocument);*/

        //添加菜单
        Menu menu = new Menu();
        menu.setId(ids);
        //父集菜单id(61)
        menu.setPid("30");
        menu.setCoder(ids);
        menu.setName(BaseDocumentCategory.getName());
        //父集菜单组(community)
        menu.setMenuGroup("repo");
        menu.setReserve("repository");  // /repository/warehouseMgt/classifyMgt
        //父集菜单url(/community/communityMgt/circleTypeMgt)
        menu.setUrl("/repository/knowledgeClassify/" + ids);
        menu.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        menuMapper.insertMenu(menu);

        return result > 0;
    }

    /**
     * 功能描述: 知识分类修改
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean modifyBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception {
        if(BaseDocumentCategory == null){
            return false;
        }
        BaseDocumentCategory.setUpdateTime(new Date());
        BaseDocumentCategory.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
       if(BaseDocumentCategory.getStatus() != null){ // 表示参与审批
           BaseDocumentCategory.setAddrovalTime(new Date());
       }

        Menu menu = new Menu();
        menu.setId(BaseDocumentCategory.getId());
        menu.setUpdateTime(new Date());
        menu.setName(BaseDocumentCategory.getName());
        menu.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        menuMapper.updateMenu(menu);


        int result = baseDocumentCategoryMapper.updateByPrimaryKeySelective(BaseDocumentCategory);

        return result > 0;
    }
    /**
     * 功能描述: 知识分类删除
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    @Override
    @Transactional
    public boolean removeBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception {
        if(BaseDocumentCategory == null){
            return false;
        }
        int result = baseDocumentCategoryMapper.deleteByPrimaryKey(BaseDocumentCategory.getId());

        log.debug(BaseDocumentCategory.getUrl() + "--------------");
        if(StringUtils.isNotBlank(BaseDocumentCategory.getUrl())){
            FileUtil.DeleteFolder( BaseDocumentCategory.getUrl());
        }

        baseDocumentMapper.deleteByPrimaryKey(BaseDocumentCategory.getId());
           // 级联删除

     //   menuMapper.deleteMenuById(BaseDocumentCategory.getId());

        iMenuService.removeMenuById(BaseDocumentCategory.getId());

        return result > 0;
    }
    /**
     * 功能描述: 单个查询
     * @auther: fuyuan
     * @date: 2020/3/24 0024 14:17
     */
    @Override
    public BaseDocumentCategory getBaseDocumentCategory(BaseDocumentCategory baseDocumentCategory) {
        if(baseDocumentCategory == null){
            return null;
        }

        BaseDocumentCategory data = baseDocumentCategoryMapper.selectByPrimaryKey(baseDocumentCategory.getId());
        return data;
    }
}
