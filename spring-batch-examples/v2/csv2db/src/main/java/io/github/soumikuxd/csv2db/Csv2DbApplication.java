package io.github.soumikuxd.csv2db;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Csv2DbApplication {
	public static void main(String[] args) {
		SpringApplication.run(Csv2DbApplication.class, args);
	}
}
