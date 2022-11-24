/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.servlet;

import hiennt.course.CourseDAO;
import hiennt.course.UpdateCourseError;
import hiennt.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class UpdateCourseController extends HttpServlet {

    private final String ERROR = "upateCourseError.html";
    private final String SUCCESS = "updateCourseSuccess.jsp";

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //String url = ERROR;
            String courseId = request.getParameter("txtCourseId");
            String coureName = request.getParameter("txtCourseName");
            String img = request.getParameter("txtImage");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String desc = request.getParameter("txtDesc");
            String startDate = request.getParameter("txtStartDate");
            String endDate = request.getParameter("txtEndDate");
            Double courseFee = Double.parseDouble(request.getParameter("txtCourseFee"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            boolean active = Boolean.parseBoolean(request.getParameter("txtIsActive"));
            /* TODO output your page here. You may use following sample code. */

            CourseDAO dao = new CourseDAO();
            boolean result = dao.updateCourse(courseId, coureName, img, categoryId, desc, startDate, endDate, courseFee, quantity, active);
            if (result) {
                HttpSession session = request.getSession();
                RegistrationDTO dto = (RegistrationDTO) session.getAttribute("LOGINED_USER");
                String userUpdate = dto.getFullname();
                Date date = new Date(System.currentTimeMillis());
                String dateUpdate = "" + date;
                session.setAttribute("USERUPDATED", userUpdate);
                session.setAttribute("TIMEUPDATED", dateUpdate);
                url = SUCCESS;
                
            }
            /*RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);*/

        } catch (SQLException ex) {
            log("UpdateCourseController_ SQL " + ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
