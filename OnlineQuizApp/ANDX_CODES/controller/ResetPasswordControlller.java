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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.OnlineQuizApp.dao.UserDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordControlller", urlPatterns = {"/ResetPasswordControlller"})
public class ResetPasswordControlller extends HttpServlet {

    private static String LOGIN_PAGE = "login.html";
    private static String HOME_PAGE = "index.html";
    private static String RESET_PAGE = "reset.html";
            /**
             * Processes requests for both HTTP <code>GET</code> and
             * <code>POST</code> methods.
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
        //default page url is reset page
        String url = RESET_PAGE;
        //check if user chose remember me
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));
        try {
            UserDAO dao = new UserDAO();
            String email = request.getParameter("txtEmail");
            String currentPassWord = request.getParameter("txtPassword");
            String resetPassWord = request.getParameter("txtResetPass");
            
            //check if reset password is different from current password
            if(!currentPassWord.equals(resetPassWord)|| resetPassWord.length()<6 || resetPassWord.length()>20){
                //set an attribute for reset page know whether (an) error(s) was(were) found or not
                request.setAttribute("ERROR_FOUND", false); 
                dao.resetPassword(email, resetPassWord);
                url = HOME_PAGE;
            }else{
                request.setAttribute("ERROR_FOUND", true);
            }
            
            //if user chose remember me then we will change the cookie that hold user password with a new password
            if(rememberMe){
               Cookie[] cookies = request.getCookies();
               for(Cookie aCookie : cookies){
                   String cookiePass = aCookie.getName();
                   if(cookiePass.equals("PASSWORD")){
                      Cookie cookie = new Cookie("PASSWORD", resetPassWord);
                      response.addCookie(cookie);
                   }
               }
            }
        } catch (NamingException ex) {
            log("reset password naming err: " + ex.getMessage());
        } catch (SQLException ex) {
            log("reset password sql err: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
