package com.example.groupupcab302;

// Java doesnt support static classes, hence class will just have to be constantly instantiated
// Since fields are static all calsses point to the same values in fields
public class UserInformation {
    private static GroupUpUser loggedInUserInformation;
    private static Event eventSelectedByUser;

    // Manage condition based on the screen user is on to identify if they want to edit their created events
    // Since events cards only have 1 behaviour when being clicked, this attribute allows another condition \
    // If true, any event when clicked will redirect user to screen where event created/selected by user can be updated
    private static boolean doesUserWantToEditTheirEvents;


    public void setLoggedInUserInformation(GroupUpUser groupUpUser){
        if (groupUpUser instanceof GroupUpUser){
            loggedInUserInformation = groupUpUser;
        }

        else{
            System.out.println("There was an error when trying to get the information of the logged in users");
        }

    }

    public GroupUpUser getLoggedInUserInformation(){
        return loggedInUserInformation;
    }

    public void setEventSelectedByUser(Event eventSelected){
        if (eventSelected instanceof Event){
            eventSelectedByUser = eventSelected;
        }
        else{
            System.out.println("The event supplied for setting was not of the type event. Please check the code!");
        }
    }

    public Event getEventSelectedByUser(){
        return eventSelectedByUser;
    }

    public boolean getDoesUserWantToEditTheirEvents(){
        return doesUserWantToEditTheirEvents;
    }

    public void setDoesUserWantToEditTheirEvents(boolean condition){
        doesUserWantToEditTheirEvents = condition;
    }
}
