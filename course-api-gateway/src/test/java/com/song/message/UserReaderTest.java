package com.song.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by song on 2017/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserReaderTest {

    @Autowired
    private UserReader userReader;

    @Test
    public void read() throws Exception {
        userReader.read().toString();
    }

}