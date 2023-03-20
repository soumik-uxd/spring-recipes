package io.github.soumikuxd.pagerhazelcastdemo.services;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.github.soumikuxd.pagerhazelcastdemo.models.PagedResponse;
import io.github.soumikuxd.pagerhazelcastdemo.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private static final String RESPONSE_CACHE = "response_cache";
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    private AppProperties appProperties;
    public CacheService(@Autowired ClientConfig config) {
        this.hazelcastInstance = HazelcastClient.getOrCreateHazelcastClient(config);
    }
    public PagedResponse getItem(String token) {
        final IMap<String, PagedResponse> map = this.hazelcastInstance.getMap(RESPONSE_CACHE);
        final PagedResponse result = map.get(token);
        if(logger.isDebugEnabled()){
            if(result != null)
                logger.debug("responseCache: Cache HIT for key {}", token);
            else
                logger.debug("responseCache: Cache MISS for key {}", token);
        }
        return result;
    }
    public void setItem(String token, PagedResponse page) {
        final IMap<String, PagedResponse> map = this.hazelcastInstance.getMap(RESPONSE_CACHE);
        map.putIfAbsent(token, page, this.appProperties.getResponseCacheTtlDuration(), this.appProperties.getResponseCacheTtlDurationTimeUnit());
    }

    public void clear() {
        this.hazelcastInstance.getMap(RESPONSE_CACHE).clear();
    }
}
