/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
<%-- 
    Document   : index
    Created on : Mar 13, 2021, 9:58:50 AM
    Author     : hd
--%>
 */
package sample.dtos;

import java.sql.Timestamp;

/**
 *
 * @author hd
 */
public class ProductDTO {
   String id ;
    String name ;
    String description  ;
    float price ;
    int cookingTime ;
    boolean status ;
    Timestamp createDate;

    public ProductDTO(String id, String name, String description, float price, int cookingTime, boolean status, Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cookingTime = cookingTime;
        this.status = status;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
}