package com.example.groupupcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class SignUpPageController {
    private UserDAO userDAO;

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
    private TextField GenderTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField ConfirmationPasswordTextField;

    public SignUpPageController(){
        userDAO = new UserDAO();
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onSignInButtonClick(){
        String email = isEmailValid(EmailTextField.getText()) ? EmailTextField.getText() : null;
        GroupUpUser registeringUser = new GroupUpUser(UserNameTextField.getText(), FirstNameTextField.getText(),
                LastNameTextField.getText(), email, Integer.parseInt(PhoneNumberTextField.getText()),
                Integer.parseInt(AgeTextField.getText()), PasswordTextField.getText(), GenderTextField.getText());
        userDAO.insert(registeringUser);
    }


    public boolean isEmailValid(String email){
        System.out.println(email);
        if (email.contains("@") && email.length() > 1){
            return true;
        }
        return false;
    }

    public boolean isInputValid(String valueToCheck){
        if (valueToCheck ==  null){
            return false;
        }
        return true;
    }

    public void displayErrorMessage(){

    }


    @FXML
    protected void onTyping(){

    }
}