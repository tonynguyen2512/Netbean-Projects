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
import org.onlinequizapp.dtos.CategoryDTO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.utils.DBUtils;

/**
 *
 * @author User-PC
 */
public class CategoryDAO {

    public List<CategoryDTO> getListQ(String search) throws SQLException {
        List<CategoryDTO> listCate = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select categoryID, categoryName, description , status, level "
                        + "from tblCategory "
                        + "WHERE categoryName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    String level = rs.getString("level");
                    String status = rs.getString("status");
                    if (listCate == null) {
                        listCate = new ArrayList<>();
                    }
                    listCate.add(new CategoryDTO(categoryID, categoryName, description, status, level));

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

    public List<CategoryBlogDTO> getListB(String search) throws SQLException {
        List<CategoryBlogDTO> listCate = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select categoryID, categoryName, description, status "
                        + "from tblCategoryBlog "
                        + "WHERE categoryName like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    if (listCate == null) {
                        listCate = new ArrayList<>();
                    }
                    listCate.add(new CategoryBlogDTO(categoryID, categoryName, description, status));

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

    public boolean deleteQ(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblCategory "
                        + "Where categoryID=?";
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

    public boolean deleteB(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblCategoryBlog "
                        + "Where categoryID=?";
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

    public boolean updateQ(CategoryDTO cate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblCategory SET categoryName=?, description=?, level=?, status=? "
                        + " Where categoryID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getCategoryName());
                stm.setString(2, cate.getDescription());
                stm.setString(3, cate.getLevel());
                stm.setByte(4, (byte) Integer.parseInt(cate.getStatus()));
                stm.setString(5, cate.getCategoryID());
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

    public boolean updateB(CategoryBlogDTO cate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPdaTE tblCategoryBlog SET categoryName=?, description=?, status=? "
                        + " Where categoryID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getCategoryName());
                stm.setString(2, cate.getDescription());
                stm.setByte(3, (byte) Integer.parseInt(cate.getStatus()));
                stm.setString(4, cate.getCategoryID());
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

    public boolean updateEnableQ(int ID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status == true) {
                    String sql = "UPdaTE tblCategory SET status='1' "
                            + " Where categoryID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (status == false) {
                    String sql = "UPdaTE tblCategory SET status='0' "
                            + " Where categoryID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                }
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

    public boolean updateEnableB(int ID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status == true) {
                    String sql = "UPdaTE tblCategoryBlog SET status='1' "
                            + " Where categoryID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (status == false) {
                    String sql = "UPdaTE tblCategory SET status='0' "
                            + " Where categoryID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                }
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

    public boolean checkDuplicateQ(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select categoryID "
                        + " FROM tblCategory "
                        + " Where categoryID=?";
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

    public boolean checkDuplicateB(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select categoryID "
                        + " FROM tblCategoryBlog "
                        + " Where categoryID=?";
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

    public void insertQ(CategoryDTO cate) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblCategory( categoryName, description, level, status) "
                        + " VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getCategoryName());
                stm.setString(2, cate.getDescription());
                stm.setString(3, cate.getLevel());
                stm.setByte(4, (byte)Integer.parseInt(cate.getStatus()));
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
    
    public void insertB(CategoryBlogDTO cate) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblCategoryBlog( categoryName, description, status) "
                        + " VALUES(?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, cate.getCategoryName());
                stm.setString(2, cate.getDescription());
                stm.setByte(3, (byte)Integer.parseInt(cate.getStatus()));
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
