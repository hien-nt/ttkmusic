/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.course;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UpdateCourseError implements Serializable{
    private String courseIdLenghtError;
    private String courseNameLengthError;
    private String imgLengthError;
    private String descLengthError;
    private String startDateInputError;
    private String endDateInputError;
    private String endDateValidError;
    private String courseFeeInputError;
    private String quantityInputError;

    public UpdateCourseError() {
    }

    public UpdateCourseError(String courseIdLenghtError, String courseNameLengthError, String imgLengthError, String descLengthError, String startDateInputError, String endDateInputError, String endDateValidError, String courseFeeInputError, String quantityInputError) {
        this.courseIdLenghtError = courseIdLenghtError;
        this.courseNameLengthError = courseNameLengthError;
        this.imgLengthError = imgLengthError;
        this.descLengthError = descLengthError;
        this.startDateInputError = startDateInputError;
        this.endDateInputError = endDateInputError;
        this.endDateValidError = endDateValidError;
        this.courseFeeInputError = courseFeeInputError;
        this.quantityInputError = quantityInputError;
    }

    public String getCourseIdLenghtError() {
        return courseIdLenghtError;
    }

    public void setCourseIdLenghtError(String courseIdLenghtError) {
        this.courseIdLenghtError = courseIdLenghtError;
    }

    public String getCourseNameLengthError() {
        return courseNameLengthError;
    }

    public void setCourseNameLengthError(String courseNameLengthError) {
        this.courseNameLengthError = courseNameLengthError;
    }

    public String getImgLengthError() {
        return imgLengthError;
    }

    public void setImgLengthError(String imgLengthError) {
        this.imgLengthError = imgLengthError;
    }

    public String getDescLengthError() {
        return descLengthError;
    }

    public void setDescLengthError(String descLengthError) {
        this.descLengthError = descLengthError;
    }

    public String getStartDateInputError() {
        return startDateInputError;
    }

    public void setStartDateInputError(String startDateInputError) {
        this.startDateInputError = startDateInputError;
    }

    public String getEndDateInputError() {
        return endDateInputError;
    }

    public void setEndDateInputError(String endDateInputError) {
        this.endDateInputError = endDateInputError;
    }

    public String getEndDateValidError() {
        return endDateValidError;
    }

    public void setEndDateValidError(String endDateValidError) {
        this.endDateValidError = endDateValidError;
    }

    public String getCourseFeeInputError() {
        return courseFeeInputError;
    }

    public void setCourseFeeInputError(String courseFeeInputError) {
        this.courseFeeInputError = courseFeeInputError;
    }

    public String getQuantityInputError() {
        return quantityInputError;
    }

    public void setQuantityInputError(String quantityInputError) {
        this.quantityInputError = quantityInputError;
    }
    
}
