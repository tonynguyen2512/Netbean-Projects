package thien.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn= null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//com.microsoft.sqlserver.jdbc.SQLServerDriver
        String url="jdbc:sqlserver://LocalHost:1433;databaseName=UserManagement";
        conn= DriverManager.getConnection(url, "sa", "251201");
        return conn;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        if(conn != null){
            System.out.println("11111111");
        }else{
            System.out.println("ffffff");
        }
    }
}
