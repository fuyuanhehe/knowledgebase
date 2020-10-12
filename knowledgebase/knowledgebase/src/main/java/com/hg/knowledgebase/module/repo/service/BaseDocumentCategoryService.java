package com.hg.knowledgebase.module.repo.service;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.bean.TreeBean;
import com.hg.knowledgebase.module.repo.entity.BaseDocumentCategory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 功能描述: 知识分类service
 * @auther: fuyuan
 * @date: 2020/3/18 0018 11:03
 */
public interface BaseDocumentCategoryService
{
    /**
     * 功能描述: 知识分类分页
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    List<BaseDocumentCategory> findBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory);

    /**
     * 功能描述: 知识分类新增
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean addBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception;
    /**
     * 功能描述: 知识分类修改
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean modifyBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception;
    /**
     * 功能描述: 知识分类删除
     * @auther: fuyuan
     * @date: 2020/3/18 0018 11:43
     */
    boolean removeBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory)throws Exception;


   /**
    * 功能描述: 单个查询
    * @auther: fuyuan
    * @date: 2020/3/24 0024 14:17
    */

   BaseDocumentCategory getBaseDocumentCategory(BaseDocumentCategory BaseDocumentCategory);
}
