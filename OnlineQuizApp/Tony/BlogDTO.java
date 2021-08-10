package org.onlinequizapp.dtos;

import java.io.Serializable;

public class BlogDTO implements Serializable {

    private String blogID;
    private String Title;
    private String authorID;
    private String categoryID;
    private String content;
    private String Image;
    
    
   
    public BlogDTO(String blogID, String Title, String authorID, String categoryID, String content, String Image) {
        this.blogID = blogID;
        this.Title = Title;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.content = content;
        this.Image = Image;
        
    }

    public BlogDTO() {
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }
    
    public void setTitle(String Title) {
        this.Title = Title;
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
        return Title;
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
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    

}
