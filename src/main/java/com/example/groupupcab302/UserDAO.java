package com.example.groupupcab302;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implement the database interface with the datatype of the class as the parameter
// Allows overriding of interface methods and for specific operations on the user objects and database
public class UserDAO implements IDatabaseDAO<GroupUpUser>{
    private Connection connectionToDatabase;

    public UserDAO(){
        connectionToDatabase = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS GroupUpUsers ("
                            + "userID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userName VARCHAR UNIQUE NOT NULL, "
                            + "firstName VARCHAR NOT NULL, "
                            + "lastName VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL UNIQUE, "
                            + "phoneNumber INT(20) NOT NULL UNIQUE, "
                            + "age INT NOT NULL, "
                            + "password VARCHAR NOT NULL, "
                            + "gender VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void insert(GroupUpUser groupUpUser) {
        try {
            PreparedStatement insertUser = connectionToDatabase.prepareStatement(
                    "INSERT INTO GroupUpUsers (userName, firstName, lastName, email, phoneNumber, age, password, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            insertUser.setString(1, groupUpUser.getUserName());
            insertUser.setString(2, groupUpUser.getFirstName());
            insertUser.setString(3, groupUpUser.getLastName());
            insertUser.setString(4, groupUpUser.getEmail());
            insertUser.setInt(5, groupUpUser.getPhoneNumber());
            insertUser.setInt(6, groupUpUser.getAge());
            insertUser.setString(7, groupUpUser.getPassword());
            insertUser.setString(8, groupUpUser.getGender());

            insertUser.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GroupUpUser> getAll() {
        List<GroupUpUser> accounts = new ArrayList<>();
        // Todo Later: Create a Statement to run the SELECT * query
        // and populate the accounts list above
        return accounts;
    }

}
