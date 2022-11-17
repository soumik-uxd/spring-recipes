package io.github.soumikuxd.PagerDemo.services;

import io.github.soumikuxd.PagerDemo.models.PagedResponse;
import io.github.soumikuxd.PagerDemo.models.ResponseRow;
import io.github.soumikuxd.PagerDemo.properties.AppProperties;
import io.github.soumikuxd.PagerDemo.repositories.DbRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
        List<ResponseRow> results = this.dbRepository.getAll(); // Get Items from DB
        logger.debug("Fetched " + results.size() + " rows from DB");
        final int PAGE_SIZE = appProperties.getPageSize();
        logger.debug("Page size set to" + PAGE_SIZE);
        String cacheKey = "";
        PagedResponse firstPage = null;
        if (results.size() >= PAGE_SIZE) {
            // Create pages and return the first, push the rest to a cache
            List<ResponseRow> page = new java.util.ArrayList<>(Collections.emptyList());
            int itemCount = 0;
            String token = "";
            for(ResponseRow row: results) {
                logger.debug("User: " + row.toString());
                if (itemCount >= PAGE_SIZE) {
                    logger.debug("Page size b4 cache: " + page.size());
                    token = UUID.randomUUID().toString();
                    if (firstPage == null) {
                        logger.debug("Setting up first page with token: " + token);
                        // Set the first page
                        firstPage = new PagedResponse(token, new ArrayList<>(page)); // Shim to copy list instead of ref
                    } else {
                        logger.debug("Caching with key: " + cacheKey + " and token: " + token);
                        this.cacheService.setItem(cacheKey, new PagedResponse(token, new ArrayList<>(page)));
                    }
                    cacheKey = token;
                    itemCount = 0;
                    page.clear();
                }
                // Add the current row to new page
                page.add(row);
                itemCount++;
            }
            if (!page.isEmpty()) { // Cache the last page
                logger.debug("Caching with key: " + cacheKey + " and token: " + token);
                this.cacheService.setItem(cacheKey, new PagedResponse("", new ArrayList<>(page))); // No token at end
            }
            logger.debug("Loop exit!");
        } else {
            logger.debug("Page size no cache: " + results.size());
            firstPage = new PagedResponse("", results);
        }
        return firstPage;
    }

    private void processPage() {

    }
    public PagedResponse getFromCache(final String nextPage) { // Get items from DB
        return this.cacheService.getItem(nextPage);
    }
}
