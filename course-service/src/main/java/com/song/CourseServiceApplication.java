package com.song;

import com.song.message.CourseChannel;
import com.song.model.User;
import com.song.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@EnableBinding(CourseChannel.class)
@EnableDiscoveryClient
@SpringBootApplication
public class CourseServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Bean
    public Sampler sampler() {
        return new AlwaysSampler();
    }

    @Component
    static class DummyDataCLR implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... strings) throws Exception {
            Stream.of("Mars", "Jacky", "Lucy", "Mike")
                    .forEach(name -> userRepository.save(new User(name,passwordEncoder.encode("123456"))));
            userRepository.findAll().forEach(System.out::println);
        }
    }

}
