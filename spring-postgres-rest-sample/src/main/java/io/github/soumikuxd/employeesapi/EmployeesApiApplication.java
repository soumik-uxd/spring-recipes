package io.github.soumikuxd.employeesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class EmployeesApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeesApiApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.github.soumikuxd"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Employee API",
				"A sample spring boot REST with a Postgres DB",
				"0.1",
				"",
				new Contact("Soumik Das", "http://soumik-uxd.github.io", "soumik.uxd@gmail.com"),
				"MIT License",
				"",
				Collections.emptyList()
		);
	}
}
