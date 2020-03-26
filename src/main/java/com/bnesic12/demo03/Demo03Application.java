package com.bnesic12.demo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo03Application {

	public static void main(String[] args) {
		System.out.println("Demo03Application.ctor(): start/end");
		SpringApplication.run(Demo03Application.class, args);
	}

}
