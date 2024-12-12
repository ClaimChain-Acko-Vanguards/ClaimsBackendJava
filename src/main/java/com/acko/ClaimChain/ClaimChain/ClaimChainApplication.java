package com.acko.ClaimChain.ClaimChain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClaimChainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimChainApplication.class, args);
	}

}
