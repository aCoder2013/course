package com.song.service;

import com.song.message.UserReader;
import com.song.message.UserWriter;
import com.song.model.User;
import com.song.util.Result;
import com.song.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by song on 2017/2/4.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserReader userReader;

    @Autowired
    private UserWriter userWriter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userReader.readByNameOrMobile(username, null)
                .getContent()
                .stream()
                .findFirst()
                .map(user -> new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("could find user with name :" + username));
    }

    /**
     * 注册新用户，这里会检查用户名或手机号是否已经存在
     *
     * @param userVO user 对象
     * @return 是否成功注册
     */
    public Result register(UserVO userVO) {
        Result result = Result.create();
        Optional<User> userInDB = userReader.readByNameOrMobile(userVO.getName(), userVO.getMobile()).getContent().stream().findFirst();
        if (userInDB.isPresent()) {
            result.setMessage("用户名或手机号已经存在!");
            return result;
        }
        userWriter.write(userVO);
        result.setSuccess(true);
        return result;
    }

    public Result login(String mobile,String password){
        return userReader.readByMobileAndPassword(mobile,password);
    }
}
