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
    public boolean findByUsername(String uName) throws UserException {
        /**
         * 先获取链接
         * 写好sql
         * 调用selectInfo(conng,sql,args)
         */
        Connection connection= Utils.getConnection();
        String sql="SELECT count(*) FROM User WHERE username=? ";
        ResultSet resultSet=Utils.selectInfo(connection,sql,uName);

        try {
            resultSet.next();
            int value = resultSet.getInt(1);
            // 得到第二行第一列的值
            if(value != 0){
                return  true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
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
        Utils.updateInfo(connection,sql,
                user.getUsername(),String.valueOf(user.getPassword().hashCode()),user.getSex(),user.getEmail(),user.getWebsite(),user.getProfile());

    }
}
