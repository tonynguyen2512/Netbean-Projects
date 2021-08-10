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
import org.onlinequizapp.dtos.CategoryBlogDTO;
import org.onlinequizapp.dtos.CategoryDTO;

/**
 *
 * @author Category-PC
 */
@WebServlet(name = "CategorySearchController", urlPatterns = {"/CategorySearchController"})
public class CategorySearchController extends HttpServlet {

    private static final String SUCCESS = "all-category.jsp";
    private static final String COURSE = "courseAdd.jsp";
    private static final String QUESTION = "questionAdd.jsp";
    private static final String BLOG = "create-blog.jsp";
    private static final String BLOGU = "updateBlog.jsp";
    private static final String Course = "updateCourse.jsp";
    private static final String Question = "updateQuestion.jsp";
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
        String cate = request.getParameter("cate");
        if (cate.equals("1")) {
            if (check.equals("Search")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryDTO> list = dao.getListQ(search);
                    if (list != null) {
                        request.setAttribute("LIST_QUIZ_CATEGORY", list);
                        url = SUCCESS;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else if (check.equals("Course")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryDTO> list = dao.getListQ(search);
                    if (list != null) {
                        request.setAttribute("LIST_QUIZ_CATEGORY", list);
                        url = COURSE;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else if (check.equals("Question")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryDTO> list = dao.getListQ(search);
                    if (list != null) {
                        request.setAttribute("LIST_QUIZ_CATEGORY", list);
                        url = QUESTION;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } 
        } else if (cate.equals("2")) {
            if (check.equals("Search")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryBlogDTO> list = dao.getListB(search);
                    if (list != null) {
                        request.setAttribute("LIST_BLOG_CATEGORY", list);
                        url = SUCCESS;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else if (check.equals("Blog")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryBlogDTO> list = dao.getListB(search);
                    if (list != null) {
                        request.setAttribute("LIST_BLOG_CATEGORY", list);
                        url = BLOG;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } else if (check.equals("BlogU")) {
                try {
                    String search = request.getParameter("search");
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryBlogDTO> list = dao.getListB(search);
                    if (list != null) {
                        request.setAttribute("LIST_BLOG_CATEGORY", list);
                        url = BLOGU;
                    }
                } catch (SQLException e) {
                    log("Error at CategorySearchController: " + e.toString());
                } finally {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } else {
            try {
                String search = request.getParameter("search");
                CategoryDAO dao = new CategoryDAO();
                List<CategoryDTO> list = dao.getListQ(search);
                if (list != null) {
                    request.setAttribute("LIST_QUIZ_CATEGORY", list);
                    url = SUCCESS;
                }
                List<CategoryBlogDTO> Blist = dao.getListB(search);
                if (Blist != null) {
                    request.setAttribute("LIST_BLOG_CATEGORY", Blist);
                    url = SUCCESS;
                }
            } catch (SQLException e) {
                log("Error at CategorySearchController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
