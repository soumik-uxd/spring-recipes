package io.github.soumikuxd.springbatchexamples.jobs;

import io.github.soumikuxd.springbatchexamples.mappers.EmployeeDBRowMapper;
import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.processors.EmployeeProcessor;
import io.github.soumikuxd.springbatchexamples.writer.EmailWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class EmailSender {
    private DataSource dataSource;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;

    @Bean(name="emailsenderjob")
    public Job EmailSenderJob() throws Exception {
        return new JobBuilder("EmailSender", this.jobRepository)
                .start(EmailSenderStep())
                .build();
    }

    @Bean
    public Step EmailSenderStep() throws Exception {
        return new StepBuilder("emailSenderstep", this.jobRepository)
                .<Employee, Employee>chunk(5, this.transactionManager)
                .reader(employeeDBReader3())
                .processor(this.employeeProcessor)
                .writer(emailSenderWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<? super Employee> emailSenderWriter() {
        return new EmailWriter();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Employee> employeeDBReader3() {
        JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("select * from employees_copy where length(employee_id) = 4;");
        reader.setRowMapper(new EmployeeDBRowMapper());
        return reader;
    }
}


