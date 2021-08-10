/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.SourceDTO;

import org.onlinequizapp.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class SourceDAO {
  public SourceDTO checkSource(String sourceID, String lectureID) throws SQLException {
        SourceDTO source = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select FileDoc, FileVid, FilePic, Reference ,status "
                        + "FROM tblBlog "
                        + "Where sourceID=\'" + sourceID + "\' AND lectureID=\'" + lectureID + "\'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String FileDoc = rs.getString("FileDoc");
                    String FileVid = rs.getString("FileVid");
                    String FilePic = rs.getString("FilePic");
                    String Reference = rs.getString("Reference");
                    String Status = rs.getString("Status");
                    source = new SourceDTO(sourceID, lectureID, FileDoc, FilePic, FileVid, Reference, Status);
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
        return source;
    }

    public List<SourceDTO> getListSource(String search) throws SQLException {
        List<SourceDTO> listSource = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select SourceID, LectureID, FileDoc, FilePic, FileVid , Reference, Status  "
                        + "from tblSource "
                        + "WHERE LectureID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String SourceID = rs.getString("SourceID");
                    String LectureID = rs.getString("LectureID");
                    String FileDoc = rs.getString("FileDoc");
                    String FilePic = rs.getString("FilePic");
                    String FileVid = rs.getString("FileVid");
                    String Reference = rs.getString("Reference");
                    String Status = rs.getString("Status");
                    if (listSource == null) {
                        listSource = new ArrayList<>();
                    }
                    listSource.add(new SourceDTO(SourceID, LectureID, FileDoc, FilePic, FileVid, Reference, Status));

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
        return listSource;
    }

    public boolean deleteSource(String sourceID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblSource "
                        + "Where sourceID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sourceID);
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

    public boolean update(SourceDTO source) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblSource SET LectureID=?, FileDoc=?, FilePic=?, FileVid=? , Reference=?, Status=? "
                        + " Where SourceID=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(source.getLectureID()));
                stm.setString(2, source.getFileDoc());
                stm.setString(3, source.getFilePic());
                stm.setString(4, source.getFileVid());
                stm.setString(5, source.getReference());
                stm.setString(6, source.getStatus());
                stm.setInt(7, Integer.parseInt(source.getSourceID()));
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

    public boolean checkDuplicate(String SourceID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select SourceID "
                        + " FROM tblSource "
                        + " Where SourceID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, SourceID);
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

    public void insert(SourceDTO source) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblSource( LectureID, FileDoc, FilePic, FileVid , Reference, Status) "
                        + "VALUES(?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(source.getLectureID()));
                stm.setString(2, source.getFileDoc());
                stm.setString(3, source.getFilePic());
                stm.setString(4, source.getFileVid());
                stm.setString(5, source.getReference());
                stm.setString(6, source.getStatus());
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

   /* public void insertNew(BlogDTO Blog) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblBlog(BlogID, Title, authorID, categoryID, content, Image) "
                        + " VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Blog.getBlogID());
                stm.setString(2, Blog.getAuthorID());
                stm.setString(3, Blog.getCategoryID());
                stm.setString(4, Blog.getContent());
                stm.setString(5, Blog.getTitle());
                stm.setString(6, Blog.getImage());
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

    public void insertNew1(BlogDTO Blog) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblBlog(BlogID, Title, authorID, categoryID, content, Image) "
                        + " VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Blog.getBlogID());
                stm.setString(2, Blog.getAuthorID());
                stm.setString(3, Blog.getCategoryID());
                stm.setString(4, Blog.getContent());
                stm.setString(5, Blog.getTitle());
                stm.setString(6, Blog.getImage());
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
    }  */ 
}
