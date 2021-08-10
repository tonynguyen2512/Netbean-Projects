package thien.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable{
    private String userID;
    private String fullName;
    private String roleID;
    private String password;

    public UserDTO(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public UserDTO(String userID, String fullName, String roleID, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return roleID;
    }

    public void setRole(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

