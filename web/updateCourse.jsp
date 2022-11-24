<%-- 
    Document   : updateCourse
    Created on : Jul 4, 2022, 8:34:33 PM
    Author     : Admin
--%>

<%@page import="hiennt.registration.RegistrationDTO"%>
<%@page import="hiennt.course.UpdateCourseError"%>
<%@page import="java.util.List"%>
<%@page import="hiennt.Category.CategoryDTO"%>
<%@page import="hiennt.course.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Course Page</title>
    </head>
    <body>
        <%
            RegistrationDTO loginedUser = (RegistrationDTO) session.getAttribute("LOGINED_USER");
            if (loginedUser.isIsRole()) {
        %>
        <h2>UPDATE COURSE</h2>
        <%
            CourseDTO course = (CourseDTO) request.getAttribute("COURSE");
            UpdateCourseError updateError = (UpdateCourseError) request.getAttribute("UPDATEERROR");
            if (course != null) {
        %>
        <form action="MainController" method="POST">
            COURSE ID (2 - 5 chars requires) <input readonly="" type="text" name="txtCourseId" value="<%= course.getCourseID()%>" /></br>
            <%
                if (updateError != null) {
                    if (updateError.getCourseIdLenghtError() != null) {
            %>
            <font color="red"><%= updateError.getCourseIdLenghtError()%> </font></br>
            <%
                    }
                }
            %>
            COURSE Name (More than 5 chars requires) <input type="text" name="txtCourseName" value="<%= course.getCourseName()%>" /></br>
            <%
                if (updateError != null) {
                    if (updateError.getCourseNameLengthError() != null) {
            %>
            <font color="red"><%= updateError.getCourseNameLengthError()%> </font></br>
            <%
                    }
                }
            %>
            IMAGE URL (More than 5 chars requires) <input type="text" name="txtImage" value="<%= course.getImage()%>" /></br>
            <%
                if (updateError != null) {
                    if (updateError.getImgLengthError() != null) {
            %>
            <font color="red"><%= updateError.getImgLengthError()%> </font></br>
            <%
                    }
                }
            %>
            CATEGORY <select name="categoryId">
                <option value="<%= course.getCategoryID()%>"><%= course.getCategoryName()%></option>
                <%
                    List<CategoryDTO> listCategory = (List<CategoryDTO>) request.getAttribute("CATEGORYLIST");
                    for (CategoryDTO category : listCategory) {
                        if (category.getCategoryId() != course.getCategoryID()) {
                %>
                <option value="<%= category.getCategoryId()%>"><%= category.getCategoryName()%></option>
                <%
                        }
                    }
                %>
            </select></br>
            DESCRIPTION (More than 5 chars requires) <input type="text" name="txtDesc" value="<%= course.getDesc()%>" /></br>
            <%
                if (updateError != null) {
                    if (updateError.getDescLengthError() != null) {
            %>
            <font color="red"><%= updateError.getDescLengthError()%> </font></br>
            <%
                    }
                }
            %>
            START DATE <input type="date" name="txtStartDate" value="<%= course.getStartDate()%>"/> </br>
            <%
                if (updateError != null) {
                    if (updateError.getStartDateInputError() != null) {
            %>
            <font color="red"><%= updateError.getStartDateInputError()%> </font></br>
            <%
                    }
                }
            %>
            END DATE <input type="date" name="txtEndDate" value="<%= course.getEndDate()%>"/></br>
            <%
                if (updateError != null) {
                    if (updateError.getEndDateInputError() != null) {
            %>
            <font color="red"><%= updateError.getEndDateInputError()%> </font></br>
            <%
            } else if (updateError.getEndDateValidError() != null) {
            %><font color="red"><%= updateError.getEndDateValidError()%> </font></br><%
                    }
                }
            %>
            COURSE FEE <input type="number" name="txtCourseFee" value="<%= course.getCourseFee()%>" min="1" max="2147483647"/></br>
            <%
                if (updateError != null) {
                    if (updateError.getCourseFeeInputError() != null) {
            %>
            <font color="red"><%= updateError.getCourseFeeInputError()%> </font></br>
            <%
                    }
                }
            %>
            QUANTITY <input type="number" name="txtQuantity" value="<%= course.getQuantity()%>" min="1" max="2147483647" /></br>
            <%
                if (updateError != null) {
                    if (updateError.getQuantityInputError() != null) {
            %>
            <font color="red"><%= updateError.getQuantityInputError()%> </font></br>
            <%
                    }
                }
            %>
            ACTIVE <select name="txtIsActive">
                <option value="<%= course.isActive()%>">
                    <%
                        if (course.isActive()) {
                    %>Active<%
                    } else {
                    %>Inactive<%
                        }
                    %>
                </option>
                <option value="<%
                    if (course.isActive()) {
                        %>False<%
                        } else {
                        %>True<%
                            }
                        %>"><%
                            if (course.isActive()) {
                    %>Inactive<%
                    } else {
                    %>Active<%
                        }
                    %>
                </option>
            </select></br>
            <%
            }//end if course != null
            else {
            %>Cannot get course information to update</br><%
                }
            %>
            <input type="submit" value="Update Course" name="btAction" />
        </form>
        <%
        }//end loginuser isRole
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
