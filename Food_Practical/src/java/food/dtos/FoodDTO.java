/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package food.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class FoodDTO implements  Serializable{
  private String ID,Name,Description,createDate;
  private float Price;
  private int cookingTime,status;

    public FoodDTO(String userid, String fullName, String roleID, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
  
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Float getPrice() {
        return Price;
    }

    public FoodDTO(String ID, String Name, String Description, Float Price, String createDate, int cookingTime, int Satatus) {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.cookingTime = cookingTime;
        this.createDate = createDate;
        this.status = status;
    }

    public FoodDTO() {
    }
}
