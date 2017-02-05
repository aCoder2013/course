package com.song.message;

import com.song.vo.UserVO;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by song on 2017/1/25.
 */
@MessagingGateway
public interface UserWriter {

    @Gateway(requestChannel = "output")
    void write(UserVO userVO);
}
