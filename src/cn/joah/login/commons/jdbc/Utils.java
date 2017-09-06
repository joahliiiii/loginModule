package cn.joah.login.commons.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 使用c3p0
 * 导包: c3p0,mchange
 * 需要写的函数:
 * 1.获取连接
 * 2.增删查改
 * 3.关闭数据库
 */
public class Utils {
    private static DataSource dataSource=null;
    static{
//        System.setProperty("com.mchange.v2.c3p0.cfg.xml", "configs/c3p0-config.xml");
        dataSource=new ComboPooledDataSource("c3p0config");
    }
    private static Connection connection=null;

    /**
     * 返回数据库连接
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        connection=dataSource.getConnection();
        return connection;
    }

}
