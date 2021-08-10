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

    private String sourceID;
    private String lectureID;
    private String fileDoc;
    private String filePic;
    private String fileVid;
    private String reference;
    private String status;

    public SourceDTO(String SourceID, String LectureID, String FileDoc, String FilePic, String FileVid, String Reference, String Status) {
        this.sourceID = SourceID;
        this.lectureID = LectureID;
        this.fileDoc = FileDoc;
        this.filePic = FilePic;
        this.fileVid = FileVid;
        this.reference = Reference;
        this.status = Status;
    }
    
    public SourceDTO(){
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String SourceID) {
        this.sourceID = SourceID;
    }

    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String LectureID) {
        this.lectureID = LectureID;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String FileDoc) {
        this.fileDoc = FileDoc;
    }

    public String getFilePic() {
        return filePic;
    }

    public void setFilePic(String FilePic) {
        this.filePic = FilePic;
    }

    public String getFileVid() {
        return fileVid;
    }

    public void setFileVid(String FileVid) {
        this.fileVid = FileVid;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String Reference) {
        this.reference = Reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }
    
}