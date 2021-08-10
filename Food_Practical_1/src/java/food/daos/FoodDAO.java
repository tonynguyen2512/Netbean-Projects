/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import food.dtos.FoodDTO;
import food.utils.DBUntils;
import sun.security.util.Password;

/**
 *
 * @author Admin
 */
public class FoodDAO {

    public FoodDTO checkLogin(String userid, String pass) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FoodDTO food = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String url = "SELECT userID, fullName, roleID FROM tblUser WHERE userID = '" + userid + "' AND password = '" + pass + "'";
                stm = conn.prepareStatement(url);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    food = new FoodDTO(userid, fullName, roleID, "***");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return food;
    }

    public List<FoodDTO> getListUser(String search) throws SQLException {
        List<FoodDTO> listuser = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        FoodDTO user = null;
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String url = "SELECT userID, fullName, roleID FROM tblUser  WHERE fullName like ?";
                stm = conn.prepareStatement(url);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userid = rs.getString("userID");
                    String fullname = rs.getString("fullName");
                    String roleid = rs.getString("roleID");
                    String password = "***";
                    if (listuser == null) {
                        listuser = new ArrayList<FoodDTO>();
                    }
                    listuser.add(new FoodDTO(userid, fullname, roleid, password));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listuser;
    }

    public boolean deleteUser(String userid) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String sql = "DELETE tbluser WHERE userID  = ?  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userid);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public boolean update(FoodDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String url = "UPDATE tbluser set  fullName = ? , roleID =? where userID = ? ";
                stm = conn.prepareStatement(url);
                stm.setString(1, user.getFullname());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getUserID());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rss = null;
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String url = "SELETE userID FROM tbluser WHERE  userID = ? ";
                stm = conn.prepareStatement(url);
                stm.setString(1, userID);
                rss = stm.executeQuery();
                if(rss.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rss != null) {
                rss.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
    
    public void insert(FoodDTO user) throws SQLException{
    Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUntils.getConnection();
            if (conn != null) {
                String url = "INSERT INTO tbluser(userID, fullName, roleID,password) VALUES(?,?,?,?) ";
                stm = conn.prepareStatement(url);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullname());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getPassword());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
