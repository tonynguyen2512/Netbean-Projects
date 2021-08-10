package org.onlinequizapp.controllers;

import org.onlinequizapp.daos.UserDAO;
import org.onlinequizapp.dtos.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.UserDAO;

import org.onlinequizapp.dtos.GooglePojoDTO;
import org.onlinequizapp.utils.GoogleUtils;
//@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/login-google"})

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "dashboardadmin.jsp";
    private static final String STUDENT = "dashboardstudent.jsp";
    private static final String TEACHER = "dashboardteacher.jsp";

    public LoginGoogleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String url=STUDENT;
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojoDTO googlePojo = GoogleUtils.getUserInfo(accessToken);
            request.setAttribute("id", googlePojo.getId());
            request.setAttribute("name", googlePojo.getName());
            request.setAttribute("email", googlePojo.getEmail());
            String userID = googlePojo.getId();
            String fullName = googlePojo.getEmail();
            String roleID = "S";
            String password = "***";
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            UserDTO user = null;
            try {
                if (dao.checkEmail(fullName)) {
                    user = dao.getListEmail(fullName);
                    if(user.getRole().equals("T")||user.getRole().equals("T1")){
                        url=TEACHER;
                    }else if(user.getRole().equals("U")){
                        dao.updateEnable(user);
                    }else if(user.getRole().equals("AD")){
                        url=SUCCESS;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user == null) {
                user = new UserDTO(userID, fullName, roleID, password, fullName);
                try {
                    if (!dao.checkDuplicate(userID)) {
                        try {
                            dao.insertNew1(user);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (user != null) {
                session.setAttribute("LOGIN_USER", user);
            }
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
