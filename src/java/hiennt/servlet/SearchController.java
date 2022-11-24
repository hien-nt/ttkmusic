/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.servlet;

import hiennt.course.CourseDAO;
import hiennt.course.CourseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SearchController extends HttpServlet {

    private final String SHOWSEARCHRESULT = "showSearch.jsp";

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            String url = SHOWSEARCHRESULT;
            String searchValue = request.getParameter("txtSearchValue");
            String typeSearch = request.getParameter("typeSearch");

            CourseDAO dao = new CourseDAO();
            dao.searchResult(searchValue, typeSearch);
            List<CourseDTO> searchResult = dao.getListCourse();
            if (searchResult != null) {
                String requestPage = request.getParameter("requestPage");
                int size = searchResult.size();
                int responsePage;
                int maxSizeCoursePerPage = 5;
                if (requestPage == null) {
                    responsePage = 1;
                } else {
                    responsePage = Integer.parseInt(requestPage);
                }
                int maxPage;
                if (searchResult.size() % 5 == 0) {
                    maxPage = searchResult.size() / 5;
                } else {
                    maxPage = searchResult.size() / 5 + 1;
                }
                int start = (responsePage - 1) * maxSizeCoursePerPage;
                int end = Math.min(responsePage * maxSizeCoursePerPage, size);
                List<CourseDTO> searchResultByPage = dao.getListByPage(searchResult, start, end);
                request.setAttribute("RESPONSEPAGE", responsePage);
                request.setAttribute("MAXPAGE", maxPage);
                request.setAttribute("SEARCHRESULT", searchResultByPage);
            } else {
                request.setAttribute("SEARCHRESULT", searchResult);
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
