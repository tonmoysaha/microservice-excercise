package com.course.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CourseCatelogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseCatelogApplication.class, args);
	}

}
