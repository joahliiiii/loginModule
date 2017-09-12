package cn.joah.login.test;


import cn.joah.login.commons.jdbc.Utils;
import cn.joah.login.entity.User;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UtilsTest {
    @Test
    public void getConnectionTest() {
        Connection connection = Utils.getConnection();
        System.out.println("connection: "+connection);
    }
    @Test
    public void updateInfoTest(){
//        Utils utils=new Utils();
        String sql="insert into User(username,password) values(?,?)";
        Connection connection;
        connection = Utils.getConnection();
        Utils.updateInfo(connection,sql,"summering0","joah");

        Utils.release(connection,null,null);

    }
    @Test
   /* public void seletInfoTest2(){
        Connection connection=Utils.getConnection();
        String sql="select * from User where username=? ";
        ResultSet resultSet = Utils.selectInfo(connection, sql, "joah");
        try {
            if(resultSet!=null){
                // 获取元数据对象
                ResultSetMetaData meData=resultSet.getMetaData();
                int count = meData.getColumnCount();
                // 遍历输出每一列的名字
                for (int i = 1; i <= count; i++) {
                    System.out.print(meData.getColumnName(i)+"\t");
                }
                System.out.println();
                // 输出每个值
                while(resultSet.next()){
                    for (int i = 1; i <= count; i++) {
                        System.out.print(resultSet.getString(i)+"\t");
                    }
                    System.out.println();
                }
            }else{
                System.out.println("resultSet: 为空");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Utils.release(connection,null,resultSet);
        }
    }*/
    public void selectInfoUserTest(){
        Connection conn;
        String sql="select * from User";


        conn=Utils.getConnection();
//            System.out.println("connection: "+conn);
        User user=Utils.selectInfoUser(conn,sql);
        System.out.println("user: "+user.getUsername()+" pass: "+user.getPassword());
//            System.out.println(resultSet);
    }
}