package org.onlinequizapp.dtos;

import java.io.Serializable;

/**
 *
 * @author User-PC
 */
public class ScoreDTO implements Serializable{

    private String QuizID;
    private String UserID;
    private String StartTime;
    private String EndTime;
    private String Mark;

    public ScoreDTO(String QuizID, String UserID, String StartTime, String EndTime, String Mark) {
        this.QuizID = QuizID;
        this.UserID = UserID;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Mark = Mark;

    }
    
    public ScoreDTO(){
    }

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String QuizID) {
        this.QuizID = QuizID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String Mark) {
        this.Mark = Mark;
    }
    
}
