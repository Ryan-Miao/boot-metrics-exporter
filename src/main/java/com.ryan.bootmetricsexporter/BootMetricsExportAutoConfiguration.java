package com.ryan.bootmetricsexporter;

import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@AutoConfigureAfter({HealthEndpointAutoConfiguration.class})
@AutoConfigureBefore(MetricsAutoConfiguration.class)
public class BootMetricsExportAutoConfiguration {

    @Bean
    public MeterConfig meterConfig() {
        return new MeterConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public HealthMetrics healthMetrics() {
        return new HealthMetrics();
    }

    /**
     * 这里采用healthEndpoint来判断系统的健康状况。如果有别的需要，可以实现AbstractHealthCheckStatusSetter，自己设置health.
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(HealthEndpoint.class)
    public AbstractHealthCheckStatusSetter healthCheckSchedule(HealthEndpoint healthEndpoint, HealthMetrics healthMetrics) {
        return new HealthCheckStatusSetter(healthMetrics, healthEndpoint);
    }

}
