package com.song.message;

import com.song.model.User;
import com.song.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * Created by song on 2017/1/25.
 */
@MessageEndpoint
public class CourseMessageProcessor {

    @Autowired
    private UserRepository userRepository;

    @ServiceActivator(inputChannel = "input")
    public void receivedMessage(Message<String> msg) {
        userRepository.save(new User(msg.getPayload()));
    }
}
