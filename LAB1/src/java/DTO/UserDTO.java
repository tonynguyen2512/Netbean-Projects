/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.io.Serializable;

/**
 *
 * @author User-PC
 */
public class UserDTO implements Serializable {
    private String UserID;
    private String fullName;
    private String roleID;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String UserID, String password) {
        this.UserID = UserID;
        this.password = password;
    }

    public UserDTO(String UserID, String fullName, String roleID, String password) {
        this.UserID = UserID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return UserID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getPassword() {
        return password;
    }
    
    

    
}
