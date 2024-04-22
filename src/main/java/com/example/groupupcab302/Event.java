package com.example.groupupcab302;

import java.time.LocalDate;

public class Event {

    private int eventID;

    private int eventIDCounter;
    private int eventCreatorUserID;
    private String name;
    private LocalDate date;
    private int time;
    private String location;
    private String genre;
    private int numberOfRegistrationsAvailable;
    private String description;
    private int image;

    public Event(int groupUpUser, String name, LocalDate date, int time, String location, String genre, int numberOfRegistrationsAvailable, String description, int image){
        eventIDCounter += 1;
        this.eventID = eventIDCounter;
        eventCreatorUserID = groupUpUser;
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

    public String getName() {
        return name;
    }

    public LocalDate getDate() {return date; }

    public int getTime() {
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

    public int getImage() {
        return image;
    }

    // Setters
    public void setEventID(int ID){
        eventID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {this.date = date; }

    public void setTime(int time) {
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

    public void setImage(int image) {
        this.image = image;
    }

    // To do add the stuff for retrieving all the details
    public String getAllEventDetails() {

        return ("eventName: " + name + ", date: " + date + ", time: "
                + time + ", location: " + location + ", genre: " + genre + ", numberOfRegistrationsAvailable: " + numberOfRegistrationsAvailable + ", description: " +
                description);
    }
}

