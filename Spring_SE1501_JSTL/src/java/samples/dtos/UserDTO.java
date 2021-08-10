/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserDTO implements  Serializable{
  private String userID,fullname,roleID,password;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO(String userID, String fullname, String roleID, String password) {
        this.userID = userID;
        this.fullname = fullname;
        this.roleID = roleID;
        this.password = password;
    }

    public UserDTO() {
    }
}
