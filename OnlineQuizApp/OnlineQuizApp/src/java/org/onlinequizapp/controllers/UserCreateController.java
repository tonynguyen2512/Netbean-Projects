/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onlinequizapp.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class UserCreateController extends HttpServlet {

    private static final String SUCCESS = "verify.jsp";
    private static final String ERROR = "register.jsp";

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
        UserError userError = new UserError("", "", "", "", "", "", "", "");
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            boolean flag = true;
            if (request.getParameter("agreement") == null) {
                flag = false;
                userError.setConfirmError("Please hava a look at our policies and tick the agreement box");
            }
            if (password.length() < 6 || password.length() > 20) {
                flag = false;
                userError.setPasswordError("Password length must be between 6 and 20 characters");
            }
            // ReGex to check if a string
            // contains uppercase, lowercase
            // numeric value
            Pattern regex_3_1 = Pattern.compile("[^a-zA-Z0-9]");//check if password doesn't match the pattern
            // Find match between given string
            Matcher matcher_3_1 = regex_3_1.matcher(password);
            // condition: password must have atleast 1 caps/ 1 non-caps/ 1 numberic
            if (matcher_3_1.matches()) {
                flag = false;
                userError.setPasswordError("Password must have at least 1 capital letter, 1 lower case letter, 1 number");
            }
            //check if password contain any special character
            Pattern regex_spec_char = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");//check if password match the pattern 
            Matcher matcher_spec_char = regex_spec_char.matcher(password);
            if (matcher_spec_char.matches()) {
                flag = false;
                userError.setPasswordError("Password must not contain any special character");
            }
            if (!password.equals(confirm)) {
                flag = false;
                userError.setConfirmError("2 passwords are not matched!");
            }
            if (flag) {
                UserDAO dao = new UserDAO();
                EmailDAO sm = new EmailDAO();
                //get the 6-digit code
                String code = sm.getRandom();
                String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
                if (dao.checkEmail(email)) {
                    userError.setEmailError("Email has been used!");
                } 
                else {
                    UserDTO user = new UserDTO(code, email, "U", sha256hex, email, code);
                    dao.insertNew2(user);
                    boolean test = sm.sendEmail(user, code);
                    if (test) {
                        HttpSession session = request.getSession();
                        session.setAttribute("authcode", user);
                        session.setAttribute("Pass", "Create");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", userError);
                    }
                }

            } else {
                request.setAttribute("ERROR", userError);
            }
        } catch (Exception e) {

            log("Error at CreateController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("User Name duplicate!");
                request.setAttribute("ERROR", userError);
            };
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserCreateController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserCreateController.class.getName()).log(Level.SEVERE, null, ex);
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
