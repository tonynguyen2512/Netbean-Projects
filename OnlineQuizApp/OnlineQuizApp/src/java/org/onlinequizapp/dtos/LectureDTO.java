package org.onlinequizapp.dtos;

import java.io.Serializable;

public class LectureDTO implements Serializable {

    private String lectureID;
    private String courseID;
    private String lectureName;
    private String description;
    private String status;
    private String classID;
    
    public LectureDTO(String LectureID, String CourseID, String LectureName, String ClassID, String Description, String Status) {
        
        this.lectureID = LectureID;
        this.courseID = CourseID;
        this.lectureName = LectureName;
        this.status = Status;
        this.description = Description;
        this.classID = ClassID;
    }

    public LectureDTO() {
    }

    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String LectureID) {
        this.lectureID = LectureID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String CourseID) {
        this.courseID = CourseID;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String LectureName) {
        this.lectureName = LectureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String ClassID) {
        this.classID = ClassID;
    }
    
    
}