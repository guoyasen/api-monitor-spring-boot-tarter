# api-monitor-spring-boot-starter 接入文档

### 1、引入Maven依赖
```
<dependency>
    <groupId>com.iquantex.common</groupId>
    <artifactId>api-monitor-spring-boot-starter</artifactId>
    <version>最新版本</version>
</dependency>
```

### 2、添加配置文件
```yaml
api-monitor:
  enabled: true                                       # 是否启用api监控
  db:
    enabled: false                                    # 是否启用数据库存储
    driver-class-name: oracle.jdbc.OracleDriver       # 数据库信息 DriverClassName
    url: jdbc:oracle:thin:@10.10.21.170:1521/sigma6   # 数据库信息 URL
    username: cs                                      # 数据库信息 用户名
    password: cs                                      # 数据库信息 密码
  kafka:
    enabled: false                                    # 监控消息是否发送Kafka
    bootstrap-servers: 10.10.23.112:9092              # Kafka服务器地址
    key-serializer: org.apache.kafka.common.serialization.StringSerializer     # Kafka key-serializer
    value-serializer: org.apache.kafka.common.serialization.StringSerializer   # Kafka value-serializer
    topic: api-monitor-kafka                                                   # Kafka Topic
```

### 3、在对应数据库中执行V__INIT.SQL
SQL文件地址: resources/db/V__INIT.SQL
