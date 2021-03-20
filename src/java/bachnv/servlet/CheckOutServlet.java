/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.bill.BillDAO;
import bachnv.bill.BillDTO;
import bachnv.billdetail.BillDetailDAO;
import bachnv.billdetail.BillDetailDTO;
import bachnv.cart.CartItemDTO;
import bachnv.cart.CartObject;
import bachnv.food.FoodDAO;
import bachnv.food.FoodDTO;
import bachnv.user.UpdateUserError;
import bachnv.user.UserDAO;
import bachnv.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class CheckOutServlet extends HttpServlet {

    private final String LOAD_FOOD_SERVLET = "";
    private final String CHECK_OUT_PAGE = "checkOutPage";

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

        String url = CHECK_OUT_PAGE;

        String fullname = request.getParameter("txtFullName");
        String address = request.getParameter("txtAddress");
        String email = request.getParameter("txtEmail");
        String phone = request.getParameter("txtPhone");
        String paymentOption = request.getParameter("paymentOption");

        UpdateUserError errors = new UpdateUserError();
        boolean error = false;
        try {

            if (fullname.length() < 5 || fullname.length() > 100) {
                errors.setFullnameLengthError("Full Name requite tying 5 to 100 char...");
                error = true;
            }
            if (address.length() < 5 || address.length() > 200) {
                errors.setAddressLengthError("Address requite tying 5 to 200 char...");
                error = true;
            }
            if (email.length() < 5 || email.length() > 50) {
                errors.setEmailLengthError("Email requite tying 5 to 50 char...");
                error = true;
            }
            if (phone.length() != 10) {
                errors.setPhoneLengthError("Full Name requite tying 10 digits...");
                error = true;
            }
            if (!error) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                    if (cart != null) {
                        List<CartItemDTO> foods = cart.getFoods();
                        if (!foods.isEmpty()) {
                            UserDTO user = (UserDTO) session.getAttribute("USER");
                            if (user != null) {
                                user.setFullname(fullname);
                                user.setAddress(address);
                                user.setEmail(email);
                                user.setPhone(phone);
                                UserDAO userDAO = new UserDAO();
                                boolean resultInsertUser = userDAO.updateInfoUser(user);
                                if (resultInsertUser) {
                                    Map<FoodDTO, Integer> foodOutOfStock = null;
                                    FoodDAO foodDAO = new FoodDAO();
                                    for (int i = 0; i < foods.size(); i++) {
                                        int quantityFoodInStore = foodDAO.getQuantityFood(foods.get(i).getFood().getFoodID());
                                        int quantityFoodInCart = foods.get(i).getAmount();
                                        if (quantityFoodInCart > quantityFoodInStore) {
                                            error = true;
                                            if (foodOutOfStock == null) {
                                                foodOutOfStock = new LinkedHashMap<>();
                                            }
                                            foodOutOfStock.put(foods.get(i).getFood(), quantityFoodInStore);
                                        } else if (!foodDAO.isStatusFood(foods.get(i).getFood().getFoodID())) {
                                            error = true;
                                            if (foodOutOfStock == null) {
                                                foodOutOfStock = new LinkedHashMap<>();
                                            }
                                            foodOutOfStock.put(foods.get(i).getFood(), 0);
                                        }
                                    }
                                    if (error) {
                                        request.setAttribute("OUTOFSTOCK", foodOutOfStock);
                                    }
                                    if (!error) {
                                        BillDTO billDTO = new BillDTO(user.getUsername() + "_" + df.format(Calendar.getInstance().getTime()),
                                                cart.getTotalPrice(), sdf.parse(sdf.format(Calendar.getInstance().getTime())), user, paymentOption);
                                        BillDAO billDAO = new BillDAO();
                                        boolean resultBill = billDAO.insertBill(billDTO);
                                        if (resultBill) {
                                            BillDetailDAO billDetailDAO = new BillDetailDAO();
                                            Boolean resultBillDetai = false;
                                            for (int i = 0; i < foods.size(); i++) {
                                                BillDetailDTO billDetailDTO
                                                        = new BillDetailDTO(df.format(Calendar.getInstance().getTime()) + i, billDTO, foods.get(i).getFood(),
                                                                foods.get(i).getAmount(),
                                                                foods.get(i).getPrice(), foods.get(i).getTotal());
                                                int foodQuantity = foodDAO.getQuantityFood(foods.get(i).getFood().getFoodID());
                                                boolean updateQuantity = foodDAO.updateQuantityFood(foods.get(i).getFood().getFoodID(),
                                                        foodQuantity - foods.get(i).getAmount());
                                                if (updateQuantity) {
                                                    resultBillDetai = billDetailDAO.InsertBillDetail(billDetailDTO);
                                                }

                                            }
                                            if (resultBillDetai) {
                                                url = LOAD_FOOD_SERVLET;
                                                session.removeAttribute("CUSTCART");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                request.setAttribute("ERRORS", errors);
            }
        } catch (ParseException ex) {
            log("CheckOut _ Parse " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckOut _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOut _ Naming " + ex.getMessage());
        } finally {
            if (error) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
