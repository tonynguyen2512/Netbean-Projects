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

/**
 *
 * @author User-PC
 */
@WebServlet(name = "BlogCreateController", urlPatterns = {"/BlogCreateController"})
public class BlogCreateController extends HttpServlet {

    private static final String SUCCESS = "create-blog.jsp";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String check = request.getParameter("check");
        String LogID = "";
        HttpSession session = request.getSession();
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
        }
        if (check.equals("blogCreate")) {
            BlogDTO BlogDTO = new BlogDTO("", "", "", "", "", "", "");
            try {
                String Title = request.getParameter("Title");
                String content = request.getParameter("content");
                String Image = request.getParameter("Image");
                String BlogCategoryID = request.getParameter("BlogCategory");
                String status = request.getParameter("status");
                if (status == null) {
                    status = "0";
                } else if (status.equals("on")) {
                    status = "1";
                } else {
                    status = "0";
                }
                boolean flag = true;
                if (Title.isEmpty() || Title.length() > 250) {
                    flag = false;
                    BlogDTO.setTitle("Title must be [1-250]");
                }
                if (flag) {
                    BlogDAO dao = new BlogDAO();
                    BlogDTO dto = new BlogDTO("", Title, LogID, BlogCategoryID, content, Image, status);
                    dao.insert(dto);
                    request.setAttribute("CREATE_BLOG_SUCCESS", "Create Success!");
                    url = SUCCESS;

                } else {
                    request.setAttribute("CREATE_BLOG_ERROR", "Create Fail!");
                    url = SUCCESS;
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (e.toString().contains("duplicate")) {
                    BlogDTO.setTitle("Title duplicate!");
                    request.setAttribute("ERROR", BlogDTO);
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
