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
import org.onlinequizapp.daos.CategoryDAO;
import org.onlinequizapp.daos.QuestionDAO;
import org.onlinequizapp.dtos.CategoryDTO;
import org.onlinequizapp.dtos.QuestionDTO;

@WebServlet(name = "QuestionUpdateController", urlPatterns = {"/QuestionUpdateController"})
public class QuestionUpdateController extends HttpServlet {

    private static final String SUCCESS = "QuestionSearchController";
    private static final String ERROR = "updateQuestion.jsp";

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
        CategoryDAO dao1 = new CategoryDAO();
        List<CategoryDTO> list = null;
        try {
            list = dao1.getListQ("");
        } catch (SQLException e) {
            log("Error at CategorySearchController: " + e.toString());
        } finally {
            if (list != null) {
                request.setAttribute("LIST_QUIZ_CATEGORY", list);
            }
        }
        if (action.equals("Confirm Update Question")) {
            try {
                String questionID = request.getParameter("questionID");
                String name = request.getParameter("name");
                String status = request.getParameter("status");
                String description = request.getParameter("description");
                String categoryID = request.getParameter("categoryID");
                String answer = request.getParameter("answer");
                String answer1 = request.getParameter("answer1");
                String answer2 = request.getParameter("answer2");
                String answer3 = request.getParameter("answer3");
                String answer4 = request.getParameter("answer4");
                String authorID = request.getParameter("authorID");
                QuestionDAO dao = new QuestionDAO();
                QuestionDTO question = new QuestionDTO(questionID, name, answer1, answer2, answer3, answer4, description, answer, authorID, status, categoryID);
                boolean flag = true;
                if (name.length() > 250 || name.length() < 1) {
                    flag = false;
                    question.setName("Number Of Student must be [1-250]");
                }
                if (answer1.length() < 1) {
                    flag = false;
                    question.setName("Answer's Data must be more than 0 character");
                }
                if (answer2.length() < 1) {
                    flag = false;
                    question.setName("Answer's Data must be more than 0 character");
                }
                if (answer3.length() < 1) {
                    flag = false;
                    question.setName("Answer's Data must be more than 0 character");
                }
                if (answer4.length() < 1) {
                    flag = false;
                    question.setName("Answer's Data must be more than 0 character");
                }
                if (!(answer.equals("1")) && !(answer.equals("2")) && !(answer.equals("3")) && !(answer.equals("4"))) {
                    flag = false;
                    question.setName("Correcct Answer must be from 1 to 4");
                }
                if (!(status.equals("1")) && !(status.equals("0"))) {
                    flag = false;
                    question.setStatus("Status must be 0 or 1");
                }
                if (flag) {
                    boolean update = dao.updateQ(question);
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
