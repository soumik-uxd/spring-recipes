package io.github.soumikuxd.pagerhazelcastdemo.controllers;

import io.github.soumikuxd.pagerhazelcastdemo.models.PagedResponse;
import io.github.soumikuxd.pagerhazelcastdemo.services.CacheService;
import io.github.soumikuxd.pagerhazelcastdemo.services.ResponseService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/data/")
public class PagerController {
    private static final Logger logger = LoggerFactory.getLogger(PagerController.class);

    @Autowired
    private ResponseService responseService;

    @Autowired
    private CacheService cacheService;

    @PostConstruct
    private void initCache() {
        this.cacheService.clear();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "all")
    @ResponseBody
    public PagedResponse getAllData(@RequestParam(required = false) final String nextPage) {
        // Check if we have a page token
        if (nextPage != null) {
            // Get page info from cache
            logger.debug("Getting info from cache for token: " + nextPage);
            return this.responseService.getFromCache(nextPage);
        } else {
            // Call Db for further information
            logger.debug("Getting info from DB");
            return this.responseService.getAllItems();
        }
    }
}