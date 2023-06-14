package com.bree.paymentprocessingmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentProcessingManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessingManageApplication.class, args);
	}

}
