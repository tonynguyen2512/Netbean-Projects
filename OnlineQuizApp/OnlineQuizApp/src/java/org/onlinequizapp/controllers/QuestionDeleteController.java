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
import org.onlinequizapp.daos.CourseDAO;
import org.onlinequizapp.daos.QuestionDAO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "QuestionDeleteController", urlPatterns = {"/QuestionDeleteController"})
public class QuestionDeleteController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "QuestionSearchController";

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
        String action = request.getParameter("action");
        if (action.equals("Delete")) {
            try {
                if (!request.getParameter("status").equals("1")) {
                    String QuestionID = request.getParameter("QuestionID");
                    QuestionDAO dao = new QuestionDAO();
                    boolean checkDelete = dao.deleteQ(QuestionID);
                    if (checkDelete) {
                        request.setAttribute("DELETE_SUCCESS", "Delete Success!");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("DELETE_ERROR", "Cannot delete!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("DELETE_ERROR", "Question is being used!");
                    url = SUCCESS;
                }
            } catch (Exception e) {

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
