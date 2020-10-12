package com.hg.knowledgebase.config.security;

import java.util.Objects;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager
{

    private final CustomAuthenticationProvider authenticationProvider;

    public CustomAuthenticationManager(
            CustomAuthenticationProvider authenticationProvider)
    {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        Authentication result = authenticationProvider
                .authenticate(authentication);
        if (Objects.nonNull(result))
        {
            return result;
        }
        throw new ProviderNotFoundException("Authentication failed!");
    }
}
