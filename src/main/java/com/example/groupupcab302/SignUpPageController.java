package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageController {
    private UserDAO userDAO;

    //Define a const to represent when there is a value for an integer
    private final Integer PARSE_ERROR = -1;
    private final Integer INVALID_AGE = -2;

    private final Integer INVALID_PHONE_NUMBER = -3;

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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
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
                Integer age = userDAO.validateInteger(intInputValues[1], VALIDATION_TYPE_AGE);

                validatedIntInputValues = new Integer[] {phoneNumber, age};

                // Check if phoneNumber is not equal to any of the error codes
                if ((phoneNumber != PARSE_ERROR && phoneNumber != INVALID_PHONE_NUMBER)
                        && (age != PARSE_ERROR && age != INVALID_AGE)) {

                    System.out.print(textFieldValues[4]);
                    if (userDAO.isPasswordValid(textFieldValues[4]) && doPasswordsMatch(textFieldValues[4], textFieldValues[5])){
                        createUser();
                    }

                    // display error message if passwords dont match
                    else{
                        SigningInStatus.setText("The passwords you entered either do not match or your passwords do not" +
                                " have atleast: 1 upper case letter, 1 lowercase, 1 special character (i.e $,. ^),  1 number " +
                                "or are not 8 characters long");
                    }

                }

                // display error message if phone number or age dont satisfy validators
                else if (age == INVALID_AGE){
                    SigningInStatus.setText("You must be 18 years or older to use this site!");
                }

                else if (phoneNumber == INVALID_PHONE_NUMBER){
                    SigningInStatus.setText("Your phone number must be atleast 10 digits long! Do not include spaces, + or -");
                }

                else if (phoneNumber == PARSE_ERROR || age == PARSE_ERROR){
                    SigningInStatus.setText("Please ensure entries for age and phone number are fulfilled with only integers");
                }

            }

            //display error message if email input is incorrect
            else{
                SigningInStatus.setText("Email entered is invalid. Please make sure it has a @ in the input!");
            }

        }
        //dispaly error message if form isnt filled out properly
        else{
            SigningInStatus.setText("One or more input fields for either username, first name, last name, password or password " +
                    "confimation fields were not filled in correctly.");
        }
    }

    public void createUser(){
        GroupUpUser registeringUser = new GroupUpUser(textFieldValues[0], textFieldValues[1],
                textFieldValues[2], textFieldValues[3], validatedIntInputValues[0], validatedIntInputValues[1], textFieldValues[4]);
        userDAO.insert(registeringUser);
        SigningInStatus.setText("Sign Up Successful! Welcome to groupup!");
        return;
    }

    public boolean areBasicTextFieldsValid() {
        for (int counter = 0; counter < textFieldValues.length; counter++) {
            // Ensure fields are not empty
            if (textFieldValues[counter].length() == 0) {
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

    public boolean isEmailValid(String email){
        if (email.contains("@") && email.length() > 1){
            return true;
        }
        return false;
    }


}