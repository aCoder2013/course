package com.song.controller;

import com.song.model.User;
import com.song.repository.UserRepository;
import com.song.util.Result;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by song on 2017/2/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录操作
     *
     * @param mobile   手机号
     * @param password 密码，加密前
     */
    @PostMapping("/login")
    public Result login(String mobile, String password) {
        Result result = Result.create();
        if (StringUtils.isEmpty(mobile) | StringUtils.isEmpty(password)) {
            result.setMessage("用户名或密码不能为空!");
            return result;
        }
        Optional<User> userResult = userRepository.findByMobile(mobile);
        if (userResult.isPresent()) {
            User user = userResult.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                result.setSuccess(true);
                return result;
            }
            result.setMessage("密码不正确!");
        } else {
            result.setMessage("用户名不存在!");
        }
        return result;
    }
}
