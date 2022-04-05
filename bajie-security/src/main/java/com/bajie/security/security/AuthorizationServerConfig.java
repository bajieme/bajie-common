package com.bajie.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证服务器配置
 *
 * @author bajie
 * @date 2022-04-05 10:00 上午
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 授权码模式
        clients.inMemory()
                // 配置client-id
                .withClient("third")
                // 配置client-secret
                .secret(passwordEncoder.encode("123456"))
                // 配置token的有效期
                .accessTokenValiditySeconds(3600)
                // 配置redirect_uri,用于授权成功后跳转
                .redirectUris("https://bajieme.com")
                // 自动授权
                .autoApprove(true)
                // 配置申请的权限范围
                .scopes("all")
                // 授权资源
                // 配置grant_type,标识授权类型, 授权码模式:authorization_code 密码模式：password
                // "authorization_code", "password", "refresh_token", "implicit", "client_credentials"
                .authorizedGrantTypes("authorization_code");

    }
}
