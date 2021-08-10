package thien.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBUtils {
    public static Connection getConnection1() throws ClassNotFoundException, SQLException{
        Connection conn= null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//com.microsoft.sqlserver.jdbc.SQLServerDriver
        String url="jdbc:sqlserver://LocalHost:1433;databaseName=UserManagement";
        conn= DriverManager.getConnection(url, "sa", "251201");
        return conn;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException{
        Connection conn= null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBCon");
        conn = ds.getConnection();
        return conn;
    }
}

