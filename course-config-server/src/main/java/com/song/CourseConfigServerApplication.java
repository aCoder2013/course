package com.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CourseConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseConfigServerApplication.class, args);
    }
}
