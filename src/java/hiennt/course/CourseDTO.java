/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.course;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class CourseDTO implements Serializable{
    /*SELECT [courseID]
      ,[courseName]
      ,[image]
      ,[categoryID]
      ,[description]
      ,[startDate]
      ,[endDate]
      ,[courseFee]
      ,[quantity]
      ,[active]
  FROM [dbo].[Course]*/
    private String courseID;
    private String courseName;
    private String image;
    private int categoryID;
    private String desc;
    private Date startDate;
    private Date endDate;
    private double courseFee;
    private int quantity;
    private boolean active;
    private String categoryName;

    public CourseDTO() {
    }

    public CourseDTO(String courseID, String courseName, String image, int categoryID, String desc, Date startDate, Date endDate, double courseFee, int quantity, boolean active, String categoryName) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.image = image;
        this.categoryID = categoryID;
        this.desc = desc;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseFee = courseFee;
        this.quantity = quantity;
        this.active = active;
        this.categoryName = categoryName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    
    
}
