/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.daos.UserDAO;
import samples.dtos.UserDTO;
import samples.dtos.UserError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "createController", urlPatterns = {"/createController"})
public class createController extends HttpServlet {

    private final static String ERROR = "createUser.jsp";
    private final static String SUCCESS = "Login.html";

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
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            boolean flag = true;
            UserError error = new UserError("", "", "", "", "");
            if (userID.length() > 5 || userID.length() < 2) {
                flag = false;
                error.setUserIDError("UserID must be [2-5]");
            }
            if (fullName.length() > 10 || fullName.length() < 2) {
                flag = false;
                error.setFullNameError("fullName must be [2-10]");
            }
            if (roleID.length() > 5 || roleID.length() < 1) {
                flag = false;
                error.setRoleIDError("roleID must be [2-5]");
            }
            if (!password.equals(confirm)) {
                flag = false;
                error.setConfirmError("password ko gioongs  confirm");
            }
            if (flag) {
                UserDAO dao = new UserDAO();
                boolean check = dao.checkDuplicate(userID);
                if (check) {
                    error.setUserIDError("UserID is duplicate");
                    request.setAttribute("ERROR", error);
                } else {
                    UserDTO user = new UserDTO(userID, fullName, roleID, password);
                    dao.insert(user);
                    
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
            log("ERROR at createController " + e.toString());
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
