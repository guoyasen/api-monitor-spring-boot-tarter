package com.iquantex.common.apimonitorspringbootstarter.config;

import com.iquantex.common.apimonitorspringbootstarter.common.Constant;
import com.iquantex.common.apimonitorspringbootstarter.interceptor.ApiMonitorInterceptor;
import com.iquantex.common.apimonitorspringbootstarter.service.ApiMonitorService;
import com.iquantex.common.apimonitorspringbootstarter.service.DbService;
import com.iquantex.common.apimonitorspringbootstarter.service.impl.ApiMonitorServiceImpl;
import com.iquantex.common.apimonitorspringbootstarter.service.impl.DbServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

/**
 * ApiMonitorAutoConfiguration
 *
 * @author liko
 * @date 2022/2/17
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({DbConfig.class, KafkaConfig.class})
public class ApiMonitorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = Constant.API_MONITOR_CONFIG_PREFIX, name = Constant.API_MONITOR_CONFIG_KEY_ENABLED, matchIfMissing = false)
    public ApiMonitorInterceptor apiMonitorInterceptor() {
        return new ApiMonitorInterceptor();
    }

    @Bean
    @ConditionalOnBean(ApiMonitorInterceptor.class)
    @ConditionalOnProperty(prefix = Constant.API_MONITOR_DB_CONFIG_PREFIX, name = Constant.API_MONITOR_CONFIG_KEY_ENABLED, matchIfMissing = false)
    public DbService apiMonitorDbService() {
        return new DbServiceImpl();
    }

    @Bean
    @ConditionalOnBean(ApiMonitorInterceptor.class)
    public ApiMonitorService apiMonitorService() {
        return new ApiMonitorServiceImpl();
    }

    @Bean
    @ConditionalOnBean(ApiMonitorInterceptor.class)
    public WebMvcConfigurer apiMonitorWebMvcConfigurer() {
        return new WebMvcConfig();
    }

    @Bean
    @ConditionalOnBean(ApiMonitorInterceptor.class)
    @ConditionalOnProperty(prefix = Constant.API_MONITOR_KAFKA_CONFIG_PREFIX, name = Constant.API_MONITOR_CONFIG_KEY_ENABLED, matchIfMissing = false)
    public KafkaProducer<String, String> kafkaProducer(KafkaConfig kafkaConfig) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        properties.put("key.serializer", kafkaConfig.getKeySerializer());
        properties.put("value.serializer", kafkaConfig.getValueSerializer());

        return new KafkaProducer<String, String>(properties);
    }
}
