package io.github.soumikuxd.springbatchexamples.configurations;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@AllArgsConstructor
public class BaseConfig {
    private JobRepository jobRepository;

    @Bean
    public JobLauncher simpleJobLauncher() {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(this.jobRepository);
        return jobLauncher;
    }

    @Bean
    public ExecutionContext executionContext(){
        return new ExecutionContext();
    }
}
