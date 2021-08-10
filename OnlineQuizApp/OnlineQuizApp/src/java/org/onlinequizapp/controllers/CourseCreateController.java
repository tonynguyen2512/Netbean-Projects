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
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.daos.CourseDAO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CategoryDTO;
import org.onlinequizapp.dtos.CourseDTO;
import org.onlinequizapp.dtos.UserDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "CourseCreateController", urlPatterns = {"/CourseCreateController"})
public class CourseCreateController extends HttpServlet {

    private static final String SUCCESS = "courseAdd.jsp";
    private static final String LECTURE = "courseLectureAdd.jsp";
    private static final String SOURCE = "courseSourceAdd.jsp";
    private static final String CLASS = "courseClassAdd.jsp";
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
        String function = request.getParameter("function");
        HttpSession session = request.getSession();
        String LogID="";
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
        }
        if (function.equals("course")) {
            CourseDTO categoryDTO = new CourseDTO("", "", "", "", "","","");
            try {
                String courseName = request.getParameter("Name");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                String duration = request.getParameter("duration");
                String categoryID = request.getParameter("categoryID");
                if (status == null) {
                    status = "0";
                } else if (status.equals("on")) {
                    status = "1";
                } else {
                    status = "0";
                }
                boolean flag = true;
                if (courseName.length() > 250 || courseName.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Course Name must be [1-250]");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Description must be [1-250]");
                }
                if (duration.length() > 50 || duration.length() < 1) {
                    flag = false;
                    categoryDTO.setCourseName("Duration must be [1-50]");
                }
                if (flag) {
                    CourseDAO dao = new CourseDAO();
                    CourseDTO course = new CourseDTO("", courseName, LogID, duration, status, categoryID, description);
                    dao.insert(course);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", categoryDTO);
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
        } else if (function.equals("lecture")) {
            CategoryBlogDTO categoryBlogDTO = new CategoryBlogDTO("", "", "", "");
            try {
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                if (status == null) {
                    status = "0";
                } else if (status.equals("on")) {
                    status = "1";
                } else {
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
                    url = SUCCESS;

                } else {
                    request.setAttribute("ERROR", categoryBlogDTO);
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
