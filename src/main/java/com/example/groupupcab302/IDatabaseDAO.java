package com.example.groupupcab302;

// Declare the interface with a generic "T" paramater
// allows abstract functions to take varying datatypes as paramaters depending on the database class extending the interface
public interface IDatabaseDAO<T> {

    // Allow all the function to throw customised SQL exceptions when required for personalised error messages during database operations

    public void createTable() throws CustomSQLException;

    public void insert(T databaseModelObject) throws CustomSQLException;

    public void delete(int ID) throws CustomSQLException;

    /*
    // datatype of the function to be returned depends on the class extending the interface, as a result its defined "generic"
    // return type can be either of an event or user type
    public T getRecordByID(int ID) throws CustomSQLException; */
}
