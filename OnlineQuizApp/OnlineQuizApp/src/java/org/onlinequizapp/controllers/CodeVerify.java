/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
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
public class CodeVerify extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String ERROR = "verify.jsp";
    private static final String ERROR1 = "resetPass.jsp";

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
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("authcode");
            String code = request.getParameter("authcode");
            String Pass = (String) session.getAttribute("Pass");
            String Password = request.getParameter("Password");
            String Confirm = request.getParameter("Confirm");
            Pattern regex_3_1 = Pattern.compile("[^a-zA-Z0-9]");//check if password doesn't match the pattern
            // Find match between given string
            Matcher matcher_3_1 = regex_3_1.matcher(Password);
            // condition: password must have atleast 1 caps/ 1 non-caps/ 1 numberic
            if (matcher_3_1.matches()) {
                url = ERROR;
                request.setAttribute("ERROR", "Password must have at least 1 capital letter, 1 lower case letter, 1 number");
            }
            //check if password contain any special character
            Pattern regex_spec_char = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");//check if password match the pattern 
            Matcher matcher_spec_char = regex_spec_char.matcher(Password);

            if (matcher_spec_char.matches()) {
                url = ERROR;
                request.setAttribute("ERROR", "Password must not contain any special character");
            }
            if (code.equals(user.getVerification())) {
                if (Pass.equalsIgnoreCase("Create")) {
                    UserDAO userdao = new UserDAO();
                    userdao.updateEnable(user);
                    if (userdao.updateEnable(user)) {
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "The Verification has met an exception, please try again or contact support team!");
                    }
                } else if (Pass.equalsIgnoreCase("Reset")) {
                    if (Password.equals(Confirm)) {
                        String sha256hex = Hashing.sha256().hashString(Password, StandardCharsets.UTF_8).toString();
                        UserDAO userdao = new UserDAO();
                        userdao.updatePass(user, sha256hex);
                        if (userdao.updatePass(user, sha256hex)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR1;
                            request.setAttribute("ERROR", "The Verification has met an exception, please try again or contact support team!");
                        }
                    }
                }
            } else {
                request.setAttribute("ERROR", "The verification code is either wrong, expired or used. Please check your verification code again!");
            }
        } catch (Exception e) {
            log("Error at CodeVerifyController: " + e.toString());
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
