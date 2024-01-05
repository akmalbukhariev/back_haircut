package com.barbie.haircut.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages = {"com.barbie.haircut.api"})
@SpringBootApplication
@EnableScheduling
//@PropertySource("classpath:application.yaml")
public class HaircutApplication {
	public static void main(String[] args) {
		SpringApplication.run(HaircutApplication.class, args);
	}

}
