package com.hg.knowledgebase.module.global.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.FileInfoBean;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.global.service.IResourcesService;
import com.hg.knowledgebase.util.FileUtil;

/**
 * 
 * @Description: 资源控制层
 * @Author: 况克冬
 * @CreateDate: 2019年7月18日下午2:05:22
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/global/resources")
public class ResourcesController
{

    private static Logger log = LoggerFactory
            .getLogger(ResourcesController.class);

    @Autowired
    private IResourcesService resourcesService;

    /**
     * 
     * @Description: 文件上传
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午2:20:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午2:20:33
     * @UpdateRemark: 修改内容
     * @param multipartFiles
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/upload")
    @SystemLog(value = "文件上传")
    public ResponseResultBean upload(
            @RequestParam(value = GlobalConstant.FILE_NAMES) List<MultipartFile> multipartFiles)
    {
        ResponseResultBean responseResult = null;
        try
        {
            List<FileInfoBean> fileInfoList = resourcesService
                    .uploadResources(multipartFiles, false);
            boolean flg = fileInfoList != null;
            responseResult = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    fileInfoList);
        } catch (Exception e)
        {
            log.error("---文件上传异常---", e.getMessage());
            responseResult = new ResponseResultBean(false, GlobalConstant.FAIL);
        }
        return responseResult;
    }

    /**
     * 
     * @Description: 上传富文本编辑器文件
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午2:20:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午2:20:33
     * @UpdateRemark: 修改内容
     * @param multipartFiles
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/uploadUediter")
    @SystemLog(value = "上传富文本编辑器文件")
    public JSONObject uploadUediter(
            @RequestParam("upfile") List<MultipartFile> multipartFiles)
    {
        JSONObject jsobject = new JSONObject();
        try
        {
            List<FileInfoBean> fileInfoList = resourcesService
                    .uploadResources(multipartFiles, true);
            boolean flg = fileInfoList != null;
            jsobject.put("state", flg ? "SUCCESS" : "上传失败");
            jsobject.put("url",
                    flg ? /* SystemUtil.getServerPrefix() + */ fileInfoList
                            .get(0).getUrl() : "");
            jsobject.put("original", "");
        } catch (Exception e)
        {
            log.error("---文件上传异常---", e.getMessage());
        }
        return jsobject;
    }

    /**
     * 
     * @Description: 预览资源
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午2:23:10
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午2:23:10
     * @UpdateRemark: 修改内容
     * @param reqData
     *            void
     * @Version: 1.0.0
     */
    @RequestMapping(value = "/preview")
    @SystemLog(value = "预览资源")
    public void preview(@RequestParam(GlobalConstant.PATH) String filePath)
    {
        InputStream in = null;// 输入流
        BufferedInputStream bis = null;// 输入缓存流
        OutputStream out = null;// 输出流
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        try
        {
            in = resourcesService.findResources(filePath);
            if (in != null)
            {
                bis = new BufferedInputStream(in);
                out = response.getOutputStream();
                int i = -1;
                byte[] b = new byte[1024];
                while ((i = bis.read(b)) != -1)
                {
                    out.write(b, 0, i);
                }
                out.flush();
            } else
            {
                log.error("预览资源异常：文件不存在（{}）", filePath);
            }
        } catch (Exception e)
        {
            log.error("---下载资源异常---", e.getMessage());
        } finally
        {
            FileUtil.closeStream(in, bis, out, null);
        }
    }

    /**
     * 
     * @Description: 下载资源
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午2:23:10
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午2:23:10
     * @UpdateRemark: 修改内容
     * @param reqData
     *            void
     * @Version: 1.0.0
     */
    @GetMapping(value = "/download")
    @SystemLog(value = "下载资源")
    public void download(@RequestParam(GlobalConstant.PATH) String filePath)
    {
        InputStream in = null;// 输入流
        BufferedInputStream bis = null;// 输入缓存流
        OutputStream out = null;// 输出流
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        try
        {
            in = resourcesService.findResources(filePath);
            if (in != null)
            {
                bis = new BufferedInputStream(in);
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(
                                FileUtil.getFileName(filePath), "UTF-8"));
                out = response.getOutputStream();
                int i = -1;
                byte[] b = new byte[1024];
                while ((i = bis.read(b)) != -1)
                {
                    out.write(b, 0, i);
                }
                out.flush();
            } else
            {
                log.error("---下载资源异常：文件不存在（{}---", filePath);
            }
        } catch (Exception e)
        {
            log.error("---下载资源异常---", e.getMessage());
        } finally
        {
            FileUtil.closeStream(in, bis, out, null);
        }
    }

    /**
     * 
     * @Description: 下载打包资源
     * @Author: 况克冬
     * @CreateDate: 2019年7月18日下午2:23:10
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月18日下午2:23:10
     * @UpdateRemark: 修改内容
     * @param reqData
     *            void
     * @Version: 1.0.0
     */
    @GetMapping(value = "/downloadPack")
    @SystemLog(value = "下载打包资源")
    public void downloadPack(
            @RequestParam(GlobalConstant.PATHS) String filePaths)
    {
        InputStream in = null;// 输入流
        BufferedInputStream bis = null;// 输入缓存流
        OutputStream out = null;// 输出流
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        try
        {
            in = resourcesService.findResourcesAndPack(filePaths);
            if (in != null)
            {
                bis = new BufferedInputStream(in);
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(
                                FileUtil.getFileName("打包资源.zip"), "UTF-8"));
                out = response.getOutputStream();
                int i = -1;
                byte[] b = new byte[1024];
                while ((i = bis.read(b)) != -1)
                {
                    out.write(b, 0, i);
                }
                out.flush();
            } else
            {
                log.error("下载打包资源异常：文件不存在（{}）", filePaths);
            }
        } catch (Exception e)
        {
            log.error("---下载打包资源异常---", e.getMessage());
        } finally
        {
            FileUtil.closeStream(in, bis, out, null);
        }
    }
}
