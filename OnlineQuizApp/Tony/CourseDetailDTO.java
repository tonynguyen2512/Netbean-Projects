/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.dtos;

import java.io.Serializable;

/**
 *
 * @author User-PC
 */
public class CourseDetailDTO implements Serializable {
    
    private String CourseID;
    private String LearnerID;
    private String StartDate;
    private String Progress;
    private String ClassID;
    
    public CourseDetailDTO(String CourseID, String LearnerID, String ClassID, String StartDate, String Progress) {
        this.CourseID = CourseID;
        this.LearnerID = LearnerID;
        this.Progress = LearnerID;
        this.StartDate = StartDate;
        this.ClassID = ClassID;
    }

    public CourseDetailDTO(){

    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getLearnerID() {
        return LearnerID;
    }

    public void setLearnerID(String learnerID) {
        LearnerID = learnerID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }
}
