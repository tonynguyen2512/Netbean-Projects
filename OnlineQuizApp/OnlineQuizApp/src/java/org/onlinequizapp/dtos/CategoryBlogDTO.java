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
public class CategoryBlogDTO implements Serializable {

    private String categoryID;
    private String categoryName;
    private String description;
    private String status;
    
    
   
    public CategoryBlogDTO(String categoryID, String categoryName, String description, String status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
        
    }
    
     public CategoryBlogDTO() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
}