package io.github.soumikuxd.listeners;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListenersApplication implements CommandLineRunner {
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("listenerjob")
	Job job;

	public static void main(String[] args) {
		SpringApplication.run(ListenersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters parameters = new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, parameters);
	}
}
