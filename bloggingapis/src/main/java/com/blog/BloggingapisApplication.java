package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EnableAutoConfiguration
@EntityScan("com.blog.entity")
@SpringBootApplication
public class BloggingapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingapisApplication.class, args);
		System.out.println("Hey Blogger");
	}

}
