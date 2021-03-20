/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.cart.CartObject;
import bachnv.food.FoodDAO;
import bachnv.food.FoodDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngvba
 */
public class AddFoodToCartServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shoppingPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String lastSearchNameValue = request.getParameter("lastSearchNameValue");
        String lastSearchCategoryValue = request.getParameter("lastSearchCategoryValue");
        String lastSearchPriceFormValue = request.getParameter("lastSearchPriceFormValue");
        String lastSearchPriceToValue = request.getParameter("lastSearchPriceToValue");
        String currentPage = request.getParameter("currentPage");

        String url = SHOPPING_PAGE;
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            if (cart == null) {
                cart = new CartObject();
            }
            String foodID = request.getParameter("txtFoodID");
            FoodDAO dao = new FoodDAO();
            FoodDTO food = dao.getFood(foodID);
            if (food != null) {
                cart.addFoodToCart(food);
                session.setAttribute("CUSTCART", cart);
                if (lastSearchNameValue == null) {
                    lastSearchNameValue = "";
                }
                if (lastSearchCategoryValue == null) {
                    lastSearchCategoryValue = "";
                }
                if (lastSearchPriceFormValue == null) {
                    lastSearchPriceFormValue = "";
                }
                if (lastSearchPriceToValue == null) {
                    lastSearchPriceToValue = "";
                }
                if (currentPage == null) {
                    currentPage = "1";
                }
                url = "searchFood"
                        + "?txtSearchFood=" + lastSearchNameValue
                        + "&DDLCategory=" + lastSearchCategoryValue
                        + "&txtPriceFrom=" + lastSearchPriceFormValue
                        + "&txtPriceTo=" + lastSearchPriceToValue
                        + "&page=" + currentPage;
            }
        } catch (SQLException ex) {
            log("AddFoodToCart _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddFoodToCart _ Naming " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
