package com.song.controller;

import com.song.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by song on 2017/2/5.
 */
@RestController
@RequestMapping("/users")
public class UserController {


    @PostMapping("/query")
    public Collection<User> users(){

        return null;
    }

}
