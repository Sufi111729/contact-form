package com.contect.sufi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.contect.sufi.entity") // 👈 Add this
@EnableJpaRepositories("com.contect.sufi.repository")

public class ContectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContectBackEndApplication.class, args);
	}

}
