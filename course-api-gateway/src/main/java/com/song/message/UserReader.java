package com.song.message;


import com.song.model.User;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by song on 2017/1/25.
 */
@FeignClient("course-service")
public interface UserReader {

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    Resources<User> read();

    @RequestMapping(value = "/users/search/by-name",method = RequestMethod.GET)
    Resources<User> readByName(@RequestParam("name") String name);
}
