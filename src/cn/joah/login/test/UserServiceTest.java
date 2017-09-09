package cn.joah.login.test;

import cn.joah.login.entity.User;
import cn.joah.login.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    @Test
    public void registerTest() throws Exception {
        UserService userService=new UserService();
        User user=new User();
        user.setUsername("summeringL");
        user.setPassword(String.valueOf("joah".hashCode()));
        user.setSex("female");
        user.setEmail("summering@joah.com");
        user.setWebsite("www.joah.com");
        user.setProfile("local");

        userService.register(user);
    }

}