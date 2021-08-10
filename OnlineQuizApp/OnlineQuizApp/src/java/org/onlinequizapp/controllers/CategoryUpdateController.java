/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CategoryDTO;

/**
 *
 * @author Category-PC
 */
@WebServlet(name = "CategoryUpdateController", urlPatterns = {"/CategoryUpdateController"})
public class CategoryUpdateController extends HttpServlet {

    private static final String SUCCESS = "CategorySearchController";
    private static final String ERROR = "updateCategory.jsp";
    private static final String ERROR2 = "updateCategoryBlog.jsp";

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
        String url = ERROR;
        String url2 = ERROR2;
        String check = request.getParameter("action");
        CategoryDTO categoryDTO = new CategoryDTO("", "", "", "", "");
        if (check.equals("Confirm Update Quiz")) {
            try {
                String categoryID = request.getParameter("categoryID");
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                String level = request.getParameter("level");
                CategoryDAO dao = new CategoryDAO();
                CategoryDTO category = new CategoryDTO(categoryID, categoryName, description, status, level);
                boolean flag = true;
                if (categoryName.length() > 250 || categoryName.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Category Name must be [1-250]");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    categoryDTO.setStatus("Status must be 0 or 1");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Description must be [1-250]");
                }
                if (level.length() > 50 || level.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Level must be [1-50]");
                }
                if (flag) {
                    boolean update = dao.updateQ(category);
                    if (update) {
                        request.setAttribute("UPDATE_Q_SUCCESS", "Update Success!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", categoryDTO);
                }
            } 
            catch (Exception e) {

            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (check.equals("Update Quiz")){
            request.getRequestDispatcher(url).forward(request, response);
        }
        
        CategoryBlogDTO categoryBlogDTO = new CategoryBlogDTO("", "", "", "");
        if (check.equals("Confirm Update Blog")) {
            try {
                String categoryID = request.getParameter("categoryID");
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                CategoryDAO dao = new CategoryDAO();
                CategoryBlogDTO category = new CategoryBlogDTO(categoryID, categoryName, description, status);
                boolean flag = true;
                if (categoryName.length() > 250 || categoryName.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Category Name must be [1-250]");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    categoryDTO.setStatus("Status must be 0 or 1");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setCategoryName("Description must be [1-250]");
                }
                if (flag) {
                    boolean update = dao.updateB(category);
                    if (update) {
                        request.setAttribute("UPDATE_B_SUCCESS", "Update Success!");
                        url2 = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", categoryBlogDTO);
                }
            } catch (Exception e) {

            } finally {
                request.getRequestDispatcher(url2).forward(request, response);
            }
        } else if (check.equals("Update Blog")){
            request.getRequestDispatcher(url2).forward(request, response);
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
