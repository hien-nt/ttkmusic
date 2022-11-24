<%-- 
    Document   : createCourse
    Created on : Jul 2, 2022, 12:24:03 AM
    Author     : Admin
--%>

<%@page import="hiennt.registration.RegistrationDTO"%>
<%@page import="hiennt.course.InsertCourseError"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course Page</title>
    </head>
    <body>
        <%
            RegistrationDTO loginedUser = (RegistrationDTO) session.getAttribute("LOGINED_USER");
            if (loginedUser.isIsRole()) {
        %>
        <h2>Create New Course</h2>
        <form action="MainController" method="POST">
            COURSE ID (2 - 5 chars requires) <input type="text" name="txtCourseId" 
                                                    <%
                                                        if (request.getParameter("txtCourseId") == null) {
                                                    %>    value="" /></br><%
                                                    } else {
            %>    value="<%= request.getParameter("txtCourseId")%>"/></br><%
                }
            %>

            <%
                InsertCourseError insertError = (InsertCourseError) request.getAttribute("INSERTERROR");
                if (insertError != null) {
                    if (insertError.getCourseIdLenghtError() != null) {
            %>
            <font color="red"><%= insertError.getCourseIdLenghtError()%></font></br>
            <%
                    }
                }
            %>

            COURSE NAME (More than 5 chars requires) <input type="text" name="txtCourseName"
                                                            <%
                                                                if (request.getParameter("txtCourseName") == null) {
                                                            %>    value="" /></br><%
                                                            } else {
            %>     value="<%= request.getParameter("txtCourseName")%>"/></br><%
                }
            %>

            <%
                if (insertError != null) {
                    if (insertError.getCourseNameLengthError() != null) {
            %>
            <font color="red"><%= insertError.getCourseNameLengthError()%></font></br>
            <%
                    }
                }
            %>

            IMAGE URL (More than 5 chars requires) <input type="text" name="txtImage" 
                                                          <%
                                                              if (request.getParameter("txtImage") == null) {
                                                          %>    value="" /></br><%
                                                          } else {
            %>    value="<%= request.getParameter("txtImage")%>"/></br><%
                }
            %>


            <%
                if (insertError != null) {
                    if (insertError.getImgLengthError() != null) {
            %>
            <font color="red"><%= insertError.getImgLengthError()%></font></br>
            <%
                    }
                }
            %>

            CATEGORY <select name="categoryId">
                <option value="1">Guitar</option>
                <option value="2">Piano</option>
                <option value="3">Ukulele</option>
                <option value="4">Drum</option>
                <option value="5">Bass Guitar</option>
                <option value="6">Flute</option>
            </select></br>
            DESCRIPTION (More than 5 chars requires) <input type="text" name="txtDesc"
                                                            <%
                                                                if (request.getParameter("txtDesc") == null) {
                                                            %>   value="" /></br><%
                                                            } else {
            %>   value="<%= request.getParameter("txtDesc")%>"/></br><%
                }
            %>

            <%
                if (insertError != null) {
                    if (insertError.getDescLengthError() != null) {
            %>
            <font color="red"><%= insertError.getDescLengthError()%></font></br>
            <%
                    }
                }
            %>

            <%
                Date date = new Date(System.currentTimeMillis());
            %>
            CREATE DATE <input readonly="" type="date" name="txtStartDate" value="<%= date%>" /></br>
            END DATE <input type="date" name="txtEndDate" value="<%= request.getParameter("txtEndDate")%>" /></br>
            <%
                if (insertError != null) {
                    if (insertError.getEndDateInputError() != null) {
            %>
            <font color="red"><%= insertError.getEndDateInputError()%></font></br>
            <%
            } else if (insertError.getEndDateValidError() != null) {
            %>
            <font color="red"><%= insertError.getEndDateValidError()%></font></br>
            <%
                    }
                }
            %>
            COURSE FEE <input type="number" name="txtCourseFee" value="<%= request.getParameter("txtCourseFee")%>" min="1" max="2147483647"/></br>
            <%
                if (insertError != null) {
                    if (insertError.getCourseFeeInputError() != null) {
            %>
            <font color="red"><%= insertError.getCourseFeeInputError()%></font></br>
            <%
                    }
                }
            %>
            QUANTITY <input type="number" name="txtQuantity" value="<%= request.getParameter("txtQuantity")%>" min="1" max="2147483647"/></br>
            <%
                if (insertError != null) {
                    if (insertError.getQuantityInputError() != null) {
            %>
            <font color="red"><%= insertError.getQuantityInputError()%></font></br>
            <%
                    }
                }
            %>
            <input type="reset" value="Reset" />
            <input type="submit" name="btAction" value="Create New Course" />
        </form>
        <%
            if (insertError != null) {
                if (insertError.getCourseIdExistedError() != null) {
        %>
        <font color="red"><%= insertError.getCourseIdExistedError()%></font></br>
        <%
                }
            }
        }//end loginuser !=null
        else {
        %>
    <center>
        <h2><font color="red">This function can be use only by Admin</font></h2>
        <a href="login.html">Login to check your permission</a></br>
        <a href="MainController?btAction=HomePage">Return Home Page</a>
    </center>
    <%
        }
    %>
</body>
</html>
