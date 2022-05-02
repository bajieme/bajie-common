package com.bajie.boot.config;

import com.bajie.boot.filter.HttpTraceLogFilter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bajie
 * @date 2022-05-02 2:54 下午
 * @since 1.0.0
 */
@Configuration
@ConditionalOnWebApplication
public class HttpTraceConfiguration {

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    static class ServletTraceFilterConfiguration {
        @Bean
        public HttpTraceLogFilter httpTraceLogFilter(MeterRegistry registry) {
            return new HttpTraceLogFilter(registry);
        }
    }
}
