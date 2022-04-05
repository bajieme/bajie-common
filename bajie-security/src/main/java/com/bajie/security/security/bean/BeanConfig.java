package com.bajie.security.security.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 需要初始化的Bean
 *
 * @author bajie
 * @date 2022-03-22 5:58 下午
 * @since 1.0.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder getPwd() {
        // 可以使用自定义的密码工具类，缓存md5
        // return new DefaultPasswordEncoder();
        return new BCryptPasswordEncoder();
    }
}
