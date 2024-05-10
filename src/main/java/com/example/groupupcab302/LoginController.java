package com.example.groupupcab302;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;
import javafx.util.Duration;
import org.controlsfx.control.*;

public class LoginController extends ParentViewController {

    private UserInformation userInformation = new UserInformation();
    public PasswordField passwordTextField;
    public TextField emailTextField;
    private GroupUpUser signedInUser;
    private UserDAO userDAO;
    private String[] textFieldValues;
    @FXML
    public static String pageID;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Identify the user who successfully logged in
    private GroupUpUser loggedInUser;
    public LoginController() {
        userDAO = new UserDAO();
    }

    @FXML
    protected void onLoginButton(ActionEvent event) throws IOException, SQLException {
        try{
            textFieldValues = new String[] {emailTextField.getText(), passwordTextField.getText()};
            handleUsersLogin(event);

        }
        catch (SQLException exception){
            displayNotification("Login Failure", "When trying to log you in there was an error!\n" + exception.getMessage(), true);
        }
    }

    public static void changeScene(Button button, String pageID) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(pageID));
        Scene scene = new Scene(fxmlLoader.load(),1280 , 720);
        stage.setScene(scene);
    }

    public void handleUsersLogin(ActionEvent event) throws SQLException{
        getUserDetails(event);
    }

    public void getUserDetails(ActionEvent event) throws SQLException {
        if (areTextFieldsValid()) {
            String inputtedEmail = emailTextField.getText();
            String inputtedPassword = passwordTextField.getText();
            if (areDetailsFoundInDB(inputtedEmail,inputtedPassword)){
                signedInUser = userDAO.getUserRecordByEmail(inputtedEmail);
                signedInUser.setIsLoggedIn(true);
                updateInformationOfLoggedInUser(inputtedEmail);
                redirectToEventDiscoveryPage(event);
            }
        }
    }

    private void updateInformationOfLoggedInUser(String userEmail) throws SQLException {
        try{
            GroupUpUser loggedInUser = userDAO.getUserRecordByEmail(userEmail);
            userInformation.setLoggedInUserInformation(loggedInUser);
        }

        catch (SQLException sqlException){
            displayNotification("Database error", "When trying to access your information in the database the following " +
                    "exception was produced!\n" + sqlException, true);
        }
    }
    public boolean areDetailsFoundInDB(String inputtedEmail, String inputtedPassword) throws SQLException {
        // User is found if not null
        if (userDAO.getUserRecordByEmail(inputtedEmail) != null){
            GroupUpUser userAttemptingToLoginAs = userDAO.getUserRecordByEmail(inputtedEmail);
            if (doesPasswordEqualUserInDB(userAttemptingToLoginAs, inputtedPassword)){
                displayNotification("Login Success", "Successfully Logged in! Welcome Back to GroupUp!", false);
                return true;
            }
        }
        displayNotification("Login Failure", ErrorConstants.INVALID_ACCOUNT_CREDENTIALS.getErrorDescription(), true);
        return false;
    }

    public boolean doesPasswordEqualUserInDB(GroupUpUser retrievedUserFromDB, String inputtedPassword){
        if (inputtedPassword.equals(retrievedUserFromDB.getPassword())){
            return true;
        }
        return false;
    }

    public boolean areTextFieldsValid() {
        for (String textFieldValue : textFieldValues) {
            if (textFieldValue.isEmpty()) {
                displayNotification("Login Failure",ErrorConstants.INVALID_USERINPUT.getErrorDescription(), true);
                return false;
            }
        }
        return true;
    }
}