package com.song.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by song on 2017/1/21.
 */
@RefreshScope
@RestController
public class MessageController {

    @Value("${message:Hello default}")
    private String message;

    @GetMapping("message")
    public String getMessage() {
        return message;
    }
}
