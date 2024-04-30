package com.example.groupupcab302;

public class Event {

    private int eventID;
    private int eventCreatorUserID;
    private String name;
    private String date;
    private String time;
    private String location;
    private String genre;
    private int numberOfRegistrationsAvailable;
    private String description;
    private String image;

    // Datatype for attendees cant be a list because it isnt supported by SQL
    // Adding to a list must involve retrieving current value and conctatenating a string
    private String eventAttendees;

    // Default constructor
    // Should be called for instances where an event is created with values from controller
    public Event(GroupUpUser groupUpUser, String name, String date, String time, String location, String genre, int numberOfRegistrationsAvailable, String description, String image){
        eventCreatorUserID = groupUpUser.getUserID();
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.genre = genre;
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
        this.description = description;
        this.image = image;
    }

    // Added constructor for special cases where event can be expiticly be created with an ID
    // Should be used to create an event  when the data for an event is returned from the database
    public Event(int eventID,GroupUpUser groupUpUser, String name, String date, String time, String location, String genre, int numberOfRegistrationsAvailable, String description, String image){
        this.eventID = eventID;
        eventCreatorUserID = groupUpUser.getUserID();
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.genre = genre;
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
        this.description = description;
        this.image = image;
    }

    public int getEventID() {
        return eventID;
    }


    public int getEventCreatorUserID() {
        return eventCreatorUserID;
    }

    public String getEventAttendees(){
        return eventAttendees;
    }

    public void setEventAttendees(String userEmailToAdd){
        eventAttendees += eventAttendees + "," + userEmailToAdd;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumberOfRegistrationsAvailable() {
        return numberOfRegistrationsAvailable;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    // Setters
    public void setEventID(int ID){
        eventID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumberOfRegistrationsAvailable(int numberOfRegistrationsAvailable) {
        this.numberOfRegistrationsAvailable = numberOfRegistrationsAvailable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // To do add the stuff for retrieving all the details

}