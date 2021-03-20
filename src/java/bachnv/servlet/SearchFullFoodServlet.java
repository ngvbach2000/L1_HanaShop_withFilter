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
public class SearchFullFoodServlet extends HttpServlet {

    private final String LOAD_FOOD_SERVLET = "";
    private final String MANAGER_PAGE = "managerPage";

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

                url = MANAGER_PAGE;
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
                    dao.searchFullFoodByName(nameValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchNameFullFood(nameValue);
                } else //By Category
                if (nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (priceFromValue.trim().isEmpty() && priceToValue.trim().isEmpty())) {
                    dao.searchFullFoodByCategory(categoryValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchCategoryFullFood(categoryValue);
                } else //By Price
                if (nameValue.trim().isEmpty()
                        && (categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFullFoodByPrice(priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchPriceFullFood(priceFromValue, priceToValue);
                } else //By Name and Category
                if (!nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (priceFromValue.trim().isEmpty() && priceToValue.trim().isEmpty())) {
                    dao.searchFullFoodByNameACategory(nameValue, categoryValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchNameACategoryFullFood(nameValue, categoryValue);
                } else //By Name and price
                if (!nameValue.trim().isEmpty()
                        && (categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFullFoodByNameAPrice(nameValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchNameAPriceFullFood(nameValue, priceFromValue, priceToValue);
                } else //By Category and Price
                if (nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFullFoodByCategoryAPrice(categoryValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchCategoryAPriceFullFood(categoryValue, priceFromValue, priceToValue);
                } else //By all
                if (!nameValue.trim().isEmpty()
                        && (!categoryValue.trim().isEmpty())
                        && (!priceFromValue.trim().isEmpty() || !priceToValue.trim().isEmpty())) {
                    dao.searchFullFood(nameValue, categoryValue, priceFromValue, priceToValue, (page - 1) * recordsPerPage, recordsPerPage);
                    List<FoodDTO> listFullFood = dao.getListFullFood();
                    request.setAttribute("LISTFULLFOOD", listFullFood);
                    noOfRecords = dao.loadRecordSearchFullFood(nameValue, categoryValue, priceFromValue, priceToValue);
                }
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("NOOFPAGE", noOfPages);
                request.setAttribute("CURRENTPAGE", page);
            }
        } catch (SQLException ex) {
            log("SearchFullFood _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchFullFood _ Naming " + ex.getMessage());
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
