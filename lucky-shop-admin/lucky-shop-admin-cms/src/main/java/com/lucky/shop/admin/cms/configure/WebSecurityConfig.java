package com.lucky.shop.admin.cms.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * 校验发往本服务的请求头的令牌
 *
 * @Create by Guo Huaijian
 * @Since 2020/6/2 20:09
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${Oauth2.oauth2.resource.token-info-url}")
    public String tokenInfoUrl;

    @Value("${Oauth2.oauth2.client.client-id}")
    public String oauth2ClientId;

    @Value("${Oauth2.oauth2.client.client-secret}")
    public String oauth2ClientSecret;

    private final RestTemplate restTemplate;

    /**
     * 通过这个Bean去远程调用认证服务器验token
     *
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        // 在认证服务器配置的服务的clientId
        tokenServices.setClientId(oauth2ClientId);
        // 在认证服务器配置的服务的ClientSecret
        tokenServices.setClientSecret(oauth2ClientSecret);
        tokenServices.setCheckTokenEndpointUrl(tokenInfoUrl);
        tokenServices.setRestTemplate(restTemplate);
        return tokenServices;
    }


    /**
     * 要认证跟用户相关的信息，一般用 AuthenticationManager
     * 覆盖这个方法，可以将AuthenticationManager暴露为一个Bean
     *
     * @return
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        // 设置为自定义的TokenServices去校验令牌
        authenticationManager.setTokenServices(tokenServices());
        return authenticationManager;
    }
}
