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
public class FoodUpdateError implements Serializable{
    private String foodImageIsNotAPicture;

    public FoodUpdateError() {
    }

    public FoodUpdateError(String foodImageIsNotAPicture) {
        this.foodImageIsNotAPicture = foodImageIsNotAPicture;
    }

    public String getFoodImageIsNotAPicture() {
        return foodImageIsNotAPicture;
    }

    public void setFoodImageIsNotAPicture(String foodImageIsNotAPicture) {
        this.foodImageIsNotAPicture = foodImageIsNotAPicture;
    }
    
}
