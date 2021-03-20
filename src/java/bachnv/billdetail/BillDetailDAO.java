/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.billdetail;

import bachnv.bill.BillDTO;
import bachnv.util.DBAccess;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ngvba
 */
public class BillDetailDAO implements Serializable{
    
    public boolean InsertBillDetail(BillDetailDTO billDetail) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Insert Into BillDetail (billDetailID, billID, foodID, amount, price, total) "
                        + "Values (?,?,?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, billDetail.getBillDetailID());
                ps.setString(2, billDetail.getBillID().getBillID());
                ps.setString(3, billDetail.getFood().getFoodID());
                ps.setInt(4, billDetail.getAmount());
                ps.setFloat(5, billDetail.getPrice());
                ps.setFloat(6, billDetail.getTotal());
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
    
    private List<BillDetailDTO> listBillDetail;
    
    public List<BillDetailDTO> getListBillDetail(){
        return this.listBillDetail;
    }
    
    public void getBillDetailByBill(BillDTO bill) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            listBillDetail = null;
            if (con != null) {
                String sql = "Select billDetailID, foodID, amount, price, total "
                        + "From BillDetail "
                        + "Where billID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, bill.getBillID());
                rs = ps.executeQuery();
                while (rs.next()) {
                    String billDetailID = rs.getString(1);
                    String foodID = rs.getString(2);
                    int amount = rs.getInt(3);
                    float price = rs.getFloat(4);
                    float total = rs.getFloat(5);
                    BillDetailDTO billDetail = new BillDetailDTO(billDetailID, bill, null, amount, price, total);
                    if (listBillDetail == null) {
                        listBillDetail = new ArrayList<>();
                    }
                    listBillDetail.add(billDetail);
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
    
}
