package org.onlinequizapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.onlinequizapp.dtos.ScoreDTO;
import org.onlinequizapp.utils.DBUtils;

/**
 *
 * @author User-PC
 */
public class ScoreDAO {

    public ScoreDTO getListS(String search, String search1) throws SQLException {
        ScoreDTO listQuiz = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, UserID, StartTime, EndTime, Mark "
                        + "from tblScore "
                        + "WHERE quizID like ? and userId like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "%" + search1 + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuizID = rs.getString("QuizID");
                    String userID = rs.getString("userID");
                    Timestamp STime = rs.getTimestamp("StartTime");
                    Timestamp ETime = rs.getTimestamp("EndTime");
                    String Mark = rs.getString("Mark");
                    listQuiz.setEndTime(ETime);
                    listQuiz.setMark(Mark);
                    listQuiz.setUserID(userID);
                    listQuiz.setQuizID(QuizID);
                    listQuiz.setStartTime(STime);
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

    public boolean deleteS(String ID, String QID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblScore "
                        + "Where QuizID=? and UserID=? ";
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

    public boolean updateS(ScoreDTO quiz) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPdaTE tblSccore SET StartTime='CURRENT_TIMESTAMP', EndTime='CURRENT_TIMESTAMP', Mark=? "
                        + " Where QuizID=? and UserID=? ";
                stm = conn.prepareStatement(sql);
                stm.setFloat(1, Float.parseFloat(quiz.getMark()));
                stm.setString(2, quiz.getQuizID());
                stm.setInt(3, Integer.parseInt(quiz.getUserID()));
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

    public void insertS(ScoreDTO quiz) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblScore(QuizID, UserID, StartTime,EndTime, Mark ) "
                        + " VALUES(?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(quiz.getQuizID()));
                stm.setString(2, quiz.getUserID());
                stm.setFloat(3, Float.parseFloat(quiz.getMark()));
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

    public List<ScoreDTO> getList(String search) throws SQLException {
        List<ScoreDTO> listScore = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select QuizID, Name, userID, startTime, endTime, Mark "
                        + " from tblScore WHERE UserID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuizID = rs.getString("quizID");
                    String UserID = rs.getString("userID");
                    Timestamp StartTime = rs.getTimestamp("startTime");
                    Timestamp EndTime = rs.getTimestamp("endTime");
                    String Mark = rs.getString("mark");
                    if (listScore == null) {
                        listScore = new ArrayList<>();
                    }
                    listScore.add(new ScoreDTO(QuizID, UserID, StartTime, EndTime, Mark));

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
        return listScore;
    }

}