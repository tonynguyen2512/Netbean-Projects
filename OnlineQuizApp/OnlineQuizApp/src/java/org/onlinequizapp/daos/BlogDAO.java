package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.BlogDTO;
import org.onlinequizapp.utils.DBUtils;

public class BlogDAO {

    public BlogDTO checkBlog(String BlogID, String Title) throws SQLException {
        BlogDTO Blog = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select BlogID, Title, authorID "
                        + "FROM tblBlog "
                        + "Where BlogID=\'" + BlogID + "\' AND Title=\'" + Title + "\'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String content = rs.getString("content");
                    String authorID = rs.getString("authorID");
                    String Image = rs.getString("Image");
                    String Status = rs.getString("Status");
                    Blog = new BlogDTO(BlogID, Title, authorID, categoryID, content, Image, Status);
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
        return Blog;
    }

    public BlogDTO checkBlogAuthor(String auID) throws SQLException {
        BlogDTO Blog = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select BlogID, Title, content, Image, categoryID , authorID, Status  "
                        + "FROM tblBlog "
                        + "Where authorID=\'%" + auID + "%\'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String BlogID = rs.getString("BlogID");
                    String Title = rs.getString("Title");
                    String categoryID = rs.getString("categoryID");
                    String content = rs.getString("content");
                    String authorID = rs.getString("authorID");
                    String Image = rs.getString("Image");
                    String Status = rs.getString("Status");
                    Blog = new BlogDTO(BlogID, Title, authorID, categoryID, content, Image, Status);
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
        return Blog;
    }

    public List<BlogDTO> getListBlog(String search) throws SQLException {
        List<BlogDTO> listBlog = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select BlogID, Title, content, Image, categoryID , authorID, Status  "
                        + "from tblBlog "
                        + "WHERE Title like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String BlogID = rs.getString("BlogID");
                    String title = rs.getString("title");
                    String authorID = rs.getString("authorID");
                    String categoryID = rs.getString("categoryID");
                    String content = rs.getString("content");
                    String Image = rs.getString("Image");
                    String Status = rs.getString("Status");
                    if (listBlog == null) {
                        listBlog = new ArrayList<>();
                    }
                    listBlog.add(new BlogDTO(BlogID, title, authorID, categoryID, content, Image, Status));

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
        return listBlog;
    }

    public boolean deleteBlog(String BlogID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblBlog "
                        + "Where BlogID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, BlogID);
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

    public boolean update(BlogDTO Blog) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblBlog SET  Title=?, content=?, Image=?, categoryID=? , authorID=?, Status=? "
                        + " Where BlogID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Blog.getTitle());
                stm.setString(2, Blog.getContent());
                stm.setString(3, Blog.getImage());
                stm.setInt(4, Integer.parseInt(Blog.getCategoryID()));
                stm.setString(5, Blog.getAuthorID());
                stm.setByte(6, (byte) Integer.parseInt(Blog.getStatus()));
                stm.setInt(7, Integer.parseInt(Blog.getBlogID()));
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

    public boolean checkDuplicate(String BlogID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select BlogID "
                        + " FROM tblBlog "
                        + " Where BlogID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, BlogID);
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

    public void insert(BlogDTO Blog) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblBlog( Title, authorID, categoryID, content, Image, Status) "
                        + "VALUES(?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Blog.getTitle());
                stm.setString(2, Blog.getAuthorID());
                stm.setInt(3, Integer.parseInt(Blog.getCategoryID()));
                stm.setString(4, Blog.getContent());
                stm.setString(5, Blog.getImage());
                stm.setByte(6, (byte) Integer.parseInt(Blog.getStatus()));
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

    public void insertNew(BlogDTO Blog) throws SQLException, ClassNotFoundException {
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
    }
}
