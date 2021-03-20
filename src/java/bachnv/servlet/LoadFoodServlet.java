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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngvba
 */
public class LoadFoodServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shoppingPage";
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

        String url = SHOPPING_PAGE;

        try {
            
            HttpSession session = request.getSession();
            
            int page = 1;
            int recordsPerPage = 20;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            FoodDAO dao = new FoodDAO();
            
            dao.loadFood((page - 1) * recordsPerPage, recordsPerPage);
            List<FoodDTO> listFood = dao.getListFood();
            int noOfRecords = dao.loadRecordFood();
            request.setAttribute("LISTFOOD", listFood);
            
            dao.loadCategory();
            List<String> listCategory = dao.getListCategory();
            session.setAttribute("LISTCATEGORY", listCategory);
            
            dao.loadFullFood((page - 1) * recordsPerPage, recordsPerPage);
            List<FoodDTO> listFullFood = dao.getListFullFood();
            int noOfFullRecords = dao.loadRecordFullFood();
            request.setAttribute("LISTFULLFOOD", listFullFood);
            
            dao.loadFullCategory();
            List<String> listFullCategory = dao.getListFullCategory();
            session.setAttribute("LISTFULLCATEGORY", listFullCategory);
            
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            int noOfFullPages = (int) Math.ceil(noOfFullRecords * 1.0 / recordsPerPage);
            request.setAttribute("NOOFPAGE", noOfPages);
            request.setAttribute("NOOFFULLPAGE", noOfFullPages);
            request.setAttribute("CURRENTPAGE", page);
            
            if (session.getAttribute("USERROLE") != null) {
                if (session.getAttribute("USERROLE").equals("admin")) {
                    url = MANAGER_PAGE;
                }
            }
        } catch (SQLException ex) {
            log("LoadFood _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoadFood _ Naming " + ex.getMessage());
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
