package org.onlinequizapp.dtos;

public class ClassDTO {

    private String classID;
    private String numberOfStudent;
    private String status;

    public ClassDTO(String classID, String numberOfStudent, String status) {
        this.classID = classID;
        this.numberOfStudent = numberOfStudent;
        this.status = status;
    }
    
    public ClassDTO() {
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(String numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
