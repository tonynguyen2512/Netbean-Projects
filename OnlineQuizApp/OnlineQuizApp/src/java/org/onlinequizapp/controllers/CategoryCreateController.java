/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.TRUE;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CategoryDTO;

/**
 *
 * @author Category-PC
 */
@WebServlet(name = "CategoryCreateController", urlPatterns = {"/CategoryCreateController"})
public class CategoryCreateController extends HttpServlet {

    private static final String SUCCESS = "categoryAdd.jsp";
    private static final String SUCCESS2 = "categoryBlogAdd.jsp";
    private static final String ERROR = "error.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String check = request.getParameter("check");
        
        if (check.equals("quiz")) {
            CategoryDTO categoryDTO = new CategoryDTO("", "", "", "", "");
            try {
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                String level = request.getParameter("level");
                if(status==null){
                        status="0";
                    }
                else if (status.equals("on")) {
                    status = "1";
                } else{
                    status = "0";
                }
                boolean flag = true;
                if (categoryName.length() > 250 || categoryName.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Category Name must be [1-250]");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Description must be [1-250]");
                }
                if (!level.equalsIgnoreCase("Hard") && !level.equalsIgnoreCase("Medium") && !level.equalsIgnoreCase("Easy")) {
                    flag = false;
                    categoryDTO.setCategoryName("Level must be Hard, Easy or Medium");
                }
                if (flag) {
                    CategoryDAO dao = new CategoryDAO();
                    
                    CategoryDTO category = new CategoryDTO("", categoryName, description, status, level);
                    dao.insertQ(category);
                    request.setAttribute("CREATE_Q_SUCCESS", "Create Success!");
                    url = SUCCESS;

                } else {
                    request.setAttribute("CREATE_Q_ERROR", "Create Fail!");
                    url = SUCCESS;
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (e.toString().contains("duplicate")) {
                    categoryDTO.setCategoryID("Category Name duplicate!");
                    request.setAttribute("ERROR", categoryDTO);
                };
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (check.equals("blog")) {
            CategoryBlogDTO categoryBlogDTO = new CategoryBlogDTO("", "", "", "");
            try {
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                if(status==null){
                        status="0";
                    }
                else if (status.equals("on")) {
                    status = "1";
                } else{
                    status = "0";
                }
                boolean flag = true;
                if (categoryName.length() > 250 || categoryName.length() < 1) {
                    flag = false;
                    categoryBlogDTO.setCategoryName("Category Name must be [1-250]");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryBlogDTO.setCategoryName("Description must be [1-250]");
                }
                if (flag) {
                    CategoryDAO dao = new CategoryDAO();
                    
                    CategoryBlogDTO category = new CategoryBlogDTO("", categoryName, description, status);
                    dao.insertB(category);
                    request.setAttribute("CREATE_B_SUCCESS", "Create Success!");
                    url = SUCCESS2;

                } else {
                    request.setAttribute("CREATE_B_ERROR", "Create Fail!");
                    url = SUCCESS2;
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (e.toString().contains("duplicate")) {
                    categoryBlogDTO.setCategoryID("Category Name duplicate!");
                    request.setAttribute("ERROR", categoryBlogDTO);
                };
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
