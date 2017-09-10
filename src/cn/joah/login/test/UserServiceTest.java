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
        user.setUsername("qwerty123");
//        String pass="123456789";
        //在这里只需要吧值穿进去就可以了
//        user.setPassword(String.valueOf(pass.hashCode()));
        user.setPassword("123456789");
        user.setSex("female");
        user.setEmail("summering@joah.com");
        user.setWebsite("www.joah.com");
        user.setProfile("local");

        userService.register(user);
    }
    @Test
    public void loginTest(){
        UserService userService= new UserService();
        boolean loginStatus = userService.login("qwerty123", "123456789");
//        System.out.println("123456789: "+String.valueOf("123456789".hashCode())+" String123456789: "+String.valueOf(new String("123456789").hashCode()));
        if (loginStatus){
            System.out.println("登陆成功!");
        }else{
            System.out.println("用户名或密码错误!");
        }
    }

}