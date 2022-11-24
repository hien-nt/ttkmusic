/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.servlet;

import hiennt.registration.InsertRegistrationError;
import hiennt.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SignUpController extends HttpServlet {
    private final String ERROR = "signup.jsp";
    private final String SUCCESS = "login.html";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        InsertRegistrationError insertError = new InsertRegistrationError();
        boolean ckError = false;
        String url = ERROR;
        try {
            /* TODO output your page here. You may use following sample code. */

            if (username.trim().length() < 6 || username.trim().length() > 20) {
                insertError.setUsernameLengthError("Username requires 6 - 20 chars");
                ckError = true;
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                insertError.setPasswordLengthError("Password requires 6 - 20 chars");
                ckError = true;
            } else if (!confirm.equals(password)) {
                insertError.setConfirmPasswordError("Confirm and passowrd not the same");
                ckError = true;
            }
            if (name.trim().length() < 2 || name.trim().length() > 50) {
                insertError.setNameLengthError("Name requires 2 - 50 chars");
                ckError = true;
            }
            if(ckError){
                request.setAttribute("INSERTERROR", insertError);
                
            }else{
                RegistrationDAO dao = new RegistrationDAO();
                boolean resul = dao.insertRegistration(username, password, name, false);
                url = SUCCESS;
            }

        } catch (SQLException ex) {
            log("SignUpController _ SQL :" + ex.getMessage());
            insertError.setUsernameExistedError("This username has existed");
            request.setAttribute("INSERTERROR", insertError);
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
