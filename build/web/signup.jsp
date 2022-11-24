<%-- 
    Document   : signup
    Created on : Jul 5, 2022, 8:55:59 PM
    Author     : Admin
--%>

<%@page import="hiennt.registration.InsertRegistrationError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
       <h2>SIGN UP</h2>
        <form action="MainController" method="POST">
            Username <input type="text" name="txtUsername" value="<%= request.getParameter("txtUsername") %>" />(6 - 20 Chars)</br>
            <%
                InsertRegistrationError insertError = (InsertRegistrationError)request.getAttribute("INSERTERROR");
                if(insertError!=null){
                    if(insertError.getUsernameLengthError()!=null){
                        %>
                        <font color="red"><%= insertError.getUsernameLengthError() %></font></br>
                        <%
                    }
                }
                
            %>
            Password <input type="password" name="txtPassword" value="" />(6 - 20 Chars)</br>
             <%
                if(insertError!=null){
                    if(insertError.getPasswordLengthError()!=null){
                        %>
                        <font color="red"><%= insertError.getPasswordLengthError()%></font></br>
                        <%
                    }
                }
                
            %>
            Confirm <input type="password" name="txtConfirm" value="" /></br>
             <%
                if(insertError!=null){
                    if(insertError.getConfirmPasswordError()!=null){
                        %>
                        <font color="red"><%= insertError.getConfirmPasswordError()%></font></br>
                        <%
                    }
                }
                
            %>
            Name <input type="text" name="txtName" value="<%= request.getParameter("txtName") %>" /> (2 - 50 Chars)</br>
             <%
                if(insertError!=null){
                    if(insertError.getNameLengthError()!=null){
                        %>
                        <font color="red"><%= insertError.getNameLengthError()%></font></br>
                        <%
                    }
                }
                
            %>
            <input type="reset" value="Reset" />
            <input type="submit" value="Sign Up" name="btAction" />
        </form>
             <%
                if(insertError!=null){
                    if(insertError.getUsernameExistedError()!=null){
                        %>
                        <font color="red"><%= insertError.getUsernameExistedError()%></font></br>
                        <%
                    }
                }
                
            %>
    </body>
</html>
