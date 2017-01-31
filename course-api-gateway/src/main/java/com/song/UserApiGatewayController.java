package com.song;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.song.message.UserReader;
import com.song.message.UserWriter;
import com.song.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    private UserReader userReader;

    @Autowired
    private UserWriter userWriter;

    public Collection<String> getDefaultUsers() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/users")
    @HystrixCommand(fallbackMethod = "getDefaultUsers")
    public Collection<String> users() {
        return userReader.read()
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
