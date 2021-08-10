/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.untils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBUntils {
    
//    public static Connection getConnection() throws ClassNotFoundException, SQLException{
//        Connection conn = null;
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=userManagement";
//        conn = DriverManager.getConnection(url, "se140296", "se140296");
//        return conn;
//    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException, NamingException{
        Connection conn = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds =(DataSource) end.lookup("DBCon");
        conn = ds.getConnection();
        return conn ;
    }
//        public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
//        Connection connection = getConnection();
//        if(connection != null)
//            System.out.println("Connection successful\n"+connection);
//        else
//            System.out.println("Connection fail");
//    }
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
//        Connection connection = getConnection();
//        if(connection != null)
//            System.out.println("Connection successful\n"+connection);
//        else
//            System.out.println("Connection fail");
//    }
}
