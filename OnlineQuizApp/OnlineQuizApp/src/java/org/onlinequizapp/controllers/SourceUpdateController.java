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
import org.onlinequizapp.daos.LectureDAO;
import org.onlinequizapp.dtos.LectureDTO;
import org.onlinequizapp.daos.SourceDAO;
import org.onlinequizapp.dtos.SourceDTO;

@WebServlet(name = "SourceUpdateController", urlPatterns = {"/SourceUpdateController"})
public class SourceUpdateController extends HttpServlet {

    private static final String SUCCESS = "SourceSearchController";
    private static final String ERROR = "updateSource.jsp";

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
        String cate = request.getParameter("cate");
        String action = request.getParameter("action");
        LectureDAO dao1 = new LectureDAO();
        List<LectureDTO> list = null;
        try {
            list = dao1.getList("");
        } catch (SQLException e) {
            log("Error at CategorySearchController: " + e.toString());
        } finally {
            if (list != null) {
                request.setAttribute("LIST_LECTURE", list);
            }
        }
        if (action.equals("Confirm Update Source")) {
            try {
                String SourceID = request.getParameter("sourceID");
                String LectureID = request.getParameter("lectureID");
                String FileDoc = request.getParameter("fileDoc");
                String FilePic = request.getParameter("filePic");
                String FileVid = request.getParameter("fileVid");
                String Reference = request.getParameter("reference");
                String Status = request.getParameter("status");
                SourceDAO dao = new SourceDAO();
                SourceDTO question = new SourceDTO(SourceID, LectureID, FileDoc, FilePic, FileVid, Reference, Status);
                boolean flag = true;
                if (FileDoc.length() > 250 || FileDoc.length() < 1) {
                    flag = false;
                    question.setFileDoc("File must be [1-250]");
                }
                if (FilePic.length() > 250 || FilePic.length() < 1) {
                    flag = false;
                    question.setFilePic("File must be [1-250]");
                }
                if (FileVid.length() > 250 || FileVid.length() < 1) {
                    flag = false;
                    question.setFileVid("File must be [1-250]");
                }
                if (!(Status.equals("1")) && !(Status.equals("0"))) {
                    flag = false;
                    question.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    boolean update = dao.update(question);
                    if (update) {
                        request.setAttribute("UPDATE_SUCCESS", "Update Success!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", question);
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
