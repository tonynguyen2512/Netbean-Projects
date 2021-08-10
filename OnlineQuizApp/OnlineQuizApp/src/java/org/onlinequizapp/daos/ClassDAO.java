package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.ClassDTO;
import org.onlinequizapp.utils.DBUtils;

public class ClassDAO {

    public List<ClassDTO> getList(String search) throws SQLException {
        List<ClassDTO> listCate = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select classID, NumberOfStudent, status "
                        + "from tblClass "
                        + "WHERE classID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String classID = rs.getString("classID");
                    String NumberOfStudent = rs.getString("numberOfStudent");
                    String status = rs.getString("status");
                    if (listCate == null) {
                        listCate = new ArrayList<>();
                    }
                    listCate.add(new ClassDTO(classID, NumberOfStudent, status));

                }
            }

        } catch (ClassNotFoundException | SQLException e) {

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
        return listCate;
    }

    public boolean delete(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblClass "
                        + "Where classID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, ID);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (ClassNotFoundException | SQLException e) {

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

    public boolean update (ClassDTO cate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblClass SET NumberOfStudent=?, status=? "
                        + " Where classID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getNumberOfStudent());
                stm.setByte(2, (byte) Integer.parseInt(cate.getStatus()));
                stm.setString(3, cate.getClassID());
                check = stm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {

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

    public boolean checkDuplicate(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select classID "
                        + " FROM tblClass "
                        + " Where classID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public void insert(ClassDTO cate) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblClass( NumberOfStudent, status) "
                        + " VALUES(?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getNumberOfStudent());
                stm.setByte(2, (byte)Integer.parseInt(cate.getStatus()));
                stm.executeQuery();
            }
        } catch (Exception e) {

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
