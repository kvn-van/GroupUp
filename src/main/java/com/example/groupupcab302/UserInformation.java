package com.example.groupupcab302;

// Java doesnt support static classes, hence class will just have to be constantly instantiated
// Since fields are static all calsses point to the same values in fields
public class UserInformation {
    private static GroupUpUser loggedInUserInformation;
    private static Event eventSelectedByUser;


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
}
