package io.github.soumikuxd.PagerDemo.properties;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Generated
@Component("appProperties")
@ConfigurationProperties(prefix = "app.property")
public class AppProperties {
    private int pageSize = 10;
}
