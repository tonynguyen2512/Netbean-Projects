/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thiennh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thiennh.dao.UserDAO;
import thiennh.dto.UserDTO;

/**
 *
 * @author Arslan
 */
public class SearchServlet extends HttpServlet {
    private static final String SUCCESS="SearchResultServlet";
    private static final String ERROR="success.html";
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
        String url=ERROR;
        try{
            String search=request.getParameter("search");
            if(!search.isEmpty()){
                UserDAO dao = new UserDAO();
                List<UserDTO> list= dao.getListUser(search);
                if(!list.isEmpty()){
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>ListUser</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>SearchResult " + request.getContextPath() + "</h1>");
                    out.println("<table border=1>");
                    
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>UserID</th>");
                    out.println("<th>FullName</th>");
                    out.println("<th>Role</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    
                    int count=0;
                    for(UserDTO dto:list){
                        out.println("<tr>");
                        out.println("<td>"+ ++count +"</td>");
                        out.println("<td>"+ dto.getUserID() +"</td>");
                        out.println("<td>"+ dto.getFullName() +"</td>");
                        out.println("<td>"+ dto.getRoleID() +"</td>");
                        out.println("<td>"+ dto.getPassword() +"</td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</tbody");
                    out.println("</table>");                 
                    out.println("</body>");
                    out.println("</html>");                                       
                }
                else{
                    response.sendRedirect(url);
                }
            }else{
                response.sendRedirect(url);
            }
            
        }catch(Exception e){

        }finally{
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
