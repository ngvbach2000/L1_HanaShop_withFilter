/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.food;

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
public class FoodDAO implements Serializable {

    private List<FoodDTO> listFood;

    public List<FoodDTO> getListFood() {
        return this.listFood;
    }

    public void loadFood(int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setInt(3, offset);
                ps.setInt(4, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordFood() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    private List<FoodDTO> listFullFood;

    public List<FoodDTO> getListFullFood() {
        return this.listFullFood;
    }

    public void loadFullFood(int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, offset);
                ps.setInt(2, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordFullFood() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    private List<String> listCategory;

    public List<String> getListCategory() {
        return this.listCategory;
    }

    public void loadCategory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select DISTINCT category "
                        + "From Food "
                        + "Where status = ? and quantity > ? "
                        + "Order by category ASC ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String category = rs.getString(1);
                    if (listCategory == null) {
                        listCategory = new ArrayList<>();
                    }
                    listCategory.add(category);
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

    private List<String> listFullCategory;

    public List<String> getListFullCategory() {
        return this.listFullCategory;
    }

    public void loadFullCategory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select DISTINCT category "
                        + "From Food "
                        + "Order by category ASC ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String category = rs.getString(1);
                    if (listFullCategory == null) {
                        listFullCategory = new ArrayList<>();
                    }
                    listFullCategory.add(category);
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
    
    public void searchFoodByName(String keyword, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and foodName LIKE ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + keyword + "%");
                ps.setInt(4, offset);
                ps.setInt(5, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchNameFood(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and foodName LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + keyword + "%");
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFoodByCategory(String keyword, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and category = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, keyword);
                ps.setInt(4, offset);
                ps.setInt(5, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchCategoryFood(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and category = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, keyword);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFoodByPrice(String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, priceTo);
                ps.setString(4, priceFrom);
                ps.setInt(5, offset);
                ps.setInt(6, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchPriceFood(String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, priceTo);
                ps.setString(4, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFood(String foodName, String FoodCategory, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and category = ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, FoodCategory);
                ps.setString(5, priceTo);
                ps.setString(6, priceFrom);
                ps.setInt(7, offset);
                ps.setInt(8, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchFood(String foodName, String FoodCategory, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and category = ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, FoodCategory);
                ps.setString(5, priceTo);
                ps.setString(6, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFoodByNameACategory(String foodName, String FoodCategory, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and category = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, FoodCategory);
                ps.setInt(5, offset);
                ps.setInt(6, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchNameACategoryFood(String foodName, String FoodCategory) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and category = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, FoodCategory);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFoodByCategoryAPrice(String FoodCategory, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "category = ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, FoodCategory);
                ps.setString(4, priceTo);
                ps.setString(5, priceFrom);
                ps.setInt(6, offset);
                ps.setInt(7, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchCategoryAPriceFood(String FoodCategory, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "category = ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, FoodCategory);
                ps.setString(4, priceTo);
                ps.setString(5, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFoodByNameAPrice(String foodName, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, priceTo);
                ps.setString(5, priceFrom);
                ps.setInt(6, offset);
                ps.setInt(7, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFood == null) {
                        listFood = new ArrayList<>();
                    }
                    listFood.add(food);
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

    public int loadRecordSearchNameAPriceFood(String foodName, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where status = ? and quantity > ? and "
                        + "foodName LIKE ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, true);
                ps.setInt(2, 0);
                ps.setString(3, "%" + foodName + "%");
                ps.setString(4, priceTo);
                ps.setString(5, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }
    
    public void searchFullFoodByName(String keyword, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where foodName LIKE ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, offset);
                ps.setInt(3, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchNameFullFood(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where foodName LIKE ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFoodByCategory(String keyword, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where category = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                ps.setInt(2, offset);
                ps.setInt(3, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchCategoryFullFood(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where category = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, keyword);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFoodByPrice(String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, priceTo);
                ps.setString(2, priceFrom);
                ps.setInt(3, offset);
                ps.setInt(4, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchPriceFullFood(String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodID "
                        + "From Food "
                        + "Where price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, priceTo);
                ps.setString(2, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFood(String foodName, String FoodCategory, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and category = ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, FoodCategory);
                ps.setString(3, priceTo);
                ps.setString(4, priceFrom);
                ps.setInt(5, offset);
                ps.setInt(6, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchFullFood(String foodName, String FoodCategory, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and category = ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, FoodCategory);
                ps.setString(3, priceTo);
                ps.setString(4, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFoodByNameACategory(String foodName, String FoodCategory, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and category = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, FoodCategory);
                ps.setInt(3, offset);
                ps.setInt(4, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchNameACategoryFullFood(String foodName, String FoodCategory) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and category = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, FoodCategory);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFoodByCategoryAPrice(String FoodCategory, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where "
                        + "category = ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, FoodCategory);
                ps.setString(2, priceTo);
                ps.setString(3, priceFrom);
                ps.setInt(4, offset);
                ps.setInt(5, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchCategoryAPriceFullFood(String FoodCategory, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where "
                        + "category = ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, FoodCategory);
                ps.setString(2, priceTo);
                ps.setString(3, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public void searchFullFoodByNameAPrice(String foodName, String priceFrom, String priceTo, int offset, int fetch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID, foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and price < = ? and price > = ? "
                        + "Order by createDate DESC "
                        + "Offset ? Rows Fetch Next ? Rows Only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, priceTo);
                ps.setString(3, priceFrom);
                ps.setInt(4, offset);
                ps.setInt(5, fetch);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String description = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date createDate = rs.getDate(6);
                    String category = rs.getString(7);
                    boolean status = rs.getBoolean(8);
                    int quantity = rs.getInt(9);
                    FoodDTO food = new FoodDTO(id, name, image, description, price, createDate, category, status, quantity);
                    if (listFullFood == null) {
                        listFullFood = new ArrayList<>();
                    }
                    listFullFood.add(food);
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

    public int loadRecordSearchNameAPriceFullFood(String foodName, String priceFrom, String priceTo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select distinct foodID "
                        + "From Food "
                        + "Where "
                        + "foodName LIKE ? and price < = ? and price > = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + foodName + "%");
                ps.setString(2, priceTo);
                ps.setString(3, priceFrom);
                rs = ps.executeQuery();
                int total = 0;
                while (rs.next()) {
                    total++;
                }
                return total;
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
        return 0;
    }

    public boolean createFood(FoodDTO food) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Insert Into Food (foodID, foodName, image, description, price, createDate, category, status, quantity) "
                        + "Values (?,?,?,?,?,?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, food.getFoodID());
                ps.setString(2, food.getFoodName());
                ps.setString(3, food.getImage());
                ps.setString(4, food.getDescription());
                ps.setFloat(5, food.getPrice());
                java.sql.Date sqlDate = new java.sql.Date(food.getCreateDate().getTime());
                ps.setDate(6, sqlDate);
                ps.setString(7, food.getCategory());
                ps.setBoolean(8, food.isStatus());
                ps.setInt(9, food.getQuantity());
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

    public boolean updateFood(FoodDTO food) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Update Food "
                        + "Set foodName = ?, image = ?, description = ?, price = ?, category = ?, status = ?, quantity = ? "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, food.getFoodName());
                ps.setString(2, food.getImage());
                ps.setString(3, food.getDescription());
                ps.setFloat(4, food.getPrice());
                ps.setString(5, food.getCategory());
                ps.setBoolean(6, food.isStatus());
                ps.setInt(7, food.getQuantity());
                ps.setString(8, food.getFoodID());
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

    public boolean deleteFood(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Update Food "
                        + "Set status = 'False' "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
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

    public FoodDTO getFood(String foodID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodName, image, description, price, createDate, category, status, quantity "
                        + "From Food "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString(1);
                    String image = rs.getString(2);
                    String description = rs.getString(3);
                    float price = rs.getFloat(4);
                    Date createDate = rs.getDate(5);
                    String category = rs.getString(6);
                    boolean status = rs.getBoolean(7);
                    int quantity = rs.getInt(8);
                    FoodDTO food = new FoodDTO(foodID, name, image, description, price, createDate, category, status, quantity);
                    return food;
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
    
    public boolean updateQuantityFood(String foodID, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Update Food "
                        + "Set quantity = ? "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, foodID);
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
    
    public int getQuantityFood(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select quantity "
                        + "From Food "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }
    
    public boolean isStatusFood(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select status "
                        + "From Food "
                        + "Where foodID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getBoolean(1);
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
    
    public FoodDTO getFoodByBillDetailID(String billDetailID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBAccess.openConnection();
            if (con != null) {
                String sql = "Select foodName, image, description, food.price, createDate, category, status, quantity, food.foodID "
                        + "From Food "
                        + "Inner Join BillDetail "
                        + "On Food.foodID = BillDetail.foodID "
                        + "Where billDetailID = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, billDetailID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString(1);
                    String image = rs.getString(2);
                    String description = rs.getString(3);
                    float price = rs.getFloat(4);
                    Date createDate = rs.getDate(5);
                    String category = rs.getString(6);
                    boolean status = rs.getBoolean(7);
                    int quantity = rs.getInt(8);
                    String foodID = rs.getString(9);
                    FoodDTO food = new FoodDTO(foodID, name, image, description, price, createDate, category, status, quantity);
                    return food;
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
    
}
