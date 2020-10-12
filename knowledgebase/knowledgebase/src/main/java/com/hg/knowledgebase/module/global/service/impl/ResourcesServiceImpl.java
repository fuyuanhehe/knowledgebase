package com.hg.knowledgebase.module.global.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.FileInfoBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.global.service.IResourcesService;
import com.hg.knowledgebase.util.FileUtil;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 资源业务实现
 * @Author: 况克冬
 * @CreateDate: 2019年7月18日下午1:49:20
 * @Version: 1.0.0
 */
@Service("resourcesServiceImpl")
public class ResourcesServiceImpl implements IResourcesService
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
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public List<FileInfoBean> uploadResources(
            List<MultipartFile> multipartFiles, boolean isUediter)
            throws GlobalException
    {
        List<FileInfoBean> fileInfoList = null;
        if (multipartFiles == null || multipartFiles.size() == 0)
        {
            throw new GlobalException("上传资源文件为空，入参【multipartFiles: {}】",
                    multipartFiles);
        } else
        {
            fileInfoList = new ArrayList<>(multipartFiles.size());
            String url = null;
            for (MultipartFile multipartFile : multipartFiles)
            {
                try
                {
                    String fileName = FileUtil
                            .getFileName(multipartFile.getOriginalFilename());
                    // 为了避免文件冲突，添加时间戳
                    fileName = StringUtil.getTimeStamp()
                            + GlobalConstant.HORIZONTAL_SPLITTER + fileName;
                    // 保存文件到本地磁盘
                    String filePath = FileUtil.saveToTargetPath(
                            multipartFile.getInputStream(),
                            isUediter ? FileUtil.UPLOAD_UEDITER_PATH
                                    : FileUtil.UPLOAD_PATH,
                            fileName);
                    // 截取并组合网页可直接访问的地址
                    url = GlobalConstant.BACKSLASH + GlobalConstant.REQ_RES_PATH
                            + filePath
                                    .substring(filePath.indexOf(FileUtil.UPLOAD)
                                            + FileUtil.UPLOAD.length());
                    // 替换路径反斜杠为正斜杠
                    url = url.replace(File.separator, GlobalConstant.BACKSLASH);
                    // fileInfoList.add(new FileInfoBean(fileName, filePath,
                    // url));
                    fileInfoList.add(new FileInfoBean(
                            multipartFile.getOriginalFilename(), filePath,
                            url));
                } catch (IOException e)
                {
                    throw new GlobalException("上传资源异常：{}", e.getMessage());
                }
            }
        }
        return fileInfoList;
    }

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
    @Override
    public InputStream findResources(String pathName) throws GlobalException
    {
        InputStream in = null;
        if (StringUtils.isEmpty(pathName))
        {
            throw new GlobalException("查询资源文件为空，入参【pathName: {}】", pathName);
        } else
        {
            pathName = pathName.replace(GlobalConstant.BACKSLASH
                    + GlobalConstant.REQ_RES_PATH + GlobalConstant.BACKSLASH,
                    FileUtil.UPLOAD_PATH);
            File file = new File(pathName);
            if (file.exists() && file.isFile()) // 文件存在
            {
                try
                {
                    in = new FileInputStream(file);
                } catch (FileNotFoundException e)
                {
                    throw new GlobalException("查询资源文件异常：{}", e.getMessage());
                }
            }
        }
        return in;
    }

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
    @Override
    public InputStream findResourcesAndPack(String filePaths)
            throws GlobalException
    {
        InputStream in = null;
        if (StringUtils.isEmpty(filePaths))
        {
            throw new GlobalException("查询资源文件并打包为空，入参【filePaths: {}】",
                    filePaths);
        } else
        {
            // 单文件对象
            InputStream singIn = null;
            // 创建zip输出流对象
            ZipOutputStream zos = null;
            // 临时zip路径文件名
            String tempZipPathName = StringUtil.getTimeStamp() + ".zip";
            // 创建临时目录
            FileUtil.createFilePath(FileUtil.TEMP_FILE_PATH);
            // 创建临时文件对象
            File tempFile = new File(FileUtil.TEMP_FILE_PATH, tempZipPathName);
            try
            {
                zos = new ZipOutputStream(new FileOutputStream(tempFile));
                // 拆分多路径
                String[] pathNames = filePaths.split(GlobalConstant.SPLITTER);
                for (String pathName : pathNames)
                {
                    pathName = pathName.replace(
                            GlobalConstant.BACKSLASH
                                    + GlobalConstant.REQ_RES_PATH
                                    + GlobalConstant.BACKSLASH,
                            FileUtil.UPLOAD_PATH);
                    File file = new File(pathName);
                    // 如果文件存在，则执行文件压缩并输出
                    if (file.exists() && file.isFile())
                    {
                        singIn = new FileInputStream(file);
                        // 组装zip文件
                        if (singIn != null)
                        {
                            ZipEntry ze = new ZipEntry(file.getName());
                            zos.putNextEntry(ze);
                            int len = -1;
                            byte[] bytes = new byte[2048];
                            while ((len = singIn.read(bytes)) != -1)
                            {
                                zos.write(bytes, 0, len);
                            }
                            zos.closeEntry();
                            singIn.close();
                        }
                    }
                }
                zos.flush();
                in = new FileInputStream(tempFile);
            } catch (IOException e)
            {
                throw new GlobalException("查询资源文件并打包异常：{}", e.getMessage());
            } finally
            {
                if (singIn != null)
                {
                    try
                    {
                        singIn.close();
                    } catch (IOException e)
                    {
                        e.getMessage();
                    }
                }
                if (zos != null)
                {
                    try
                    {
                        zos.closeEntry();
                        zos.close();
                    } catch (IOException e)
                    {
                        e.getMessage();
                    }
                }
                if (tempFile != null)
                {
                    // 取出文件流后，删除该zip文件,如果临时文件没删除掉，会在定时任务中批量删除文件
                    tempFile.delete();
                }
            }
        }
        return in;
    }
}
