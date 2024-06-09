package com.backend.StudentTipMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentTipMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTipMasterApplication.class, args);
	}

}
