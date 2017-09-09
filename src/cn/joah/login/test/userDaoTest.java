package cn.joah.login.test;

import cn.joah.login.dao.UserDao;
import cn.joah.login.entity.User;
import cn.joah.login.service.UserException;
import org.junit.Test;

public class userDaoTest {
    @Test
    public void findByUsernameTest() throws UserException {
        UserDao UserDao =new UserDao();
        boolean find = UserDao.findByUsername("joah");
        if(find){
            System.out.println("joah 在数据库内存在!");
        }else{
            System.out.println("joah 在数据库内不存在!");
        }
    }
    @Test
    public void insertUserTest(){
        User user=new User();
        user.setUsername("summering");
        user.setPassword(String.valueOf("joah".hashCode()));
        user.setSex("female");
        user.setEmail("summering@joah.com");
        user.setWebsite("www.joah.com");
        user.setProfile("local");
        UserDao userDao=new UserDao();
        userDao.insertUser(user);
    }

}