package com.iquantex.common.apimonitorspringbootstarter.service.impl;

import com.iquantex.common.apimonitorspringbootstarter.model.ApiMonitor;
import com.iquantex.common.apimonitorspringbootstarter.service.ApiMonitorService;
import com.iquantex.common.apimonitorspringbootstarter.service.DbService;
import com.iquantex.common.apimonitorspringbootstarter.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class ApiMonitorServiceImpl implements ApiMonitorService {

    public static ThreadLocal<ApiMonitor> threadLocal = new ThreadLocal<>();

    @Autowired
    public DbService apiMonitorDbService;

    @Override
    public void before(HttpServletRequest request) {
        ApiMonitor apiMonitorBean = ApiMonitor.builder()
                .ip(IpUtil.getIP(request))
                .domain(domain(request))
                .path(request.getRequestURI())
                .queryParam(request.getQueryString())
                .startTime(new Date())
                .operator(request.getHeader("user_id"))
                .build();
        threadLocal.set(apiMonitorBean);
    }

    @Override
    public void after(Exception ex) {
        ApiMonitor apiMonitor = threadLocal.get();
        Long endTime = System.currentTimeMillis();
        apiMonitor.setEndTime(new Date(endTime));
        apiMonitor.setCostTime(endTime - apiMonitor.getStartTime().getTime());
        if (ex == null) {
            apiMonitor.setIsSuccess(1);
        } else {
            apiMonitor.setErrorMsg(ex.getMessage());
            apiMonitor.setIsSuccess(0);
        }
        log.debug("[API-MONITOR] -> " + apiMonitor);
        apiMonitorDbService.saveApiMonitor(apiMonitor);
    }

    private String domain(HttpServletRequest request) {
        return String.format("%s://%s:%s", request.getScheme(), request.getServerName(), request.getServerPort());
    }

}

