package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageController {
    private UserDAO userDAO;

    //Define a const to represent when there is a value for an integer

    private final String VALIDATION_TYPE_PHONE_NUMBER = "Phone Number";

    // Create data collection for basic text fields: username, first, last name, email, password and password confirmation
    private String[] textFieldValues;

    // Create data collection for text fields that receive integer values: phone number and age
    private String[] intInputValues;

    // Create data collection for phone number and age values after successful parsing from string to int
    private Integer[] validatedIntInputValues;



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
    protected void onSignInButtonClick(){
        //Initialize the data collections with values of basic user string input
         textFieldValues = new String[] {UserNameTextField.getText(), FirstNameTextField.getText(),
                LastNameTextField.getText(), EmailTextField.getText(), PasswordTextField.getText(), ConfirmationPasswordTextField.getText()};

        //Initialize the data collections with integer values from user input
         intInputValues = new String[] {PhoneNumberTextField.getText(), AgeTextField.getText()};

         handleUserSignUp();
    }

    public void handleUserSignUp(){
        if (areBasicTextFieldsValid()){
            if (isEmailValid(textFieldValues[3])){
                Integer phoneNumber = userDAO.validateInteger(intInputValues[0], VALIDATION_TYPE_PHONE_NUMBER);
                String VALIDATION_TYPE_AGE = "Age";
                Integer age = userDAO.validateInteger(intInputValues[1], VALIDATION_TYPE_AGE);
                validatedIntInputValues = new Integer[] {phoneNumber, age};

                // Check if phoneNumber is not equal to any of the error codes
                if (validatePhoneNumberAndAge(phoneNumber, age)) {
                    if (validatePassword(textFieldValues[4],textFieldValues[5])){

                        createUser();
                    }
                }
            }
        }
    }

    public boolean validatePassword(String password, String passwordConfirmation){
        if(userDAO.isPasswordValid(password) && doPasswordsMatch(password, passwordConfirmation)){
            return true;
        }
        // display error message if passwords don't match
        SigningInStatus.setText(ErrorConstants.INVALID_PASSWORD.getErrorDescription());
        return false;
    }

    public void createUser(){
            GroupUpUser groupUpUser = new GroupUpUser(textFieldValues[0], textFieldValues[1],
                    textFieldValues[2], textFieldValues[3], validatedIntInputValues[0], validatedIntInputValues[1], textFieldValues[4]);

                try {
                    userDAO.insert(groupUpUser);
                    SigningInStatus.setText("Successful! Welcome To GroupUp!");
                }

                catch (CustomSQLException sqlException){
                    SigningInStatus.setText(sqlException.getMessage());
                }

    }

    public boolean validatePhoneNumberAndAge(Integer phoneNumber, Integer age){
        if ((!Objects.equals(phoneNumber, ErrorConstants.INT_PARSE_ERROR.getErrorValue()) && !Objects.equals(phoneNumber, ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue()))
                && (!Objects.equals(age, ErrorConstants.INT_PARSE_ERROR.getErrorValue()) && !Objects.equals(age, ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue()))){
            return true;
        }

        // display error message if phone number or age don't satisfy validators
        else if (Objects.equals(age, ErrorConstants.INVALID_AGE.getErrorValue())){
            SigningInStatus.setText(ErrorConstants.INVALID_AGE.getErrorDescription());
            return false;
        }

        else if (Objects.equals(phoneNumber, ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue())){
            SigningInStatus.setText(ErrorConstants.INVALID_PHONE_NUMBER.getErrorDescription());
            return false;
        }

        else if (Objects.equals(phoneNumber, ErrorConstants.INT_PARSE_ERROR.getErrorValue()) || Objects.equals(age, ErrorConstants.INT_PARSE_ERROR.getErrorValue())){
            SigningInStatus.setText(ErrorConstants.INT_PARSE_ERROR.getErrorDescription());
            return false;
        }

        return false;
    }

    public boolean areBasicTextFieldsValid() {
        for (String textFieldValue : textFieldValues) {
            // Ensure fields are not empty
            if (textFieldValue.isEmpty()) {
                //display error message if form isn't filled out properly
                SigningInStatus.setText(ErrorConstants.INVALID_USERINPUT.getErrorDescription());
                return false; // Return false if any string is invalid
            }
        }
        return true; // Return true if all strings are valid
    }


    public boolean doPasswordsMatch(String password, String passwordConfirmation){
        return password.equals(passwordConfirmation);
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