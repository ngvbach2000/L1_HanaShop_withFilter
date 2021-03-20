/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.food.FoodDAO;
import bachnv.food.FoodDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngvba
 */
public class SearchFoodServlet extends HttpServlet {

    private final String LOAD_FOOD_SERVLET = "";
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

        String url = LOAD_FOOD_SERVLET;
        String nameValue = request.getParameter("txtSearchFood");
        String categoryValue = request.getParameter("DDLCategory");
        String priceFromValue = request.getParameter("txtPriceFrom");
        String priceToValue = request.getParameter("txtPriceTo");

        try {
            if (!nameValue.trim().isEmpty()
                    || (!categoryValue.trim().isEmpty())
                    || (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {

                url = SHOPPING_PAGE;
                
                int page = 1;
                int recordsPerPage = 20;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                int noOfRecords = 0;
                FoodDAO dao = new FoodDAO();

                //By Name
                if (!nameValue.trim().isEmpty()
                        && (categoryValue.trim().isEmpty())
                        && (priceFromValue.trim().isEmpty() && priceToValue.trim().isEmpty())) {
                    dao.searchFoodByName(nameValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchNameFood(nameValue);
                } else

                //By Category
                if (nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (priceFromValue.trim().isEmpty() && priceToValue.trim().isEmpty())) {
                    dao.searchFoodByCategory(categoryValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchCategoryFood(categoryValue);
                } else

                //By Price
                if (nameValue.trim().isEmpty()
                        && (categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFoodByPrice(priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchPriceFood(priceFromValue, priceToValue);
                } else

                //By Name and Category
                if (!nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (priceFromValue.trim().isEmpty() && priceToValue.trim().isEmpty())) {
                    dao.searchFoodByNameACategory(nameValue, categoryValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchNameACategoryFood(nameValue, categoryValue);
                } else

                //By Name and price
                if (!nameValue.trim().isEmpty()
                        && (categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFoodByNameAPrice(nameValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchNameAPriceFood(nameValue, priceFromValue, priceToValue);
                } else

                //By Category and Price
                if (nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFoodByCategoryAPrice(categoryValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchCategoryAPriceFood(categoryValue, priceFromValue, priceToValue);
                } else

                //By all
                if (!nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFood(nameValue, categoryValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFood = dao.getListFood();
                    request.setAttribute("LISTFOOD", listFood);
                    noOfRecords = dao.loadRecordSearchFood(nameValue, categoryValue, priceFromValue, priceToValue);
                }
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("NOOFPAGE", noOfPages);
                request.setAttribute("CURRENTPAGE", page);
            }
        } catch (SQLException ex) {
            log("SearchFood _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchFood _ Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
