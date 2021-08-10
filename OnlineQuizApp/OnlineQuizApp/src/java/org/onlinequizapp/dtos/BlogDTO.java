package org.onlinequizapp.dtos;

import java.io.Serializable;

public class BlogDTO implements Serializable {

    private String blogID;
    private String title;
    private String authorID;
    private String categoryID;
    private String content;
    private String image;
    private String status;
    
   
    public BlogDTO(String blogID, String title, String authorID, String categoryID, String content, String image, String Status) {
        this.blogID = blogID;
        this.title = title;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.content = content;
        this.image = image;
        this.status = Status;
    }

    public BlogDTO() {
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getBlogID() {
        return blogID;
    }
    
    public String getTitle() {
        return title;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }
    

}