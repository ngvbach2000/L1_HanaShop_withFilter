/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.userhistory;

import bachnv.util.DBAccess;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ngvba
 */
public class UserHistoryDAO implements Serializable{
    
    public boolean insertUserHistory(UserHistoryDTO userHistory) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Insert Into [UserHistory](HistoryID, username, [content], date) "
                        + "Values(?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userHistory.getHistoryID());
                ps.setString(2, userHistory.getUser().getUsername());
                ps.setString(3, userHistory.getContent());
                java.sql.Date sqlDate = new java.sql.Date(userHistory.getDate().getTime());
                ps.setDate(4, sqlDate);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
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
        return false;
    }
    
}
