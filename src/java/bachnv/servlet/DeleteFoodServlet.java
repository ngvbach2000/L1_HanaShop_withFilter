/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.food.FoodDAO;
import bachnv.user.UserDTO;
import bachnv.userhistory.UserHistoryDAO;
import bachnv.userhistory.UserHistoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class DeleteFoodServlet extends HttpServlet {

    private final String LOAD_FOOD_SERVLET = "";

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

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String url = LOAD_FOOD_SERVLET;

        try {
            String[] deleteFood = request.getParameterValues("ckbDeleteFood");
            String searchFood = request.getParameter("searchFood");
            String searchCategory = request.getParameter("searchCategory");
            String searchPriceFrom = request.getParameter("searchPriceFrom");
            String searchPriceTo = request.getParameter("searchPriceTo");
            String currentPage = request.getParameter("currentPage");

            if (deleteFood != null) {
                FoodDAO dao = new FoodDAO();
                boolean result = true;
                String foodDelete = "";
                for (String food : deleteFood) {
                    result = dao.deleteFood(food);
                    foodDelete = foodDelete + " - " + food;
                }
                if (result) {
                    HttpSession session = request.getSession(false);
                    UserDTO user = (UserDTO) session.getAttribute("USER");
                    String history = "DELETE food which foodID: " + foodDelete;
                    UserHistoryDTO userHistoryDTO = new UserHistoryDTO(df.format(Calendar.getInstance().getTime()),
                             user, history, sdf.parse(sdf.format(Calendar.getInstance().getTime())));
                    UserHistoryDAO userHistoryDAO = new UserHistoryDAO();
                    userHistoryDAO.insertUserHistory(userHistoryDTO);
                }
            }
                
            url = "searchFullFood"
                    + "?txtSearchFood=" + searchFood
                    + "&DDLCategory=" + searchCategory
                    + "&txtPriceFrom=" + searchPriceFrom
                    + "&txtPriceTo=" + searchPriceTo
                    + "&page=" + currentPage;
        } catch (SQLException ex) {
            log("DeleteFood _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteFood _ Naming " + ex.getMessage());
        } catch (ParseException ex) {
            log("DeleteFood _ Parse " + ex.getMessage());
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
