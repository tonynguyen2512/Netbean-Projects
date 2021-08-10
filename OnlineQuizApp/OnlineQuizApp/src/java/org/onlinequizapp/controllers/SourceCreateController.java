/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.daos.LectureDAO;
import org.onlinequizapp.daos.SourceDAO;
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CourseDTO;
import org.onlinequizapp.dtos.LectureDTO;
import org.onlinequizapp.dtos.SourceDTO;
import org.onlinequizapp.dtos.UserDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "SourceCreateController", urlPatterns = {"/SourceCreateController"})
public class SourceCreateController extends HttpServlet {

    private static final String SUCCESS = "sourceAdd.jsp";
    private static final String ERROR = "error.jsp";

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
        String function = request.getParameter("function");
        List<LectureDTO> list1 = null;
        try {
            LectureDAO dao = new LectureDAO();
            list1 = dao.getList("");

        } catch (SQLException e) {
            log("Error at ClassSearchController: " + e.toString());
        } finally {
            if (list1 != null) {
                request.setAttribute("LIST_LECTURE", list1);
            }
        }
        if (function.equals("source")) {
            SourceDTO source = new SourceDTO("", "", "", "", "", "", "");
            try {
                String LectureID = request.getParameter("lectureID");
                String FileDoc = request.getParameter("fileDoc");
                String FilePic = request.getParameter("filePic");
                String FileVid = request.getParameter("fileVid");
                String Reference = request.getParameter("reference");
                String Status = request.getParameter("status");
                if (Status == null) {
                    Status = "0";
                } else if (Status.equals("on")) {
                    Status = "1";
                } else {
                    Status = "0";
                }
                boolean flag = true;
                if (FileDoc.length() > 250 || FileDoc.length() < 1) {
                    flag = false;
                    source.setFileDoc("File must be [1-250]");
                }
                if (FilePic.length() > 250 || FilePic.length() < 1) {
                    flag = false;
                    source.setFilePic("File must be [1-250]");
                }
                if (FileVid.length() > 250 || FileVid.length() < 1) {
                    flag = false;
                    source.setFileVid("File must be [1-250]");
                }
                if (!(Status.equals("1")) && !(Status.equals("0"))) {
                    flag = false;
                    source.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    SourceDAO dao = new SourceDAO();
                    SourceDTO course = new SourceDTO("", LectureID, FileDoc, FilePic, FileVid, Reference, Status);
                    dao.insert(course);
                    request.setAttribute("CREATE_SUCCESS", "Create Success!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("CREATE_ERROR", "Create Fail!");
                    url = SUCCESS;
                }
            } catch (Exception e) {
                log("Error at CreateController: " + e.toString());
                if (e.toString().contains("duplicate")) {
                    source.setSourceID("Source duplicate!");
                    request.setAttribute("ERROR", source);
                };
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
