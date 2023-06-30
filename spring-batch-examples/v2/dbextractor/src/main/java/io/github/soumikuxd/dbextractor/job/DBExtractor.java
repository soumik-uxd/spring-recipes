package io.github.soumikuxd.dbextractor.job;

import io.github.soumikuxd.dbextractor.mappers.EmployeeDBRowMapper;
import io.github.soumikuxd.dbextractor.models.Employee;
import io.github.soumikuxd.dbextractor.processors.EmployeeProcessor;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DBExtractor {
    private DataSource dataSource;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;

    @Bean(name="dbextractorjob")
    public Job DBExtractorJob() throws Exception {
        return new JobBuilder("dbextractor", this.jobRepository)
                .start(DBExtractorStep())
                .build();
    }

    @Bean
    public Step DBExtractorStep() throws Exception {
        return new StepBuilder("dbextractorstep", this.jobRepository)
                .<Employee, Employee>chunk(10, this.transactionManager)
                .reader(employeeDBReader())
                .processor(employeeProcessor)
                .writer(employeeFileWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemStreamReader<Employee> employeeDBReader() {
        JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("select * from employees;");
        reader.setRowMapper(new EmployeeDBRowMapper());
        return reader;
    }

    @Bean
    public ItemWriter<Employee> employeeFileWriter() throws Exception {
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output/employees_extract.csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<Employee>() {{
            setFieldExtractor(new BeanWrapperFieldExtractor<Employee>() {{
                setNames(new String[]{"employeeId", "firstName", "lastName", "email", "age"});
            }});
        }});
        writer.setShouldDeleteIfExists(true);
        return writer;
    }
}
