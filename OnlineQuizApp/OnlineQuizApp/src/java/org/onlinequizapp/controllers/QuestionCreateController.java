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
import org.onlinequizapp.daos.QuestionDAO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CourseDTO;
import org.onlinequizapp.dtos.QuestionDTO;
import org.onlinequizapp.dtos.UserDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "QuestionCreateController", urlPatterns = {"/QuestionCreateController"})
public class QuestionCreateController extends HttpServlet {

    private static final String SUCCESS = "questionAdd.jsp";
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
        String LogID = "";
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
        }
        if (function.equals("question")) {
            QuestionDTO categoryDTO = new QuestionDTO("", "", "", "", "","","","","","","");
            try {
                String Name = request.getParameter("Name");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                String categoryID = request.getParameter("categoryID");
                String answer = request.getParameter("answer");
                String answer1 = request.getParameter("answer1");
                String answer2 = request.getParameter("answer2");
                String answer3 = request.getParameter("answer3");
                String answer4 = request.getParameter("answer4");
                if (status == null) {
                    status = "0";
                } else if (status.equals("on")) {
                    status = "1";
                } else {
                    status = "0";
                }
                boolean flag = true;
                if (Name.length() > 250 || Name.length() < 1) {
                    flag = false;
                    categoryDTO.setName("Question must be [1-250]");
                }
                if (description.length() > 250 || description.length() < 1) {
                    flag = false;
                    categoryDTO.setName("Description must be [1-250]");
                }
                if ((answer1.length() > 250 || answer1.length() < 1)&&(answer2.length() > 250 || answer2.length() < 1)&&(answer3.length() > 250 || answer3.length() < 1)&&(answer4.length() > 250 || answer4.length() < 1)) {
                    flag = false;
                    categoryDTO.setName("Answer must be [1-250]");
                }
                if(!answer.equals("1")&&!answer.equals("2")&&!answer.equals("3")&&!answer.equals("4")){
                    flag = false;
                    categoryDTO.setName("Answer must be 1,2,3 or 4!");
                }
                if (flag) {
                    QuestionDAO dao = new QuestionDAO();
                    QuestionDTO course = new QuestionDTO("", Name, answer1, answer2, answer3, answer4, description, answer, LogID, status, categoryID );
                    dao.insertQ(course);
                    request.setAttribute("CREATE_SUCCESS", "Create Success!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("CREATE_ERROR", "Create Fail!");
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
