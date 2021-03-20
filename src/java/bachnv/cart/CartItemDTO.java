/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.cart;

import bachnv.food.FoodDTO;
import java.io.Serializable;

/**
 *
 * @author ngvba
 */
public class CartItemDTO implements Serializable {

    private FoodDTO food;
    private int amount;
    private float price;
    private float total;

    public CartItemDTO(FoodDTO food, int amount, float price, float total) {
        this.food = food;
        this.amount = amount;
        this.price = price;
        this.total = total;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
