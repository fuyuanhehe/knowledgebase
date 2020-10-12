package com.hg.knowledgebase.module.repo.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.bean.TreeBean;
import com.hg.knowledgebase.module.repo.entity.BaseDocument;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述: 文档管理service
 * @auther: fuyuan
 * @date: 2020/3/16 0016 11:03
 */
public interface BaseDocumentService
{
    /**
     * 功能描述: 文档管理分页
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    PageInfo<BaseDocument> findBaseDocumentPage(BaseDocument baseDocument, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 查询所有文档管理
     * @auther: fuyuan
     * @date: 2020/3/26 0026 13:44
     */
    List<BaseDocument> findAllBaseDocument(BaseDocument baseDocument);

    /**
     * 功能描述: 文档管理新增
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    String addBaseDocument(BaseDocument baseDocument);
    /**
     * 功能描述: 文档管理修改
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    boolean modifyBaseDocument(BaseDocument baseDocument);
    /**
     * 功能描述: 文档管理删除
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    boolean removeBaseDocument(BaseDocument baseDocument);

    /**
     * 功能描述: 文档管理树查询
     * @auther: fuyuan
     * @date: 2020/3/16 0 016 11:43
     */
   List<TreeBean> getTreeBaseDocument(String id) throws InvocationTargetException, NoSuchFieldException, InstantiationException, IllegalAccessException;


   /**
    * 功能描述: 新增文件夹
    * @auther: fuyuan
    * @date: 2020/3/16 0016 16:03
    */
   boolean addFolder(BaseDocument baseDocument);

 /**
  * 功能描述: 查询单个
  * @auther: fuyuan
  * @date: 2020/3/19 0019 9:24
  */
    BaseDocumentVO getBaseDocument(BaseDocument baseDocument);


    /**
     * 功能描述: 批量删除
     * @auther: fuyuan
     * @date: 2020/3/26 0026 13:47
     */
    boolean delBaseDocumentList(List<String> list);

    /**
     * 功能描述: 查询不重名名称
     * @auther: fuyuan
     * @date: 2020/3/26 0026 14:07
     */
    String getName();


    /**
     * 功能描述: 下载多个文件
     * @auther: fuyuan
     * @date: 2020/3/27 0027 14:09
     */
    List<String> getMultiFile( List<String> ids);

    /**
     * 功能描述: 添加多个文档
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */
    boolean addMultiBaseDocument(List<BaseDocument> list);
    /**
     * 功能描述:批量修改
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */

    boolean updMultiBaseDocument(List<BaseDocument> list);

    /**
     * 功能描述: 统计
     * @auther: fuyuan
     * @date: 2020/4/2 0002 15:11
     */
    HashMap<String,Object> getStatistics();


    /**
     * 功能描述: 分类统计
     * @auther: fuyuan
     * @date: 2020/4/2 0002 15:37
     */
    PageInfo<BaseDocument> getCompareStatistics(BaseDocumentVO baseDocument, Integer pageNum, Integer pageSize);


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

    List<Map<String,Object>> getStatisticsByTime();
}
