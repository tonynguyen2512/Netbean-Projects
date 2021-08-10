/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.TRUE;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.ClassDAO;
import org.onlinequizapp.dtos.ClassDTO;

@WebServlet(name = "ClassCreateController", urlPatterns = {"/ClassCreateController"})
public class ClassCreateController extends HttpServlet {

    private static final String SUCCESS = "classAdd.jsp";
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String check = request.getParameter("check");
        
        if (check.equals("lop")) {
            ClassDTO classDTO = new ClassDTO("", "", "");
            try {
                String NumberOfStudent = request.getParameter("numberOfStudent");
                String status = request.getParameter("status");
                if(status==null){
                        status="0";
                    }
                else if (status.equals("on")) {
                    status = "1";
                } else{
                    status = "0";
                }
                boolean flag = true;
                if (NumberOfStudent.length() > 250 || NumberOfStudent.length() < 1) {
                    flag = false;
                    classDTO.setNumberOfStudent("NumberOfStudent must be [1-250]");
                }
                if (flag) {
                    ClassDAO dao = new ClassDAO();
                    
                    ClassDTO cate = new ClassDTO("", NumberOfStudent, status);
                    dao.insert(cate);
                    request.setAttribute("CREATE_SUCCESS", "Create Success!");
                    url = SUCCESS;

                } else {
                    request.setAttribute("CREATE_ERROR", "Create Fail!");
                    url = SUCCESS;
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (e.toString().contains("duplicate")) {
                    classDTO.setClassID("Class Name duplicate!");
                    request.setAttribute("ERROR", classDTO);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClassCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClassCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
