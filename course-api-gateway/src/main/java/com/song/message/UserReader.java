package com.song.message;

import com.song.domain.User;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by song on 2017/1/25.
 */
@FeignClient("course-services")
public interface UserReader {

    @RequestMapping(value = "/users/all",method = RequestMethod.GET)
    Resources<User> read();
}
