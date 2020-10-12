package com.hg.knowledgebase.config.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * token的校验 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * 
 * @author Yq
 */
@Service
public class CustomAuthenticationFilter extends GenericFilterBean
{

    @Resource
    TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers",
                "X-Requested-With,X_Requested_With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = tokenProvider.resolveToken(httpServletRequest);
        try
        {
            if (!(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)))
            {
                filterChain.doFilter(request, response);
                return;
            }

            Authentication authentication = tokenProvider
                    .getAuthentication(jwt);
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            // 系统权限
            AccessRights(httpServletRequest);
        } catch (ExpiredJwtException e)
        {
            resp.setStatus(401);
        }
        filterChain.doFilter(request, resp);
    }

    private void AccessRights(HttpServletRequest httpServletRequest)
            throws ServletException
    {

    }

}
