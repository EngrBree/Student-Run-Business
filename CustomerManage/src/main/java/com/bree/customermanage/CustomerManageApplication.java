package com.bree.customermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManageApplication.class, args);
    }

}
