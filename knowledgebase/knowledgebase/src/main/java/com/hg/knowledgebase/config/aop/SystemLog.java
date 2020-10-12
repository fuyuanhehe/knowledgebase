package com.hg.knowledgebase.config.aop;

import java.lang.annotation.*;

/**
 * 
 * @TODO 注解参数
 * @author Ye.Qiang
 * @date 2020年3月16日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog
{
    String value() default "";
}
