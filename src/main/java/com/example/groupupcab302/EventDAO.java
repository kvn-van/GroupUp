package com.example.groupupcab302;

import java.sql.*;


// Implement the database interface with the datatype of the class event as the parameter
// Allows overriding of interface methods and for specific operations on the user objects and database
// Defines the generic for the interface to be event objects
public class EventDAO implements IDatabaseDAO<Event>{

    //TODO: database diagram to accomodate list of guests

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
                            + "date DATETIME NOT NULL, "
                            + "location VARCHAR NOT NULL, "
                            + "genre VARCHAR NOT NULL, "
                            + "numberOfRegistrationsAvailable INT NOT NULL, "
                            + "descriptionOfEvent VARCHAR NOT NULL, "
                            + "image VARBINARY NOT NULL, "
                            // need clarification + "listOfAttendees STRING UNIQUE NULL, "
                            + "customerEventCreationID INT NOT NULL UNIQUE, "
                            + "FOREIGN KEY (customerEventCreationID) REFERENCES GroupUpUsers(userID)"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }

    public String getEventNameById(int eventID) throws SQLException {
        String eventName = null;
        String sql = "SELECT name FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventName = rs.getString("name");
        }
        rs.close();
        stmt.close();
        return eventName;
    }

    public String getLocationById(int eventID) throws SQLException {
        String eventLocation = null;
        String sql = "SELECT location FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventLocation = rs.getString("location");
        }
        rs.close();
        stmt.close();
        return eventLocation;
    }

    public String getDateById(int eventID) throws SQLException {
        String eventDate= null;
        String sql = "SELECT date FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventDate= rs.getString("date");
        }
        rs.close();
        stmt.close();
        return eventDate;
    }

    public String getGenreById(int eventID) throws SQLException {
        String eventGenre= null;
        String sql = "SELECT genre FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventGenre = rs.getString("genre");
        }
        rs.close();
        stmt.close();
        return eventGenre;
    }

    public String getRegNumberById(int eventID) throws SQLException {
        String eventRegNum= null;
        String sql = "SELECT numberOfRegistrationsAvailable FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventRegNum = rs.getString("numberOfRegistrationsAvailable");
        }
        rs.close();
        stmt.close();
        return eventRegNum;
    }

    public String getDescriptionById(int eventID) throws SQLException {
        String eventDescription= null;
        String sql = "SELECT descriptionOfEvent FROM GroupUpEvents WHERE eventID = ?";
        PreparedStatement stmt = connectionToDatabase.prepareStatement(sql);
        stmt.setInt(1, eventID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            eventDescription = rs.getString("descriptionOfEvent");
        }
        rs.close();
        stmt.close();
        return eventDescription;
    }
    @Override
    public void insert(Event event) throws CustomSQLException{
        try {
            PreparedStatement insertEvent = connectionToDatabase.prepareStatement("INSERT INTO GroupUpEvents (eventCreatorUserID, name, " +
                    "date, location, genre, numberOfRegistrationsAvailable, descriptionOfEvent, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            insertEvent.setInt(1, event.getEventCreatorUserID());
            insertEvent.setString(2, event.getName());
            insertEvent.setDate(3, Date.valueOf(event.getDate()));
            insertEvent.setString(4, event.getLocation());
            insertEvent.setString(5, event.getGenre());
            insertEvent.setInt(6, event.getNumberOfRegistrationsAvailable());
            insertEvent.setString(7, event.getDescription());
            insertEvent.setString(8, event.getImage());

            insertEvent.execute();
        }
        catch(SQLException sqlException){
            System.out.println(sqlException);
            System.out.println("Error found please see above ^");
            System.out.println("Any errors here should be created as classes for the custom SQLException and given a meaningful message.");
        }
    }

    @Override
    public void delete(int eventID) throws CustomSQLException{
        try{
            PreparedStatement deleteEvent = connectionToDatabase.prepareStatement("DELETE * FROM GroupUpEvents WHERE eventID = ?");
            deleteEvent.setInt(1, eventID);
        }

        catch(SQLException sqlException){
            throw new CustomSQLException("Please enter a reason/give a meaningful message to the user on the error for why the db couldn't be deleted");
        }

    }

    // update not defined in interface as not all child tables require
    public void update(Event event, String attributeOfEventToUpdate, String valueToSetAttributeTo) throws CustomSQLException{
        try{
            PreparedStatement updateToRowStatement = connectionToDatabase.prepareStatement("UPDATE GroupUpEvents SET ? = ? WHERE eventID = ?");
            updateToRowStatement.setString(1, attributeOfEventToUpdate);
            updateToRowStatement.setString(2, valueToSetAttributeTo);
            updateToRowStatement.setInt(3,event.getEventID());

        }

        catch (SQLException sqlException){
            throw new CustomSQLException("Enter a detailed message here for reason of error and what user did wrong");
        }
    }
}
