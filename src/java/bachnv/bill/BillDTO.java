/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.bill;

import bachnv.user.UserDTO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngvba
 */
public class BillDTO implements Serializable{
    private String billID;
    private float total;
    private Date dayOfPurchase;
    private UserDTO username;
    private String paymentOption;

    public BillDTO() {
    }

    public BillDTO(String billID, float total, Date dayOfPurchase, UserDTO username, String paymentOption) {
        this.billID = billID;
        this.total = total;
        this.dayOfPurchase = dayOfPurchase;
        this.username = username;
        this.paymentOption = paymentOption;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDayOfPurchase() {
        return dayOfPurchase;
    }

    public void setDayOfPurchase(Date dayOfPurchase) {
        this.dayOfPurchase = dayOfPurchase;
    }

    public UserDTO getUsername() {
        return username;
    }

    public void setUsername(UserDTO username) {
        this.username = username;
    }
    
    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }
}
