/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.OnlineQuizApp.dao;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserDTO implements Serializable{
    private String userID;
    private String password;
    private String fullname;
    private String role;
    private int phone;
    private String email;
    private String address;
    private int status;
    public UserDTO() {
    }

    public UserDTO(String userID, String password, String fullname, String role, int phone, String email, String address, int status) {
        this.userID = userID;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
 
    
}
