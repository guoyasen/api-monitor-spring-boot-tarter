package com.iquantex.common.apimonitorspringbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xianbin.yang
 * @date 2020/10/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiMonitor {

    private String ip;
    /**
     * 域名
     */
    private String domain;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 1-成功; 0-失败
     */
    private Integer isSuccess;

    /**
     * 查询参数
     */
    private String queryParam;
    /**
     * 耗时: 毫秒
     */
    private long costTime;

    /**
     * 异常消息
     */
    private String errorMsg;

    /**
     * 操作用户
     */
    private String operator;
}
