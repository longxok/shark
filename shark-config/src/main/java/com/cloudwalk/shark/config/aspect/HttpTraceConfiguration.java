package com.cloudwalk.shark.config.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
