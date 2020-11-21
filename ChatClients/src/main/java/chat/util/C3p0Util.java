/*
package chat.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Util {
    private static ComboPooledDataSource dataSource =
            new ComboPooledDataSource("mysql");

    static Connection cn = null;

    static {
        if (cn == null) {
            try {
                cn = dataSource.getConnection();
            } catch (SQLException e) {
                System.out.println("读取MySQL配置异常");
                e.printStackTrace();
            }
        }
    }

    */
/**
     * 获取连接
     * @return
     *//*

    public static Connection getConnection() {
        return cn;
    }

    //关闭连接
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {

        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
*/
