package io.github.soumikuxd.springbatchexamples.jobs;

import io.github.soumikuxd.springbatchexamples.listeners.LoggerJobListener;
import io.github.soumikuxd.springbatchexamples.listeners.LoggerStepListener;
import io.github.soumikuxd.springbatchexamples.repositories.EmployeeRepository;
import io.github.soumikuxd.springbatchexamples.tasklets.EmployeeAgeAggregator;
import io.github.soumikuxd.springbatchexamples.tasklets.TableCleaner;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class AgeSummary {
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeRepository employeeRepository;

    @Bean(name="agesummaryjob")
    public Job AgeSummaryJob() throws Exception {
        return new JobBuilder("AgeSummary", this.jobRepository)
                .start(AgeSummaryStep())
                .listener(new LoggerJobListener())
                .build();
    }

    @Bean
    public Step AgeSummaryStep() throws Exception {
        return new StepBuilder("AgeSummaryStep", this.jobRepository)
                .tasklet(new EmployeeAgeAggregator(employeeRepository), this.transactionManager)
                .listener(new LoggerStepListener())
                .build();
    }
}


