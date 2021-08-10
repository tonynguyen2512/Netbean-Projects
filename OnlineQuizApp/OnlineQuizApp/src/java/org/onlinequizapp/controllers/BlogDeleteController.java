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
import org.onlinequizapp.dtos.UserDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "BlogDeleteController", urlPatterns = {"/BlogDeleteController"})
public class BlogDeleteController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "BlogSearchController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *a
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        String LogID = "";
        String role = "";
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
            role = ((UserDTO) session.getAttribute("LOGIN_USER")).getRole();
        }
        try {
            String blogID = request.getParameter("blogID");
            String status = request.getParameter("status");
            BlogDAO dao = new BlogDAO();
            BlogDTO dto = dao.checkBlogAuthor(LogID);
            if (dto != null) {
                if (dto.getAuthorID().equals(LogID) || role.contains("AD")) {
                    if (!status.equals("1")) {
                        boolean check = dao.deleteBlog(blogID);
                        if (check) {
                            url = SUCCESS;
                        }
                    }
                } else {
                    request.setAttribute("DELETE_ERROR", "Cannot delete other user blog!");
                    url = SUCCESS;
                }
            } else if (role.contains("AD")) {
                if (!status.equals("1")) {
                    boolean check = dao.deleteBlog(blogID);
                    if (check) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("DELETE_ERROR", "Cannot delete other user blog!");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at BlogDelete Controller");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
