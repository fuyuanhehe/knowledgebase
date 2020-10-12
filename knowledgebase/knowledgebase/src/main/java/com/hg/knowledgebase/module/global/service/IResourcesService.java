package com.hg.knowledgebase.module.global.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hg.knowledgebase.module.global.bean.FileInfoBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;

/**
 * 
 * @Description: 资源服务层
 * @Author: 况克冬
 * @CreateDate: 2019年7月18日下午1:41:47
 * @Version: 1.0.0
 */
public interface IResourcesService
{

    /**
     * 
     * @Description: 上传资源
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午1:44:21
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午1:44:21
     * @UpdateRemark: 修改内容
     * @param multipartFiles
     * @return List<FileInfo>
     * @Version: 1.0.0
     */
    List<FileInfoBean> uploadResources(List<MultipartFile> multipartFiles,
            boolean isUediter) throws GlobalException;

    /**
     * 
     * @Description: 查询资源文件 注意：调用方关闭输入流
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午1:46:48
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午1:46:48
     * @UpdateRemark: 修改内容
     * @param pathName
     * @return InputStream
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    InputStream findResources(String pathName) throws GlobalException;

    /**
     * 
     * @Description: 查询资源文件并打包 注意：调用方关闭输入流
     * @Author: 况克冬
     * @CreateDate: 2019年10月31日下午4:10:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年10月31日下午4:10:08
     * @UpdateRemark: 修改内容
     * @param filePaths
     * @return InputStream
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    InputStream findResourcesAndPack(String filePaths) throws GlobalException;
}
