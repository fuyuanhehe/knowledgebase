package com.hg.knowledgebase.module.repo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.global.bean.TreeBean;
import com.hg.knowledgebase.module.repo.entity.BaseDocument;
import com.hg.knowledgebase.module.repo.entity.BaseDocumentCategory;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import com.hg.knowledgebase.module.repo.mapper.BaseDocumentCategoryMapper;
import com.hg.knowledgebase.module.repo.mapper.BaseDocumentMapper;
import com.hg.knowledgebase.module.repo.service.BaseDocumentService;
import com.hg.knowledgebase.util.FileUtil;
import com.hg.knowledgebase.util.StringUtil;
import com.hg.knowledgebase.util.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述: 文档管理impl
 *
 * @auther: fuyuan
 * @date: 2020/3/16 0016 11:44
 */
@Service
public class BaseDocumentServiceImpl implements BaseDocumentService
{
    private static Logger log = LoggerFactory.getLogger(BaseDocumentServiceImpl.class);

    @Resource
    private BaseDocumentMapper baseDocumentMapper;

    @Resource
    private BaseDocumentCategoryMapper baseDocumentCategoryMapper;
    /**
     * 功能描述: 文档管理分页
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    @Override
    public PageInfo<BaseDocument> findBaseDocumentPage(BaseDocument baseDocument, Integer pageNum, Integer pageSize)
    {
        PageInfo<BaseDocument> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            log.error("---查询管理分页信息条数不能为空，入参【页码：{}，条数：{}】---", pageNum, pageSize);
            return pageInfo;
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<BaseDocument> userVOList = baseDocumentMapper.selectBaseDocumentPage(baseDocument);
            pageInfo = new PageInfo<>(userVOList);
        } finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;

    }

    @Override
    public List<BaseDocument> findAllBaseDocument(BaseDocument baseDocument)
    {
        return baseDocumentMapper.selectBaseDocumentPage(baseDocument);
    }

    /**
     * 功能描述: 文档管理新增
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    @Override
    @Transactional
    public String addBaseDocument(BaseDocument baseDocument)
    {
        if (baseDocument == null)
        {
            return null;
        }

        baseDocument.setId(StringUtil.getUUID());
        baseDocument.setCreateTime(new Date());
        baseDocument.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        if (StringUtils.isBlank(baseDocument.getName()))
        {
            baseDocument.setName(getName());
        }
        if(StringUtils.isBlank(baseDocument.getReserve3())){
            baseDocument.setReserve3("1");
        }

        baseDocumentMapper.insertSelective(baseDocument);

        return baseDocument.getId();
    }

    /**
     * 功能描述: 文档管理修改
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    @Override
    @Transactional
    public boolean modifyBaseDocument(BaseDocument baseDocument)
    {
        if (baseDocument == null)
        {
            return false;
        }
        baseDocument.setUpdateTime(new Date());
        baseDocument.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());

        int result = baseDocumentMapper.updateByPrimaryKeySelective(baseDocument);

        return result > 0;
    }

    /**
     * 功能描述: 文档管理删除
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    @Override
    @Transactional
    public boolean removeBaseDocument(BaseDocument baseDocument)
    {
        if (baseDocument == null)
        {
            return false;
        }
        int result = baseDocumentMapper.deleteByPrimaryKey(baseDocument.getId());

        log.debug(baseDocument.getUrl() + "--------------");
        if (StringUtils.isNotBlank(baseDocument.getUrl()))
        {
            FileUtil.DeleteFolder(baseDocument.getUrl());
        }

        return result > 0;
    }

    /**
     * 功能描述: 文档管理树查询
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 11:43
     */
    @Override
    public List<TreeBean> getTreeBaseDocument(String id)
            throws InvocationTargetException, NoSuchFieldException, InstantiationException, IllegalAccessException
    {
        List<BaseDocument> data = baseDocumentMapper.getTreeBaseDocument(id);

        List<TreeBean> treeList = TreeUtil.composeTreeExpand(data);

        return treeList;
    }

    /**
     * 功能描述: 新建文件夹
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 16:01
     */
    public boolean addFolder(BaseDocument baseDocument)
    {
      /*  if (baseDocument == null)
        {
            return false;
        }
        baseDocument.setCreateTime(new Date());
        baseDocument.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        File file = new File(baseDocument.getUrl() + baseDocument.getName());
        file.mkdir();
        int result = baseDocumentMapper.insertSelective(baseDocument);*/
        return  false;
    }

    @Override
    public BaseDocumentVO getBaseDocument(BaseDocument baseDocument)
    {
        if (baseDocument == null)
        {
            return null;
        }
        BaseDocumentVO data = baseDocumentMapper.selectByPrimaryKey(baseDocument.getId());

        BaseDocumentCategory base = baseDocumentCategoryMapper.selectByPrimaryKey(data.getCategoryId());
           if(base != null){
               data.setCategoryName(base.getName());
           }

        return data;
    }

    @Override
    @Transactional
    public boolean delBaseDocumentList(List<String> list)
    {
        if (list == null || list.size() <= 0)
        {
            return false;
        }
        return baseDocumentMapper.delBaseDocumentList(list) > 0;
    }

    /**
     * 功能描述: 获取文档名称下一个
     *
     * @auther: fuyuan
     * @date: 2020/3/26 0026 14:43
     */
    @Override
    public String getName()
    {
        List<String> data = baseDocumentMapper.getName();

        Integer mark = 0;
        Integer sign = 0;
        if (data == null || data.size() == 0)
        {
            return "新建文件夹1";
        }
        for (String num : data)
        {

            mark = Integer.valueOf(num.substring(5));

            if (mark > sign)
            { // 循环比较
                sign = mark;
            }
        }
        sign = sign + 1;
        return "新建文件夹" + sign;
    }

    /**
     * 功能描述: 查询多个文件的url
     *
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:02
     */
    @Override
    public synchronized List<String> getMultiFile(List<String> ids)
    {
        temporaryId = new LinkedList<>();
        BaseDocument data = null;
        for (String id : ids) {
            data = baseDocumentMapper.selectByPrimaryKey(id);
             if(data.getType() == 1 && StringUtils.isNotBlank(data.getUrl())){
                 temporaryId.add(data.getUrl());
             }
        }


        getFileUrl(ids);
        return temporaryId;

    }

    /**
     * 功能描述: 添加多个文档
     *
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */
    @Override
    @Transactional
    public boolean addMultiBaseDocument(List<BaseDocument> list)
    {
        if (list == null || list.isEmpty())
        {
            return false;
        }
        Date data = new Date();
        String name = SecurityUtils.getCurrentUserLogin().get();
        // String baseName = getName();
        for (BaseDocument base : list)
        { // 循环插入id data userName
            base.setId(StringUtil.getUUID());
            base.setCreateTime(data);
            base.setCreateUser(name);
            if(StringUtils.isBlank(base.getReserve3())){
                base.setReserve3("1");
            }
            if (StringUtils.isBlank(base.getName()))
            {
                base.setName(getName());
            }
        }

        return baseDocumentMapper.addMultiBaseDocument(list) > 0;
    }

    /**
     * 功能描述:批量修改
     *
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:01
     */
    @Override
    @Transactional
    public boolean updMultiBaseDocument(List<BaseDocument> list)
    {
        if (list == null || list.isEmpty())
        {
            return false;
        }

        String name = SecurityUtils.getCurrentUserLogin().get();
        Date data = new Date();

        for (BaseDocument base : list)
        {
            base.setUpdateTime(data);
            base.setUpdateUser(name);
        }
        return baseDocumentMapper.updMultiBaseDocument(list) > 0;
    }
    /**
     * 功能描述:  公司文档；1：部门文档；2：个人文档 3:知识文档 4:动态图 统计
     * @auther: fuyuan
     * @date: 2020/4/2 0002 15:17
     */
    @Override
    public HashMap<String, Object> getStatistics( ) {
         int number = baseDocumentMapper.getStatistics(0);
        int numberTwo = baseDocumentMapper.getStatistics(1);
        int numberThrre = baseDocumentMapper.getStatistics(2);
        int numberFour = baseDocumentMapper.getStatistics(3);
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("companyDocuments",number);
        map.put("departmentalDocuments",numberTwo);
        map.put("personalDocuments",numberThrre);
        map.put("knowledgeDocuments",numberFour);
        //   * 文档类型（0：公司文档；1：部门文档；2：个人文档 3:知识文档 4:动态图）
        return map;
    }

    /**
     * 功能描述: 分类查询文档
     * @auther: fuyuan
     * @date: 2020/4/2 0002 15:37
     */
    @Override
    public PageInfo<BaseDocument> getCompareStatistics(BaseDocumentVO baseDocument, Integer pageNum, Integer pageSize) {
        PageInfo<BaseDocument> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            log.error("---查询管理分页信息条数不能为空，入参【页码：{}，条数：{}】---", pageNum, pageSize);
            return pageInfo;
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<BaseDocument> userVOList = baseDocumentMapper.getCompareStatistics(baseDocument);
            pageInfo = new PageInfo<>(userVOList);
        } finally
        {
            PageHelper.clearPage();
        }

        return pageInfo;
    }


    private List<String> temporaryId = null;

    /**
     * 功能描述: 查询所有下级文档
     *
     * @auther: fuyuan
     * @date: 2020/3/27 0027 15:03
     */
    public void getFileUrl(List<String> ids)
    {
        List<String> id = new LinkedList<>();
        List<BaseDocument> data = baseDocumentMapper.getFileUrl(ids);

        if (data != null && data.size() > 0)
        {

            for (BaseDocument base : data)
            {
                id.add(base.getId());
                 // 判断url 类型
                if (StringUtils.isNotBlank(base.getUrl()) && base.getType() == 1)
                {
                    temporaryId.add(base.getUrl());
                }

            }
            getFileUrl(id);
        }

    }
    /**
     * 功能描述: 根据文档分类 统计数量
     * @auther: fuyuan
     * @date: 2020/4/28 0028 10:13
     */
    @Override
    public List<Map<String,Object>> getNumberOfDocumentsCount() {
        return baseDocumentMapper.getNumberOfDocumentsCount();
    }
    /**
     * 功能描述: 根据文档分类 统计下载数量
     * @auther: fuyuan
     * @date: 2020/4/28 0028 10:13
     */
    @Override
    public List<Map<String,Object>> getDownloadCount() {
        return baseDocumentMapper.getDownloadCount();
    }

    @Override
    public List<Map<String, Object>> getStatisticsByTime() {
      //  String datas = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
/*
        List<HashMap<String,Object>> mapList = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();

        HashMap<String, Object> parmMap = new HashMap<>();
        HashMap<String, Object> map = null;

        String year = calendar.get(Calendar.YEAR)+"-";
        String month = new SimpleDateFormat("MM").format(new Date());
        int day = calendar.get(Calendar.DATE);

        for (int i = 0; i < 7; i++) {
            parmMap.put("beginTime", year+month+"-"+ (day-i-1));
            parmMap.put("endTime",year+month+"-"+(day-i));
           map = baseDocumentMapper.getStatisticsByTime(parmMap);
           if(map == null){
               map = new HashMap<>();
               map.put("sum",0);
           }
            map.put("data",month+"/"+(day-i));
          mapList.add(map);
        }
*/
        return baseDocumentMapper.getStatisticsByTime();
    }



}
