package io.github.soumikuxd.listeners.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class LoggerStepListener implements StepExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggerStepListener.class);
    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("Before step -> {} execution", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            logger.info("Job {} ran successfully!", stepExecution.getStepName());
        } else {
            logger.error("Job {} failed!", stepExecution.getStepName());
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }
}