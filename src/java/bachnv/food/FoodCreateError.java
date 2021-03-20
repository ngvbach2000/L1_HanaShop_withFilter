/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.food;

import java.io.Serializable;

/**
 *
 * @author ngvba
 */
public class FoodCreateError implements Serializable{
    private String foodIDIsExited;
    private String foodImageIsNotAPicture;
    private String foodNameLengthError;
    private String descriptionLengthError;
    private String priceLengthError;
    private String categoryLengthError;
    private String quantityLengthError;

    public FoodCreateError() {
    }

    public String getFoodIDIsExited() {
        return foodIDIsExited;
    }

    public void setFoodIDIsExited(String foodIDIsExited) {
        this.foodIDIsExited = foodIDIsExited;
    }

    public String getFoodImageIsNotAPicture() {
        return foodImageIsNotAPicture;
    }

    public void setFoodImageIsNotAPicture(String foodImageIsNotAPicture) {
        this.foodImageIsNotAPicture = foodImageIsNotAPicture;
    }

    public String getFoodNameLengthError() {
        return foodNameLengthError;
    }

    public void setFoodNameLengthError(String foodNameLengthError) {
        this.foodNameLengthError = foodNameLengthError;
    }

    public String getDescriptionLengthError() {
        return descriptionLengthError;
    }

    public void setDescriptionLengthError(String descriptionLengthError) {
        this.descriptionLengthError = descriptionLengthError;
    }

    public String getPriceLengthError() {
        return priceLengthError;
    }

    public void setPriceLengthError(String priceLengthError) {
        this.priceLengthError = priceLengthError;
    }

    public String getCategoryLengthError() {
        return categoryLengthError;
    }

    public void setCategoryLengthError(String categoryLengthError) {
        this.categoryLengthError = categoryLengthError;
    }

    public String getQuantityLengthError() {
        return quantityLengthError;
    }

    public void setQuantityLengthError(String quantityLengthError) {
        this.quantityLengthError = quantityLengthError;
    }
    
}
