package Servlet;

import DTO.UserDTO;
import static DAO.UserDAO.checkLogin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User-PC
 */
public class LoginServlet extends HttpServlet {

    private static final String ERROR = "Login.html";
    private static final String SUCCESS = "success.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            UserDTO user = new UserDTO(userID, password);
            boolean check = checkLogin(userID, password);
            if (check) {
                url = SUCCESS;
            }
        } catch (Exception e) {

        } finally {
            response.sendRedirect(url);
        }
        return;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
