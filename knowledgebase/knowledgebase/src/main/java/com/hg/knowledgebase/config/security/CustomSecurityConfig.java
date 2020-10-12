package com.hg.knowledgebase.config.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hg.knowledgebase.constant.GlobalConstant;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Resource
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}").antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html").antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 文件放行
                .antMatchers(GlobalConstant.BACKSLASH
                        + GlobalConstant.REQ_RES_PATH + "/**")
                .permitAll()
                // 全局模块放行
                .antMatchers("/api/global/**").permitAll()
                // 测试接口放行
                .antMatchers("/test/**").permitAll()
                // 列表接口放行
                .antMatchers("/api/*/*/*Page").permitAll()
                // 详情接口放行
                .antMatchers("/api/*/*/get*").permitAll()
                // 校验接口放行
                .antMatchers("/api/*/*/check*").permitAll()
                // 导出接口放行
                .antMatchers("/api/*/*/output*").permitAll()
                // 用户-修改密码接口放行
                .antMatchers("/api/sys/user/modifyPassword").permitAll()
                // 用户-登录接口放行
                .antMatchers("/api/sys/user/login").permitAll()
                // 暂时放行所以接口
                /* .antMatchers("/api/**").permitAll() */
                .antMatchers("/api/*/*/preview*").permitAll()
                // 其他接口全部进行验证
                .antMatchers("/**").authenticated().and()
                .addFilterBefore(customAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
