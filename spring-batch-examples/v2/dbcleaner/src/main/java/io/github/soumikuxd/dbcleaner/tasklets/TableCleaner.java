package io.github.soumikuxd.dbcleaner.tasklets;

import io.github.soumikuxd.dbcleaner.repository.EmployeeRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@AllArgsConstructor
@Slf4j
public class TableCleaner implements Tasklet {
    private EmployeeRepository employeeRepository;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @Nonnull ChunkContext chunkContext) throws Exception {
        log.info("{} records will be deleted.", this.employeeRepository.count());
        this.employeeRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}