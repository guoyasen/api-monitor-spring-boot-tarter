# api-monitor-spring-boot-starter 接入文档

1、引入Maven依赖
```
<dependency>
    <groupId>com.iquantex.common</groupId>
    <artifactId>api-monitor-spring-boot-starter</artifactId>
    <version>最新版本</version>
</dependency>
```

2、添加配置文件
```yaml
api-monitor:
  enabled: true                                       # 是否启用api监控
  db:
    driver-class-name: oracle.jdbc.OracleDriver       # 数据库信息 DriverClassName
    url: jdbc:oracle:thin:@10.10.21.170:1521/sigma6   # 数据库信息 URL
    username: cs                                      # 数据库信息 用户名
    password: cs                                      # 数据库信息 密码
```
