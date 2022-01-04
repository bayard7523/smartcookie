package com.smartcookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContainerInitializer;

@SpringBootApplication
public class SmartCookieApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SmartCookieApplication.class, args);
	}
}
