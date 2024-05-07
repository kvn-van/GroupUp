package com.example.groupupcab302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        UserDAO userDAO = new UserDAO();
        EventDAO eventDAO = new EventDAO();
        userDAO.createTable();
        eventDAO.createTable();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Start.fxml")); //"Log-In-Page.fxml"
        Scene scene = new Scene(fxmlLoader.load(),1280 , 720);
        stage.setTitle("GroupUp");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}