package com.song.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by song on 2017/2/5.
 */
@RestController()
@RequestMapping("/uaa")
public class UaaController {

    @GetMapping("/user")
    public Principal principal(Principal principal) {
        return principal;
    }
}
