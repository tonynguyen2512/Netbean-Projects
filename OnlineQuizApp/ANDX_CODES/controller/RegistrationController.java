/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// reference regex pattern https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html
package org.OnlineQuizApp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.OnlineQuizApp.dao.UserDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegistrationController", urlPatterns = {"/RegistrationController"})
public class RegistrationController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.html";
    private static final String REGISTRATION_PAGE = "register.jsp";

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
        PrintWriter out = response.getWriter();
        String url = REGISTRATION_PAGE;
        try {
            String userID = request.getParameter("UserID");
            String password = request.getParameter("Password");
            String confirmPassword = request.getParameter("ConfirmPassword");
            String fullname = request.getParameter("Fullname");
            String phoneNumber = request.getParameter("Phone");
            int phone = Integer.parseInt(phoneNumber);
            String email = request.getParameter("Email");
            String address = request.getParameter("Address");
            boolean foundError = false;

            RegistrationError err_msg = new RegistrationError();
           //userID-------------------------------------------------------------
            if (userID.isEmpty() || userID.length() > 20) { 
                foundError = true;
                err_msg.setUserID_ERR_msg("The user ID must not be empty and under 20 characters.");
            }
            //password----------------------------------------------------------
            if (password.length() < 6 || password.length() > 20) {
                foundError = true;
                err_msg.setPasswordLength_ERR_msg("Password length must be between 6 and 20 characters");
            }
            if (!password.equals(confirmPassword)) {
                foundError = true;
                err_msg.setPassword_matches("Password must be match");
            }
            // ReGex to check if a string
            // contains uppercase, lowercase
            // numeric value
            Pattern regex_3_1 = Pattern.compile("[^a-zA-Z0-9]");//check if password doesn't match the pattern
            // Find match between given string
            Matcher matcher_3_1 = regex_3_1.matcher(password);
            // condition: password must have atleast 1 caps/ 1 non-caps/ 1 numberic
            if (matcher_3_1.matches()) {
                foundError = true;
                err_msg.setPassword3_1_ERR_msg("Password must have at least 1 capital letter, 1 lower case letter, 1 number");
            }
            //check if password contain any special character
            Pattern regex_spec_char = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");//check if password match the pattern 
            Matcher matcher_spec_char = regex_spec_char.matcher(password);
            if (matcher_spec_char.matches()) {
                foundError = true;
                err_msg.setPasswordSpecial_char_ERR_msg("Password must not contain any special character");
            }
            //fullName----------------------------------------------------------
            if (fullname.isEmpty() || fullname.length() > 50) {
                foundError = true;
                err_msg.setFullname_ERR_msg("Your full name can not be empty and over 50 characters");
            }
            //phoneNumber-------------------------------------------------------
            if (phoneNumber.length() < 7 || phoneNumber.length() > 15) {
                foundError = true;
                err_msg.setPhone_ERR_msg("Your phone number length must be between 7 and 15");
            }
            //email-------------------------------------------------------------
            Pattern regex_email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"); //check if email doesn't match the pattern
            Matcher matcher_email = regex_email.matcher(email);
            if(matcher_email.matches()){
                foundError = true ;
                err_msg.setEmail_ERR_msg("Invalid email");
            }
            //ERR_found---------------------------------------------------------
            if (foundError) {
                request.setAttribute("REGISTRATION_ERROR", err_msg);
            } else {
                UserDAO dao = new UserDAO();
                boolean result = dao.registerUser(userID, password, fullname, phone, email, address);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (NamingException ex) {
            log("Registration naming err: " + ex.getMessage());
        } catch (SQLException ex) {
            log("Registration sql err: " + ex.getMessage());
        } finally {
            out.close();
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
