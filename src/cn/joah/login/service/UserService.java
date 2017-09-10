package cn.joah.login.service;

import cn.joah.login.dao.UserDao;
import cn.joah.login.entity.User;

/**
 * user的业务逻辑
 */
public class UserService {

    /**
     * 注册功能
     * @param uName 用户名
     * @param user 用户对象
     * @throws UserException
     */
    /*public void register(String uName, User user) throws UserException{
        *//**
         * 先进行用户名的检测,查看用户名是否存在, findByUsername()
         *      如果存在那么返回错误星信息,
         *      否则,添加用户insertUser()
         *//*
        UserDao userDao=new UserDao();
        // 如果返回true 那么就是这个用户名已经存在
        if(userDao.findByUsername(uName)){
//            System.out.println(uName+" 已经存在! ");
            throw new UserException("用户名 "+uName+" 已被注册! ");
        }else{
            userDao.insertUser(user);
        }
    }*/
    public void register(User user) throws UserException{
        /**
         * 先进行用户名的检测,查看用户名是否存在, findByUsername()
         *      如果存在那么返回错误星信息,
         *      否则,添加用户insertUser()
         */
        UserDao userDao=new UserDao();
        // 如果返回true 那么就是这个用户名已经存在
        if(userDao.findByUsername(user.getUsername())){
//            System.out.println(uName+" 已经存在! ");
            throw new UserException("用户名 "+user.getUsername()+" 已被注册! ");
        }else{
            userDao.insertUser(user);
        }
    }

    /**
     * 登录
     * @param uName 用户名
     * @param password 密码
     * @return 登陆成功返回true 否则
     */
    public boolean login(String uName,String password)  {

        UserDao userDao = new UserDao();
        String dbPass = userDao.selectPasswordByUsername(uName);
        String hashPassword = String.valueOf(password.hashCode());
       /* System.out.println(" pass: "+dbPass+" password: "+password);
        System.out.println("hPassword: "+hashPassword);*/
        return hashPassword.equals(dbPass);
    }

    /**
     * 校验验证码是否写正确
     * @param captche  从表单获取的验证码
     * @param textFromSession 存在session里面的验证码文本
     * @return 如果相同就返回true 否则返回false
     */
    public boolean checkCaptche(String captche,String textFromSession){
        if(captche!=null){
            if(textFromSession.equalsIgnoreCase(captche)){
                return true;
            }
        }
        return false;
    }
}
