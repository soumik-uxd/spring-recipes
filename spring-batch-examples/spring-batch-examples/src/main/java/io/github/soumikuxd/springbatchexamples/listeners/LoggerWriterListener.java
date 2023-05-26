package io.github.soumikuxd.springbatchexamples.listeners;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

public class LoggerWriterListener implements ItemWriteListener<Employee> {
    private static int chunkCount = 0;
    private static final Logger logger = LoggerFactory.getLogger(LoggerWriterListener.class);
    @Override
    public void beforeWrite(Chunk<? extends Employee> items) {
        logger.info("Before write of chunk #{}.", ++chunkCount);
        items.getItems().forEach(item -> logger.info("Item: {}", item));
    }

    @Override
    public void afterWrite(@Nonnull Chunk<? extends Employee> items) {
        logger.info("After write of chunk #{}.", chunkCount);
    }

    @Override
    public void onWriteError(@Nonnull Exception exception, @Nonnull Chunk<? extends Employee> items) {
        logger.error("Error during write of chunk #{}. {}", chunkCount, exception.getMessage());
    }
}
