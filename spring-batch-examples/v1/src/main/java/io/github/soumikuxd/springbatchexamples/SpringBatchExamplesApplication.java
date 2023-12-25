package io.github.soumikuxd.springbatchexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringBatchExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchExamplesApplication.class, args);
	}

}
