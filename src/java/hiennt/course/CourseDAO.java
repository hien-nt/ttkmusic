/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.course;

import hiennt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CourseDAO implements Serializable {

    List<CourseDTO> listCourse;

    public List<CourseDTO> getListCourse() {
        return listCourse;
    }

    public void getAllCourse() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();

            String sql = "SELECT TOP 20 [courseID]\n"
                    + "      ,[courseName]\n"
                    + "      ,[image]\n"
                    + "      ,[description]\n"
                    + "      ,[startDate]\n"
                    + "      ,[endDate]\n"
                    + "      ,[courseFee]\n"
                    + "      ,[quantity]\n"
                    + "      ,[active]\n"
                    + "	  ,Course.categoryID as 'cateID', categoryName\n"
                    + "  FROM Course, Category \n"
                    + "  Where Course.categoryID = Category.categoryID\n"
                    + "  AND quantity > 0 AND active = 1\n"
                    + "  ORDER BY startDate ";

            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String courseId = rs.getString("courseID");
                String courseName = rs.getString("courseName");
                String image = rs.getString("image");
                String desc = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                double courseFee = rs.getDouble("courseFee");
                int quantity = rs.getInt("quantity");
                boolean active = rs.getBoolean("active");
                int cateId = rs.getInt("cateID");
                String cateName = rs.getString("categoryName");
                CourseDTO dto = new CourseDTO(courseId, courseName, image, cateId, desc, startDate, endDate, courseFee, quantity, active, cateName);
                if (listCourse == null) {
                    listCourse = new ArrayList<>();
                }
                listCourse.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }

    }

    public void searchResult(String searchValue, String typeSearch) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();

            String sql = "SELECT [courseID]\n"
                    + "      ,[courseName]\n"
                    + "      ,[image]\n"
                    + "      ,[description]\n"
                    + "      ,[startDate]\n"
                    + "      ,[endDate]\n"
                    + "      ,[courseFee]\n"
                    + "      ,[quantity]\n"
                    + "      ,[active]\n"
                    + "	  ,Course.categoryID as 'cateID', categoryName\n"
                    + "  FROM Course, Category \n"
                    + "  Where Course.categoryID = Category.categoryID\n"
                    + "  AND quantity > 0 AND active = 1\n";
            //+ "  ORDER BY startDate\n";
            if (typeSearch.equalsIgnoreCase("byCourseName")) {
                sql = sql + "AND [courseName] like ?\n"
                        + "ORDER BY startDate DESC";

            } else {
                sql = sql + "AND categoryName like ?\n"
                        + "ORDER BY startDate DESC";

            }

            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String courseId = rs.getString("courseID");
                String courseName = rs.getString("courseName");
                String image = rs.getString("image");
                String desc = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                double courseFee = rs.getDouble("courseFee");
                int quantity = rs.getInt("quantity");
                boolean active = rs.getBoolean("active");
                int cateId = rs.getInt("cateID");
                String cateName = rs.getString("categoryName");
                CourseDTO dto = new CourseDTO(courseId, courseName, image, cateId, desc, startDate, endDate, courseFee, quantity, active, cateName);
                if (listCourse == null) {
                    listCourse = new ArrayList<>();
                }
                listCourse.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
    }

    public boolean insertNewCourse(String cid, String cName, String img, int cateId, String desc, String startDate, String endDate, String courseFee, String quantity, boolean active) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {

            con = DBUtils.makeConnection();
            String sql = "INSERT INTO [dbo].[Course]\n"
                    + "           ([courseID]\n"
                    + "           ,[courseName]\n"
                    + "           ,[image]\n"
                    + "           ,[categoryID]\n"
                    + "           ,[description]\n"
                    + "           ,[startDate]\n"
                    + "           ,[endDate]\n"
                    + "           ,[courseFee]\n"
                    + "           ,[quantity]\n"
                    + "           ,[active])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, cid);
            stm.setString(2, cName);
            stm.setString(3, img);
            stm.setInt(4, cateId);
            stm.setString(5, desc);
            stm.setString(6, startDate);
            stm.setString(7, endDate);
            stm.setString(8, courseFee);
            stm.setString(9, quantity);
            stm.setBoolean(10, active);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;

    }

    public CourseDTO getCourseByID(String courseID) throws SQLException {
        CourseDTO course = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT [courseID]\n"
                    + "      ,[courseName]\n"
                    + "      ,[image]\n"
                    + "      ,[description]\n"
                    + "      ,[startDate]\n"
                    + "      ,[endDate]\n"
                    + "      ,[courseFee]\n"
                    + "      ,[quantity]\n"
                    + "      ,[active]\n"
                    + "	  ,Course.categoryID as 'cateID', categoryName\n"
                    + "  FROM Course, Category \n"
                    + "  Where Course.categoryID = Category.categoryID\n"
                    + " AND [courseID] = ?";

            stm = con.prepareStatement(sql);
            stm.setString(1, courseID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String courseName = rs.getString("courseName");
                String image = rs.getString("image");
                int categoryID = rs.getInt("cateID");
                String desc = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                double courseFee = rs.getDouble("courseFee");
                int quantity = rs.getInt("quantity");
                boolean active = rs.getBoolean("active");
                String categoryName = rs.getString("categoryName");
                course = new CourseDTO(courseID, courseName, image, categoryID, desc, startDate, endDate, courseFee, quantity, active, categoryName);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return course;
    }

    public boolean updateCourse(String cid, String cName, String img, int cateId, String desc, String startDate, String endDate, double courseFee, int quantity, boolean active) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {

            con = DBUtils.makeConnection();
            String sql = "UPDATE [dbo].[Course]\n"
                    + "   SET [courseName] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[categoryID] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[startDate] = ?\n"
                    + "      ,[endDate] = ?\n"
                    + "      ,[courseFee] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[active] = ?\n"
                    + " WHERE [courseID] = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, cName);
            stm.setString(2, img);
            stm.setInt(3, cateId);
            stm.setString(4, desc);
            stm.setString(5, startDate);
            stm.setString(6, endDate);
            stm.setDouble(7, courseFee);
            stm.setInt(8, quantity);
            stm.setBoolean(9, active);
            stm.setString(10, cid);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    
    public List<CourseDTO> getListByPage(List<CourseDTO> list, int start, int end){
        List<CourseDTO> arrayList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }
}
