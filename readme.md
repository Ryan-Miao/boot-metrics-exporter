


## How to use

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Ryan-Miao</groupId>
    <artifactId>boot-metrics-exporter</artifactId>
    <version>1.0</version>
</dependency>
```

application.yaml

```yml
management:
  metrics:
    export:
      influx:
        db: kidflash
        uri: http://192.168.5.9:8086
        user-name: admin
        password: admin
        enabled: true
    web:
      server:
        auto-time-requests: true
    tags:
      app: ${spring.application.name}

```

More details:  https://www.cnblogs.com/woshimrf/p/springboot-metrics-expore-influxdb-grafana.html


