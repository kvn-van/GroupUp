package com.example.groupupcab302.Interfaces;

import com.example.groupupcab302.misc.CustomSQLException;

// Declare the interface with a generic "T" parameter
// allows abstract functions to take varying datatypes as parameters depending on the database class extending the interface
public interface IDatabaseDAO<T> {

    // Allow all the function to throw customised SQL exceptions when required for personalised error messages during database operations
    // Interface does not extend functions like getRecordByID due to varying parameter requirements dependent on class

    void createTable() throws CustomSQLException;

    void insert(T databaseModelObject) throws CustomSQLException;

    void delete(int ID) throws CustomSQLException;

    public void update(T databaseModelobject, String attributeToUpdate, String valueToSetAttributeTo) throws CustomSQLException, UnsupportedOperationException;

}