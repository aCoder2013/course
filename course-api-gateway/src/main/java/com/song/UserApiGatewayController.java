package com.song;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.song.domain.User;
import com.song.message.UserWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by song on 2017/1/21.
 */
@RestController
public class UserApiGatewayController {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private UserWriter userWriter;

    public Collection<String> getDefaultUsers() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/users")
    @HystrixCommand(fallbackMethod = "getDefaultUsers")
    public Collection<String> users() {
        ParameterizedTypeReference<Resources<User>> ptr = new ParameterizedTypeReference<Resources<User>>() {
        };
        ResponseEntity<Resources<User>> exchange = restTemplate.exchange("http://course-service/users", HttpMethod.GET, null, ptr);
        return exchange
                .getBody()
                .getContent()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/new")
    public String newUsers(User user) {
        userWriter.write(user.getName());
        return "Success";
    }
}
