package org.onlinequizapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.onlinequizapp.daos.LectureDAO;
import org.onlinequizapp.dtos.LectureDTO;

@WebServlet(name = "LectureSearchController", urlPatterns = {"/LectureSearchController"})
public class LectureSearchController extends HttpServlet {

    private static final String SUCCESS = "lecture.jsp";
    private static final String LECTURE = "lectureAdd.jsp";
    private static final String ERROR = "404.html";

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
        String check = request.getParameter("check");
        String search = request.getParameter("search");
        if (check.equals("Search")) {
            try {
                LectureDAO dao = new LectureDAO();
                List<LectureDTO> list = dao.getList(search);
                if (list != null) {
                    request.setAttribute("LIST_LECTURE", list);
                    url = SUCCESS;
                }
            } catch (SQLException e) {
                log("Error at LectureSearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (check.equals("Course")) {
            try {
                String search2 = request.getParameter("search");
                LectureDAO dao = new LectureDAO();
                List<LectureDTO> list = dao.getList(search2);
                if (list != null) {
                    request.setAttribute("LIST_COURSE", list);
                    url = LECTURE;
                }
            } catch (SQLException e) {
                log("Error at ClassSearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else {
            request.setAttribute("LIST_CLASS_ERROR", "ERROR at ClassSearchController");
            url = ERROR;
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
