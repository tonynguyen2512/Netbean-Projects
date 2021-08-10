/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.onlinequizapp.daos.ClassDAO;
import org.onlinequizapp.daos.CourseDAO;
import org.onlinequizapp.dtos.ClassDTO;
import org.onlinequizapp.dtos.CourseDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "CourseSearchController", urlPatterns = {"/CourseSearchController"})
public class CourseSearchController extends HttpServlet {

    private static final String SUCCESS = "course.jsp";
    private static final String ERROR = "404.html";
    private static final String LECTURE = "lectureAdd.jsp";

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
        if (check.equals("Search")) {
            try {
                String search = request.getParameter("search");
                CourseDAO dao = new CourseDAO();
                List<CourseDTO> list = dao.getListCourse(search);
                if (list != null) {
                    request.setAttribute("LIST_COURSE", list);
                    url = SUCCESS;
                }
            } catch (SQLException e) {
                log("Error at ClassSearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (check.equals("Course")) {
            try {
                String search = request.getParameter("search");
                CourseDAO dao = new CourseDAO();
                List<CourseDTO> list = dao.getListCourse(search);
                if (list != null) {
                    request.setAttribute("LIST_COURSE", list);
                    url = ERROR;
                }
            } catch (SQLException e) {
                log("Error at CourseSearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (check.equals("Lecture")) {
            try {
                String search = request.getParameter("search");
                CourseDAO dao = new CourseDAO();
                List<CourseDTO> list = dao.getListCourse(search);
                if (list != null) {
                    request.setAttribute("LIST_COURSE", list);
                    url = LECTURE;
                }
            } catch (SQLException e) {
                log("Error at CourseSearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else {
            request.setAttribute("LIST_CLASS_ERROR", "ERROR at CourseSearchController");
            url = ERROR;
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
