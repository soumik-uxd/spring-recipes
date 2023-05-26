package io.github.soumikuxd.springbatchexamples.jobs;

import io.github.soumikuxd.springbatchexamples.mappers.EmployeeFileRowMapper;
import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.processors.EmployeeProcessor;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class TaskExec {
    private DataSource dataSource;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;

    @Bean(name="taskexecjob")
    public Job TaskExecJob() throws Exception {
        return new JobBuilder("TaskExec", this.jobRepository)
                .start(CleanupStep())
                .next(TaskExecStep())
                .build();
    }

    @Bean
    public Step CleanupStep() throws Exception {
        return new StepBuilder("cleanupstep", this.jobRepository)
                .tasklet(cleanupTasklet(), this.transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet cleanupTasklet() {
        return (contribution, chunkContext) -> {
            new JdbcTemplate(this.dataSource).execute("DELETE FROM employees;");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step TaskExecStep() throws Exception {
        return new StepBuilder("taskexecstep", this.jobRepository)
                .<Employee, Employee>chunk(10, this.transactionManager)
                .reader(employeeFlatFileItemReader2())
                .processor(this.employeeProcessor)
                .writer(employeeDBWriter2())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> employeeFlatFileItemReader2() throws Exception {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileResource2(null));
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
    public Resource inputFileResource2(@Value("#{jobParameters['fileName']}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Employee> employeeDBWriter2() {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into employees (employee_id, first_name, last_name, email, age) values (:employeeId, :firstName, :lastName, :email, :age)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
        return writer;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(5);
        return simpleAsyncTaskExecutor;
    }
}


