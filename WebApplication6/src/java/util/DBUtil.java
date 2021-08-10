/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class DBUtil {
    public static Connection getConnection(){
        String url ="jdbc:sqlserver://localhost:1433;databaseName=FoodManagement";
        String user = "sa";
        String pass = "123";
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        
        return conn;
    }
    
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection!=null) {
           
        }
    }
}
