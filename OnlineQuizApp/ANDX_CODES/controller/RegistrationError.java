/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.OnlineQuizApp.controller;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationError implements Serializable {

    private String userID_ERR_msg;
    private String passwordLength_ERR_msg;
    private String password3_1_ERR_msg;
    private String passwordSpecial_char_ERR_msg;
    private String password_matches;
    private String fullname_ERR_msg;
    private String phone_ERR_msg;
    private String email_ERR_msg;
    private String address_ERR_msg;

    public String getUserID_ERR_msg() {
        return userID_ERR_msg;
    }

    public void setUserID_ERR_msg(String userID_ERR_msg) {
        this.userID_ERR_msg = userID_ERR_msg;
    }

    public String getPasswordLength_ERR_msg() {
        return passwordLength_ERR_msg;
    }

    public void setPasswordLength_ERR_msg(String passwordLength_ERR_msg) {
        this.passwordLength_ERR_msg = passwordLength_ERR_msg;
    }

    public String getPassword3_1_ERR_msg() {
        return password3_1_ERR_msg;
    }

    public void setPassword3_1_ERR_msg(String password3_1_ERR_msg) {
        this.password3_1_ERR_msg = password3_1_ERR_msg;
    }

    public String getPasswordSpecial_char_ERR_msg() {
        return passwordSpecial_char_ERR_msg;
    }

    public void setPasswordSpecial_char_ERR_msg(String passwordSpecial_char_ERR_msg) {
        this.passwordSpecial_char_ERR_msg = passwordSpecial_char_ERR_msg;
    }

    public String getFullname_ERR_msg() {
        return fullname_ERR_msg;
    }

    public void setFullname_ERR_msg(String fullname_ERR_msg) {
        this.fullname_ERR_msg = fullname_ERR_msg;
    }

    public String getPhone_ERR_msg() {
        return phone_ERR_msg;
    }

    public void setPhone_ERR_msg(String phone_ERR_msg) {
        this.phone_ERR_msg = phone_ERR_msg;
    }

    public String getEmail_ERR_msg() {
        return email_ERR_msg;
    }

    public void setEmail_ERR_msg(String email_ERR_msg) {
        this.email_ERR_msg = email_ERR_msg;
    }

    public String getAddress_ERR_msg() {
        return address_ERR_msg;
    }

    public void setAddress_ERR_msg(String address_ERR_msg) {
        this.address_ERR_msg = address_ERR_msg;
    }

    public String getPassword_matches() {
        return password_matches;
    }

    public void setPassword_matches(String password_matches) {
        this.password_matches = password_matches;
    }

}
