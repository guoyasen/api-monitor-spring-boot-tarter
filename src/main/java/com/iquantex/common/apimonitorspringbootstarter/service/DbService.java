package com.iquantex.common.apimonitorspringbootstarter.service;

import com.iquantex.common.apimonitorspringbootstarter.model.ApiMonitor;

import java.sql.Connection;

/**
 * DbService
 *
 * @author liko
 * @date 2022/2/17
 */
public interface DbService {
    Connection getConnection();
    boolean saveApiMonitor(ApiMonitor apiMonitor);
}
