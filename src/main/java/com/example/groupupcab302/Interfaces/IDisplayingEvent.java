package com.example.groupupcab302.Interfaces;

// Define an interface to distribute a common function required to show/display events from the DB
// Allows child classes to provide a modified/overriden implemenetation
public interface IDisplayingEvent {

    void showEventsFromDB();

}
