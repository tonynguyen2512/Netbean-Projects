package org.onlinequizapp.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.EmailDAO;
import org.onlinequizapp.daos.UserDAO;
import org.onlinequizapp.dtos.UserDTO;
import org.onlinequizapp.dtos.UserError;

@WebServlet(name = "ServiceController", urlPatterns = {"/ServiceController"})
public class ServiceController extends HttpServlet {

    private static final String SUCCESS = "verify.jsp";
    private static final String ERROR = "register.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError("", "", "", "", "", "", "", "");
        try {
            String userID = request.getParameter("userID");
            String roleID = "U";
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            //boolean agree = "on".equals(request.getParameter("agreement"));
            boolean flag = true;
            if (userID.length() > 20 || userID.length() < 1) {
                flag = false;
                userError.setUserIDError("UserID must be [1-5]");
            }
            if (fullName.length() > 250 || fullName.length() < 1) {
                flag = false;
                userError.setFullNameError("Full Name must be [1-250]");
            }
            if (roleID.length() > 2 || roleID.length() < 1 || (!roleID.equals("G") && !roleID.equals("M")&& !roleID.equals("U"))) {
                flag = false;
                userError.setRoleIDError("RoleID must be [1-2] and must be G - guest, U - Unvalidated Member or M - member");
            }
            if (!password.equals(confirm)) {
                flag = false;
                userError.setConfirmError("2 passwords are not matched!");
            }
            /*if (!agree) {
                flag = false;
                userError.setConfirmError("Please hava a look at our policies and tick the agreement box");
            }*/
            if (flag) {
                UserDAO dao = new UserDAO();
               
                EmailDAO sm = new EmailDAO();
                //get the 6-digit code
                String code = sm.getRandom();
                UserDTO user = new UserDTO(userID, fullName, roleID, password, phone, email, address, code);
                dao.insertNew(user);
                dao.updateCode(user, code);
                boolean test = sm.sendEmail(user, code);
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authcode", user);
                    session.setAttribute("Pass", "Create");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", userError);
                }

            } else {
                request.setAttribute("ERROR", userError);
            }
        } catch (Exception e) {

            log("Error at CreateController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("User Name duplicate!");
                request.setAttribute("ERROR", userError);
            };
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
