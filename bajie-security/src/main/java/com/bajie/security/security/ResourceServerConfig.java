package com.bajie.security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 *
 * @author bajie
 * @date 2022-04-05 10:00 上午
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许 路径为/noauth/ 后面为任意信息的路径地址 不需要通过oauth2登录授权
                .antMatchers("/api/noauth/**").permitAll()
                // 所有的资源都要授权访问
                .anyRequest().authenticated()
                .and()
                // 授权的资源
                .requestMatchers().antMatchers("/api/**");
    }
}
