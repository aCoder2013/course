package com.song.message;


import com.song.model.User;
import com.song.util.Result;

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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    Resources<User> read();

    @RequestMapping(value = "/users/search/by-name-or-mobile", method = RequestMethod.GET)
    Resources<User> readByNameOrMobile(@RequestParam("name") String name, @RequestParam("mobile") String mobile);

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    Result readByMobileAndPassword(@RequestParam("mobile") String mobile, @RequestParam("password") String password);
}
