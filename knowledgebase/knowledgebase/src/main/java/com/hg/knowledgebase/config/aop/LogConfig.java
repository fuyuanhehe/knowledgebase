package com.hg.knowledgebase.config.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.log.entity.SystemLogDO;
import com.hg.knowledgebase.module.log.service.SysLogService;

/**
 * @TODO 日志接口输入
 * @author Ye.Qiang
 * @date 2020年3月16日
 */
@Aspect
@Component
public class LogConfig
{

    private final static Logger logger = LoggerFactory.getLogger(LogConfig.class);

    @Autowired
    SysLogService logService;

    @Pointcut("execution(public * com.hg.knowledgebase.module.*.controller..*(..))")
    public void log()
    {

    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint)
    {

        SystemLog systemLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SystemLog.class);
//        String methodName = joinPoint.getSignature().getName();
//        Object[] params = joinPoint.getArgs();

        SystemLogDO sld = new SystemLogDO();
        if (null != systemLog)
        {

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            // 请求地址
//            String requestURL = request.getRequestURI();

            // 获取用户账号
            Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
            sld.setId(UUID.randomUUID().toString());
            sld.setCoder(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            sld.setOperationMsg(systemLog.value());
            sld.setOperationTime(new Date());
            sld.setOperationUser(currentUserLogin.get());
            sld.setOperationUserIp(getIpAddress(request));
         //   logService.addSysLog(sld);

        }

    }

    /**
     * 
     * @Title: 查询用户IP地址
     * @author Ye.Qiang
     * @date 2020年3月17日
     */
    public static String getIpAddress(HttpServletRequest request)
    {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor))
        {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1)
            {
                return XFor.substring(0, index);
            } else
            {
                return XFor;
            }
        }
        XFor = Xip;
        if (!StringUtils.isEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor))
        {
            return XFor;
        }
        if (!StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor))
        {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor))
        {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor))
        {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor))
        {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(XFor) || "unknown".equalsIgnoreCase(XFor))
        {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
