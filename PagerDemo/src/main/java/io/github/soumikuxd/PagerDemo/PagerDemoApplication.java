package io.github.soumikuxd.PagerDemo;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PagerDemoApplication {
	@Configuration
	@NoArgsConstructor
	public class DBHandler {
		@PostConstruct
		public void initDB() {
			// Load data into table
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(PagerDemoApplication.class, args);
	}
}
