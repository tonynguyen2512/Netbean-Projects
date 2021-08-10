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
import org.onlinequizapp.dtos.QuizDetailDTO;
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
                        + "AuthorID, ClassID from tblQuiz WHERE Name like ?";
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
                    listQuiz.add(new QuizDTO(QuizID, Name, NumberOfQuestions, Description, TotalMark, AuthorID, Status, ClassID));

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

    public QuizDTO getQ(String search) throws SQLException {
        QuizDTO listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, Name, Description , NumberOfQuestions, TotalMark, Status, "
                        + "AuthorID, ClassID from tblQuiz WHERE QuizID like ?";
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
                    listQuiz.setAuthorID(AuthorID);
                    listQuiz.setClassID(ClassID);
                    listQuiz.setDescription(Description);
                    listQuiz.setName(Name);
                    listQuiz.setStatus(Status);
                    listQuiz.setTotalMark(TotalMark);
                    listQuiz.setNumberOfQuestions(NumberOfQuestions);
                    listQuiz.setQuizID(QuizID);
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

    public List<QuizDetailDTO> getListQD(String search) throws SQLException {
        List<QuizDetailDTO> listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, questionID, Time, Mark "
                        + "from tblQuizDetail "
                        + "WHERE quizID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuizID = rs.getString("QuizID");
                    String questionID = rs.getString("questionID");
                    String Time = rs.getString("Time");
                    String Mark = rs.getString("Mark");
                    if (listQuiz == null) {
                        listQuiz = new ArrayList<>();
                    }
                    listQuiz.add(new QuizDetailDTO(QuizID, questionID, Time, Mark));

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

    public boolean deleteQD(String ID, String QID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblQuizDetail "
                        + "Where QuizID=? and QuestionID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, ID);
                stm.setString(2, QID);
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
                String sql = "UPdaTE tblQuiz SET Name=?, Description=?, NumberOfQuestions=?, TotalMark=?, AuthorID=?, Status=?, ClassID=? "
                        + " Where quizID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getName());
                stm.setString(2, quiz.getDescription());
                stm.setInt(3, Integer.parseInt(quiz.getNumberOfQuestions()));
                stm.setFloat(4, Float.parseFloat(quiz.getTotalMark()));
                stm.setString(5, quiz.getAuthorID());
                stm.setString(6, quiz.getStatus());
                stm.setInt(7, Integer.parseInt(quiz.getClassID()));
                stm.setInt(8, Integer.parseInt(quiz.getQuizID()));
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

    public boolean updateQD(QuizDetailDTO quiz, String QID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPdaTE tblQuizDetail SET Time=?, Mark=?, QuestionID=? "
                        + " Where QuizID=? and questionID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getTime());
                stm.setString(2, quiz.getMark());
                stm.setString(3, quiz.getQuestionID());
                stm.setString(4, quiz.getQuizID());
                stm.setString(5, QID);
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

    public void insertQ(QuizDTO quiz) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblQuiz(Name, NumberOfQuestions, Description, TotalMark, AuthorID, Status, ClassID ) "
                        + " VALUES(?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, quiz.getName());
                stm.setInt(2, Integer.parseInt(quiz.getNumberOfQuestions()));
                stm.setString(3, quiz.getDescription());
                stm.setFloat(4, Float.parseFloat(quiz.getTotalMark()));
                stm.setString(5, quiz.getAuthorID());
                stm.setString(6, quiz.getStatus());
                stm.setInt(7, Integer.parseInt(quiz.getClassID()));
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

    public void insertQD(QuizDetailDTO quiz) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblQuizDetail(QuizID, QuestionID, Time, Mark ) "
                        + " VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(quiz.getQuizID()));
                stm.setInt(2, Integer.parseInt(quiz.getQuestionID()));
                stm.setString(3, quiz.getTime());
                stm.setFloat(4, Float.parseFloat(quiz.getMark()));
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

    public boolean updateEnableQ(int ID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status == true) {
                    String sql = "UPdaTE tblQuiz SET status='1' "
                            + " Where quizID=?";
                    stm = conn.prepareStatement(sql);
                    stm.setInt(1, ID);
                    check = stm.executeUpdate() > 0 ? true : false;
                } else if (status == false) {
                    String sql = "UPdaTE tblQuiz SET status='0' "
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
