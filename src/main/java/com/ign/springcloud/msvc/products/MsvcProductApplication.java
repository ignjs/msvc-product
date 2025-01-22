package com.ign.springcloud.msvc.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ign.libs.msvc.commons.entity")
public class MsvcProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductApplication.class, args);
	}

}
