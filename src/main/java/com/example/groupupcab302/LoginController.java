package com.example.groupupcab302;

import com.example.groupupcab302.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
//import javafx.scene.control.*; // imports all scene control objects
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.io.IOException;

public class LoginController {

    private UserDAO userDAO;

    // Create data collection to hold value of all fields
    private String textFieldValues[];

    @FXML
    private Button signUpButton;

    @FXML
    private Button noAccountButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextArea LoginStatus;

    public void LoginPageController() {
        userDAO = new UserDAO();
    }

    @FXML
    protected void onSignUpButton() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Sign-Up-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onNoAccountButton() throws IOException {
        Stage stage = (Stage) noAccountButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Sign-Up-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onLoginButton() {
        // Initialise data collection variable using form values
        textFieldValues = new String[] {EmailTextField.getText(), PasswordTextField.getText()}
        handleUsersLogin();
    }

    public void handleUsersLogin() {
        if (isUserLoginValid) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Events-Page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            stage.setScene(scene);
        }
    }

    public boolean isUserLoginValid() {
        return (areTextFieldsValid() &&
                isEmailValid(textFieldValues[0]) &&
                isPasswordValid(textFieldValues[1]) &&);
    }

    public boolean isEmailValid(String email){
        if (email.contains("@") && email.length() > 1){
            return true;
        }
        //display error message if email input is incorrect
        LoginStatus.setText(ErrorConstants.INVALID_EMAIL.getErrorDescription());
        return false;
    }

    public boolean isPasswordValid(String password) {
        try {
            // Check password with password in db for provided email
            LoginStatus.setText("Successful! Welcome Back To GroupUp!");
            return true;
        }

        catch (CustomSQLException sqlException){
            LoginStatus.setText(sqlException.getMessage());
            return false;
        }
    }

    public boolean areTextFieldsValid() {
        for (int i = 0; i < textFieldValues.length; i++) {
            // Ensure fields are not empty
            if (textFieldValues[i].length() == 0) {
                //display error message if form is not filled out correctly
                LoginStatus.setText(ErrorConstants.INVALID_USERINPUT.getErrorDescription());
                return false; // Return false if any string is invalid
            }
        }
        return true; // Return true if all strings are valid
    }
}