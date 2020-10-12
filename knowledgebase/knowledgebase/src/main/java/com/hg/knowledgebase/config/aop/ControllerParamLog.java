package com.hg.knowledgebase.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description: 控制层参数日志
 * @Author: 况克冬
 * @CreateDate: 2020年4月28日上午9:37:43
 * @Version: 1.0.0
 */
@Aspect
@Component
public class ControllerParamLog
{

    private static Logger log = LoggerFactory
            .getLogger(ControllerParamLog.class);

    /**
     * 
     * @Description: 监听的控制层方法
     * @Author: 况克冬
     * @CreateDate: 2020年4月28日上午10:33:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月28日上午10:33:24
     * @UpdateRemark: 修改内容 void
     * @Version: 1.0.0
     */
    @Pointcut("execution(public * com.hg.knowledgebase.module.*.controller.*.*(..))")
    public void monitor()
    {
    }

    /**
     * 
     * @Description: 参数日志
     * @Author: 况克冬
     * @CreateDate: 2020年4月28日上午10:34:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月28日上午10:34:12
     * @UpdateRemark: 修改内容
     * @param joinPoint
     *            void
     * @Version: 1.0.0
     */
    @Before("monitor()")
    public void paramLog(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        log.debug("---控制层入参信息，入参【param：{}】---", args != null ? args : "");
    }
}
