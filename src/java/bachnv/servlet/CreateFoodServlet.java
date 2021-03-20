/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.servlet;

import bachnv.food.FoodCreateError;
import bachnv.food.FoodDAO;
import bachnv.food.FoodDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ngvba
 */
public class CreateFoodServlet extends HttpServlet {

    private final String CREATE_FOOD_PAGE = "createFoodPage";
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

        String url = CREATE_FOOD_PAGE;

        boolean isMutilPart = ServletFileUpload.isMultipartContent(request);

        if (isMutilPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (Exception e) {
                log("CreateFood _ Exception " + e.getMessage());
            }
            Iterator iter = items.iterator();
            Hashtable params = new Hashtable();
            String filename = null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    params.put(item.getFieldName(), item.getString());
                } else {
                    try {
                        String itemName = item.getName();
                        filename = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        String realPath = getServletContext().getRealPath("/") + "image\\" + filename;
                        File saveFile = new File(realPath);
                        item.write(saveFile);
                    } catch (Exception ex) {
                        log("CreateFood _ Exception " + ex.getMessage());
                    }
                }
            }

            FoodCreateError errors = new FoodCreateError();
            boolean error = false;
            String foodID = (String) params.get("txtFoodID");
            String foodName = (String) params.get("txtFoodName");
            String image = "image/" + filename;
            String description = (String) params.get("txtDescription");
            String stringPrice = (String) params.get("txtPrice");

            String category = (String) params.get("txtCategory");
            String stringQuantity = (String) params.get("txtQuantity");

            boolean status = true;
            Date createDate = Calendar.getInstance().getTime();
            FoodDTO newFood = new FoodDTO(foodID, foodName, image, description, (float) 0.01, createDate, category, status, 0);
            request.setAttribute("NEWFOOD", newFood);

            try {
                if (foodID.length() < 1 || foodID.length() > 20) {
                    errors.setFoodIDIsExited("Please input Food ID (1 - 20 char)!");
                    error = true;
                }
                if (foodName.length() < 2 || foodName.length() > 30) {
                    errors.setFoodNameLengthError("Please input Food Name (2 - 30 char)!");
                    error = true;
                }
                if ("image/".equals(image)) {
                    errors.setFoodImageIsNotAPicture("Please input Image!");
                    error = true;
                }
                if (description.length() < 2 || description.length() > 250) {
                    errors.setDescriptionLengthError("Please input Description (2 - 250 char)!");
                    error = true;
                }
                if ("".equals(stringPrice)) {
                    errors.setPriceLengthError("Please input Price (0.01 - 999)!");
                    error = true;
                }
                if (category.length() < 2 || category.length() > 20) {
                    errors.setCategoryLengthError("Please input Category (2 - 20 char)!");
                    error = true;
                }
                if ("".equals(stringQuantity)) {
                    errors.setQuantityLengthError("Please input Quantity (0 - 9999)!");
                    error = true;
                }
                if (!error) {
                    float price = Float.parseFloat(stringPrice);
                    int quantity = Integer.parseInt(stringQuantity);
                    newFood.setPrice(price);
                    newFood.setQuantity(quantity);
                    request.setAttribute("NEWFOOD", newFood);
                    String format = "";
                    int index = filename.lastIndexOf(".");
                    if (index > 0) {
                        format = filename.substring(index + 1);
                    }
                    if (format.equalsIgnoreCase("jpeg")
                            || format.equalsIgnoreCase("png")
                            || format.equalsIgnoreCase("jpg")) {

                        FoodDAO dao = new FoodDAO();
                        boolean result = dao.createFood(newFood);
                        if (result) {
                            url = LOAD_FOOD_SERVLET;
                        }

                    } else {
                        errors.setFoodImageIsNotAPicture("Please input file image with extension file (.png, .jpeg, .jpg)");
                        error = true;
                    }
                }
            } catch (SQLException ex) {
                String errMsg = ex.getMessage();
                log("CreateFood _ Sql" + errMsg);
                if (errMsg.contains("duplicate")) {
                    errors.setFoodIDIsExited(foodID + " is existed!!!");
                    request.setAttribute("ERRORS", errors);
                    error = true;
                }
            } catch (NamingException ex) {
                log("CreateFood _ Naming " + ex.getMessage());
            } finally {
                if (error) {
                    request.setAttribute("ERRORS", errors);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(url);
                }
                out.close();
            }
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
