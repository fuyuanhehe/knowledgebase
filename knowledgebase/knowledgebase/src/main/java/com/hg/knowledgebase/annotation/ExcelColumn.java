package com.hg.knowledgebase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hg.knowledgebase.customenum.SexEnum;

/**
 * 
* @Description: excel列注解
* 由于每个业务数据校验不同，并且为了提高解析效率，因此此处没有做值校验
* @Author: 况克冬
* @CreateDate: 2020年2月27日下午8:21:37
* @Version: 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    
    /**
     * 
    * @Description: 列的中文名称
    * @Author: 况克冬
    * @CreateDate: 2020年2月27日下午9:13:39
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年2月27日下午9:13:39
    * @UpdateRemark: 修改内容
    * @return
    * String
    * @Version: 1.0.0
     */
    String value();
    
    /**
     * 
    * @Description: 列名排序号
    * @Author: 况克冬
    * @CreateDate: 2020年2月28日上午9:20:22
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年2月28日上午9:20:22
    * @UpdateRemark: 修改内容
    * @return
    * int
    * @Version: 1.0.0
     */
    int sort() default 0;
    
    /**
     * 
    * @Description: 性别
    * @Author: 况克冬
    * @CreateDate: 2020年2月28日下午1:38:14
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年2月28日下午1:38:14
    * @UpdateRemark: 修改内容
    * @return
    * SexEnum[]
    * @Version: 1.0.0
     */
    SexEnum[] sexs() default {};
    
    /**
     * 
    * @Description: 是否是性别字段，配合sexs使用，如果为true属性sexs生效
    * @Author: 况克冬
    * @CreateDate: 2020年3月5日下午3:35:48
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月5日下午3:35:48
    * @UpdateRemark: 修改内容
    * @return
    * boolean
    * @Version: 1.0.0
     */
    boolean isSex() default false;
}
