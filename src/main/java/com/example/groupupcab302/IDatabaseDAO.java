package com.example.groupupcab302;

// Declare the interface with a generic "T" paramater
// allows abstract functions to take varying datatypes as paramaters depending on the database class extending the interface
public interface IDatabaseDAO<T> {

    // Allow all the function to throw customised SQL exceptions when required for personalised error messages during database operations
    // Interface does not extend functions like getRecordByID due to varying paramater requirements dependent on class

    public void createTable() throws CustomSQLException;

    public void insert(T databaseModelObject) throws CustomSQLException;

    public void delete(int ID) throws CustomSQLException;

}
