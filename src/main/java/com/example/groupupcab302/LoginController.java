package com.example.groupupcab302;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

public class LoginController {

    private UserInformation userInformation = new UserInformation();
    public PasswordField passwordTextField;
    public TextField emailTextField;
    public TextArea loginStatus;
    private GroupUpUser signedInUser;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Identify the user who successfully logged in
    private GroupUpUser loggedInUser;


    public LoginController() {
        userDAO = new UserDAO();
    }

    @FXML
    protected void onNoAccountClick(ActionEvent event) throws IOException {
        //Basic code to switch the scene to an appropriate scene
        Parent root = FXMLLoader.load(getClass().getResource("Sign-Up-Page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    protected void onLoginButton() throws IOException, SQLException {
        try{
            textFieldValues = new String[] {emailTextField.getText(), passwordTextField.getText()};
            handleUsersLogin();

        }
        catch (SQLException exception){
            loginStatus.setText(exception.getMessage());
        }
    }

    public static void changeScene(Button button, String pageID) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(pageID));
        Scene scene = new Scene(fxmlLoader.load(),1280 , 720);
        stage.setScene(scene);
    }

    public void handleUsersLogin() throws SQLException{
        getUserDetails();
    }

    public void getUserDetails() throws SQLException {
        try {
            if (areTextFieldsValid()) {
                String inputtedEmail = emailTextField.getText();
                String inputtedPassword = passwordTextField.getText();
                if (areDetailsFoundInDB(inputtedEmail,inputtedPassword)){
                    signedInUser = userDAO.getUserRecordByEmail(inputtedEmail);
                    signedInUser.setIsLoggedIn(true);
                    updateInformationOfLoggedInUser(inputtedEmail);
                    changeScene(loginButton, "event-view.fxml");
                }
            }

        }

        catch (IOException ioException) {
            loginStatus.setText("Failed to load the events page." + ioException);
        }
    }

    private void updateInformationOfLoggedInUser(String userEmail) throws SQLException {
        try{
            GroupUpUser loggedInUser = userDAO.getUserRecordByEmail(userEmail);
            userInformation.setLoggedInUserInformation(loggedInUser);
        }

        catch (SQLException sqlException){
            loginStatus.setText("When trying to access your information in the database the following exception was produced!" + sqlException);
        }
    }
    public boolean areDetailsFoundInDB(String inputtedEmail, String inputtedPassword) throws SQLException {
        // User is found if not null
        if (userDAO.getUserRecordByEmail(inputtedEmail) != null){
            GroupUpUser userAttemptingToLoginAs = userDAO.getUserRecordByEmail(inputtedEmail);
            if (doesPasswordEqualUserInDB(userAttemptingToLoginAs, inputtedPassword)){
                loginStatus.setText("Successfully Logged in! Welcome back to GroupUp!");
                return true;
            }

        }
        loginStatus.setText("Either the email entered was invalid or the password was incorrect! Please try again");
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
                loginStatus.setText("Please fill out all fields.");
                return false;
            }
        }
        return true;
    }
}