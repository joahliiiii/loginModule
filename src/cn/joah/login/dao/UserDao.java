package cn.joah.login.dao;

import cn.joah.login.commons.jdbc.Utils;
import cn.joah.login.entity.User;
import cn.joah.login.service.UserException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 这个用来访问数据库的
 */
public class UserDao {
    /**
     * 使用用户名检索用户是否存在
     * @param uName
     * @return 如果不存在这个username就返回false
     */
    public boolean findByUsername(String uName) {
        /*
         * 先获取链接
         * 写好sql
         * 调用selectInfo(conng,sql,args)
         */

        Connection connection= Utils.getConnection();
        String sql="SELECT username,password FROM User WHERE username=? ";
        User user = Utils.selectInfoUser(connection, sql, uName);
        return user != null;
    }
    /**
     * 查询用户的密码,
     * @param uName 用户名
     * @return 如果有密码 返回密码的hash
     *          如果没密码 返回""
     */
    public String selectPasswordByUsername(String uName){
        /*
         * 先获取连接,
         * 写好 sql 语句
         * 先调用 findByUsername
         *      如果返回true
         *          调用 selectInfo 得到结果集,然后在得到第一行第一列的值
         *      如果返回false
         *          返回""
         */
        Connection connection = Utils.getConnection();
        String sql="SELECT password FROM User WHERE username=? ";
        boolean hsUname = findByUsername(uName);
        // 如果存在这个用户
        if(hsUname){
            // 得到User 对象
            User user = Utils.selectInfoUser(connection, sql, uName);
            if(user!=null){
                return user.getPassword();
            }
        }
        return "";
    }

    /**
     * 把一个用户对象插入到数据库内
     * @param user 用户对象
     */
    public void insertUser(User user)  {
        /**
         * 先获取连接
         * 写好sql
         * 调用 updateInfo() 插入数据
         */
        String sql="INSERT INTO User(username, password, sex, email, website, profile) VALUES(?,?,?,?,?,?) ";
        Connection connection=Utils.getConnection();
        // 在这里注意插入的是pass的 hashCode
        // 之后发现hash 结果和直接hash 不一样...不知道为什么 ..
        // 原因找出来了,原来是我进行了双重hash....()
        Utils.updateInfo(connection,sql,
                user.getUsername(),String.valueOf(user.getPassword().hashCode()),user.getSex(),user.getEmail(),user.getWebsite(),user.getProfile());
//
    }


}
