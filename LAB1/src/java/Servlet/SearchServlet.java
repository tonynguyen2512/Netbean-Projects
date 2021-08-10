package Servlet;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User-PC
 */
public class SearchServlet extends HttpServlet {

    private static final String SUCCESS = "SearchResultServlet";
    private static final String ERROR = "/success.html";

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
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            if (!search.isEmpty()) {
                UserDAO dao = new UserDAO();
                List<UserDTO> list = dao.getListUser(search);
                if (!list.isEmpty()) {
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet Test</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet Test at " + request.getContextPath() + "</h1>");
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>UserID</th>");
                    out.println("<th>FullName</th>");
                    out.println("<th>RoleID</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    int count =0;
                    for(UserDTO dto:list){
                    out.println("<tr>");
                    out.println("<td>"+ ++ count + "</td>");
                    out.println("<td>"+ dto.getUserID() + "</td>");
                    out.println("<td>"+ dto.getFullName() + "</td>");
                    out.println("<td>"+ dto.getRoleID() + "</td>");
                    out.println("<td>"+ dto.getPassword() + "</td>");
                    out.println("</tr>");}
                    
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

        } catch (Exception e) {

        } finally {
            response.sendRedirect(url);
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
