package io.github.soumikuxd.springbatchexamples.jobs;

import io.github.soumikuxd.springbatchexamples.mappers.EmployeeFileRowMapper;
import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.processors.EmployeeProcessor;
import io.github.soumikuxd.springbatchexamples.utils.Constants;
import io.github.soumikuxd.springbatchexamples.services.JobSkipPolicy;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class ExecContext {
    private DataSource dataSource;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;
    private ExecutionContext executionContext;
    private JobSkipPolicy skipPolicy;

    @Bean(name="execcontextjob")
    public Job ExecContextJob() throws Exception {
        return new JobBuilder("execcontext", this.jobRepository)
                .start(ExecContextStep())
                .build();
    }

    @Bean
    public Step ExecContextStep() throws Exception {
        return new StepBuilder("execcontextstep", this.jobRepository)
                .<Employee, Employee>chunk(10, this.transactionManager)
                .reader(employeeFlatFileItemReader3())
                .processor(this.employeeProcessor)
                .writer(employeeDBWriter3())
                .faultTolerant().skipPolicy(skipPolicy)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> employeeFlatFileItemReader3() throws Exception {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileResource3());
        reader.setLineMapper(new DefaultLineMapper<Employee>() {{
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
    public Resource inputFileResource3() throws Exception {
        return new ClassPathResource(this.executionContext.getString(Constants.FILE_NAME_CONTEXT_KEY));
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Employee> employeeDBWriter3() {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into employees (employee_id, first_name, last_name, email, age) values (:employeeId, :firstName, :lastName, :email, :age)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
        return writer;
    }
}


