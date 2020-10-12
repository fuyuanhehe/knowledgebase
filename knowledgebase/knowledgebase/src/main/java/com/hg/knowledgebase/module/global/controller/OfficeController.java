package com.hg.knowledgebase.module.global.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.global.service.IOfficeService;

/**
 * 
 * @Description: office控制层
 * @Author: 况克冬
 * @CreateDate: 2020年3月7日上午11:30:38
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/global/office")
public class OfficeController
{

    @Autowired
    private IOfficeService officeService;

    private static Logger log = LoggerFactory.getLogger(OfficeController.class);

    /**
     * 
     * @Description: 获取office配置
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日下午3:19:49
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月11日下午3:19:49
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getConfig")
    @SystemLog(value = "获取office配置")
    public ResponseResultBean getConfig(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String fileUrlPath = jsonObj.getString(GlobalConstant.FILE_NAME);
            String mode = jsonObj.getString(GlobalConstant.MODE);
            OfficeConfigBean config = officeService
                    .findOfficeConfig(fileUrlPath, mode);
            boolean flg = config != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, config);
        } catch (Exception e)
        {
            result = new ResponseResultBean(false, e.getMessage());
            log.error("---获取office配置异常---", e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 编辑文档
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午2:33:06
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午2:33:06
     * @UpdateRemark: 修改内容
     * @param reqStr
     * @return ResponseResultBean
     * @Version: 1.0.0
     */
    @RequestMapping(value = "/editDoc")
    @SystemLog(value = "编辑文档")
    public Object editDoc(
            @RequestParam(value = GlobalConstant.PATH, required = false) String fileUrlPath)
    {
        // 错误状态（注意这个值不能任意设置，因为是第三方的规范）
        int errorStatus = 1;
        try
        {
            boolean flg = officeService.editDoc(fileUrlPath);
            if (flg)
            {
                errorStatus = 0;
            }
        } catch (Exception e)
        {
            log.error("---编辑文档异常---", e.getMessage());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("error", errorStatus);
        return result;
    }
}
