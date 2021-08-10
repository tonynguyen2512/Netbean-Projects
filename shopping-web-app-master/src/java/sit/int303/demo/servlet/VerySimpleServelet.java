/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bas
 */
public class VerySimpleServelet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String xStr, yStr;
            int xValue, yValue;
            String yourBrowser = request.getHeader("User-Agent");
            
            xStr = request.getParameter("x");
            yStr = request.getParameter("y");
            String operatorStr = request.getParameter("operator");
            try{
              xValue = Integer.valueOf(xStr);
              yValue = Integer.valueOf(yStr);  
            } catch(Exception err){
                /* ignored error */
                xValue = 0;
                yValue = 0;
            }
            int result =  0;
            switch(operatorStr) {
                case " ": 
                    result = xValue + yValue;
                    break;
                case "-":
                    result = xValue - yValue;
                    break;
                case "*":
                    result = xValue * yValue;
                    break;
                case "/":
                    result = xValue / yValue;
                    break;
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerySimpleServelet</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet VerySimpleServelet at " + request.getContextPath() + "</h1>");
            out.println("<h1 style='color: red;'>Very Simple Servelet (Addr) </h1><hr>");
            out.println("<h1>your browser is " + yourBrowser + "</h1>");
            out.println("<h3>&nbsp; &nbsp; &nbsp; x = " + xValue + "</h3>");
            out.println("<h3>&nbsp; &nbsp; &nbsp; y = " + yValue + "</h3>");
            out.println("<hr><h3>&nbsp; &nbsp; &nbsp; x + y = " + (result) + " </h3>");
            out.println("</body>");
            out.println("</html>");
            
//            response.sendRedirect("/");
            
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
