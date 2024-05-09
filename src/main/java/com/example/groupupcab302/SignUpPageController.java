package com.example.groupupcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageController extends ParentViewController {
    private UserDAO userDAO;

    // Create data collection to hold value of all fields
    private String textFieldValues[];

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

    private Stage stage;
    private Scene scene;
    private Parent root;


    public SignUpPageController(){
        userDAO = new UserDAO();
    }


    @FXML
    protected void onAlreadyHaveAnAccountClick(ActionEvent event) throws IOException {
       onLoginButtonClick(event);
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
            SigningInStatus.setText("Successful! Welcome To GroupUp!");
            //Redirect to login page as details are valid
            onAlreadyHaveAnAccountClick(event);
        }

        catch (CustomSQLException sqlException){
            SigningInStatus.setText(sqlException.getMessage());
        }

        catch (IOException ioException){
            SigningInStatus.setText("There was an issue when trying to take you to the login page! Please try it manually via the \"Dont " +
                    "Have an account button?\" " + ioException.getMessage());
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