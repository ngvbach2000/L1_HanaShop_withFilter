/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.cart.CartObject;
import bachnv.user.UserDAO;
import bachnv.user.UserDTO;
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
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalidPage";
    private final String ADMIN_CANT_NOT_ACCESS_PAGE = "adminCantAccessPage";
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

        String url = INVALID_PAGE;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLogin(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                if (user.getRole().equals("admin")) {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart != null) {
                        url = ADMIN_CANT_NOT_ACCESS_PAGE;
                    } else {
                        url = LOAD_FOOD_SERVLET;
                        session.setAttribute("USERFULLNAME", user.getFullname());
                        session.setAttribute("USERROLE", user.getRole());
                        session.setAttribute("USER", user);
                    }
                }
                if (user.getRole().equals("cust")) {
                    url = LOAD_FOOD_SERVLET;
                    session.setAttribute("USERFULLNAME", user.getFullname());
                    session.setAttribute("USERROLE", user.getRole());
                    session.setAttribute("USER", user);
                }
            }
        } catch (SQLException ex) {
            log("Login _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("Login _ Naming " + ex.getMessage());
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
