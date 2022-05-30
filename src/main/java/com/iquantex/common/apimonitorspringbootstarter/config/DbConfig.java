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
    private Integer initialPoolSize = 10;
    private Integer minPoolSize = 10;
    private Integer maxPoolSize = 30;
    private Integer maxStatements = 0;
    private Integer maxIdleTime = 60;
}
