package com.hg.knowledgebase.module.repo.mapper;


import com.hg.knowledgebase.module.repo.entity.BaseDocumentCategory;

import java.util.List;

/**
 * 功能描述: 文档类别mapper
 * @auther: fuyuan
 * @date: 2020/3/18 0018 10:04
 */
public interface BaseDocumentCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseDocumentCategory record);

    int insertSelective(BaseDocumentCategory record);

    BaseDocumentCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseDocumentCategory record);

    int updateByPrimaryKey(BaseDocumentCategory record);
    /**
     * 功能描述: 文档类别查询所有
     * @auther: fuyuan
     * @date: 2020/3/18 0018 10:04
     */
    List<BaseDocumentCategory> selectBaseDocumentCategoryList(BaseDocumentCategory baseDocumentCategory);

}