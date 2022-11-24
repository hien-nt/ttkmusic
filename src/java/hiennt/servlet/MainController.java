/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private final String HOMEPAGECONTROLLER = "HomePageController";
    private final String SEARCHCONTROLLER = "SearchController";
    private final String CREATECOURSECONTROLLER = "CreateCourseController";
    private final String GETCOURSEBYID = "GetCourseByIDController";
    private final String UPDATECOURSECONTROLLER = "UpdateCourseController";
    private final String LOGINCONTROLLER = "LoginController";
    private final String SIGNUPCONTROLLER = "SignUpController";
    private final String LOGOUTCONTROLLER = "LogoutController";
    private final String AUTHENCHECKCREATECOURSE = "CheckAuthenCreateCourse";
    private final String AUTHENCHECKUPDATECOURSE = "CheckAuthenUpdateCourse";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String btAction = request.getParameter("btAction");
            //chua co se = null
            String url = "";//HOMEPAGECONTROLLER;
            
            if (btAction == null || btAction.equals("HomePage")) {
                url = HOMEPAGECONTROLLER;
            } else if (btAction.equals("Search")) {
                url = SEARCHCONTROLLER;
            } else if (btAction.equals("Create New Course")) {
                url = CREATECOURSECONTROLLER;
            } else if (btAction.equals("GetCourseById")) {
                url = GETCOURSEBYID;
            } else if (btAction.equals("Update Course")) {
                url = UPDATECOURSECONTROLLER;
            } else if (btAction.equals("Login")) {
                url = LOGINCONTROLLER;
            } else if (btAction.equals("Sign Up")) {
                url = SIGNUPCONTROLLER;
            } else if (btAction.equals("Logout")) {
                url = LOGOUTCONTROLLER;
            }else if(btAction.equals("checkAuthCreateCourse")){
                url = AUTHENCHECKCREATECOURSE;
            }else if(btAction.equals("CheckAuthenUpdateCourse")){
                url = AUTHENCHECKUPDATECOURSE;
            }
            
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
