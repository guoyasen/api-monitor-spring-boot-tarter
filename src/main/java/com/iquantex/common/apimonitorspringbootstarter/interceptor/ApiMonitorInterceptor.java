package com.iquantex.common.apimonitorspringbootstarter.interceptor;

import com.iquantex.common.apimonitorspringbootstarter.service.ApiMonitorService;
import com.iquantex.common.apimonitorspringbootstarter.service.impl.ApiMonitorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ApiMonitorInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiMonitorService apiMonitorServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            apiMonitorServiceImpl.before(request);
        } catch (Exception e) {
            log.error("前置处理失败!", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // FIXME 无法获取到Exception
        try {
            apiMonitorServiceImpl.after(ex);
            ApiMonitorServiceImpl.threadLocal.remove();
        } catch (Exception e) {
            log.error("后置处理失败!", e);
        }
    }

}
