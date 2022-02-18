package com.iquantex.common.apimonitorspringbootstarter.service.impl;

import com.iquantex.common.apimonitorspringbootstarter.config.DbConfig;
import com.iquantex.common.apimonitorspringbootstarter.model.ApiMonitor;
import com.iquantex.common.apimonitorspringbootstarter.service.DbService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.OutParameter;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.Objects;
import java.util.UUID;

/**
 * ConnectionManager
 *
 * @author liko
 * @date 2022/2/17
 */
@Slf4j
public class DbServiceImpl implements DbService {

    @Autowired
    private DbConfig dbConfig;

    private ComboPooledDataSource dataSource;

    @PostConstruct
    public void init() {
        try {
            log.debug("开始初始化DataSource, dbConfig<{}>", dbConfig);
            dataSource = new ComboPooledDataSource();
            dataSource.setUser(dbConfig.getUsername());
            dataSource.setPassword(dbConfig.getPassword());
            dataSource.setJdbcUrl(dbConfig.getUrl());
            dataSource.setDriverClass(dbConfig.getDriverClassName());
            dataSource.setInitialPoolSize(dbConfig.getInitialPoolSize());
            dataSource.setMinPoolSize(dbConfig.getMinPoolSize());
            dataSource.setMaxPoolSize(dbConfig.getMaxPoolSize());
            dataSource.setMaxStatements(dbConfig.getMaxStatements());
            dataSource.setMaxIdleTime(dbConfig.getMaxIdleTime());
        } catch (Exception e) {
            log.error("初始化DataSource失败!", e);
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        if (Objects.nonNull(dataSource)) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                log.error("获取Connection失败", e);
            }
        }
        return connection;
    }

    private static final String API_MONITOR_INSERT_SQL = "insert into API_MONITOR_LOG (ID, IP, DOMAIN, PATH, QUERY_PARAM, START_TIME, END_TIME, IS_SUCCESS, COST_TIME, ERROR_MSG, OPERATOR) values  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public boolean saveApiMonitor(ApiMonitor apiMonitor) {
        try (Connection connection = getConnection()) {
            if (Objects.nonNull(connection)) {
                String uuid = UUID.randomUUID().toString();
                log.debug("ID: " + uuid);
                int size = new QueryRunner().update(connection, API_MONITOR_INSERT_SQL, uuid, apiMonitor.getIp(), apiMonitor.getDomain(), apiMonitor.getPath(), apiMonitor.getQueryParam(), new Timestamp(apiMonitor.getStartTime().getTime()), new Timestamp(apiMonitor.getEndTime().getTime()), apiMonitor.getIsSuccess(), apiMonitor.getCostTime(), apiMonitor.getErrorMsg(), apiMonitor.getOperator());
                if (size == 1) {
                    return true;
                }
                log.error("落库数据量{}, 请检查落库逻辑!", size);
            } else {
                log.error("获取Connection失败!");
            }
        } catch (SQLException e) {
            log.error("日志落库失败!", e);
        }

        return false;
    }

}
