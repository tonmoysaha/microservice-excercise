package com.user;

import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserAppApplication {

	@Autowired
	UserRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

}
