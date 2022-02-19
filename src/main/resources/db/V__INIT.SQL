create table API_MONITOR_LOG
(
    ID VARCHAR2(36) not null primary key,
    IP VARCHAR2(128),
    DOMAIN VARCHAR2(128),
    PATH VARCHAR2(1024),
    QUERY_PARAM CLOB,
    START_TIME TIMESTAMP(6),
    END_TIME TIMESTAMP(6),
    IS_SUCCESS NUMBER(2),
    COST_TIME NUMBER,
    ERROR_MSG CLOB,
    OPERATOR VARCHAR2(128)
);

comment on column API_MONITOR_LOG.ID is '主键ID';
comment on column API_MONITOR_LOG.IP is '访问IP';
comment on column API_MONITOR_LOG.DOMAIN is '域名';
comment on column API_MONITOR_LOG.PATH is '访问路径';
comment on column API_MONITOR_LOG.QUERY_PARAM is '查询参数';
comment on column API_MONITOR_LOG.START_TIME is '开始时间';
comment on column API_MONITOR_LOG.END_TIME is '结束时间';
comment on column API_MONITOR_LOG.IS_SUCCESS is '是否成功';
comment on column API_MONITOR_LOG.COST_TIME is '耗时';
comment on column API_MONITOR_LOG.ERROR_MSG is '错误信息';
comment on column API_MONITOR_LOG.OPERATOR is '操作用户';
