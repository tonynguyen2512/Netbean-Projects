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
import org.onlinequizapp.daos.ClassDAO;
import org.onlinequizapp.daos.CourseDAO;
import org.onlinequizapp.daos.LectureDAO;
import org.onlinequizapp.dtos.ClassDTO;
import org.onlinequizapp.dtos.CourseDTO;
import org.onlinequizapp.dtos.LectureDTO;

@WebServlet(name = "LectureUpdateController", urlPatterns = {"/LectureUpdateController"})
public class LectureUpdateController extends HttpServlet {

    private static final String SUCCESS = "LectureSearchController";
    private static final String ERROR = "updateLecture.jsp";

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
        String action = request.getParameter("action");
        List<CourseDTO> list = null;
        List<ClassDTO> list1 = null;
        try {
            CourseDAO dao1 = new CourseDAO();
            list = dao1.getListCourse("");
        } catch (SQLException e) {
            log("Error at CategorySearchController: " + e.toString());
        } finally {
            if (list != null) {
                request.setAttribute("LIST_COURSE", list);
            }
        }
        try {
            ClassDAO dao = new ClassDAO();
            list1 = dao.getList("");

        } catch (SQLException e) {
            log("Error at ClassSearchController: " + e.toString());
        } finally {
            if (list1 != null) {
                request.setAttribute("LIST_CLASS", list1);
            }
        }
        if (action.equals("Confirm Update Lecture")) {
            try {
                String lectureID = request.getParameter("lectureID");
                String LectureName = request.getParameter("lectureName");
                String status = request.getParameter("status");
                String Description = request.getParameter("description");
                String CourseID = request.getParameter("courseID");
                String ClassID = request.getParameter("classID");
                LectureDAO dao = new LectureDAO();
                LectureDTO category = new LectureDTO(lectureID, CourseID, LectureName, ClassID, Description, status);
                boolean flag = true;
                if (LectureName.length() > 250 || LectureName.length() < 1) {
                    flag = false;
                    category.setLectureName("Number Of Student must be [1-250]");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    category.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    boolean update = dao.update(category);
                    if (update) {
                        request.setAttribute("UPDATE_SUCCESS", "Update Success!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", category);
                }
            } catch (Exception e) {

            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else if (action.equals("Update")) {
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
