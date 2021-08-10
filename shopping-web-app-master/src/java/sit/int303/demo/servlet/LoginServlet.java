/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.Customer;
import sit.int303.demo.model.controller.CustomerJpaController;

/**
 *
 * @author bas
 */
public class LoginServlet extends HttpServlet {
  @PersistenceUnit(unitName = "DemoWebAppG2PU")
  EntityManagerFactory emf;
  
  @Resource
  UserTransaction utx;
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
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");

    if (userName != null && password != null) {
      CustomerJpaController customerJpaCtrl = new CustomerJpaController(utx, emf);
      Customer c = null;
      try {
        c = customerJpaCtrl.findCustomer(Integer.valueOf(userName));
      } catch (NumberFormatException e) {
        /*
         * Ignore error
         */
      }
      if (c == null) { // customer id does not exist
        request.setAttribute("message", "Invalid Username / password");
      } else if (c.getContactfirstname().trim().equals(password)){
        request.getSession().setAttribute("user", c);
        String targetUrl = request.getParameter("targetUrl");
        if (targetUrl == null || targetUrl.equals("")) {
          targetUrl = "/index.jsp";
        }
        getServletContext().getRequestDispatcher(targetUrl).forward(request, response);
        return;
      } else {
        request.setAttribute("message", "Invalid Username / password");
      }
    }
    
    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
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
