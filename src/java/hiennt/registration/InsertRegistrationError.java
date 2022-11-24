/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.registration;

/**
 *
 * @author Admin
 */
public class InsertRegistrationError {
    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmPasswordError;
    private String nameLengthError;
    private String usernameExistedError;

    public InsertRegistrationError() {
    }

    public InsertRegistrationError(String usernameLengthError, String passwordLengthError, String confirmPasswordError, String nameLengthError, String usernameExistedError) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.confirmPasswordError = confirmPasswordError;
        this.nameLengthError = nameLengthError;
        this.usernameExistedError = usernameExistedError;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getNameLengthError() {
        return nameLengthError;
    }

    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    public String getUsernameExistedError() {
        return usernameExistedError;
    }

    public void setUsernameExistedError(String usernameExistedError) {
        this.usernameExistedError = usernameExistedError;
    }
    
}
