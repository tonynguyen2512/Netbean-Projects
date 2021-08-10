package org.onlinequizapp.dtos;

import java.io.Serializable;

public class QuestionDTO implements Serializable {

    private String QuestionID;
    private String Name;
    private String Description;
    private String Answer;
    private String AuthorID;
    private String Question1;
    private String Question2;
    private String Question3;
    private String Question4;
    private String Status;
    private String categoryID;
   
    public QuestionDTO(String QuestionID, String Name, String Description, String Answer, 
            String AuthorID, String Question1, String Question2, String Question3, String Question4, String Status, String categoryID) {
        
        this.QuestionID = QuestionID;
        this.Name = Name;
        this.Description = Description;
        this.Answer = Answer;
        this.AuthorID = AuthorID;
        this.QuestionID = Question1;
        this.Name = Question2;
        this.Description = Question3;
        this.Answer = Question4;
        this.AuthorID = Status;
        this.AuthorID = categoryID;
        
    }

    public QuestionDTO() {
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getQuestion1() {
        return Question1;
    }

    public void setQuestion1(String Question1) {
        this.Question1 = Question1;
    }

    public String getQuestion2() {
        return Question2;
    }

    public void setQuestion2(String Question2) {
        this.Question2 = Question2;
    }

    public String getQuestion3() {
        return Question3;
    }

    public void setQuestion3(String Question3) {
        this.Question3 = Question3;
    }

    public String getQuestion4() {
        return Question4;
    }

    public void setQuestion4(String Question4) {
        this.Question4 = Question4;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    
}