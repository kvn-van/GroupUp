package com.example.groupupcab302;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Implement the database interface with the datatype of the class as the parameter
// Allows overriding of interface methods and for specific operations on the user objects and database
public class UserDAO implements IDatabaseDAO<GroupUpUser>{
    private Connection connectionToDatabase;
    private final Integer PARSE_ERROR = -1;
    private final Integer INVALID_AGE = -2;

    private final Integer INVALID_PHONE_NUMBER = -3;

    private final String VALIDATION_TYPE_PHONE_NUMBER = "Phone Number";
    private final String VALIDATION_TYPE_AGE = "Age";

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
                            + "password VARCHAR NOT NULL"
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
                    "INSERT INTO GroupUpUsers (userName, firstName, lastName, email, phoneNumber, age, password) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            insertUser.setString(1, groupUpUser.getUserName());
            insertUser.setString(2, groupUpUser.getFirstName());
            insertUser.setString(3, groupUpUser.getLastName());
            insertUser.setString(4, groupUpUser.getEmail());
            insertUser.setInt(5, groupUpUser.getPhoneNumber());
            insertUser.setInt(6, groupUpUser.getAge());
            insertUser.setString(7, groupUpUser.getPassword());
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


    public int convertStringToInt(String valuesToConvert){
        try{
            return Integer.parseInt(valuesToConvert);

        }
        catch (NumberFormatException e) {
            return PARSE_ERROR;
        }
    }

    //Ensure data integrity before entry into database
    public Integer validateInteger(String valueToValidate, String conditionToCheck) {
        switch (conditionToCheck) {
            case (VALIDATION_TYPE_PHONE_NUMBER):
                if (valueToValidate.length() == 10) {
                    return convertStringToInt(valueToValidate);
                }
                return INVALID_PHONE_NUMBER;

            case (VALIDATION_TYPE_AGE):
                if (convertStringToInt(valueToValidate) != PARSE_ERROR) {
                    //Safe to parse the string as an int
                    Integer valueAsInt = convertStringToInt(valueToValidate);
                    if (valueAsInt >= 18) {
                        return valueAsInt;
                    }
                    return INVALID_AGE;

                }
                return PARSE_ERROR;
        }
        return PARSE_ERROR;
    }

    // Check if the password has atleast one capital letter, one lowercase, number and special character as per user story
    public boolean isPasswordValid(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object to match the password against the pattern
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, indicating it meets all criteria
        return matcher.matches();
    }


}
