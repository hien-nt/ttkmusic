<%-- 
    Document   : homePage
    Created on : Jul 1, 2022, 3:53:49 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="hiennt.registration.RegistrationDTO"%>
<%@page import="hiennt.course.CourseDTO"%>
<%@page import="java.util.List"%>
<%@page import="hiennt.course.CourseDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css"/>
        <title>Home Page</title>
    </head>
    <body>
        <%
            RegistrationDTO loginedUser = (RegistrationDTO) session.getAttribute("LOGINED_USER");
        %>
        <a style="text-decoration: none" href="HomePageController">HOME PAGE</a>
        <form action="MainController">
            Search <input type="text" name="txtSearchValue" value="" />
            <input type="submit" value="Search" name="btAction" />
            <select name="typeSearch">
                <option value="byCourseName">By Name</option>
                <option value="byCateName">By Category</option>
            </select>
        </form>
        <%
            if (loginedUser == null) {
        %>
        <a style="text-decoration: none" href="login.html">LOGIN</a>&emsp;
        <a style="text-decoration: none" href="signup.html">SIGN UP</a>&emsp;
        <%
            }
        %>


        <a style="text-decoration: none" href="MainController?btAction=checkAuthCreateCourse">CREATE NEW COURSE</a>&emsp;
        <a style="text-decoration: none" href="viewcart.jsp">VIEW CART</a>&emsp;
        <%
            if (loginedUser != null) {
        %>
        <a style="text-decoration: none" href="MainController?btAction=Logout">LOGOUT</a>&emsp;</br>
        <font color="red">Welcome to <%= loginedUser.getFullname()%></font>
        <%
            }
        %>
        <%--
        <div>
            <%@include file="header.jsp" %> 
        </div>
        --%>

        <%
            List<CourseDTO> listCourse = (List<CourseDTO>) request.getAttribute("ALLCOURSE");
            if (listCourse != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <%--<th>No.</th>--%>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Image URL</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Course Fee</th>
                    <th>Quantity</th>
                    <th>Active</th>
                    <th>Category Name</th>
                    <th>Update Course</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (CourseDTO dto : listCourse) {
                        //String URLRewriting = "MainController?btAction=GetCourseById&courseId=" + dto.getCourseID();
                %>
                <tr>
                    <%--<th>
                        <%= ++count%>
                    </th>--%>
                    <th>
                        <%= dto.getCourseID()%>
                    </th>
                    <th>
                        <%= dto.getCourseName()%>
                    </th>
                    <th>
                        <image src="<%= dto.getImage()%>" class="courseImg" >
                    </th>
                    <th>
                        <%= dto.getDesc()%>
                    </th>
                    <th>
                        <%= dto.getStartDate()%>
                    </th>
                    <th>
                        <%= dto.getEndDate()%>
                    </th>
                    <th>
                        <%= dto.getCourseFee()%>
                    </th>
                    <th>
                        <%= dto.getQuantity()%>
                    </th>
                    <th>
                        <%= dto.isActive()%>
                    </th>
                    <th>
                        <%= dto.getCategoryName()%>
                    </th>
                    <th>
                        <a href="MainController?btAction=CheckAuthenUpdateCourse&courseId=<%= dto.getCourseID()%>">Update Course</a>
                    </th>
                </tr>
                <%
                    }// end for
                %>

            </tbody>
        </table>
        <c:set var="responsePage" value="${requestScope.RESPONSEPAGE}"/>
    <center>
        <div class="pagnination">
            <c:forEach begin="${1}" end="${requestScope.MAXPAGE}" var="i">
                <a class="${i==responsePage?"active":""}" href="MainController?btAction=HomePage&requestPage=${i}">${i}</a>
            </c:forEach>
        </div>
    </center>


    <%
    }//end if 
    else {
    %>
    <h2>No course found</h2>
    <%
        }


    %>
</body>
</html>
