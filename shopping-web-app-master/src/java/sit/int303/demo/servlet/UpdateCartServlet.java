/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sit.int303.demo.model.Cart;
import sit.int303.demo.model.OrderDetailPK;

/**
 *
 * @author bas
 */
public class UpdateCartServlet extends HttpServlet {

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
    HttpSession session = request.getSession(false);
    if(session != null) {
      Cart cart = (Cart) session.getAttribute("cart");
      if (cart == null) {
        response.sendError(500, "Unknow error .. your cart is missing !!!");
        return;
      } else {
        String[] selectedItem = request.getParameterValues("deleteItems");
        if (selectedItem != null) {
          for (String productCode : selectedItem) {
            System.out.println(productCode);
            OrderDetailPK odpk = new OrderDetailPK(1, productCode);
            cart.remove(odpk);
          }
        }
        
        Enumeration<String> productCodes = request.getParameterNames();
        while(productCodes.hasMoreElements()) {
          String code = productCodes.nextElement();
          if(code.equalsIgnoreCase("deleteItems")){
            continue;
          }
          int value = Integer.valueOf(request.getParameter(code));
          OrderDetailPK odpk = new OrderDetailPK(1, code); // สร้าง orderDetail เป็น 1
          if(cart.getItem(odpk) != null) {
            if(value == 0) {
              cart.remove(odpk);
            } else {
              cart.getItem(odpk).setQuantityordered(value);
            }
          }
//          System.out.printf("code: %-10s value: %s\n", code, request.getParameter(code));
        }
        getServletContext().getRequestDispatcher("/ViewCart").forward(request, response);
        return;
      }
    }else {
      response.sendError(500, "Http Session expired ... Please add new product to your cart again");
      return;
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
