package com.iquantex.common.apimonitorspringbootstarter.config;

import com.iquantex.common.apimonitorspringbootstarter.interceptor.ApiMonitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 *
 * @author liko
 * @date 2022/2/17
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApiMonitorInterceptor apiMonitorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiMonitorInterceptor).addPathPatterns("/**");
    }
}
