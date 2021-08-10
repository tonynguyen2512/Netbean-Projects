package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.onlinequizapp.daos.ClassDAO;
import org.onlinequizapp.dtos.ClassDTO;

@WebServlet(name = "ClassUpdateController", urlPatterns = {"/ClassUpdateController"})
public class ClassUpdateController extends HttpServlet {

    private static final String SUCCESS = "ClassSearchController";
    private static final String ERROR = "updateClass.jsp";

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
        String action = request.getParameter("action");
        ClassDTO categoryDTO = new ClassDTO("", "", "");
        if (action.equals("Confirm Update Class")) {
            try {
                String categoryID = request.getParameter("classID");
                String NumberOfStudent = request.getParameter("numberOfStudent");
                String status = request.getParameter("status");
                ClassDAO dao = new ClassDAO();
                ClassDTO category = new ClassDTO(categoryID, NumberOfStudent, status);
                boolean flag = true;
                if (NumberOfStudent.length() > 250 || NumberOfStudent.length() < 1) {
                    flag = false;
                    categoryDTO.setNumberOfStudent("Number Of Student must be [1-250]");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    categoryDTO.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    boolean update = dao.update(category);
                    if (update) {
                        request.setAttribute("UPDATE_SUCCESS", "Update Success!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", categoryDTO);
                }
            } catch (Exception e) {

            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (action.equals("Update")) {
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
