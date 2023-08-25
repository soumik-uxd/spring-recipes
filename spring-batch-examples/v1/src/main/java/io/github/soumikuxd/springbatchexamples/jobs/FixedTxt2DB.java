package io.github.soumikuxd.springbatchexamples.jobs;

import io.github.soumikuxd.springbatchexamples.mappers.EmployeeFileRowMapper;
import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.processors.EmployeeProcessor;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class FixedTxt2DB {
    private static final Logger logger = LoggerFactory.getLogger(FixedTxt2DB.class);

    private DataSource dataSource;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private EmployeeProcessor employeeProcessor;

    @Bean(name="fixedtxt2dbjob")
    public Job FixedTxt2DBJob() throws Exception {
        return new JobBuilder("fixedtxt2db", this.jobRepository)
                .start(FixedTxt2DBStep())
                .build();
    }

    @Bean
    public Step FixedTxt2DBStep() throws Exception {
        return new StepBuilder("fixedtxt2dbstep", this.jobRepository)
                .<Employee, Employee>chunk(10, this.transactionManager)
                .reader(employeeFlatFileItemReader6())
                .processor(this.employeeProcessor)
                .writer(employeeDBWriter6())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Employee> employeeFlatFileItemReader6() throws Exception {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileResource6(null));
        reader.setLinesToSkip(1); // Skip header
        reader.setSkippedLinesCallback(line -> {
            logger.info("Header: {}", line);
        });
        reader.setLineMapper(new DefaultLineMapper<Employee>() {{
            setLineTokenizer(new FixedLengthTokenizer() {{
                setNames("employeeId", "firstName", "lastName", "email", "age");
                setColumns(new Range(1, 5), new Range(6, 10), new Range(11, 15), new Range(16, 30), new Range(31, 33));
                setStrict(false);
            }});
            setFieldSetMapper(new EmployeeFileRowMapper());
        }});
        return reader;
    }

    @Bean
    @StepScope
    public Resource inputFileResource6(@Value("#{jobParameters['fileName']}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Employee> employeeDBWriter6() {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into employees (employee_id, first_name, last_name, email, age) values (:employeeId, :firstName, :lastName, :email, :age)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
        return writer;
    }


}


