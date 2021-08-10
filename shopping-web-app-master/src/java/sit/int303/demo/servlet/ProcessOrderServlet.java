/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.Cart;
import sit.int303.demo.model.Customer;
import sit.int303.demo.model.OrderDetail;
import sit.int303.demo.model.Orders;
import sit.int303.demo.model.controller.OrderDetailJpaController;
import sit.int303.demo.model.controller.OrdersJpaController;
import sit.int303.demo.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author bas
 */
public class ProcessOrderServlet extends HttpServlet {

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
    Cart cart = (Cart) request.getSession(false).getAttribute("cart");
    Customer customer = (Customer) request.getSession(false).getAttribute("user");
    Date date = new Date();
    int orderNumber = AutoOrderNumberServlet.getNextOrderNumber();
    Orders order = new Orders(orderNumber, date, date, "In process");
    order.setCustomernumber(customer);
    
    OrdersJpaController orderJpaController = new OrdersJpaController(utx, emf);
    OrderDetailJpaController orderDetailJpaController = new OrderDetailJpaController(utx, emf);
    
    List<OrderDetail> orderDetailList = new ArrayList(cart.getOrders());
    
    try {
      orderJpaController.create(order); // Insert order to table
      order.setOrderDetailCollection(orderDetailList);

      short lineNumber = 1;
      for (OrderDetail orderDetail : orderDetailList) {
        orderDetail.setOrders(order);
        orderDetail.setOrderlinenumber(lineNumber++);
        orderDetailJpaController.create(orderDetail);
      }
      request.getSession().removeAttribute("cart");
      request.setAttribute("order", order);
      getServletContext().getRequestDispatcher("/WEB-INF/jsp/OrderSummary.jsp").forward(request, response);
      return;
      // will change target to order summary page
    } catch (RollbackFailureException ex) {
      Logger.getLogger(ProcessOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
      request.setAttribute("message", ex.getMessage());
    } catch (Exception ex) {
      Logger.getLogger(ProcessOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
      request.setAttribute("message", ex.getMessage());
    }
    String location = "https://stackoverflow.com/search?q=JPA " + request.getAttribute("message");
//    String location = "https://www.google.co.th/search?q=JPA " + request.getAttribute("message");
    response.sendRedirect(location);
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
