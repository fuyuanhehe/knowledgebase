package com.hg.knowledgebase.module.global.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean.Document;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean.EditorConfig;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean.EditorConfig.Customization;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean.EditorConfig.Customization.Customer;
import com.hg.knowledgebase.module.global.bean.OfficeConfigBean.User;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.global.service.IOfficeService;
import com.hg.knowledgebase.util.FileUtil;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: office服务实现层
 * @Author: 况克冬
 * @CreateDate: 2020年3月11日下午2:13:08
 * @Version: 1.0.0
 */
@Service
public class OfficeServiceImpl implements IOfficeService
{

    /**
     * 转换url地址
     */
    @Value("${files.docservice.url.converter}")
    private String converterUrl;

    /**
     * 模板url地址
     */
    @Value("${files.docservice.url.tempstorage}")
    private String tempstorageUrl;

    /**
     * api url地址
     */
    @Value("${files.docservice.url.api}")
    private String apiUrl;

    /**
     * url地址
     */
    @Value("${files.docservice.url.preloader}")
    private String preloaderUrl;

    /**
     * port端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * ip
     */
    @Value("${custom.local.ip}")
    private String ip;

    /**
     * onlyoffice address
     */
    @Value("${files.docservice.customer.address}")
    private String address;

    /**
     * onlyoffice info
     */
    @Value("${files.docservice.customer.info}")
    private String info;

    /**
     * onlyoffice logo
     */
    @Value("${files.docservice.customer.logo}")
    private String logo;

    /**
     * onlyoffice mail
     */
    @Value("${files.docservice.customer.mail}")
    private String mail;

    /**
     * onlyoffice name
     */
    @Value("${files.docservice.customer.name}")
    private String name;

    /**
     * onlyoffice www
     */
    @Value("${files.docservice.customer.www}")
    private String www;

    /**
     * 文件回调地址模板（注意此地址是当前系统资源接口的预览）
     */
    private static final String FILE_CALLBACK_URL_FORMAT = "http://%s:%s/api/global/office/editDoc?path=%s";

    /**
     * url模板常量
     */
    private static final String URL_FORMAT = "http://%s:%s%s";

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
    @Override
    public OfficeConfigBean findOfficeConfig(String fileUrlPath, String mode)
            throws GlobalException
    {
        OfficeConfigBean officeConfigBean = null;
        if (StringUtils.isEmpty(fileUrlPath))
        {
            throw new GlobalException("获取office配置条件不能为空，入参【文件地址：{}】",
                    fileUrlPath);
        }
        String filePath = FileUtil.getFileAddrPath(fileUrlPath);
        // 检测文件是否存在
        if (!FileUtil.cheakFileExist(filePath))
        {
            throw new GlobalException("获取office配置文件不存在，入参【文件地址：{}】", filePath);
        }
        /**
         * 组合文件配置对象
         */
        officeConfigBean = new OfficeConfigBean();
        String fileName = FileUtil.getFileName(fileUrlPath);
        // 组合文档对象
        Document document = officeConfigBean.new Document();
        document.setUrl(String.format(URL_FORMAT, ip, port, fileUrlPath));
        document.setFileType(FileUtil.getFileType(fileUrlPath));
        document.setTitle(fileName);
        /*
         * document.setKey(StringUtil.GenerateRevisionId(ip + "/" + fileName));
         */
        document.setKey(StringUtil.getUUID());
        // 组合编辑配置
        EditorConfig editorConfig = officeConfigBean.new EditorConfig();
        editorConfig.setCallbackUrl(
                String.format(FILE_CALLBACK_URL_FORMAT, ip, port, fileUrlPath));
        editorConfig.setMode("edit".equals(mode) ? "edit" : "view");
        Customization cust = editorConfig.new Customization();
        // 设置客服服务
        Customer customer = cust.new Customer();
        customer.setAddress(address);
        customer.setInfo(info);
        customer.setMail(mail);
        customer.setName(name);
        customer.setWww(www);
        customer.setLogo(logo);
        customer.setAddress("123");
        customer.setInfo("1234");
        customer.setMail("1235");
        customer.setName("1236");
        customer.setWww("1237");
        customer.setLogo(logo);
        cust.setCustomer(customer);
        /*
         * Goback goback = cust.new Goback();
         * goback.setUrl(String.format(FILE_CALLBACK_URL_FORMAT, ip, port, ""));
         * cust.setGoback(goback); cust.setHelp(false);
         */
        editorConfig.setCustomization(cust);
        // 组合用户对象
        User user = officeConfigBean.new User();
        user.setId(ip);
        user.setName("张三");
        // 设置文件配置对象
        officeConfigBean.setApiUrl(apiUrl);
        officeConfigBean.setDocumentType(FileUtil.getOfficeFileType(fileUrlPath)
                .toString().toLowerCase());
        officeConfigBean.setDocument(document);
        officeConfigBean.setEditorConfig(editorConfig);
        officeConfigBean.setUser(user);
        return officeConfigBean;
    }

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
     * @throws GlobalException 业务异常时候出现的异常
     * @Version: 1.0.0
     */
    @Override
    public boolean editDoc(String fileUrlPath) throws GlobalException
    {
        InputStream in = null;
        HttpURLConnection connection = null;
        try
        {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            in = servletRequestAttributes.getRequest().getInputStream();
            // 获取office服务器响应信息
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            String body = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            // 如果没有信息则返回正常，不做之后的处理
            if (StringUtils.isEmpty(body))
            {
                return true;
            }
            JSONObject jsonObj = JSONObject.parseObject(body);
            int status = jsonObj.getInteger(GlobalConstant.STATUS);
            switch (status)
            {
            case 1: // 文件正在编辑中
            case 4: // 文件已关闭，没有任何更改
                // 备用以后扩展操作
                break;
            case 2: // 文件已关闭，已更改，已保存
                if (StringUtils.isEmpty(fileUrlPath))
                {
                    throw new GlobalException("编辑文档参数为空，入参【文件地址：{}】",
                            fileUrlPath);
                }
                // 获取文件实际地址
                String fileAddrPath = FileUtil.getFileAddrPath(fileUrlPath);
                File file = new File(fileAddrPath);
                String downloadUri = jsonObj.getString(GlobalConstant.URL);
                if (!file.isFile() || StringUtils.isEmpty(downloadUri))
                {
                    throw new GlobalException("更新文档文件不存在，入参【文件地址：{}】",
                            fileAddrPath);
                }
                // 获取office服务器文件信息
                URL url = new URL(downloadUri);
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                FileUtil.saveToTargetPath(stream, fileAddrPath);
                break;
            case 3: // 文件已关闭，保存失败
                // 备用以后扩展操作
                break;
            }
        } catch (Exception e)
        {
            throw new GlobalException("编辑文档异常：{}", e.getMessage());
        } finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
        return true;
    }
}
