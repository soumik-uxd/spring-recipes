package io.github.soumikuxd.pagerhazelcastdemo.configurations;

import com.hazelcast.client.config.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    private static final Logger logger = LoggerFactory.getLogger(HazelcastConfig.class);

    @Value("${hazelCastNodes}")
    private String hazelcastNodes;

    private final String hazelCastInstanceName = "hazelcast-demo-service";
    private final String hazelCastClusterName = "dev";

    @Bean
    public ClientConfig localConfig() {
        logger.info("Initializing hazelcast config");
        final ClientConfig config = new ClientConfig();
        config.setInstanceName(hazelCastInstanceName);
        config.setClusterName(hazelCastClusterName);
        config.getNetworkConfig().addAddress(hazelcastNodes.split(",")).setConnectionTimeout(1000);
        return config;
    }
}
