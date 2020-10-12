package com.hg.knowledgebase.module.repo.mapper;

import com.hg.knowledgebase.module.repo.entity.BaseDocument;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 文档管理mapper
 * @auther: fuyuan
 * @date: 2020/3/16 0016 10:54
 */
public interface BaseDocumentMapper
{

    int deleteByPrimaryKey(String id);

    int insertSelective(BaseDocument record);

    BaseDocumentVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseDocument record);


    /**
     * 功能描述: 文档管理page
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:24
     */
   List<BaseDocument> selectBaseDocumentPage(BaseDocument baseDocument);
    /**
     * 功能描述: 文档管理树查询
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:24
     */
    List<BaseDocument> getTreeBaseDocument(String id);



    /**
     * 功能描述: 批量删除
     * @auther: fuyuan
     * @date: 2020/3/26 0026 13:52
     */

    int delBaseDocumentList(@Param("list")List<String> list);

    /**
     * 功能描述: 查询文档名称
     * @auther: fuyuan
     * @date: 2020/3/26 0026 14:06
     */
    List<String> getName();

    /**
     * 功能描述: 查询文档下级
     * @auther: fuyuan
     * @date: 2020/3/27 0027 14:42
     */
    List<BaseDocument> getFileUrl(@Param("ids") List<String> ids);

    /**
     * 功能描述: 添加多个文档
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */
   int addMultiBaseDocument(@Param("list") List<BaseDocument> list);
    /**
     * 功能描述:
     * @param:
     * @return:
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */

    int updMultiBaseDocument(@Param("list")List<BaseDocument> list);

   /**
    * 功能描述: 统计
    * @auther: fuyuan
    * @date: 2020/4/2 0002 15:11
    */
    int getStatistics(@Param(value = "weight") int weight);

    /**
     * 功能描述: 分类统计
     * @auther: fuyuan
     * @date: 2020/4/2 0002 15:37
     */
    List<BaseDocument> getCompareStatistics(BaseDocumentVO baseDocument);


    /**
     * 功能描述: 根据文档分类 统计数量
     * @auther: fuyuan
     * @date: 2020/4/28 0028 10:13
     */
    List<Map<String,Object>> getNumberOfDocumentsCount();

    /**
     * 功能描述: 根据文档分类 统计下载数量
     * @auther: fuyuan
     * @date: 2020/4/28 0028 10:13
     */
    List<Map<String,Object>> getDownloadCount();

    /**
     * 功能描述: 分时统计
     * @auther: fuyuan
     * @date: 2020/4/28 0028 15:02
     */

    List<Map<String,Object>> getStatisticsByTime ();
}
