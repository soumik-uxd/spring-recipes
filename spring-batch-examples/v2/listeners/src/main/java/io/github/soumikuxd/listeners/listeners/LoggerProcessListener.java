package io.github.soumikuxd.listeners.listeners;

import io.github.soumikuxd.listeners.models.Employee;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

public class LoggerProcessListener implements ItemProcessListener<Employee, Employee> {
    private static final Logger logger = LoggerFactory.getLogger(LoggerProcessListener.class);
    @Override
    public void beforeProcess(@Nonnull Employee item) {
        logger.info("Before process: {}", item);
    }

    @Override
    public void afterProcess(@Nonnull Employee item, Employee result) {
        logger.info("After process: {}", result);
    }

    @Override
    public void onProcessError(@Nonnull Employee item, @Nonnull Exception e) {
        logger.error("Error processing item: {}", item);
        logger.error(e.getMessage());
    }
}