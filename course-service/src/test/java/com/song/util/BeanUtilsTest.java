package com.song.util;

import com.song.model.User;
import com.song.vo.UserVO;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created by song on 2017/2/5.
 */
public class BeanUtilsTest {

    @Test
    public void test() {
        UserVO userVO = new UserVO();
        userVO.setMobile("18813975969");
        userVO.setName("Mars");
        userVO.setPassword("123");
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        System.out.println(user.getName());
        System.out.println(user.getMobile());
        System.out.println(user.getPassword());
    }
}
