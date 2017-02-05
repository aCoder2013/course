package com.song.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.song.message.UserReader;
import com.song.model.User;
import com.song.service.UserService;
import com.song.util.Result;
import com.song.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.Valid;


/**
 * Created by song on 2017/1/21.
 */
@RestController
@RequestMapping("/user")
public class UserApiGatewayController {

    @Autowired
    private UserReader userReader;

    @Autowired
    private UserService userService;

    @Auto
    @LoadBalanced
    private RestTemplate restTemplate;

    public Collection<String> getDefaultUsers() {
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/all")
    @HystrixCommand(fallbackMethod = "getDefaultUsers")
    public Collection<String> users() {
        return userReader.read()
                .getContent()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @PostMapping("/register")
    public Result newUsers(@Valid UserVO user, BindingResult bindingResult) {
        Result result = Result.create();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            result.setMessage(fieldError != null ? fieldError.getDefaultMessage() : "字段不合法，请重新检查!");
            return result;
        }
        result = userService.register(user);
        return result;
    }

}
