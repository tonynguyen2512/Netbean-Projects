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
public class QuizDTO {
    private String QuizID;
    private String Name;
    private String NumberOfQuestions;
    private String Description;
    private String TotalMark;
    private String AuthorID;
    private String Status;
    private String ClassID;

    public QuizDTO(String QuizID, String Name, String NumberOfQuestions, String Description, String TotalMark, String AuthorID, String Status, String ClassID) {
        this.QuizID = QuizID;
        this.Name = Name;
        this.NumberOfQuestions = NumberOfQuestions;
        this.Description = Description;
        this.TotalMark = TotalMark;
        this.AuthorID = AuthorID;
        this.Status = Status;
        this.ClassID = ClassID;
    }


    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String QuizID) {
        this.QuizID = QuizID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumberOfQuestions() {
        return NumberOfQuestions;
    }

    public void setNumberOfQuestions(String NumberOfQuestions) {
        this.NumberOfQuestions = NumberOfQuestions;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTotalMark() {
        return TotalMark;
    }

    public void setTotalMark(String TotalMark) {
        this.TotalMark = TotalMark;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String ClassID) {
        this.ClassID = ClassID;
    }
    
}
