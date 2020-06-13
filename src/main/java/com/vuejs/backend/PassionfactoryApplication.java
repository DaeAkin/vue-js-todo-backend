package com.vuejs.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class PassionfactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassionfactoryApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods(HttpMethod.POST.name())
						.allowedMethods(HttpMethod.PUT.name())
						.allowedMethods(HttpMethod.GET.name())
						.allowedMethods(HttpMethod.DELETE.name())
						.allowCredentials(false)
						.maxAge(3600);
			}
		};
	}
}
