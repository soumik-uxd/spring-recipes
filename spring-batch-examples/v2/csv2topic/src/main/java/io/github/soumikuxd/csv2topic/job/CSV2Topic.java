package io.github.soumikuxd.csv2topic.job;

import io.github.soumikuxd.csv2topic.mappers.EmployeeFileRowMapper;
import io.github.soumikuxd.csv2topic.models.Employee;
import io.github.soumikuxd.csv2topic.processors.EmployeeProcessor;
import io.github.soumikuxd.csv2topic.writers.EmployeeKafkaWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableKafka
@AllArgsConstructor
public class CSV2Topic {
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;

    @Bean(name="csv2topicjob")
    public Job CSV2TopicJob() throws Exception {
        return new JobBuilder("csv2Topic", this.jobRepository)
                .start(CSV2TopicStep())
                .build();
    }

    @Bean
    public Step CSV2TopicStep() throws Exception {
        return new StepBuilder("csv2TopicStep", this.jobRepository)
                .<Employee, Employee>chunk(10, this.transactionManager)
                .reader(employeeFlatFileItemReader())
                .processor(this.employeeProcessor)
                .writer(employeeKafkaWriter())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> employeeFlatFileItemReader() throws Exception {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("employees.csv"));
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("employeeId", "firstName", "lastName", "email", "age");
                setDelimiter(",");
            }});
            setFieldSetMapper(new EmployeeFileRowMapper());
        }});
        return reader;
    }

    @Bean
    @StepScope
    public EmployeeKafkaWriter employeeKafkaWriter() {
        return new EmployeeKafkaWriter();
    }
}


