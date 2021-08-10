package org.onlinequizapp.dtos;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private String courseID;
    private String courseName;
    private String authorID;
    private String duration;
    private String status;
   
    public CourseDTO(String courseID, String courseName, String authorID, String duration, String status) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.authorID = authorID;
        this.duration = duration;
        this.status = status;
        
    }

    public CourseDTO() {
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCourseID() {
        return courseID;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

}
