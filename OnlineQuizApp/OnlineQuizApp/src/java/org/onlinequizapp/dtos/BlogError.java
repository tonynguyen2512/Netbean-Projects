/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.dtos;

/**
 *
 * @author Admin
 */
public class BlogError {

    private String blogID;
    private String Title;
    private String authorID;
    private String categoryID;
    private String content;
    private String Image;

    public BlogError() {
    }

    public BlogError(String blogID, String Title, String authorID, String categoryID, String content, String Image) {
        this.blogID = blogID;
        this.Title = Title;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.content = content;
        this.Image = Image;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
}
