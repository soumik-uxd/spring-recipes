package io.github.soumikuxd.springbatchexamples.tasklets;

import io.github.soumikuxd.springbatchexamples.repositories.EmployeeRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@AllArgsConstructor
public class TableCleaner implements Tasklet {
    private static final Logger logger = LoggerFactory.getLogger(TableCleaner.class);
    private EmployeeRepository employeeRepository;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @Nonnull ChunkContext chunkContext) throws Exception {
        logger.info("{} records will be deleted.", this.employeeRepository.count());
        this.employeeRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
