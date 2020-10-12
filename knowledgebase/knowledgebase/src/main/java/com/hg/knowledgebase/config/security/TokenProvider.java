
package com.hg.knowledgebase.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider
{

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private static final String TOKEN_KEY = "tokenKey";

    private static final String TICKET_KEY = "ticket";

    public static final String LOGIN_TYPE = "loginType";

    public static final String PASS_KEY = "pass";

    private String secretKey;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    @PostConstruct
    public void init()
    {

        // my-secret-token-to-change-in-production
        // # Token is valid 24 hours
        // token-validity-in-seconds: 86400
        // token-validity-in-seconds-for-remember-me: 2592000

        this.secretKey = "my-secret-token-to-change-in-production";
        // this.tokenValidityInMilliseconds = 86400;
        this.tokenValidityInMilliseconds = 86400 * 100;
        // this.tokenValidityInMillisecondsForRememberMe = 2592000;
        // this.tokenValidityInMillisecondsForRememberMe = 1000 * 60 * 60 * 2;
        this.tokenValidityInMillisecondsForRememberMe = 1000 * 60 * 60 * 200;
    }

    public String createToken(Authentication authentication, boolean rememberMe,
            String loginType, HttpServletRequest request)
    {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe)
        {
            validity = new Date(
                    now + this.tokenValidityInMillisecondsForRememberMe);
        } else
        {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        // 保存token在redis中的key
        // String account = String.valueOf(authentication.getPrincipal());
        // String tokenValue = account + System.currentTimeMillis();
        String jwt = Jwts.builder().setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(LOGIN_TYPE, loginType)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity).compact();

        // 保存在redis中
        // 用户信息保存在redis中,以account为key,所有登录这个账号的用户都从这个位置取同一个userInfo
        // Optional<HgUser> HgUser = hgUserService.findOneByAccount(account);
        // if (!HgUser.isPresent())
        // {
        // redisService.set(account, "null",
        // tokenValidityInMilliseconds / 1000);
        // return jwt;
        // }
        // HgUser hgUser = HgUser.get();
        //// HgUserDTO userDTO = null;
        // // 实际应当保存DTO进入redis 待修改
        // redisService.set(account, HgUser.get(),
        // tokenValidityInMilliseconds / 1000);
        //
        // HgLoginLog hgLoginLog = new HgLoginLog();
        // hgLoginLog.setAccountType(hgUser.getJhiType() + "");
        // hgLoginLog.setLoginType(1);
        // hgLoginLog.setUserAccount(hgUser.getJhiAccount());
        // hgLoginLog.setUserName(hgUser.getName());
        // hgLoginLog.setOperationDateTime(new Date());
        // hgLoginLog.setLoginIp(IpAddressUtil.getIpAddress(request));
        // hgLoginLogService.save(hgLoginLog);
        return jwt;
    }

    public Authentication getAuthentication(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // Arrays
        // .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        // .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token,
                authorities);
    }

    public String getTicket(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return claims.get(TICKET_KEY).toString();
    }

    public String getLoginType(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return claims.get(LOGIN_TYPE).toString();
    }

    public String getTokenKey(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return claims.get(TOKEN_KEY).toString();
    }

    public String getPassFlag(String token)
    {

        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return claims.get(PASS_KEY).toString();
    }

    public boolean validateToken(String authToken)
    {

        try
        {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);

            return true;
        } catch (SignatureException e)
        {
            e.printStackTrace();
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e)
        {
            e.printStackTrace();
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        }
        // catch (ExpiredJwtException e) {
        // log.info("Expired JWT token.");
        // log.trace("Expired JWT token trace: {}", e);
        // throw new ServletException(ErrorConstants.LOGIN_HAS_EXPIRED);
        //
        // }
        catch (UnsupportedJwtException e)
        {
            e.printStackTrace();
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token))
        {
            return token;
        }
        return null;
    }

}
