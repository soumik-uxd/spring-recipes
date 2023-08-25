package io.github.soumikuxd.agesummary.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class LoggerStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before step -> {} execution", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            log.info("Job {} ran successfully!", stepExecution.getStepName());
        } else {
            log.error("Job {} failed!", stepExecution.getStepName());
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }
}