package cn.joah.login.test;


import cn.joah.login.commons.jdbc.Utils;
import org.junit.Test;
import java.sql.Connection;

public class UtilsTest {
    @Test
    public void getConnectionTest() throws Exception {
        Connection connection = Utils.getConnection();
        System.out.println(connection);
    }

}