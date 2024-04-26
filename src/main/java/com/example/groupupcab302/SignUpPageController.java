package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageController {
    private UserDAO userDAO;

    //Define a const to represent when there is a value for an integer

    private final String VALIDATION_TYPE_PHONE_NUMBER = "Phone Number";
    private final String VALIDATION_TYPE_AGE = "Age";

    // Create data collection for basic text fields: username, first, last name, email, password and password confirmation
    private String textFieldValues[];

    // Create data collection for text fields that recieve integer values: phone number and age
    private String intInputValues[];

    // Create data collection for phone number and age values after successful parsing from string to int
    private Integer validatedIntInputValues[];



    @FXML
    private Label welcomeText;

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

    @FXML
    private TextArea SigningInStatus;

    public SignUpPageController(){
        userDAO = new UserDAO();
    }

    @FXML
    protected void onSignUpButtonClick(){
        //Initialize the data collections with values from form
        textFieldValues = new String[] {UserNameTextField.getText(), FirstNameTextField.getText(),
                LastNameTextField.getText(), EmailTextField.getText(), PhoneNumberTextField.getText(), AgeTextField.getText(),
                PasswordTextField.getText(), ConfirmationPasswordTextField.getText()};
        handleUserSignUp();
    }

    public void handleUserSignUp(){
        if (areAllUserDetailsValid()) {
            createUser();
        }
    }

    public boolean areAllUserDetailsValid() {
        return areBasicTextFieldsValid() &&
                isEmailValid(textFieldValues[3]) &&
                isPhoneNumberValid(textFieldValues[4]) &&
                isAgeValid(textFieldValues[5]) &&
                validatePassword(textFieldValues[6], textFieldValues[7]);
    }


    public void createUser(){
        GroupUpUser groupUpUser = new GroupUpUser(textFieldValues[0], textFieldValues[1],
                textFieldValues[2], textFieldValues[3], textFieldValues[4], textFieldValues[5], textFieldValues[6]);

        try {
            userDAO.insert(groupUpUser);
            SigningInStatus.setText("Successful! Welcome To GroupUp!");
        }

        catch (CustomSQLException sqlException){
            SigningInStatus.setText(sqlException.getMessage());
        }

        catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    public boolean areBasicTextFieldsValid() {
        for (int counter = 0; counter < textFieldValues.length; counter++) {
            // Ensure fields are not empty
            if (textFieldValues[counter].length() == 0) {
                //display error message if form isnt filled out properly
                SigningInStatus.setText(ErrorConstants.INVALID_USERINPUT.getErrorDescription());
                return false; // Return false if any string is invalid
            }
        }
        return true; // Return true if all strings are valid
    }


    public boolean doPasswordsMatch(String password, String passwordConfirmation){
        if (password.equals(passwordConfirmation)){
            return true;
        }
        return false;
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        //Check if an error is returned when validating phone number
        if (userDAO.validatePhoneNumber(phoneNumber) == ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue() ||
                userDAO.validatePhoneNumber(phoneNumber) == ErrorConstants.INT_PARSE_ERROR.getErrorValue()){
            // Display the error code produced when validating phone number
            SigningInStatus.setText(ErrorConstants.retrieveErrorConstantDescription(userDAO.validatePhoneNumber(phoneNumber)));
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, String passwordConfirmation){
        if(userDAO.isPasswordValid(password) && doPasswordsMatch(password, passwordConfirmation)){
            return true;
        }
        // display error message if passwords dont match
        SigningInStatus.setText(ErrorConstants.INVALID_PASSWORD.getErrorDescription());
        return false;
    }

    public boolean isAgeValid(String age){
        //Check if an error is returned when validating phone number
        if (userDAO.validateAge(age) == ErrorConstants.INVALID_AGE.getErrorValue() ||
                userDAO.validateAge(age) == ErrorConstants.INT_PARSE_ERROR.getErrorValue()){
            // Display the error code produced when validating age
            SigningInStatus.setText(ErrorConstants.retrieveErrorConstantDescription(userDAO.validateAge(age)));
            return false;
        }
        return true;
    }

    public boolean isEmailValid(String email){
        if (email.contains("@") && email.length() > 1){
            return true;
        }
        //display error message if email input is incorrect
        SigningInStatus.setText(ErrorConstants.INVALID_EMAIL.getErrorDescription());
        return false;
    }


}