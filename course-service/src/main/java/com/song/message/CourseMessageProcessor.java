package com.song.message;

import com.song.model.User;
import com.song.repository.UserRepository;
import com.song.vo.UserVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by song on 2017/1/25.
 */
@MessageEndpoint
public class CourseMessageProcessor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ServiceActivator(inputChannel = "input")
    public void handleUserMessage(Message<UserVO> msg) {
        UserVO userVO = msg.getPayload();
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long now = System.currentTimeMillis();
        user.setCreated(now);
        user.setUpdated(now);
        userRepository.save(user);
    }
}
