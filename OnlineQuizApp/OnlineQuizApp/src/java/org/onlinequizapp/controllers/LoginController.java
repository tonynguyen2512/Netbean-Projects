package org.onlinequizapp.controllers;

import com.google.common.hash.Hashing;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.UserDAO;
import org.onlinequizapp.dtos.UserDTO;

public class LoginController extends HttpServlet {

    private static final String SUCCESS = "dashboardadmin.jsp";
    private static final String Student = "dashboardstudent.jsp";
    private static final String Teacher = "dashboardteacher.jsp";
    private static final String ERROR = "login.html";
    private static final String SHOPPING = "index.html";

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
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                if (user.getRole().contains("AD")) {
                    url = SUCCESS;
                } else if (user.getRole().contains("T") || user.getRole().contains("T1")) {
                    url = Teacher;
                } else if (user.getRole().contains("S") || user.getRole().contains("S1")) {
                    url = Student;
                } else {
                    url = SHOPPING;
                }
            } else {
                UserDAO dao = new UserDAO();
                user=dao.checkLogin(userID, sha256hex);
                if (user == null) {
                    user = dao.checkMailLogin(userID, sha256hex);
                }
                if (user != null) {
                    session.setAttribute("LOGIN_USER", user);
                    if (user.getRole().contains("AD")) {
                        url = SUCCESS;
                    } else if (user.getRole().contains("T") || user.getRole().contains("T1")) {
                        url = Teacher;
                    } else if (user.getRole().contains("S") || user.getRole().contains("S1")) {
                        url = Student;
                    } else {
                        url = SHOPPING;
                    }
                }
            }
            String rememberLogin = request.getParameter("rememberMe");
            if ("on".equals(rememberLogin)) {
                //add cookie to user device if user choose to remember their login info
                Cookie cookieID = new Cookie("USERID", userID);
                cookieID.setMaxAge(0);

                Cookie cookiePassWord = new Cookie("PASSWORD", sha256hex);
                cookiePassWord.setMaxAge(0);

                response.addCookie(cookieID);
                response.addCookie(cookiePassWord);
            }
        } catch (Exception e) {
            log("Error at LoginController:" + e.toString());
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
