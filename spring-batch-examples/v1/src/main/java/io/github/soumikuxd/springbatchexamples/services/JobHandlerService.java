package io.github.soumikuxd.springbatchexamples.services;

import io.github.soumikuxd.springbatchexamples.utils.Constants;
import io.github.soumikuxd.springbatchexamples.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobHandlerService {
    private static final Logger logger = LoggerFactory.getLogger(JobHandlerService.class);
    @Autowired
    private final JobLauncher simpleJobLauncher;

    @Autowired
    private final ExecutionContext executionContext;

    @Qualifier("csv2dbjob")
    private final Job csv2dbjob;
    @Qualifier("dbextractorjob")
    private final Job dbextractorjob;

    @Qualifier("emailsenderjob")
    private final Job emailsenderjob;

    @Qualifier("taskexecjob")
    private final Job taskexecjob;
    @Qualifier("execcontextjob")
    private final Job execcontextjob;

    @Qualifier("listenerjob")
    private final Job listenerjob;

    @Qualifier("fixedtxt2dbjob")
    private final Job fixedtxt2dbjob;

    @Qualifier("dbcleanerjob")
    private final Job dbcleanerjob;

    @Qualifier("agesummaryjob")
    private final Job agesummaryjob;

    @Qualifier("csv2topicjob")
    private final Job csv2topicjob;

    public JobHandlerService(JobLauncher simpleJobLauncher, ExecutionContext executionContext, Job csv2dbjob, Job dbextractorjob, Job emailsenderjob, Job taskexecjob, Job execcontextjob, Job listenerjob, Job fixedtxt2dbjob, Job dbcleanerjob, Job agesummaryjob, Job csv2topicjob) {
        this.simpleJobLauncher = simpleJobLauncher;
        this.executionContext = executionContext;
        this.csv2dbjob = csv2dbjob;
        this.dbextractorjob = dbextractorjob;
        this.emailsenderjob = emailsenderjob;
        this.taskexecjob = taskexecjob;
        this.execcontextjob = execcontextjob;
        this.listenerjob = listenerjob;
        this.fixedtxt2dbjob = fixedtxt2dbjob;
        this.dbcleanerjob = dbcleanerjob;
        this.agesummaryjob = agesummaryjob;
        this.csv2topicjob = csv2topicjob;
    }

    @Async
    public void submit(String jobName) throws ResourceNotFoundException {
        // Job job = this.getJobFromName(jobName);
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date(), true);
        switch (jobName) {
            case "csv2dbjob" -> {
                jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
                this.execute(csv2dbjob, jobParametersBuilder.toJobParameters());
            }
            case "taskexecjob" -> {
                jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
                this.execute(taskexecjob, jobParametersBuilder.toJobParameters());
            }
            case "execcontextjob" -> {
                executionContext.putString(Constants.FILE_NAME_CONTEXT_KEY, "employees2.csv");
                this.execute(execcontextjob, jobParametersBuilder.toJobParameters());
            }
            case "listenerjob" -> {
                jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
                this.execute(listenerjob, jobParametersBuilder.toJobParameters());
            }
            case "csv2topicjob" -> {
                jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
                this.execute(csv2topicjob, jobParametersBuilder.toJobParameters());
            }
            case "fixedtxt2dbjob" -> {
                jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.txt");
                this.execute(fixedtxt2dbjob, jobParametersBuilder.toJobParameters());
            }
            case "dbextractorjob" -> this.execute(dbextractorjob, jobParametersBuilder.toJobParameters());
            case "dbcleanerjob" -> this.execute(dbcleanerjob, jobParametersBuilder.toJobParameters());
            case "agesummaryjob" -> this.execute(agesummaryjob, jobParametersBuilder.toJobParameters());
            case "emailsenderjob" -> this.execute(emailsenderjob, jobParametersBuilder.toJobParameters());
            default -> {
                logger.error("Incorrect job name: " + jobName);
                throw new ResourceNotFoundException("Incorrect job name: " + jobName);
            }
        }
    }

    /*private Job getJobFromName(String jobName) {
        return null;
    }*/

    private void execute(Job job, JobParameters parameters) {
        try {
            JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
        } catch (JobExecutionAlreadyRunningException e) {
            logger.info("Job with fileName={} is already running.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobRestartException e) {
            logger.info("Job with fileName={} was not restarted.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.info("Job with fileName={} already completed.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobParametersInvalidException e) {
            logger.info("Invalid job parameters: {}.", parameters.getParameters());
        }
    }
}
