package io.github.soumikuxd.dbcleaner.job;

import io.github.soumikuxd.dbcleaner.listeners.LoggerJobListener;
import io.github.soumikuxd.dbcleaner.listeners.LoggerStepListener;
import io.github.soumikuxd.dbcleaner.repository.EmployeeRepository;
import io.github.soumikuxd.dbcleaner.tasklets.TableCleaner;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class DBCleaner {
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeRepository employeeRepository;

    @Bean(name="dbcleanerjob")
    public Job DBCleanerJob() throws Exception {
        return new JobBuilder("DBCleaner", this.jobRepository)
                .start(DBCleanerStep())
                .listener(new LoggerJobListener())
                .build();
    }

    @Bean
    public Step DBCleanerStep() throws Exception {
        return new StepBuilder("DBCleanerStep", this.jobRepository)
                .tasklet(new TableCleaner(employeeRepository), this.transactionManager)
                .listener(new LoggerStepListener())
                .build();
    }
}