package com.iquantex.common.apimonitorspringbootstarter.config;

import com.iquantex.common.apimonitorspringbootstarter.common.Constant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DbConfig
 *
 * @author liko
 * @date 2022/2/17
 */
@Data
@ConfigurationProperties(prefix = Constant.API_MONITOR_DB_CONFIG_PREFIX)
public class DbConfig {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
    private Integer initialPoolSize = 2;
    private Integer minPoolSize = 2;
    private Integer maxPoolSize = 10;
    private Integer maxStatements = 50;
    private Integer maxIdleTime = 60;
}
