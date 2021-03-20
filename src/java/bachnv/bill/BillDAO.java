/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.bill;

import bachnv.user.UserDTO;
import bachnv.util.DBAccess;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ngvba
 */
public class BillDAO implements Serializable {

    private List<BillDTO> listBill;

    public List<BillDTO> getListBill() {
        return this.listBill;
    }

    public boolean insertBill(BillDTO bill) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Insert Into Bill (BillID, TotalPrice, DayOfPurchase, username, paymentOption) "
                        + "Values (?,?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, bill.getBillID());
                ps.setFloat(2, bill.getTotal());
                java.sql.Date sqlDate = new java.sql.Date(bill.getDayOfPurchase().getTime());
                ps.setDate(3, sqlDate);
                ps.setString(4, bill.getUsername().getUsername());
                ps.setString(5, bill.getPaymentOption());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void getAllBillByUser(UserDTO user) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select billID, totalPrice, dayOfPurchase, paymentOption "
                        + "From Bill "
                        + "Where username = ? "
                        + "Order by dayOfPurchase desc ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                rs = ps.executeQuery();
                while (rs.next()) {
                    String billID = rs.getString(1);
                    float totalPrice = rs.getFloat(2);
                    Date dayOfPurchase = rs.getDate(3);
                    String paymentOption = rs.getString(4);
                    BillDTO bill = new BillDTO(billID, totalPrice, dayOfPurchase, user, paymentOption);
                    if (listBill == null) {
                        listBill = new ArrayList<>();
                    }
                    listBill.add(bill);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public BillDTO searchBillByDate(UserDTO user, Date date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select Distinct Bill.billID, Bill.totalPrice, Bill.dayOfPurchase, Bill.paymentOption "
                        + "From bill "
                        + "Where username = ? and dayOfPurchase = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(2, sqlDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String billID = rs.getString(1);
                    float totalPrice = rs.getFloat(2);
                    Date dayOfPurchase = rs.getDate(3);
                    String paymentOption = rs.getString(4);
                    BillDTO bill = new BillDTO(billID, totalPrice, dayOfPurchase, user, paymentOption);
                    if (listBill == null) {
                        listBill = new ArrayList<>();
                    }
                    listBill.add(bill);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public BillDTO searchBillByFoodName(UserDTO user, String foodName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select Distinct Bill.billID, Bill.totalPrice, Bill.dayOfPurchase, Bill.paymentOption "
                        + "From Bill "
                        + "Inner Join BillDetail "
                        + "On Bill.billID = BillDetail.billID "
                        + "Inner Join Food "
                        + "On Food.foodID = BillDetail.foodID "
                        + "Where Bill.username = ? and Food.foodName LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, "%" + foodName + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String billID = rs.getString(1);
                    float totalPrice = rs.getFloat(2);
                    Date dayOfPurchase = rs.getDate(3);
                    String paymentOption = rs.getString(4);
                    BillDTO bill = new BillDTO(billID, totalPrice, dayOfPurchase, user, paymentOption);
                    if (listBill == null) {
                        listBill = new ArrayList<>();
                    }
                    listBill.add(bill);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public BillDTO searchBillByDateAndFoodName(UserDTO user, Date date, String foodName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select Distinct Bill.billID, Bill.totalPrice, Bill.dayOfPurchase, Bill.paymentOption "
                        + "From Bill "
                        + "Inner Join BillDetail "
                        + "On Bill.billID = BillDetail.billID "
                        + "Inner Join Food "
                        + "On Food.foodID = BillDetail.foodID "
                        + "Where Bill.username = ? and Food.foodName LIKE ? and Bill.dayOfPurchase = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, "%" + foodName + "%");
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(3, sqlDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String billID = rs.getString(1);
                    float totalPrice = rs.getFloat(2);
                    Date dayOfPurchase = rs.getDate(3);
                    String paymentOption = rs.getString(4);
                    BillDTO bill = new BillDTO(billID, totalPrice, dayOfPurchase, user, paymentOption);
                    if (listBill == null) {
                        listBill = new ArrayList<>();
                    }
                    listBill.add(bill);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int find(String billID) {
        for (int i = 0; i < listBill.size(); i++) {
            if (billID.equals(listBill.get(i).getBillID())) {
                return i;
            }
        }
        return -1;
    }

    public BillDTO findSupplier(String supCode) {
        int i = find(supCode);
        return i < 0 ? null : listBill.get(i);
    }

}
