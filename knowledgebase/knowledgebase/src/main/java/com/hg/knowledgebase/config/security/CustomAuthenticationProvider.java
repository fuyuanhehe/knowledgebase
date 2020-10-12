package com.hg.knowledgebase.config.security;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.service.IUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{

    @Autowired
    private IUserService userService;

    /**
     * 加密对象
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    private User getUser(String username) throws Exception
    {
        return userService.findUserByName(username);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        // 获取用户登录时输入的用户名
        String username = authentication.getName();
        // 根据用户名查询系统中的用户信息
        User user;
        try
        {
            user = getUser(username);
            // 如果用户列表为 null，说明查找用户功能出现异常，抛出 AuthenticationServiceException
            if (Objects.isNull(user))
            {
                throw new AuthenticationServiceException(String.format(
                        "Searching user[%s] occurred error!", username));
            }
            // 如果用户登录时输入的密码和系统中密码匹配，则返回一个完全填充的 Authentication 对象
            /*
             * 更新密码验证 if
             * (user.getPassword().equals(authentication.getCredentials().
             * toString())) { return new
             * UsernamePasswordAuthenticationToken(authentication,
             * authentication.getCredentials(), new ArrayList<>()); }
             */
            if (encoder.matches(authentication.getCredentials().toString(),
                    user.getPassword()))
            {
                return new UsernamePasswordAuthenticationToken(authentication,
                        authentication.getCredentials(), new ArrayList<>());
            }
        } catch (Exception e)
        {
            e.getMessage();
        }
        // 如果密码不匹配则返回 null（此处可以抛异常，试具体应用场景而定）
        throw new NullPointerException();
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }
}
