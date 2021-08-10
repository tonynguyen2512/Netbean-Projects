/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.OnlineQuizApp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.OnlineQuizApp.dao.UserDAO;
import org.apache.catalina.Session;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private final static String ERROR_PAGE = "404.html";
    private final static String INDEX_PAGE = "index.html";

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
        
        //default url is error page
        String url = ERROR_PAGE;
        try {
            //get user login info
            String email = request.getParameter("Email");
            String password = request.getParameter("Password");
            
            //check if user want to remember their login info
            boolean rememberLogin = "true".equals(request.getParameter("rememberMe"));
            
            UserDAO dao = new UserDAO();
            boolean result = dao.checkLogin(email, password); //check if user valid in database
            String userName = dao.getUserInfo().getFullname();//get user info if user is in database
            
            if(rememberLogin){
                //add cookie to user device if user choose to remember their login info
                Cookie cookieEmail = new Cookie("EMAIL", email);
                cookieEmail.setMaxAge(0);
                
                Cookie cookiePassWord = new Cookie("PASSWORD", password);
                cookiePassWord.setMaxAge(0);
                
                response.addCookie(cookieEmail);
                response.addCookie(cookiePassWord);
            }
            
            if (result) {
                //if user is valid switch url to index page
                url = INDEX_PAGE;
                //save user name to session to welcome user 
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", userName);
            }
            
        } catch (SQLException ex) {
            log("Sql error : " + ex.getMessage());
        } catch (NamingException ex) {
            log("Login naming error: " + ex.getMessage());
        } finally {
            //forward because we need to store the parameters
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
