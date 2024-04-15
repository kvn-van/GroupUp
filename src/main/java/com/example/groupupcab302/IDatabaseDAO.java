package com.example.groupupcab302;

//Declare the interface with a generic "T" paramater
// allows abstract functions to take varying datatypes as paramaters depending on the database class extending the interface
public interface IDatabaseDAO<T> {

    public void createTable();

    //Databasem model object extends to events, users and bookings
    public void insert(T databaseModelObject);
}
