/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.cart;

import bachnv.food.FoodDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngvba
 */
public class CartObject implements Serializable {

    private List<CartItemDTO> foods;
    private float totalPrice;

    public List<CartItemDTO> getFoods() {
        return foods;
    }

    public float getTotalPrice() {
        return (float) Math.round(totalPrice * 100) / 100;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addFoodToCart(FoodDTO newFood) {
        if (foods == null) {
            foods = new ArrayList();
        }
        boolean isExited = false;
        for (int i = 0; i < foods.size(); i++) {
            CartItemDTO food = foods.get(i);
            if (food.getFood().getFoodID().equals(newFood.getFoodID())) {
                food.setAmount(food.getAmount() + 1);
                food.setTotal(food.getPrice() * food.getAmount());
                isExited = true;
            }
        }
        if (!isExited) {
            if (newFood.getQuantity() > 0) {
                CartItemDTO food = new CartItemDTO(newFood, 1, newFood.getPrice(), newFood.getPrice());
                foods.add(food);
            }
        }
        calculateTotalPrice();
    }

    public void removeFoodFromCart(String id) {
        if (foods == null) {
            return;
        }
        for (int i = 0; i < foods.size(); i++) {
            CartItemDTO food = foods.get(i);
            if (food.getFood().getFoodID().equals(id)) {
                foods.remove(i);
            }
        }
        if (foods.size() == 0) {
            foods = null;
            totalPrice = 0;
        } else if (foods != null) {
            calculateTotalPrice();
        }
    }

    public void updateAmount(String id, int amount) {
        for (int i = 0; i < foods.size(); i++) {
            CartItemDTO food = foods.get(i);
            if (food.getFood().getFoodID().equals(id)) {
                food.setAmount(amount);
                food.setTotal(food.getPrice() * food.getAmount());
                calculateTotalPrice();
            }
        }
    }

    private void calculateTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < foods.size(); i++) {
            CartItemDTO food = (CartItemDTO) foods.get(i);
            totalPrice += food.getTotal();
        }
        setTotalPrice(totalPrice);
    }

}
