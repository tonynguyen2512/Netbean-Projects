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
import org.onlinequizapp.daos.UserDAO;
import org.onlinequizapp.dtos.UserDTO;
import org.onlinequizapp.dtos.UserError;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UserUpdateController extends HttpServlet {

    private static final String SUCCESS = "SearchController";
    private static final String ERROR = "updateUser.jsp";
    private static final String PROFILE = "user-profile.jsp";

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
        String url = PROFILE;
        UserError userError = new UserError("", "", "", "", "", "", "", "");
        String function = request.getParameter("check");
        try {
            if (function.equals("admin")) {
                String userID = request.getParameter("userID");
                String fullName = request.getParameter("fullName");
                String roleID = request.getParameter("roleID");
                String email = request.getParameter("Email");
                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userID, fullName, roleID, "", email);
                boolean flag = true;
                if (fullName.length() > 250 || fullName.length() < 1) {
                    flag = false;
                    userError.setFullNameError("Full Name must be [1-250]");
                }
                if (roleID.length() > 2 || roleID.length() < 1 || (!roleID.equals("G") && !roleID.equals("M") && !roleID.equals("AD") && !roleID.equals("T") && !roleID.equals("T1") && !roleID.equals("S") && !roleID.equals("S1") && !roleID.equals("U"))) {
                    flag = false;
                    userError.setRoleIDError("RoleID must be [1-2] and must be G - guest, M - member or AD - admin");
                }
                if (flag) {
                    boolean check = dao.update(user);
                    if (check) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", userError);
                }
            } else if (function.equals("profile")) {
                HttpSession session=request.getSession();
                String userID = request.getParameter("Username");
                String fullName = request.getParameter("Name");
                String phone = request.getParameter("Phone");
                String address = request.getParameter("Address");
                UserDTO user = (UserDTO)session.getAttribute("LOGIN_USER");
                UserDAO dao = new UserDAO();
                boolean flag = true;
                if (fullName.length() > 250 || fullName.length() < 1) {
                    flag = false;
                    userError.setFullNameError("Full Name must be [1-250]");
                }
                if (flag) {
                    user.setAddress(address);
                    user.setFullname(fullName);
                    user.setPhone(phone);
                    user.setUserID(userID);
                    boolean check = dao.update(user);
                    if (check) {
                        url = PROFILE;
                        request.setAttribute("SUCCESS", "Update Sucessfully!");
                    }
                } else {
                    request.setAttribute("ERROR", userError);
                }
            } else {
                url = ERROR;
            }
        } catch (Exception e) {

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
