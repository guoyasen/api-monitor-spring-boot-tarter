package com.iquantex.common.apimonitorspringbootstarter.service;

import javax.servlet.http.HttpServletRequest;

/**
 * IApiMonitorService
 *
 * @author liko
 * @date 2022/2/17
 */
public interface ApiMonitorService {
    void before(HttpServletRequest request);

    void after(Exception ex);
}
