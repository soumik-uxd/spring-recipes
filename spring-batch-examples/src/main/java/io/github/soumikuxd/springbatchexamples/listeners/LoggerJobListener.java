package io.github.soumikuxd.springbatchexamples.listeners;

import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

public class LoggerJobListener implements JobExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggerJobListener.class);
    @Override
    public void beforeJob(@Nonnull JobExecution jobExecution) {
        logger.info("Before {} execution", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(@Nonnull JobExecution jobExecution) {
        if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            logger.info("Job {} ran successfully!", jobExecution.getJobInstance().getJobName());
        } else {
            logger.error("Job {} failed!", jobExecution.getJobInstance().getJobName());
        }
    }
}
