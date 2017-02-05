package com.song.repository;

import com.song.model.User;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by song on 2017/2/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByNameOrMobileIgnoreCase() throws Exception {
        List<User> users = userRepository.findByNameOrMobileIgnoreCase("MarS", null);
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() == 1);
    }
}