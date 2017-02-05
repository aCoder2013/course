package com.song.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by song on 2017/1/25.
 */
public interface CourseChannel {

    @Output
    MessageChannel output();
}