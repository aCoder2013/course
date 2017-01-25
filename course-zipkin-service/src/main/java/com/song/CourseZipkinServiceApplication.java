package com.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;

@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class CourseZipkinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseZipkinServiceApplication.class, args);
	}
}
