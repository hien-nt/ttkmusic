/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.servlet;

import hiennt.course.CourseDAO;
import hiennt.course.InsertCourseError;
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

/**
 *
 * @author Admin
 */
public class CreateCourseController extends HttpServlet {

    private final String ERRORPAGE = "createCourse.jsp";
    private final String SUCCESS = "HomePageController";

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
        String url = ERRORPAGE;
        String courseId = request.getParameter("txtCourseId");
        String coureName = request.getParameter("txtCourseName");
        String img = request.getParameter("txtImage");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String desc = request.getParameter("txtDesc");
        String startDate = request.getParameter("txtStartDate");
        String endDate = request.getParameter("txtEndDate");
        String courseFee = request.getParameter("txtCourseFee");
        String quantity = request.getParameter("txtQuantity");
        InsertCourseError insertError = new InsertCourseError();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(startDate);
        //Date d2 = sdformat.parse(endDate);

        boolean ckError = false;
        try {
            /* TODO output your page here. You may use following sample code. */

            if (courseId.length() < 2 || courseId.length() > 5) {
                insertError.setCourseIdLenghtError("Course Id require 2 - 5 chars");
                ckError = true;
            }
            if (coureName.length() < 5) {
                insertError.setCourseNameLengthError("Course name require > 5 chars");
                ckError = true;
            }
            if (img.length() < 5) {
                insertError.setImgLengthError("Image url require > 5 chars");
                ckError = true;
            }
            if (desc.length() < 5) {
                insertError.setDescLengthError("Description require > 5 chars");
                ckError = true;
            }
            if (endDate.isEmpty()) {
                insertError.setEndDateInputError("You must input end date");
                ckError = true;
            } else {
                Date d2 = sdformat.parse(endDate);
                if (d1.compareTo(d2) > 0) {
                    insertError.setEndDateValidError("End date must be greater than create date");
                    ckError = true;
                }

            }
            /*if (d1.compareTo(d2) > 0) {
                insertError.setEndDateValidError("End date must be greater than create date");
                ckError = true;
            }*/
            if (courseFee.isEmpty()) {
                insertError.setCourseFeeInputError("You must input course fee");
                ckError = true;
            }
            if (quantity.isEmpty()) {
                insertError.setQuantityInputError("You must input quantity");
                ckError = true;
            }
            if (ckError) {
                request.setAttribute("INSERTERROR", insertError);
            } else {
                CourseDAO dao = new CourseDAO();
                boolean result = dao.insertNewCourse(courseId, coureName, img, categoryId, desc, startDate, endDate, courseFee, quantity, true);
                if (result) {
                    url = SUCCESS;
                }
            }
        } catch (SQLException ex) {
            log("CreateCourseController_ SQL: " + ex.getMessage());
            insertError.setCourseIdExistedError("This course id has existed");
            request.setAttribute("INSERTERROR", insertError);

        } catch (ParseException ex) {
            log("CreateController_ Parse: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //out.close();
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
            Logger.getLogger(CreateCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateCourseController.class.getName()).log(Level.SEVERE, null, ex);

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
