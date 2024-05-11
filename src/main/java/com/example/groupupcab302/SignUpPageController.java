package com.example.groupupcab302;

import com.example.groupupcab302.Constants.ErrorConstants;
import com.example.groupupcab302.DAO.UserDAO;
import com.example.groupupcab302.Objects.GroupUpUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class SignUpPageController extends ParentViewController {
    private UserDAO userDAO;

    // Create data collection to hold value of all fields
    private String textFieldValues[];


    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private TextField UserNameTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField PhoneNumberTextField;

    @FXML
    private TextField AgeTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField ConfirmationPasswordTextField;


    private Stage stage;
    private Scene scene;
    private Parent root;


    public SignUpPageController(){
        userDAO = new UserDAO();
    }


    @FXML
    protected void onSignUpButtonClick(ActionEvent event){
        //Initialize the data collections with values from form
        textFieldValues = new String[] {UserNameTextField.getText(), FirstNameTextField.getText(),
                LastNameTextField.getText(), EmailTextField.getText(), PhoneNumberTextField.getText(), AgeTextField.getText(),
                PasswordTextField.getText(), ConfirmationPasswordTextField.getText()};
        handleUserSignUp(event);
    }

    public void handleUserSignUp(ActionEvent event){
        if (areAllUserDetailsValid()) {
            createUser(event);
        }
    }

    public boolean areAllUserDetailsValid() {
        return areBasicTextFieldsValid() &&
                isEmailValid(textFieldValues[3]) &&
                isPhoneNumberValid(textFieldValues[4]) &&
                isAgeValid(textFieldValues[5]) &&
                validatePassword(textFieldValues[6], textFieldValues[7]);
    }


    public void createUser(ActionEvent event){
        GroupUpUser groupUpUser = new GroupUpUser(textFieldValues[0], textFieldValues[1],
                textFieldValues[2], textFieldValues[3], textFieldValues[4], textFieldValues[5], textFieldValues[6]);

        try {
            userDAO.insert(groupUpUser);
            displayNotification("Sign Up Success","Account Successfully Created! Please Login To Access GroupUp!", false);
            //Redirect to login page as details are valid
            redirectToLoginPage(event);
        }

        catch (CustomSQLException sqlException){
            displayNotification("Database error", sqlException.getMessage(), true);
        }

    }

    public boolean areBasicTextFieldsValid() {
        for (int counter = 0; counter < textFieldValues.length; counter++) {
            // Ensure fields are not empty
            if (textFieldValues[counter].isEmpty()) {
                //display error message if form isnt filled out properly
                displayNotification("Sign Up Failure", ErrorConstants.INVALID_USERINPUT.getErrorDescription(), true);
                return false; // Return false if any string is invalid
            }
        }
        return true; // Return true if all strings are valid
    }


    public boolean doPasswordsMatch(String password, String passwordConfirmation){
        return password.equals(passwordConfirmation);
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        //Check if an error is returned when validating phone number
        if (Objects.equals(userDAO.validatePhoneNumber(phoneNumber), ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue()) ||
                Objects.equals(userDAO.validatePhoneNumber(phoneNumber), ErrorConstants.INT_PARSE_ERROR.getErrorValue())){
            // Display the error code produced when validating phone number
            displayNotification("Sign Up Failure",ErrorConstants.retrieveErrorConstantDescription(userDAO.validatePhoneNumber(phoneNumber)), true);
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, String passwordConfirmation){
        if(userDAO.isPasswordValid(password) && doPasswordsMatch(password, passwordConfirmation)){
            return true;
        }
        // display error notification if passwords dont match
        displayNotification("Sign Up Failure",ErrorConstants.INVALID_PASSWORD.getErrorDescription(), true);
        return false;
    }

    public boolean isAgeValid(String age){
        //Check if an error is returned when validating phone number
        if (Objects.equals(userDAO.validateAge(age), ErrorConstants.INVALID_AGE.getErrorValue()) ||
                Objects.equals(userDAO.validateAge(age), ErrorConstants.INT_PARSE_ERROR.getErrorValue())){
            // Display the error code produced when validating age
            displayNotification("Sign Up Failure",ErrorConstants.retrieveErrorConstantDescription(userDAO.validateAge(age)), true);
            return false;
        }
        return true;
    }

    public boolean isEmailValid(String email){
        if (email.contains("@") && email.length() > 1){
            return true;
        }
        //display error notification if email input is incorrect
        displayNotification("Sign Up Failure",ErrorConstants.INVALID_EMAIL.getErrorDescription(), true);
        return false;
    }


}