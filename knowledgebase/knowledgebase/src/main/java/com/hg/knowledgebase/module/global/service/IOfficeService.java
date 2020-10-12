package com.hg.knowledgebase.module.global.service;

import com.hg.knowledgebase.module.global.bean.OfficeConfigBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: office服务层
 * @Author: 况克冬
 * @CreateDate: 2020年3月11日下午2:08:04
 * @Version: 1.0.0
 */
public interface IOfficeService
{

    /**
     * 
     * @Description: 获取office配置
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日下午2:09:05
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月11日下午2:09:05
     * @UpdateRemark: 修改内容
     * @param fileUrlPath
     *            文件地址
     * @param mode
     *            操作模式
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             OfficeConfigBean
     * @Version: 1.0.0
     */
    OfficeConfigBean findOfficeConfig(String fileUrlPath, String mode)
            throws GlobalException;

    /**
     * 
     * @Description: 编辑文档
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午2:43:55
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午2:43:55
     * @UpdateRemark: 修改内容
     * @param fileUrlPath
     * @return boolean
     * @Version: 1.0.0
     */
    boolean editDoc(String fileUrlPath) throws GlobalException;
}
