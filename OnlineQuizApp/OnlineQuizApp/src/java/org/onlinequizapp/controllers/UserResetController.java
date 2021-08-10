/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.EmailDAO;
import org.onlinequizapp.daos.UserDAO;
import org.onlinequizapp.dtos.UserDTO;
import org.onlinequizapp.dtos.UserError;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "UserResetController", urlPatterns = {"/UserResetController"})
public class UserResetController extends HttpServlet {

    private static final String SUCCESS = "resetPass.jsp";
    private static final String ERROR = "forgot-password.html";

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
        UserError userError = new UserError("", "", "", "", "", "", "", "");
        try {
            String email = request.getParameter("email");
            String userID = request.getParameter("username");

            UserDAO dao = new UserDAO();
            UserDTO user = null;
            EmailDAO sm = new EmailDAO();
            //get the 6-digit code
            String code = sm.getRandom();
            List<UserDTO> userList = dao.getList("");
            for (UserDTO x : userList) {
                if (x.getUserID().equalsIgnoreCase(userID) && x.getEmail().equalsIgnoreCase(email)) {
                    user = x;
                }
            }
            if (user != null) {
                dao.updateCode(user, code);
                user = new UserDTO(user.getUserID(),user.getFullname(),user.getRole(),user.getPassword(),user.getPhone(),user.getEmail(),user.getAddress(),code);
                boolean test = sm.sendEmail(user, code);
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", user);
                    session.setAttribute("Pass", "Reset");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", userError);
                }
            } else {
                request.setAttribute("ERROR", "Cannot find user or user does not exist!");
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            request.setAttribute("ERROR", e);

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
