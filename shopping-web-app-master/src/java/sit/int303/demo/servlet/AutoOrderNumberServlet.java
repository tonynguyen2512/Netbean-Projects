/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sit.int303.demo.model.Orders;

/**
 *
 * @author bas
 */
public class AutoOrderNumberServlet extends HttpServlet {
  private static int LAST_ORDER_NUMBER;
  @PersistenceUnit(unitName = "DemoWebAppG2PU")
  EntityManagerFactory emf;

  @Override
  public void init() throws ServletException {
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("select o from Orders o order by o.ordernumber desc");
    query.setMaxResults(1);
    List<Orders> orderList = query.getResultList();
    if (orderList.size() > 0) {
      LAST_ORDER_NUMBER = orderList.get(0).getOrdernumber();
    } else {
      throw new ServletException("Cannot connect to database !!!");
    }
  }

  public static int getNextOrderNumber() {
    return ++LAST_ORDER_NUMBER;
  }
}
