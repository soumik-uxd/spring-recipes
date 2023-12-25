package io.github.soumikuxd.listeners.listeners;

import io.github.soumikuxd.listeners.models.Employee;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

public class LoggerReaderListener implements ItemReadListener<Employee> {
    private static final Logger logger = LoggerFactory.getLogger(LoggerReaderListener.class);
    @Override
    public void beforeRead() {
        logger.info("Before item is read.");
    }

    @Override
    public void afterRead(@Nonnull Employee item) {
        logger.info("After reading item: {}", item);
    }

    @Override
    public void onReadError(@Nonnull Exception ex) {
        logger.error("Error while reading. {}", ex.getMessage());
    }
}