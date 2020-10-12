package com.hg.knowledgebase.module.repo.controller;

import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.repo.entity.BaseDocumentCategory;
import com.hg.knowledgebase.module.repo.service.BaseDocumentCategoryService;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 知识分类控制层
 * @auther: fuyuan
 * @date: 2020/3/19 0019 14:43
 */
@RestController
@RequestMapping(value = "/api/repo/baseDocumentCategory")
public class BaseDocumentCategoryCotroller
{

    private static Logger log = LoggerFactory.getLogger(BaseDocumentCategoryCotroller.class);
    
    @Resource
    private BaseDocumentCategoryService BaseDocumentCategoryCategoryService;


    @Autowired
    private IDeptService deptService;

    /**
     * 功能描述: add 知识分类
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/saveBaseDocumentCategory")
    @SystemLog(value = "新增知识分类")
    public ResponseResultBean saveBaseDocumentCategory(@RequestBody BaseDocumentCategory baseDocumentCategory )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Dept dept = deptService.findCurrentDept();
            if(dept != null){
                baseDocumentCategory.setDeptId(dept.getId());
            }

            flg = BaseDocumentCategoryCategoryService.addBaseDocumentCategory(baseDocumentCategory);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---添加知识分类异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 修改 知识分类
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/modifyBaseDocumentCategory")
    @SystemLog(value = "修改知识分类")
    public ResponseResultBean modifyBaseDocumentCategory(@RequestBody Map<String,Object> map)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            map.put("createTime",null);
            map.put("updateTime",null);
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BaseDocumentCategory base = new BaseDocumentCategory();
            BeanUtils.populate(base,map);


            flg = BaseDocumentCategoryCategoryService.modifyBaseDocumentCategory(base);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("---修改知识分类异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  知识分类删除
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/removeBaseDocumentCategory")
    @SystemLog(value = "删除知识分类")
    public ResponseResultBean removeBaseDocumentCategoryById(@RequestBody BaseDocumentCategory BaseDocumentCategory)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = BaseDocumentCategoryCategoryService.removeBaseDocumentCategory(BaseDocumentCategory);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        }
        catch (Exception e)
        {
            log.error("---根据id删除知识分类异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  知识分类分页
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getAllBaseDocumentCategory")
    @SystemLog(value = "查询所有知识分类")
    public ResponseResultBean getAllBaseDocumentCategory(@RequestBody BaseDocumentCategory baseDocumentCategory)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            List<BaseDocumentCategory> data = BaseDocumentCategoryCategoryService.findBaseDocumentCategory(baseDocumentCategory);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        }
        catch (Exception e)
        {
            log.error("---获取知识分类异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 单个查询 知识分类
     * @auther: fuyuan
     * @date: 2020/3/19 0019 14:49
     */
    @RequestMapping(value = "/getBaseDocumentCategory")
    @SystemLog(value = "单个查询知识分类")
    public ResponseResultBean getBaseDocumentCategory(@RequestBody BaseDocumentCategory baseDocumentCategory )
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {

            BaseDocumentCategory data = BaseDocumentCategoryCategoryService.getBaseDocumentCategory(baseDocumentCategory);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,data);
        }
        catch (Exception e)
        {
            log.error("---单个查询知识分类---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }
}
