package cn.joah.login.commons.jdbc;


import cn.joah.login.commons.reflect.ReflectUtils;
import cn.joah.login.entity.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
    public static void updateInfo(Connection conn ,String sql ,String ...args) {
        PreparedStatement preStatemt=null;
        try {
            preStatemt = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                // setXXX() 里面的第一个参数的下标是从 1 开始
                preStatemt.setObject(i+1,args[i]);
            }
            preStatemt.executeUpdate();

//            System.out.println("update success.");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
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
    /*public static ResultSet selectInfo(Connection conn,String sql){
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
    }*/

    /**
     * 带参数的查询
     * param conn 数据库连接
     * param sql sql语句
     * param args 不定参数
     * return 返回结果集
     *//*
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
    }*/

    /**
     * 查询语句, 返回结果的user对象,但是现在只能查询结果为一条记录
     *      解决了之前的返回resultSet,不能释放连接的bug...
     * @param conn 数据库连接
     * @param sql sql语句
     * @param args 不定参数
     * @return 返回user对象
     */
    public static User selectInfoUser(Connection conn, String sql, String ...args){
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        User user=null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                preparedStatement.setString(i+1,arg);
            }
            resultSet = preparedStatement.executeQuery();
            // 如果Map的大小为0 那么就返回null
            Map<String, String> map = resultsetToMap(resultSet);
            // 如果map里面没东西
            if(map.size()==0){
                return null;
            }else{
                user = ReflectUtils.toBean2(map,User.class);
            }

        } catch (SQLException | IllegalAccessException | InstantiationException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            release(conn,preparedStatement,resultSet);
        }
        return user;
    }

    /**
     * 把数据库查询到结果集转换成map
     * @param resultSet 结果集
     * @return 返回结果集的Map
     */
    public static Map<String,String> resultsetToMap(ResultSet resultSet){
        Map<String,String> map= new HashMap<>();
        try {
            // 如果resultSet有数据
            while(resultSet.next()){
                // 得到元数据
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                // 遍历一行所有的列
                for (int i = 1;i<=columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String string = resultSet.getString(i);
                    map.put(columnName,string);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    /*public Map<String, Map<String,String> > resultsetToMap(ResultSet resultSet, String uName){
        Map<String,Map<String,String>> map = new HashMap<>();
        Map<String,String> userInfo= new HashMap<>();

        System.out.println("map size: "+map.size());

        try {
            while(resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <=columnCount; i++) {
                    String columnName = metaData.getColumnName();
                    String string = resultSet.getString(i);
                    userInfo.put(columnName,string);
                }
            }
            map.put(uName,userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }*/
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
