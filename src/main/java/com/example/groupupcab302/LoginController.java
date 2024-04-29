package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class LoginController {

    public PasswordField passwordTextField;
    public TextField emailTextField;
    public TextArea loginStatus;

    private UserDAO userDAO;
    private String[] textFieldValues;

    @FXML
    private Button signUpNav;
    @FXML
    private Button noAccountButton;
    @FXML
    private Button loginButton;
    @FXML
    public static String pageID;

    public LoginController() {
        userDAO = new UserDAO();
    }

    @FXML
    protected void onSignUpNavClick() throws IOException {
        pageID = "Sign-Up-Page.fxml";
        changeScene(signUpNav, pageID);
    }

    @FXML
    protected void onNoAccountButton() throws IOException {
        changeScene(noAccountButton, pageID);
    }

    @FXML
    protected void onLoginButton() throws IOException {
        textFieldValues = new String[] {emailTextField.getText(), passwordTextField.getText()};
        handleUsersLogin();
        changeScene(loginButton, pageID);
    }

    public static void changeScene(Button button, String pageID) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(pageID));
        Scene scene = new Scene(fxmlLoader.load(),1280 , 720);
        stage.setScene(scene);
    }

    public void handleUsersLogin() {
        if (isUserLoginValid()) {
            try {
                changeScene(loginButton, "event-view-template.fxml");
            } catch (IOException e) {
                loginStatus.setText("Failed to load the events page.");
            }
        }
    }

    public boolean isUserLoginValid() {
        return areTextFieldsValid() && isEmailValid(emailTextField.getText()) && isPasswordValid(passwordTextField.getText());
    }

    public boolean isEmailValid(String email) {
        if (email.contains("@") && email.length() > 1) {
            return true;
        }
        loginStatus.setText("Invalid email format.");
        return false;
    }

    public boolean isPasswordValid(String password) {
        // Assuming UserDAO provides a method to check password validity
        if (userDAO.isPasswordValid(password)) {
            loginStatus.setText("Successful! Welcome back to GroupUp!");
            return true;
        } else {
            loginStatus.setText("Invalid password.");
            return false;
        }
    }

    public boolean areTextFieldsValid() {
        for (String textFieldValue : textFieldValues) {
            if (textFieldValue.isEmpty()) {
                loginStatus.setText("Please fill out all fields.");
                return false;
            }
        }
        return true;
    }
}