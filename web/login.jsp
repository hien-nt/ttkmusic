<%-- 
    Document   : login
    Created on : Jul 5, 2022, 8:27:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
    <center>
        <h2>LOGIN</h2>
        <form action="MainController" method="POST">
            Username <input type="text" name="txtUsername" value="" /></br>
            Password <input type="password" name="txtPassword" value="" /></br>
            <input type="reset" value="Reset" />
            <input type="submit" value="Login" name="btAction" />
        </form>
        <%
            String loginError = (String)request.getAttribute("LOGINERROR");
            if(loginError!=null){
                %>
                <font color="red"><%= loginError %> <a href="signup.html">Sign Up</a> </font>
                <%
            }
        %>
        </center>
    </body>
</html>
