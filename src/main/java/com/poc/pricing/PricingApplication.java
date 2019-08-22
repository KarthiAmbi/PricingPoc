package com.poc.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableCaching
@SpringBootApplication
@ComponentScan("com.poc.pricing.*")
public class PricingApplication {


	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(PricingApplication.class, args);
	}
}
