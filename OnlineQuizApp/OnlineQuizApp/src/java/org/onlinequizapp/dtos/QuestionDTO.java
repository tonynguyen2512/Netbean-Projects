package org.onlinequizapp.dtos;



public class QuestionDTO {

    private String questionID;
    private String name;
    private String description;
    private String answer;
    private String authorID;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String status;
    private String categoryID;
   
    public QuestionDTO(String QuestionID, String Name, String Question1, String Question2, String Question3, String Question4, String Description, String Answer, 
            String AuthorID, String Status, String categoryID) {
        
        this.questionID = QuestionID;
        this.name = Name;
        this.description = Description;
        this.answer = Answer;
        this.authorID = AuthorID;
        this.question1 = Question1;
        this.question2 = Question2;
        this.question3 = Question3;
        this.question4 = Question4;
        this.status = Status;
        this.categoryID = categoryID;
        
    }

    public QuestionDTO() {
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public String getStatus() {
        return status;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    
}