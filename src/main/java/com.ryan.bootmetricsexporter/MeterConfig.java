package com.ryan.bootmetricsexporter;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class MeterConfig implements MeterRegistryCustomizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeterConfig.class);

    @Override
    public void customize(MeterRegistry registry) {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("设置metrics实例id为ip:" + hostAddress);
            }
            registry.config().commonTags("instance-id", hostAddress);
        } catch (UnknownHostException e) {
            String uuid = UUID.randomUUID().toString();
            registry.config().commonTags("instance-id", uuid);
            LOGGER.error("获取实例ip失败，设置实例id为uuid:" + uuid, e);
        }
    }
}
