/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.food;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngvba
 */
public class FoodDTO implements Serializable{
    private String foodID;
    private String foodName;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private String category;
    private boolean status;
    private int quantity;

    public FoodDTO(String foodID, String foodName, String image, String description, float price, Date createDate, String category, boolean status, int quantity) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
