package com.dc.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// @SpringBootApplication
public class PharmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
	}

}
