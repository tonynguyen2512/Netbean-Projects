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
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.BlogDAO;
import org.onlinequizapp.dtos.BlogDTO;
import org.onlinequizapp.dtos.BlogError;
import org.onlinequizapp.dtos.UserDTO;
//import org.onlinequizapp.dtos.BlogError;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "BlogUpdateController", urlPatterns = {"/BlogUpdateController"})
public class BlogUpdateController extends HttpServlet {

    private static final String SUCCESS = "BlogSearchController";
    private static final String ERROR = "updateBlog.jsp";

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
        String check = request.getParameter("check");
        String LogID = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
        }
        if (check.equals("confirm")) {
            BlogDTO BlogDTO = new BlogDTO("", "", "", "", "", "", "");
            try {
                String authorID = request.getParameter("authorID");
                String BlogID = request.getParameter("BlogID");
                String Title = request.getParameter("Title");
                String content = request.getParameter("content");
                String Image = request.getParameter("Image");
                String BlogCategoryID = request.getParameter("BlogCategory");
                String status = request.getParameter("status");
                boolean flag = true;
                if (status == null) {
                    status = "0";
                } else if (status.equals("on")) {
                    status = "1";
                } else {
                    status = "0";
                }
                if (content.length() < 1) {
                    flag = false;
                    BlogDTO.setContent("You must enter something to post ");
                }
                if (BlogCategoryID == null || BlogCategoryID.isEmpty()) {
                    flag = false;
                    BlogDTO.setCategoryID("Please choose a category");
                }
                if (flag) {
                    BlogDAO dao = new BlogDAO();
                    BlogDTO dto = new BlogDTO(BlogID, Title, authorID, BlogCategoryID, content, Image, status);

                    dao.update(dto);
                    if (dao.update(dto)) {
                        request.setAttribute("UPDATE_BLOG_SUCCESS", "Update");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("UPDATE_BLOG_ERROR", "Update fail");
                        url = ERROR;
                    }

                } else {
                    request.setAttribute("UPDATE_BLOG_ERROR", "Update fail");
                    url = ERROR;
                }
            } catch (Exception e) {

            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }

        } else if (check.equals("updateBlog")) {

            request.getRequestDispatcher(ERROR).forward(request, response);
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
