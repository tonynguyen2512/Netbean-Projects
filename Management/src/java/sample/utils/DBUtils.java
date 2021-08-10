/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
<%-- 
    Document   : index
    Created on : Mar 13, 2021, 9:58:50 AM
    Author     : hd
--%>
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLSyntaxErrorException;
import javax.servlet.ServletException;

/**
 *
 * @author hd
 */
public class DBUtils {

    public static Connection getConnection(){
        String url ="jdbc:sqlserver://localhost:1433;databaseName=FoodManagement";
        String user = "sa";
        String pass = "251201";
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
