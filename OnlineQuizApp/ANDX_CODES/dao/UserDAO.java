/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.OnlineQuizApp.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.OnlineQuizApp.utils.DBUtil;

/**
 *
 * @author Admin
 */
public class UserDAO implements Serializable {

    private List<UserDTO> accountList;

    public List<UserDTO> getAccountList() {
        return accountList;
    }

    private UserDTO userInfo;

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public boolean checkLogin(String email, String password)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //connect to db
            con = DBUtil.makeConnection();

            if (con != null) {
                //make call to db
                String sql = "Select userID, password, fullName, roleID, phone, email, address, status "
                        + "From OnlineQuizApp "//database name here, change if db name change
                        + "Where email =? And password =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();

                //check if user exist in db
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullName");
                    String role = rs.getString("role");
                    int phone = rs.getInt("phone");
                    String address = rs.getString("address");
                    int status = rs.getInt("status");
                    UserDTO user = new UserDTO(userID, password, fullname, role, phone, email, address, status);
                    userInfo = user;
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean registerUser(String userID, String password, String fullName, int phone, String email, String address)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //make connection
            con = DBUtil.makeConnection();
            if (con != null) {
                //call db
                String sql = "Insert OnlineQuizApp "//db name change if needed
                        + "(userID, password, fullName, roleID, phone, email, address, status) "
                        + "Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setInt(4, 0);
                stm.setInt(5, phone);
                stm.setString(6, email);
                stm.setString(7, address);
                stm.setInt(8, 0);

                //check if changes were made in db
                int rowEffect = stm.executeUpdate();

                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean resetPassword(String email, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.makeConnection();

            if (con != null) {
                //call db
                String sql = "Update OnlineQuizApp " //db name change if needed
                        + "Set password =? "
                        + "Where email =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, email);

                //check if changes were made in db
                int rowEffect = stm.executeUpdate();

                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    //auto search all
    public void getUsersInfoList()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select userID, fullName, roleID, phone, email, address, status "
                        + "From OnlineQuizGame "
                        + "Where userID Like '%%' ";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery(); //have problem
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    int status = rs.getInt("status");
                    UserDTO users = new UserDTO(userID, userID, fullName, roleID, phone, email, address, status);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(users);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    //search by name
    public void getUsersInfoListByName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select userID, fullName, roleID, phone, email, address, status "
                        + "From OnlineQuizGame "
                        + "Where userID Like '?' ";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%"); //doesn't allow to set string 
                rs = stm.executeQuery(); //query cannot execute
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    int status = rs.getInt("status");
                    UserDTO users = new UserDTO(userID, userID, fullName, roleID, phone, email, address, status);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(users);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean switchUserStatus(String userID, int userStatus) throws NumberFormatException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update OnlineQuizApp "
                        + "Set status =?"
                        + "Where userID =?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userStatus);
                stm.setString(2, userID);

                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
