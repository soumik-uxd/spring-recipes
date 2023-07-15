package io.github.soumikuxd.dbcleaner.listeners;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class LoggerJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(@Nonnull JobExecution jobExecution) {
        log.info("Before {} execution", jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(@Nonnull JobExecution jobExecution) {
        if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            log.info("Job {} ran successfully!", jobExecution.getJobInstance().getJobName());
        } else {
            log.error("Job {} failed!", jobExecution.getJobInstance().getJobName());
        }
    }
}