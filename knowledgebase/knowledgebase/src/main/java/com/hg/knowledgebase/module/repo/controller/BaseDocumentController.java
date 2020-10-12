package com.hg.knowledgebase.module.repo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.repo.entity.BaseDocument;
import com.hg.knowledgebase.module.repo.entity.BaseStatistics;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;
import com.hg.knowledgebase.module.repo.service.BaseDocumentService;
import com.hg.knowledgebase.module.repo.service.BaseStatisticsService;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 文档管理控制层
 *
 * @auther: fuyuan
 * @date: 2020/3/16 0016 14:43
 */
@RestController
@RequestMapping(value = "/api/repo/baseDocument")
public class BaseDocumentController {
    private static Logger log = LoggerFactory.getLogger(BaseDocumentController.class);

    @Resource
    private BaseDocumentService baseDocumentService;

    @Autowired
    private IDeptService deptService;

    @Resource
    private BaseStatisticsService baseStatisticsService;

    /**
     * 功能描述: add 文档管理
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/saveBaseDocument")
    @SystemLog(value = "新增文档管理")
    public ResponseResultBean savebaseDocument(@RequestBody BaseDocument baseDocument) {
        ResponseResultBean result = null;
        try {
            Dept dept = deptService.findCurrentDept();
            if (dept != null) {
                baseDocument.setDeptId(dept.getId());
            }

            String ids = baseDocumentService.addBaseDocument(baseDocument);
            result = new ResponseResultBean(GlobalConstant.TRUE, GlobalConstant.SUCCESS, ids);
        } catch (Exception e) {
            log.error("---添加文档管理异常---", e);
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 修改 文档管理
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/modifyBaseDocument")
    @SystemLog(value = "修改文档管理")
    public ResponseResultBean modifybaseDocument(@RequestBody BaseDocument baseDocument) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {
           /* BigDecimalConverter bd = new BigDecimalConverter(BigDecimal.ZERO);
           ConvertUtils.register(bd, java.math.BigDecimal.class);

            ConvertUtils.register(new DateConverter(null), java.util.Date.class);

            map.put("createTime",null);
            map.put("updateTime",null);
            BaseDocument base = new BaseDocument();
            BeanUtils.populate(base,map);
*/
            flg = baseDocumentService.modifyBaseDocument(baseDocument);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---修改文档管理异常---", e.getMessage());
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  文档管理删除
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/removeBaseDocument")
    @SystemLog(value = "删除文档管理")
    public ResponseResultBean removebaseDocumentById(@RequestBody BaseDocument baseDocument) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {
            flg = baseDocumentService.removeBaseDocument(baseDocument);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            log.error("---根据id删除文档管理异常---", e);
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  多个文档管理删除
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/removeBaseDocumentList")
    @SystemLog(value = "多个文档管理删除")
    public ResponseResultBean removeBaseDocumentList(@RequestBody String ids) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {
            List<String> id = JSONArray.parseArray(ids, String.class);

            flg = baseDocumentService.delBaseDocumentList(id);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            log.error("---多个文档管理删除异常---", e);
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }


    /**
     * 功能描述:  文档管理分页
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getBaseDocumentPage")
    @SystemLog(value = "文档管理分页")
    public ResponseResultBean getbaseDocumentPage(@RequestBody String reqStr) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {
            BaseDocument baseDocument = JSONObject.parseObject(reqStr, BaseDocument.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);

            PageInfo<BaseDocument> pageInfo = baseDocumentService.findBaseDocumentPage(baseDocument, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, pageInfo);
        } catch (Exception e) {
            log.error("---获取分页文档管理异常---", e);
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  文档管理所有查询
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getAllBaseDocument")
    @SystemLog(value = "文档管理所有查询")
    public ResponseResultBean getAllBaseDocument(@RequestBody BaseDocument baseDocument) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {

            List<BaseDocument> data = baseDocumentService.findAllBaseDocument(baseDocument);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            log.error("---文档管理所有查询异常---", e);
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述:  文档管理单个查询
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getBaseDocument")
    @SystemLog(value = "文档管理单个查询")
    public ResponseResultBean getbaseDocumentPage(@RequestBody BaseDocument baseDocument) {
        boolean flg = false;
        ResponseResultBean result = null;
        try {

            BaseDocumentVO data = baseDocumentService.getBaseDocument(baseDocument);
            Dept dept = deptService.findCurrentDept();


            if (dept != null) {
                data.setDepName(dept.getName());
            }

            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            log.error("---获取单个文档管理异常---", e);
            result = new ResponseResultBean(flg, GlobalConstant.FAIL);
        }
        return result;
    }


    /**
     * 功能描述:  获取选择的所有文件 下载
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getMultiFile")
    @SystemLog(value = "获取选择的所有文件")
    public ResponseResultBean getMultiFile(@RequestBody String ids) {
        if (StringUtils.isBlank(ids)) {
            return new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }

        List<String> id = JSONArray.parseArray(ids, String.class);
        List<String> result = baseDocumentService.getMultiFile(id);

        return new ResponseResultBean(GlobalConstant.TRUE, GlobalConstant.SUCCESS, result);
    }

    /**
     * 功能描述: 添加多个文档管理
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/addMultiBaseDocument")
    @SystemLog(value = "添加多个文档管理")
    public ResponseResultBean addMultiBaseDocument(@RequestBody String list) {
        ResponseResultBean result = null;
        try {
            List<BaseDocument> data = JSONArray.parseArray(list, BaseDocument.class);
            boolean flg = baseDocumentService.addMultiBaseDocument(data);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            log.error("---添加多个文档管理异常---", e);
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 修改多个文档管理
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/updMultiBaseDocument")
    @SystemLog(value = "修改多个文档管理")
    public ResponseResultBean updMultiBaseDocument(@RequestBody String list) {
        ResponseResultBean result = null;
        try {
            List<BaseDocument> data = JSONArray.parseArray(list, BaseDocument.class);

            boolean flg = baseDocumentService.updMultiBaseDocument(data);
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            log.error("---修改多个文档管理异常---", e);
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: * 功能描述:  公司文档；1：部门文档；2：个人文档 3:知识文档 4:动态图 统计
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getStatistics")
    @SystemLog(value = "统计文档管理")
    public ResponseResultBean getStatistics() {
        ResponseResultBean result = null;
        boolean flg;
        try {

            HashMap<String, Object> data = baseDocumentService.getStatistics();
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("---统计文档管理异常---", e);
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    /**
     * 功能描述: 分类查询文档
     *
     * @auther: fuyuan
     * @date: 2020/3/16 0016 14:49
     */
    @RequestMapping(value = "/getCompareStatistics")
    @SystemLog(value = "分类查询文档")
    public ResponseResultBean getCompareStatistics(@RequestBody String reqStr) {
        ResponseResultBean result = null;
        boolean flg;
        try {
            JSONObject json = JSONObject.parseObject(reqStr);
            BaseDocumentVO baseDocument = JSONObject.parseObject(reqStr, BaseDocumentVO.class);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);

            PageInfo<BaseDocument> data = baseDocumentService.getCompareStatistics(baseDocument, pageNum, pageSize);
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (RuntimeException e) {
            log.error("---分类查询文档---", e.getMessage());
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/getNumberOfDocumentsCount")
    @SystemLog(value = "根据文档分类统计数量")
    public ResponseResultBean getNumberOfDocumentsCount() {
        ResponseResultBean result = null;
        boolean flg;
        try {

            List<Map<String, Object>> data = baseDocumentService.getNumberOfDocumentsCount();
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            log.error("---根据文档分类 统计数量---", e);
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/getDownloadCount")
    @SystemLog(value = "根据文档分类 统计下载数量")
    public ResponseResultBean getDownloadCount() {
        ResponseResultBean result = null;
        boolean flg;
        try {

            List<Map<String, Object>> data = baseDocumentService.getDownloadCount();
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            log.error("---根据文档分类 统计下载数量---", e.getMessage());
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }


    @RequestMapping(value = "/getStatisticsByTime")
    @SystemLog(value = "分时统计文档")
    public ResponseResultBean getStatisticsByTime() {
        ResponseResultBean result = null;
        boolean flg;
        try {

            List<Map<String, Object>> data = baseDocumentService.getStatisticsByTime();
            flg = data != null;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, data);
        } catch (Exception e) {
            log.error("---分时统计文档---", e.getMessage());
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }


    /**
     * 功能描述: 新增统计
     *
     * @auther: fuyuan
     * @date: 2020/4/30 0030 10:47
     */
    @RequestMapping(value = "/addBaseStatistics")
    @SystemLog(value = "新增统计数据")
    public ResponseResultBean addBaseStatistics(@RequestBody BaseStatistics baseStatistics) {
        ResponseResultBean result = null;
        boolean flg;
        try {

            int data = baseStatisticsService.addBaseStatistics(baseStatistics);
            flg = data > 0;
            result = new ResponseResultBean(flg, flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e) {
            log.error("---新增统计---", e.getMessage());
            result = new ResponseResultBean(GlobalConstant.FALSE, GlobalConstant.FAIL);
        }
        return result;
    }

}
