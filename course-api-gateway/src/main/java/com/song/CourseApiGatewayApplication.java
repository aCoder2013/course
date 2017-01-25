package com.song;

import com.song.message.CourseChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;


@EnableFeignClients
@IntegrationComponentScan
@EnableBinding(CourseChannel.class)
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class CourseApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApiGatewayApplication.class, args);
    }


    @Bean
    public Sampler sampler(){
        return new AlwaysSampler();
    }
}
