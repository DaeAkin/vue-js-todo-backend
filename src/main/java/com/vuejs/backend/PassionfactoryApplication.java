package com.vuejs.backend;

import com.vuejs.backend.domain.user.dao.UserRepository;
import com.vuejs.backend.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class PassionfactoryApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PassionfactoryApplication.class, args);

	}
	


}
