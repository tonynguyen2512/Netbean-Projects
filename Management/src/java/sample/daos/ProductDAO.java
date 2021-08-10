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
package sample.daos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dtos.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import sample.utils.DBUtils;

/**
 *
 * @author hd
 */
public class ProductDAO {
        public void add(String idString, String nameString, String description, float price, int cookingTime) 
                throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DBUtils.getConnection();
        try {
            String sql = "INSERT tblFoods(id,name,description,price,cookingTime,status,createDate) VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idString);
            ps.setString(2, nameString);
            ps.setString(3, description);
            ps.setFloat(4, price);
            ps.setInt(5, cookingTime);
            ps.setString(6, "true");
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
        
    public ArrayList<ProductDTO> search() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = DBUtils.getConnection();
        ArrayList<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT id,name,description,price,cookingTime,status,createDate FROM tblFoods WHERE status ='true'";
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getBoolean(6), rs.getTimestamp(7)));
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public void update(String id, String name, String description, float prce, int cookingTime) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DBUtils.getConnection();
        ArrayList<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "UPDATE tblFoods SET name=?,description=?,price=?,cookingTime=? WHERE id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setFloat(3, prce);
            ps.setInt(4, cookingTime);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}




    

