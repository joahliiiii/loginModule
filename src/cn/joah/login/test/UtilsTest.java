package cn.joah.login.test;


import cn.joah.login.commons.jdbc.Utils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UtilsTest {
    @Test
    public void getConnectionTest() {
        Connection connection = Utils.getConnection();
        System.out.println(connection);
    }
    @Test
    public void updateInfoTest(){
//        Utils utils=new Utils();
        String sql="insert into user(username,password) values(?,?)";
        Connection connection;
        connection = Utils.getConnection();
        Utils.updateInfo(connection,sql,"summering","joah");

        Utils.release(connection,null,null);

    }
    @Test
    public void selectInfoTest(){
        Connection conn;
        ResultSet resultSet;
        String sql="select * from user";

        try {
            conn=Utils.getConnection();
//            System.out.println("connection: "+conn);
            resultSet=Utils.selectInfo(conn,sql);
            /*resultSet.last();
            System.out.println("rows: "+resultSet.getRow());*/
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
            }
//            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}