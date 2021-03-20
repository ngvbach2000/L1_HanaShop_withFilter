/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.accessgoogle.GoogleDTO;
import bachnv.user.UserDAO;
import bachnv.user.UserDTO;
import bachnv.util.GoogleAccess;
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
public class LoginWithGoogleServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage";
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

        String code = request.getParameter("code");
        String url = LOGIN_PAGE;

        try {
            if (code == null || code.isEmpty()) {
                url = LOGIN_PAGE;
            } else {
                String accessToken = GoogleAccess.getToken(code);
                GoogleDTO googleDTO = GoogleAccess.getUserInfo(accessToken);

                UserDAO userDAO = new UserDAO();
                boolean usernameIsExited = userDAO.checkExited(googleDTO.getId());
                if (!usernameIsExited) {
                    UserDTO userDTO = new UserDTO();
                    int pos = googleDTO.getEmail().indexOf("@");
                    String accountName = googleDTO.getEmail().substring(0, pos);
                    userDTO.setUsername(googleDTO.getId());
                    userDTO.setPassword("812AD378*&89q02@34h");
                    String fullname = googleDTO.getName();
                    if (fullname == null) {
                        userDTO.setFullname(accountName);
                    } else {
                        userDTO.setFullname(fullname);
                    }
                    userDTO.setEmail(googleDTO.getEmail());
                    userDTO.setRole("cust");
                    Boolean resultInsertUser = userDAO.insertUser(userDTO);
                    if (resultInsertUser) {
                        url = SHOPPING_PAGE;
                        HttpSession session = request.getSession();
                        session.setAttribute("USERFULLNAME", userDTO.getFullname());
                        session.setAttribute("USERROLE", userDTO.getRole());
                        session.setAttribute("USER", userDTO);
                    }
                } else {
                    String username = googleDTO.getId();
                    String password = "812AD378*&89q02@34h";
                    UserDTO user = userDAO.checkLogin(username, password);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERFULLNAME", user.getFullname());
                        session.setAttribute("USERROLE", user.getRole());
                        session.setAttribute("USER", user);
                        url = SHOPPING_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            log("LoginWithGoogle _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginWithGoogle _ Naming " + ex.getMessage());
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
