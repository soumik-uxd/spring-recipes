package io.github.soumikuxd.PagerDemo.services;

import io.github.soumikuxd.PagerDemo.models.PagedResponse;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private final Map<String, PagedResponse> cache;

    public CacheService() {
        this.cache = new HashMap<>();
    }

    PagedResponse getItem(String token) {
        return this.cache.get(token);
    }

    void setItem(String token, PagedResponse page) {
        this.cache.put(token, page);
    }
}
