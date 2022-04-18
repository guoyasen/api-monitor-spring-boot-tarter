package com.iquantex.common.apimonitorspringbootstarter.config;

import com.iquantex.common.apimonitorspringbootstarter.common.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * KafkaConfig
 *
 * @author liko
 * @date 2022/4/18
 */
@Data
@ConfigurationProperties(prefix = Constant.API_MONITOR_KAFKA_CONFIG_PREFIX)
public class KafkaConfig {
    private String bootstrapServers;
    private String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";
    private String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
    private String topic;
}
