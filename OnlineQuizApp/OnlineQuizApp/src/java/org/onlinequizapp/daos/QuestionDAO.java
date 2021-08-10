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
import org.onlinequizapp.dtos.QuestionDTO;
import org.onlinequizapp.utils.DBUtils;

/**
 *
 * @author User-PC
 */
public class QuestionDAO {

    public List<QuestionDTO> getListQ(String search) throws SQLException {
        List<QuestionDTO> listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuestionID, Name, Question1 , Question2, Question3, Question4, Description, Answer, AuthorID, Status, categoryID "
                        + " from tblQuestion WHERE Name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuestionID = rs.getString("QuestionID");
                    String Name = rs.getString("Name");
                    String Question1 = rs.getString("Question1");
                    String Question2 = rs.getString("Question2");
                    String Question3 = rs.getString("Question3");
                    String Question4 = rs.getString("Question4");
                    String AuthorID = rs.getString("AuthorID");
                    String Description = rs.getString("Description");
                    String Answer = rs.getString("Answer");
                    String Status = rs.getString("Status");
                    String categoryID = rs.getString("categoryID");
                    if (listQuiz == null) {
                        listQuiz = new ArrayList<>();
                    }
                    listQuiz.add(new QuestionDTO(QuestionID, Name, Question1, Question2, Question3, Question4, Description, Answer, AuthorID, Status, categoryID));

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
                String sql = "DELETE tblQuestion "
                        + " Where QuestionID=?";
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

    public boolean updateQ(QuestionDTO quiz) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblQuestion SET Name=?, Question1=?, Question2=?, Question3=?, Question4=?, Description=?, Answer=?, AuthorID=?, Status=?, categoryID=? "
                        + " Where questionID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getName());
                stm.setString(2, quiz.getQuestion1());
                stm.setString(3, quiz.getQuestion2());
                stm.setString(4, quiz.getQuestion3());
                stm.setString(5, quiz.getQuestion4());
                stm.setString(6, quiz.getDescription());
                stm.setInt(7, Integer.parseInt(quiz.getAnswer()));
                stm.setString(8, quiz.getAuthorID());
                stm.setString(9, quiz.getStatus());
                stm.setInt(10, Integer.parseInt(quiz.getCategoryID()));
                stm.setInt(11, Integer.parseInt(quiz.getQuestionID()));
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

    public void insertQ(QuestionDTO quiz) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblQuestion(Name, Question1, Question2, Question3, Question4, Description, Answer, AuthorID, Status, CategoryID ) "
                        + " VALUES(?,?,?,?,?,?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getName());
                stm.setString(2, quiz.getQuestion1());
                stm.setString(3, quiz.getQuestion2());
                stm.setString(4, quiz.getQuestion3());
                stm.setString(5, quiz.getQuestion4());
                stm.setString(6, quiz.getDescription());
                stm.setInt(7, Integer.parseInt(quiz.getAnswer()));
                stm.setString(8, quiz.getAuthorID());
                stm.setString(9, quiz.getStatus());
                stm.setString(10, quiz.getCategoryID());
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

    public boolean updateEnableQ(int ID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status == true) {
                    String sql = "UPdaTE tblQuestion SET status='1' "
                            + " Where quizID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (status == false) {
                    String sql = "UPdaTE tblQuestion SET status='0' "
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

}
