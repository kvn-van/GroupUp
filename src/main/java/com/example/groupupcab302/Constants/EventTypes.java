package com.example.groupupcab302.Constants;

public enum EventTypes {

    OPEN_FOR_REGISTRATION("Open For Registration"),
    CLOSED("cancelled"),
    COMPlETED("completed");

    private String eventType;

    EventTypes(String eventType){
        this.eventType = eventType;
    }

    public String getEventType(){
        return eventType;
    }

}

