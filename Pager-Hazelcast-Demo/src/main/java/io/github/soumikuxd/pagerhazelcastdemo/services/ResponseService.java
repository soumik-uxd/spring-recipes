package io.github.soumikuxd.pagerhazelcastdemo.services;

import io.github.soumikuxd.pagerhazelcastdemo.models.PagedResponse;
import io.github.soumikuxd.pagerhazelcastdemo.models.ResponseRow;
import io.github.soumikuxd.pagerhazelcastdemo.properties.AppProperties;
import io.github.soumikuxd.pagerhazelcastdemo.repositories.DbRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.UUID;
//import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ResponseService {
    private static final Logger logger = LoggerFactory.getLogger(ResponseService.class);
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private DbRepository dbRepository;
    @Autowired
    private CacheService cacheService;

    public PagedResponse getAllItems() {
        final List<ResponseRow> results = this.dbRepository.getAll(); // Get Items from DB
        logger.debug("Fetched " + results.size() + " rows from DB");
        final int PAGE_SIZE = appProperties.getPageSize();
        logger.debug("Page size set to" + PAGE_SIZE);
        final List<ResponseRow> firstPage = results.stream().limit(PAGE_SIZE).toList();
        final String token = this.cachePages(results.stream().skip(PAGE_SIZE).toList(), PAGE_SIZE);
        return new PagedResponse(token, new ArrayList<>(firstPage));
    }

    private String cachePages(List<ResponseRow> items, int limit) {
        final String firstPageToken = UUID.randomUUID().toString();
        final int pages = (int) Math.ceil((float) items.size() / limit);
        String cacheKey = firstPageToken;
        String nextPageToken;
        List<ResponseRow> pageItems;
        List<ResponseRow> restItems = items;
        for (int pageId = 1; pageId <= pages; pageId++) {
            // Save the items to the cache
            if (pageId == pages) {
                pageItems = restItems;
                this.cacheService.setItem(cacheKey, new PagedResponse("", new ArrayList<>(pageItems)));
            } else {
                pageItems = restItems.stream().limit(limit).toList();
                nextPageToken = UUID.randomUUID().toString();
                this.cacheService.setItem(cacheKey, new PagedResponse(nextPageToken, new ArrayList<>(pageItems)));
                restItems = restItems.stream().skip(limit).toList();
                cacheKey = nextPageToken;
            }
        }
        return firstPageToken;
    }

    public PagedResponse getFromCache(final String nextPage) { // Get items from DB
        return this.cacheService.getItem(nextPage);
    }
}
