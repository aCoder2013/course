package com.song.service;

import com.song.message.UserReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by song on 2017/2/4.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserReader userReader;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userReader.readByName(username)
                .getContent()
                .stream()
                .findFirst()
                .map(user -> new User(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN")))
                .orElseThrow(() -> new UsernameNotFoundException("could find user with name :" + username));
    }
}
