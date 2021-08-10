package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.CourseDTO;
import org.onlinequizapp.utils.DBUtils;

public class CourseDAO {

    public CourseDTO checkCourse(String courseID, String courseName) throws SQLException {
        CourseDTO course = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select courseID, courseName, authorID "
                        + "FROM tblCourse "
                        + "Where courseID=\'" + courseID + "\' AND courseName=\'" + courseName + "\'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String duration = rs.getString("duration");
                    String status = rs.getString("status");
                    String authorID = rs.getString("authorID");
                    course = new CourseDTO(courseID, courseName, authorID, duration, status);
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
        return course;
    }

    public List<CourseDTO> getListCourse(String search) throws SQLException {
        List<CourseDTO> listCourse = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select courseID, authorID, duration , status, categoryID, Name, Description "
                        + "from tblCourse "
                        + "WHERE name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String courseID = rs.getString("courseID");
                    String courseName = rs.getString("Name");
                    String authorID = rs.getString("authorID");
                    String duration = rs.getString("duration");
                    String status = rs.getString("status");
                    String cate = rs.getString("categoryID");
                    String description = rs.getString("Description");
                    if (listCourse == null) {
                        listCourse = new ArrayList<>();
                    }
                    listCourse.add(new CourseDTO(courseID, courseName, authorID, duration, status, cate, description));

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
        return listCourse;
    }

    public boolean delete(String courseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblCourse "
                        + "Where courseID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, courseID);
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

    public boolean update(CourseDTO course) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblCourse SET Name=?, authorID=?, duration=?, status=?, categoryID=?, description=?  "
                        + " Where courseID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, course.getCourseName());
                stm.setString(2, course.getAuthorID());
                stm.setString(3, course.getDuration());
                stm.setByte(4, (byte) Integer.parseInt(course.getStatus()));
                stm.setInt(5, Integer.parseInt(course.getCategoryID()));
                stm.setString(6, course.getDescription());
                stm.setInt(7, Integer.parseInt(course.getCourseID()));
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

    public boolean checkDuplicate(String courseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select courseID "
                        + " FROM tblCourse "
                        + " Where courseID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, courseID);
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

    public void insert(CourseDTO course) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblCourse( AuthorID, duration, status, categoryID, Name, Description) "
                        + " VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, course.getAuthorID());
                stm.setString(2, course.getDuration());
                stm.setByte(3, (byte) Integer.parseInt(course.getStatus()));
                stm.setInt(4, Integer.parseInt(course.getCategoryID()));
                stm.setString(5, course.getCourseName());
                stm.setString(6, course.getDescription());
                stm.executeUpdate();

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

    public void insertNew(CourseDTO course) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblCourse( AuthorID, duration, status, categoryID, Name, Description) "
                        + " VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, course.getAuthorID());
                stm.setString(2, course.getDuration());
                stm.setByte(3, (byte) Integer.parseInt(course.getStatus()));
                stm.setString(4, course.getCategoryID());
                stm.setString(5, course.getCourseName());
                stm.setString(6, course.getDescription());
                stm.executeQuery();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void insertNew1(CourseDTO course) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblCourse(courseID, courseName, authorID, duration, status) "
                        + " VALUES(?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, course.getCourseID());
                stm.setString(2, course.getAuthorID());
                stm.setString(3, course.getDuration());
                stm.setString(4, course.getStatus());
                stm.setString(5, course.getCourseName());
                stm.executeUpdate();
            }
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
