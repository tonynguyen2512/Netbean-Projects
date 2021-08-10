/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.daos.CourseDAO;
import org.onlinequizapp.dtos.CategoryDTO;
import org.onlinequizapp.dtos.CourseDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "CourseUpdateController", urlPatterns = {"/CourseUpdateController"})
public class CourseUpdateController extends HttpServlet {

    private static final String SUCCESS = "CourseSearchController";
    private static final String ERROR = "updateCourse.jsp";

    /**
     * /**
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
        CategoryDAO dao1 = new CategoryDAO();
        List<CategoryDTO> list = null;
        try {
            list = dao1.getListQ("");
        } catch (SQLException e) {
            log("Error at CategorySearchController: " + e.toString());
        } finally {
            if (list != null) {
                request.setAttribute("LIST_QUIZ_CATEGORY", list);
            }
        }
        if (action.equals("Confirm")) {
            try {
                String LogID = request.getParameter("authorID");
                String courseID = request.getParameter("courseID");
                String courseName = request.getParameter("courseName");
                String duration = request.getParameter("duration");
                String status = request.getParameter("status");
                String categoryID = request.getParameter("categoryID");
                String description = request.getParameter("description");
                CourseDAO dao = new CourseDAO();
                CourseDTO categoryDTO = new CourseDTO(courseID, courseName, LogID, duration, status, categoryID, description);
                boolean flag = true;
                if (courseName.length() > 250 || courseName.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Course Name must be [1-250]");
                }
                if (duration.length() > 250 || duration.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Duration must be [1-250]");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Description must be [1-250]");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    categoryDTO.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    boolean update = dao.update(categoryDTO);
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
