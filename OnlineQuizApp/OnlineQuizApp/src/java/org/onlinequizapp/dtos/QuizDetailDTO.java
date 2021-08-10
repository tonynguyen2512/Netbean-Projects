/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.dtos;

/**
 *
 * @author User-PC
 */
public class QuizDetailDTO {

    private String quizID;
    private String questionID;
    private String time;
    private String mark;

    public QuizDetailDTO(String quizID, String questionID, String time, String mark) {
        this.quizID = quizID;
        this.questionID = questionID;
        this.time = time;
        this.mark = mark;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
