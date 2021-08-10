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
import org.onlinequizapp.dtos.QuizDTO;
import org.onlinequizapp.dtos.UserDTO;
import org.onlinequizapp.utils.DBUtils;

/**
 *
 * @author User-PC
 */
public class QuizDAO {

    public List<QuizDTO> getListQ(String search) throws SQLException {
        List<QuizDTO> listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, Name, Description , NumberOfQuestions, TotalMark, Status, "
                        + "AuthorID, ClassID" + "from tblQuiz " + "WHERE Name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuizID = rs.getString("QuizID");
                    String Name = rs.getString("Name");
                    String Description = rs.getString("Description");
                    String NumberOfQuestions = rs.getString("NumberOfQuestions");
                    String TotalMark = rs.getString("TotalMark");
                    String Status = rs.getString("Status");
                    String AuthorID = rs.getString("AuthorID");
                    String ClassID = rs.getString("ClassID");
                    if (listQuiz == null) {
                        listQuiz = new ArrayList<>();
                    }
                    listQuiz.add(new QuizDTO(QuizID, Name, NumberOfQuestions, Description , TotalMark, AuthorID, Status, ClassID));

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
        return listQuiz;
    }

    public List<QuizDTO> getListB(String search) throws SQLException {
        List<QuizDTO> listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, Name, Description, Status "
                        + "from tblQuizDetail "
                        + "WHERE Name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int QuizID = rs.getInt("QuizID");
                    String Name = rs.getString("Name");
                    String Description = rs.getString("Description");
                    String Status = rs.getString("Status");
                    if (listQuiz == null) {
                        listQuiz = new ArrayList<>();
                    }
                    listQuiz.add(new QuizDTO(QuizID, Name, Description, Status));

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
        return listQuiz;
    }

    public boolean deleteQ(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblQuiz "
                        + "Where QuizID=?";
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
                String sql = "DELETE tblQuizDetail "
                        + "Where QuizID=?";
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

    public boolean updateQ(QuizDTO quiz) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPdaTE tblQuiz SET Name=?, Description=?, level=?, Status=? "
                        + " Where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getQuizName());
                stm.setString(2, quiz.getDescription());
                stm.setString(3, quiz.getLevel());
                stm.setString(4, quiz.getStatus());
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

    public boolean updateB(QuizDTO quiz) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPdaTE tblQuizDetail SET Name=?, Description=?, Status=? "
                        + " Where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getQuizName());
                stm.setString(2, quiz.getDescription());
                stm.setString(3, quiz.getStatus());
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

    public boolean updateEnableQ(int ID, boolean Status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (Status == true) {
                    String sql = "UPdaTE tblQuiz SET Status='1' "
                            + " Where QuizID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (Status == false) {
                    String sql = "UPdaTE tblQuiz SET Status='0' "
                            + " Where QuizID=?";
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

    public boolean updateEnableB(int ID, boolean Status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (Status == true) {
                    String sql = "UPdaTE tblQuiz SET Status='1' "
                            + " Where QuizID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (Status == false) {
                    String sql = "UPdaTE tblQuiz SET Status='0' "
                            + " Where QuizID=?";
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

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select userID "
                        + " FROM tblUser "
                        + " Where UserID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
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

    public void insert(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUser(userID, fullName, roleID, password) "
                        + " VALUES(\'?\',\'?\',\'?\',\'?\')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullname());
                stm.setString(3, user.getRole());
                stm.setString(4, user.getPassword());
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
