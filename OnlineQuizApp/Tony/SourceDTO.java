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
public class SourceDTO implements Serializable{

    private String SourceID;
    private String LectureID;
    private String FileDoc;
    private String FilePic;
    private String FileVid;
    private String Reference;
    private String Status;

    public SourceDTO(String SourceID, String LectureID, String FileDoc, String FilePic, String FileVid, String Reference, String Status) {
        this.SourceID = SourceID;
        this.LectureID = LectureID;
        this.FileDoc = FileDoc;
        this.FilePic = FilePic;
        this.FileVid = FileVid;
        this.Reference = Reference;
        this.Status = Status;
    }
    
    public SourceDTO(){
    }

    public String getSourceID() {
        return SourceID;
    }

    public void setSourceID(String SourceID) {
        this.SourceID = SourceID;
    }

    public String getLectureID() {
        return LectureID;
    }

    public void setLectureID(String LectureID) {
        this.LectureID = LectureID;
    }

    public String getFileDoc() {
        return FileDoc;
    }

    public void setFileDoc(String FileDoc) {
        this.FileDoc = FileDoc;
    }

    public String getFilePic() {
        return FilePic;
    }

    public void setFilePic(String FilePic) {
        this.FilePic = FilePic;
    }

    public String getFileVid() {
        return FileVid;
    }

    public void setFileVid(String FileVid) {
        this.FileVid = FileVid;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}