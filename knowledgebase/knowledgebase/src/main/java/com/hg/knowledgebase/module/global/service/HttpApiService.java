package com.hg.knowledgebase.module.global.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.GsonBuilder;
import com.hg.knowledgebase.customenum.ParamTypeEnum;

@Service
public class HttpApiService
{

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    private static final String ENCODER = "utf-8";

    /**
     * 
    * @Description: 获取Content-Type为application/json的请求头
    * @Author: 况克冬
    * @CreateDate: 2020年3月11日上午9:49:11
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月11日上午9:49:11
    * @UpdateRemark: 修改内容
    * @return
    * Map<String,String>
    * @Version: 1.0.0
     */
    public Map<String, String> getJsonHeaderMap() 
    {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }
    
    /**
     * 
    * @Description: 获取Content-Type为application/json的请求头
    * @Author: 况克冬
    * @CreateDate: 2020年3月11日上午9:50:32
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月11日上午9:50:32
    * @UpdateRemark: 修改内容
    * @param authorization 授权信息
    * @return
    * Map<String,String>
    * @Version: 1.0.0
     */
    public Map<String, String> getJsonHeaderMap(String authorization) 
    {
        Map<String, String> headerMap = getJsonHeaderMap();
        headerMap.put("Authorization", authorization);
        return headerMap;
    }
    
    /**
     * 
    * @Description: get请求
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午4:29:20
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午4:29:20
    * @UpdateRemark: 修改内容
    * @param url
    * @return
    * @throws ClientProtocolException http连接错误会出现异常
    * @throws IOException http获取信息错误会出现异常
    * String
    * @Version: 1.0.0
     */
    public String httpGet(String url) throws ClientProtocolException, IOException
    {
        if (StringUtils.isEmpty(url))
        {
            return null;
        }
        String msg = null;
        CloseableHttpResponse chr = null;
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        try
        {
            chr = httpClient.execute(get);
            if (chr.getStatusLine().getStatusCode() == 200)
            {
                msg = EntityUtils.toString(chr.getEntity(), ENCODER);
            }
        } finally
        {
            if (get != null)
            {
                get.abort();
            }
            if (chr != null)
            {
                EntityUtils.consume(chr.getEntity());
                chr.close();
            }
        }
        return msg;
    }

    /**
     * 
    * @Description: get请求
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午4:29:36
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午4:29:36
    * @UpdateRemark: 修改内容
    * @param url
    * @param param
    * @return
    * @throws ClientProtocolException http连接错误会出现异常
    * @throws IOException http获取信息错误会出现异常
    * @throws URISyntaxException url地址错误会出现异常
    * String
    * @Version: 1.0.0
     */
    public String httpGet(String url, Map<String, Object> param)
            throws ClientProtocolException, IOException, URISyntaxException
    {
        if (StringUtils.isEmpty(url))
        {
            return null;
        }
        URIBuilder urib = new URIBuilder(url);
        if (param != null && param.size() > 0)
        {
            for (Map.Entry<String, Object> entry : param.entrySet())
            {
                urib.addParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        return httpGet(urib.build().toString());
    }

    /**
     * httpClient Post请求
     * 
     * @param url
     *            请求地址
     * @param jsonParam
     *            普通json字符串参数
     * @return
     * @throws ClientProtocolException http连接错误会出现异常
    * @throws IOException http获取信息错误会出现异常
     */
    public String httpPost(String url, String jsonParam) throws ClientProtocolException, IOException
    {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(jsonParam))
        {
            return null;
        }
        String msg = null;
        CloseableHttpResponse chr = null;
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        StringEntity se = new StringEntity(jsonParam, ENCODER);
        post.setEntity(se);
        try
        {
            chr = httpClient.execute(post);
            if (chr.getStatusLine().getStatusCode() == 200)
            {
                msg = EntityUtils.toString(chr.getEntity(), ENCODER);
            }
        } finally
        {
            if (post != null)
            {
                post.abort();
            }
            if (chr != null)
            {
                EntityUtils.consume(chr.getEntity());
                chr.close();
            }
        }
        return msg;
    }

    /**
     * httpClient Post请求
     * 
     * @param url
     *            请求地址
     * @param multipartMap
     *            文件参数
     * @return
     * @throws ClientProtocolException http连接错误会出现异常
    * @throws IOException http获取信息错误会出现异常
     */
    public String httpPost(String url, Map<String, ContentBody> multipartMap)
            throws ClientProtocolException, IOException
    {
        if (StringUtils.isEmpty(url) || multipartMap == null || multipartMap.size() == 0)
        {
            return null;
        }
        String msg = null;
        CloseableHttpResponse chr = null;
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        for (Map.Entry<String, ContentBody> entry : multipartMap.entrySet())
        {
            meb.addPart(entry.getKey(), entry.getValue());
        }
        post.setEntity(meb.build());
        try
        {
            chr = httpClient.execute(post);
            if (chr.getStatusLine().getStatusCode() == 200)
            {
                msg = EntityUtils.toString(chr.getEntity(), ENCODER);
            }
        } finally
        {
            if (post != null)
            {
                post.abort();
            }
            if (chr != null)
            {
                EntityUtils.consume(chr.getEntity());
                chr.close();
            }
        }
        return msg;
    }

    /**
     * httpClient Post请求
     * 
     * @param url
     *            请求地址
     * @param param
     *            普通参数
     * @param multipartMap
     *            文件参数
     * @param type
     *            参数类型（参数类型为json字符串时，param参数可包含标签元素）
     * @param headerMap
     *            header参数
     * @return
     * @throws ClientProtocolException http连接错误会出现异常
    * @throws IOException http获取信息错误会出现异常
     */
    public String httpPost(String url, Map<String, Object> param, Map<String, ContentBody> multipartMap,
            ParamTypeEnum type, Map<String, String> headerMap) throws ClientProtocolException, IOException
    {
        if (StringUtils.isEmpty(url))
        {
            return null;
        }
        String msg = null;
        CloseableHttpResponse chr = null;
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        switch (type)
        {
        case JSON_STRING:// json字符串类型
            if (param == null || param.size() == 0)
            {
                return null;
            }
            String josnStr = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(param);
            StringEntity se = new StringEntity(josnStr, ENCODER);
            post.setEntity(se);
            break;
        case GENERAL_FORM:// 普通form表单类型
            if (param == null || param.size() == 0)
            {
                return null;
            }
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : param.entrySet())
            {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(nameValuePairs, ENCODER);
            post.setEntity(uefe);
            break;
        /*
         * case MULTIPART://文件类型 MultipartEntityBuilder meb =
         * MultipartEntityBuilder.create(); for (Map.Entry<String, InputStream>
         * entry : multipartMap.entrySet()) { meb.addBinaryBody(entry.getKey(),
         * entry.getValue()); } for (Map.Entry<String, Object> entry :
         * param.entrySet()) { meb.addTextBody(entry.getKey(),
         * entry.getValue().toString()); } post.setEntity(meb.build()); break;
         */
        case MULTIPART_FORM:// 文件form表单类型
            if (multipartMap == null || multipartMap.size() == 0)
            {
                return null;
            }
            MultipartEntityBuilder meb = MultipartEntityBuilder.create();
            for (Map.Entry<String, ContentBody> entry : multipartMap.entrySet())
            {
                meb.addPart(entry.getKey(), entry.getValue());
            }
            post.setEntity(meb.build());
            break;
        default:
            return null;
        }
        try
        {
            if (headerMap != null && headerMap.size() > 0) 
            {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) 
                {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }
            chr = httpClient.execute(post);
            if (chr.getStatusLine().getStatusCode() == 200)
            {
                msg = EntityUtils.toString(chr.getEntity(), ENCODER);
            }
        } finally
        {
            if (post != null)
            {
                post.abort();
            }
            if (chr != null)
            {
                EntityUtils.consume(chr.getEntity());
                chr.close();
            }
        }
        return msg;
    }
}
