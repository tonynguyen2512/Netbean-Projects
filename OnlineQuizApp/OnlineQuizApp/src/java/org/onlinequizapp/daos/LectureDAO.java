package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.LectureDTO;
import org.onlinequizapp.utils.DBUtils;

public class LectureDAO {

    public List<LectureDTO> getList(String search) throws SQLException {
        List<LectureDTO> listCate = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select lectureID, LectureName, Description, CourseID, ClassID, Status "
                        + "from tblLecture "
                        + "WHERE LectureName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String lectureID = rs.getString("lectureID");
                    String lectureName = rs.getString("lectureName");
                    String status = rs.getString("status");
                    String description = rs.getString("description");
                    String courseID = rs.getString("courseID");
                    String classID = rs.getString("classID");
                    if (listCate == null) {
                        listCate = new ArrayList<>();
                    }
                    listCate.add(new LectureDTO(lectureID, courseID, lectureName, classID, description, status));

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

    public boolean delete(String Name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblLecture "
                        + "Where LectureID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Name);
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

    public boolean update(LectureDTO cate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblLecture SET LectureName=?, Description=?, CourseID=?, ClassID=?, Status=? "
                        + " Where lectureID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getLectureName());
                stm.setString(2, cate.getDescription());
                stm.setInt(3, Integer.parseInt(cate.getCourseID()));
                stm.setInt(4, Integer.parseInt(cate.getClassID()));
                stm.setString(5, cate.getStatus());
                stm.setInt(6, Integer.parseInt(cate.getLectureID()));
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

    public void insert(LectureDTO cate) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblLecture( LectureName, Description, CourseID, ClassID, Status ) "
                        + " VALUES(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getLectureName());
                stm.setByte(5, (byte) Integer.parseInt(cate.getStatus()));
                stm.setString(4, cate.getClassID());
                stm.setString(2, cate.getDescription());
                stm.setString(3, cate.getCourseID());
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
