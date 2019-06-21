package com.ryan.bootmetricsexporter;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractHealthCheckStatusSetter {
    private final HealthMetrics healthMetrics;

    protected AbstractHealthCheckStatusSetter(HealthMetrics healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    /**
     * 修改health的状态定义。修改HealthMetrics.health的value。
     */
    public abstract void setHealthStatus(HealthMetrics h);

    /**
     * 定时更新health统计.
     */
    @PostConstruct
    void doSet() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> setHealthStatus(healthMetrics), 30L, 30L, TimeUnit.SECONDS);
    }


}
