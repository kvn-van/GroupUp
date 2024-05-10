package com.example.groupupcab302;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implement the database interface with the datatype of the class event as the parameter
// Allows overriding of interface methods and for specific operations on the user objects and database
// Defines the generic for the interface to be event objects
public class EventDAO implements IDatabaseDAO<Event>{
    private Connection connectionToDatabase;
    public EventDAO(){
        connectionToDatabase = DatabaseConnection.getInstance();
    }

    @Override
    public void createTable(){
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS GroupUpEvents ("
                            + "eventID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR NOT NULL, "
                            + "date VARCHAR NOT NULL, "
                            + "time VARCHAR NOT NULL, "
                            + "location VARCHAR NOT NULL, "
                            + "genre VARCHAR NOT NULL, "
                            + "numberOfRegistrationsAvailable VARCHAR NOT NULL, "
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
                    "SELECT * FROM GroupUpEvents WHERE eventID = ?"
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
                        resultSet.getString("numberOfRegistrationsAvailable"),
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

    @Override
    public void insert(Event event) throws CustomSQLException{
        try {
            PreparedStatement insertEvent = connectionToDatabase.prepareStatement(
                    "INSERT INTO GroupUpEvents (name, date, time, location, genre, numberOfRegistrationsAvailable, descriptionOfEvent, image, eventAttendees, userIDOfEventCreator) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            insertEvent.setString(1, event.getName());
            insertEvent.setString(2, event.getDate());
            insertEvent.setString(3, event.getTime());
            insertEvent.setString(4, event.getLocation());
            insertEvent.setString(5, event.getGenre());
            insertEvent.setString(6, event.getNumberOfRegistrationsAvailable());
            insertEvent.setString(7, event.getDescription());
            insertEvent.setString(8, event.getImage());
            insertEvent.setString(9, null);
            insertEvent.setInt(10, event.getEventCreatorUserID());

            insertEvent.execute();
        }

        catch(SQLException sqlException){
            System.out.println(sqlException);
            System.out.println("Error found please see above ^");
            System.out.println("Any errors here should be created as classes for the custom SQLException and given a meaningful message.");
        }
    }

    // Should only be used in cases where the id of an event is known
    @Override
    public void delete(int eventID) throws CustomSQLException{
        try{
            PreparedStatement deleteEvent = connectionToDatabase.prepareStatement("DELETE FROM GroupUpEvents WHERE eventID = ?");
            deleteEvent.setInt(1, eventID);
            deleteEvent.execute();
        }

        catch(SQLException sqlException){
            throw new CustomSQLException("Please enter a reason/give a meaningful message to the user on the error for why the db couldnt be deleted");
        }

    }

    // update not defined in interface as not all child tables require
    public void update(Event event, String attributeOfEventToUpdate, String valueToSetAttributeTo) throws CustomSQLException{
        try {
            String query = "UPDATE GroupUpEvents SET " + attributeOfEventToUpdate + " = ? WHERE eventID = ?";
            PreparedStatement updateToRowStatement = connectionToDatabase.prepareStatement(query);
            updateToRowStatement.setString(1, valueToSetAttributeTo);
            updateToRowStatement.setInt(2, event.getEventID());
            updateToRowStatement.execute();
        } catch (SQLException sqlException) {
            //keep for trouble shooting
            System.out.println(sqlException);
            throw new CustomSQLException("There was an error when trying to update the event!");
        }

    }

    public List<Event> getAllEvents() throws SQLException{
        String sqlQuery = "SELECT * FROM GroupUpEvents";
        PreparedStatement preparedStatement = connectionToDatabase.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        Event eventFromDB;

        List<Event> eventsList =  new ArrayList<Event>();

        while (resultSet.next()){
            eventFromDB =  new Event(resultSet.getInt("eventID"), resultSet.getInt("userIDofEventCreator"),
                    resultSet.getString("name"), resultSet.getString("date"), resultSet.getString("time"),
                    resultSet.getString("location"), resultSet.getString("genre"),
                    resultSet.getString("numberOfRegistrationsAvailable"), resultSet.getString("descriptionOfEvent"),
                    resultSet.getString("image"), resultSet.getString("eventAttendees"));

            eventsList.add(eventFromDB);
        }

        return eventsList;
    }
}