package com.hg.knowledgebase;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.parser.ParserConfig;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.util.FileUtil;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableCaching
@MapperScan(value = "com.hg.knowledgebase.module.*.mapper")
public class KnowledgeBaseApplication extends WebMvcConfigurationSupport
{
  
  public static void main(String[] args) {
      ParserConfig.getGlobalInstance().setAsmEnable(false); //用于关闭fastjson的反序列化问题
      SpringApplication.run(KnowledgeBaseApplication.class, args);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(GlobalConstant.BACKSLASH + GlobalConstant.REQ_RES_PATH + "/**")
      .addResourceLocations(GlobalConstant.REQ_RES_PATH + ":" + FileUtil.UPLOAD_PATH);
      super.addResourceHandlers(registry);
  }
}