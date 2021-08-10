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
import org.onlinequizapp.daos.ClassDAO;
import org.onlinequizapp.daos.QuestionDAO;
import org.onlinequizapp.daos.QuizDAO;
import org.onlinequizapp.daos.ScoreDAO;
import org.onlinequizapp.dtos.ClassDTO;
import org.onlinequizapp.dtos.QuestionDTO;
import org.onlinequizapp.dtos.QuizDTO;
import org.onlinequizapp.dtos.ScoreDTO;
import org.onlinequizapp.dtos.UserDTO;

/**
 *
 * @author User-PC
 */
@WebServlet(name = "ScoreCreateController", urlPatterns = {"/ScoreCreateController"})
public class ScoreCreateController extends HttpServlet {

    private static final String SUCCESS = "quizPlay.jsp";
    private static final String SUCCESS1 = "quizDetailAdd.jsp";
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
        List<QuestionDTO> list1 = null;
        List<QuizDTO> list2 = null;
        List<String> param = null;
        HttpSession session = request.getSession();
        String LogID = "";
        float mark = 0;
        if (session.getAttribute("LOGIN_USER") != null) {
            LogID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
        }

        try {
            QuestionDAO dao1 = new QuestionDAO();
            list1 = dao1.getListQ("");
        } catch (SQLException e) {
            log("Error at ClassSearchController: " + e.toString());
        } finally {
            if (list1 != null) {
                request.setAttribute("LIST_QUESTION", list1);
            }
        }
        try {
            QuizDAO dao = new QuizDAO();
            list2 = dao.getListQ("");
        } catch (SQLException e) {
            log("Error at ClassSearchController: " + e.toString());
        } finally {
            if (list2 != null) {
                request.setAttribute("LIST_QUIZ", list2);
            }
        }

        try {
            for (QuestionDTO x : list1) {
                if (request.getParameter(x.getQuestionID()).equals(x.getAnswer())) {
                    mark += 1;
                }
            }
            ScoreDTO score = new ScoreDTO("1", LogID, String.format("%2f", mark));
            ScoreDAO dao = new ScoreDAO();
            ScoreDTO dto1 = new ScoreDTO();
            dto1 = dao.getListS("1", LogID);
            if (dto1 != null) {
                dao.updateS(score);
            } else {
                dao.insertS(score);
            }
            request.setAttribute("Finish", "Finish Quiz!");
            request.setAttribute("Score", "Your score is: " + String.format("%2f", mark));
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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