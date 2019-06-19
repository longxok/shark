package com.cloudwalk.shark.config.aspect;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
public class TraceConfig {
    @Bean
    @ConditionalOnMissingBean(HttpTraceRepository.class)
    public RemoteHttpTraceRepository traceRepository() {
        return new RemoteHttpTraceRepository();
    }
}
