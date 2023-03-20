package io.github.soumikuxd.pagerhazelcastdemo.properties;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Data
@ToString
@Generated
@Component("appProperties")
@ConfigurationProperties(prefix = "app.property")
public class AppProperties {
    private int pageSize = 10;
    private TimeUnit responseCacheTtlDurationTimeUnit = TimeUnit.SECONDS;
    private long responseCacheTtlDuration = 10800;
}