package com.hg.knowledgebase.config.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig
{

    /**
     * 最大连接数
     */
    @Value("${http.maxTotal}")
    private Integer maxTotal;

    /**
     * 最大并发数
     */
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    @Value("${http.connectTimeout}")
    private Integer connectTimeout;

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;

    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager()
    {
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
        phccm.setMaxTotal(maxTotal);
        phccm.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return phccm;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(
            @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager phccm)
    {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        phccm.setValidateAfterInactivity(2000);
        hcb.setConnectionManager(phccm);
        return hcb;
    }

    @Bean(name = "closeableHttpClient")
    public CloseableHttpClient getCloseableHttpClient(
            @Qualifier("httpClientBuilder") HttpClientBuilder hcb)
    {
        return hcb.build();
    }

    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder()
    {
        RequestConfig.Builder buider = RequestConfig.custom();
        /*
         * return buider.setConnectionRequestTimeout(connectionRequestTimeout).
         * setConnectTimeout(connectTimeout)
         * .setSocketTimeout(socketTimeout).setStaleConnectionCheckEnabled(
         * staleConnectionCheckEnabled);
         */
        // 更新过时代码方法“setStaleConnectionCheckEnabled”转到“org.apache.http.impl.conn.PoolingHttpClientConnectionManager#setValidateAfterInactivity(int)”
        return buider.setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout);
    }

    @Bean(name = "requestConfig")
    public RequestConfig getRequestConfig(
            @Qualifier("builder") RequestConfig.Builder builder)
    {
        return builder.build();
    }
}
