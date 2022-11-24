<%-- 
    Document   : updateCourseSuccess
    Created on : Jul 9, 2022, 11:28:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Success Page</title>
    </head>
    <body>
    <center>
        <%
            String updateUser = (String) session.getAttribute("USERUPDATED");
            String timeUpdate = (String) session.getAttribute("TIMEUPDATED");
            if (updateUser != null) {
        %>
        Update Successfully </br>
        User update : <%= updateUser%></br>
        <%
            }
            if (timeUpdate != null) {
        %>
        Time update: <%= timeUpdate%></br>
        <%
            }
        %>
        <a href="MainController?btAction=HomePage">Return Home Page</a>
    </center>

</body>
</html>
