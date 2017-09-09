package cn.joah.login.dao;

import cn.joah.login.commons.jdbc.Utils;
import cn.joah.login.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个用来访问数据库的
 */
public class UserDao {
    /**
     * 使用用户名检索用户是否存在
     * @param uName
     * @return 如果查询结果不为空,那么返回ture  否则返回false
     */
    public boolean findByUsername(String uName){
        /**
         * 先获取链接
         * 写好sql
         * 调用selectInfo(conng,sql,args)
         * 如果resultSet.getMetaData().getColumnCount() 为 1 那么不存在,否则存在
         */
        Connection connection= Utils.getConnection();
        String sql="SELECT username FROM User WHERE username=? ";
        ResultSet resultSet=Utils.selectInfo(connection,sql,uName);
        try {
            int count = resultSet.getMetaData().getColumnCount();
            // 如果只有一行列名, 那么不存在, 返回false
            if(count ==1) {
                return false;
            }/*else if(count == 2){
                return true;
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 把一个用户对象插入到数据库内
     * @param user 用户对象
     */
    public void insertUser(User user){
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
