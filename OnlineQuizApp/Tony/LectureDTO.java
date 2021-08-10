package org.onlinequizapp.dtos;

import java.io.Serializable;

public class LectureDTO implements Serializable {

    private String LectureID;
    private String CourseID;
    private String LectureName;
    private String Description;
    private String Status;
    private String ClassID;
    
    public LectureDTO(String LectureID, String CourseID, String LectureName, String ClassID, String Description, String Status) {
        
        this.LectureName = LectureID;
        this.CourseID = CourseID;
        this.LectureName = LectureName;
        this.Status = LectureName;
        this.Description = Description;
        this.ClassID = ClassID;
    }

    public LectureDTO() {
    }

    public String getLectureID() {
        return LectureID;
    }

    public void setLectureID(String LectureID) {
        this.LectureID = LectureID;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String CourseID) {
        this.CourseID = CourseID;
    }

    public String getLectureName() {
        return LectureName;
    }

    public void setLectureName(String LectureName) {
        this.LectureName = LectureName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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