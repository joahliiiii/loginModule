package cn.joah.login.commons.jdbc;

import cn.joah.login.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

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
//    private static Connection connection=null;
//    private static Statement state=null;
//    private static PreparedStatement preStatemt=null;
//    private static ResultSet resultSet=null;



    /**
     * 返回数据库连接
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 适用于 INSERT , UPDATE,
     * @param conn 数据库连接
     * @param sql sql语句
     * @param args 不定参数
     * @throws SQLException
     */
    public static void updateInfo(Connection conn ,String sql ,String ...args){
        PreparedStatement preStatemt=null;
        try {
            preStatemt = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                // setXXX() 里面的第一个参数的下标是从 1 开始
                preStatemt.setObject(i+1,args[i]);
            }
            preStatemt.executeUpdate();


//            System.out.println("update success.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(conn,preStatemt,null);
        }
    }

/*    public static void updateInfo(Connection conn , String sql , User user){
        updateInfo(conn,sql,user);
    }*/
    /**
     * 查找  这里不建议返回的resultSet是成员变量.
     * 在这里要注意,如果需要返回的resultSet是成员变量,那么就要注意了,不要吧resultSet释放了,
     * 但是哪个都不能释放啊.,难道不释放么?
     * @param conn 数据库连接
     * @param sql sql语句
//     * @param args 不定参数
     */
    public static ResultSet selectInfo(Connection conn,String sql){
        PreparedStatement preStatemt=null;
        ResultSet resultSet1=null;
        try {
//            System.out.println("SQL: "+sql+"\n conn: "+conn);
            preStatemt=conn.prepareStatement(sql);
            resultSet1=preStatemt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            release(conn,preStatemt,null);
        }
        return resultSet1;
    }

    /**
     * 带参数的查询
     * @param conn 数据库连接
     * @param sql sql语句
     * @param args 不定参数
     * @return 返回结果集
     */
    public static ResultSet selectInfo(Connection conn,String sql, String ...args){
        PreparedStatement preSdtmt=null;
        ResultSet resSet=null;

        try {
            preSdtmt=conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preSdtmt.setObject(i+1,args[i]);
            }
            resSet=preSdtmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            Utils.release(conn,preSdtmt,resSet);
        }
        return resSet;
    }
    /**
     * 释放资源,最后创建的最先释放
     * 我也不知道这个释放资源方法是否完全正确 .但是感觉是正确的.
     * @param conn 数据库连接
     * @param preState preparedStatement
     * @param resSet 结果集
     */
    public static void release(Connection conn,PreparedStatement preState,ResultSet resSet){
        if(resSet!=null){
            try {
                resSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preState!=null){
            try {
                preState.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
