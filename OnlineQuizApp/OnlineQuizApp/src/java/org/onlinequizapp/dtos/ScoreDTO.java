package org.onlinequizapp.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User-PC
 */
public class ScoreDTO implements Serializable{

    private String quizID;
    private String userID;
    private Timestamp startTime;
    private Timestamp endTime;
    private String mark;

    public ScoreDTO(String QuizID, String UserID, Timestamp StartTime, Timestamp EndTime, String Mark) {
        this.quizID = QuizID;
        this.userID = UserID;
        this.startTime = StartTime;
        this.endTime = EndTime;
        this.mark = Mark;

    }

    public ScoreDTO(String quizID, String userID, String mark) {
        this.quizID = quizID;
        this.userID = userID;
        this.mark = mark;
    }
    
    public ScoreDTO(){
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String QuizID) {
        this.quizID = QuizID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String UserID) {
        this.userID = UserID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp StartTime) {
        this.startTime = StartTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp EndTime) {
        this.endTime = EndTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String Mark) {
        this.mark = Mark;
    }
    
}