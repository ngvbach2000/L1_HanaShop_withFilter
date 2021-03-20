/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.billdetail;

import bachnv.bill.BillDTO;
import bachnv.food.FoodDTO;
import java.io.Serializable;

/**
 *
 * @author ngvba
 */
public class BillDetailDTO implements Serializable{
    private String billDetailID;
    private BillDTO billID;
    private FoodDTO food;
    private int amount;
    private float price;
    private float total;

    public BillDetailDTO(String billDetailID, BillDTO billID, FoodDTO food, int amount, float price, float total) {
        this.billDetailID = billDetailID;
        this.billID = billID;
        this.food = food;
        this.amount = amount;
        this.price = price;
        this.total = total;
    }
    
    public String getBillDetailID() {
        return billDetailID;
    }

    public void setBillDetailID(String billDetailID) {
        this.billDetailID = billDetailID;
    }

    public BillDTO getBillID() {
        return billID;
    }

    public void setBillID(BillDTO billID) {
        this.billID = billID;
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

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

}
