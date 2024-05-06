package com.example.groupupcab302.mockDAO;

import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.Event;
import javafx.beans.value.ObservableBooleanValue;

import java.sql.*;

public class MockEventDAO {
    private Connection connectionToDatabase;

    public MockEventDAO () {connectionToDatabase = DatabaseConnection.getInstance();}

    public void deleteTable(){

        // Define a special method only available for  mock DAO during STS
        // Allows table to be deleted after STS unit case run in preparation for next case/re-run
        try {
            PreparedStatement deleteTableStatement = connectionToDatabase.prepareStatement("DROP TABLE IF EXISTS MockGroupUpEvents");
            deleteTableStatement.execute();
        }

        catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void createTable(){
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS MockGroupUpEvents ("
                            + "eventID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR NOT NULL, "
                            + "date VARCHAR NOT NULL, "
                            + "time VARCHAR NOT NULL, "
                            + "location VARCHAR NOT NULL, "
                            + "genre VARCHAR NOT NULL, "
                            + "numberOfRegistrationsAvailable INT NOT NULL, "
                            + "descriptionOfEvent VARCHAR NOT NULL, "
                            + "image STRING NOT NULL, "
                            + "eventAttendees STRING NULL, "
                            + "userIDOfEventCreator INT NOT NULL, "
                            + "FOREIGN KEY (userIDOfEventCreator) REFERENCES GroupUpUsers(userID)"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }

    public Event getEventById(int eventID) throws SQLException {
        Event event = null;
        try{
            PreparedStatement selectEventStatement = connectionToDatabase.prepareStatement(
                    "SELECT * FROM MockGroupUpEvents WHERE eventID = ?"
            );

            selectEventStatement.setInt(1,eventID);
            ResultSet resultSet = selectEventStatement.executeQuery();

            if (resultSet.next()) {
                event = new Event(
                        resultSet.getInt("eventID"),
                        resultSet.getInt("userIDOfEventCreator"),
                        resultSet.getString("name"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("location"),
                        resultSet.getString("genre"),
                        resultSet.getInt("numberOfRegistrationsAvailable"),
                        resultSet.getString("descriptionOfEvent"),
                        resultSet.getString("image"),
                        resultSet.getString("eventAttendees")
                );
            }
            // Close result set to prevent database locking
            resultSet.close();
        }

        catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        }

        return event;
    }
    public ObservableBooleanValue insert (Event event){
        try {
            PreparedStatement insertEvent = connectionToDatabase.prepareStatement(
                    "INSERT INTO MockGroupUpEvents (name, date, time, location, genre, numberOfRegistrationsAvailable, descriptionOfEvent, image, eventAttendees, userIDOfEventCreator) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            insertEvent.setString(1, event.getName());
            insertEvent.setString(2, event.getDate());
            insertEvent.setString(3, event.getTime());
            insertEvent.setString(4, event.getLocation());
            insertEvent.setString(5, event.getGenre());
            insertEvent.setInt(6, event.getNumberOfRegistrationsAvailable());
            insertEvent.setString(7, event.getDescription());
            insertEvent.setString(8, event.getImage());
            insertEvent.setString(9, event.getEventAttendees());
            insertEvent.setInt(10, event.getEventCreatorUserID());

            insertEvent.execute();
        }

        catch (SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }
}