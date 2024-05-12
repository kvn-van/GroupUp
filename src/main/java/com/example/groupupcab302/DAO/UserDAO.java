package com.example.groupupcab302.DAO;

import com.example.groupupcab302.Constants.ErrorConstants;
import com.example.groupupcab302.CustomSQLException;
import com.example.groupupcab302.DatabaseConnection;
import com.example.groupupcab302.Objects.GroupUpUser;
import com.example.groupupcab302.Interfaces.IDatabaseDAO;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Refactored concrete solution to the MockUserDAO
// As MockUserDAO methods passed unit test cases for a sample table, methods are kept slightly refactored

// Implement the database interface with the datatype of the class GroupUpUser as the parameter
// Allows overriding of interface methods and for specific operations on the GroupUpUser objects and database

public class UserDAO implements IDatabaseDAO<GroupUpUser> {
    private Connection connectionToDatabase;

    public UserDAO(){
        connectionToDatabase = DatabaseConnection.getInstance();
    }


    @Override
    public void update(GroupUpUser groupUpUser, String attributeToUpdate, String valueToSetAttributeTo){
        throw new UnsupportedOperationException("UserDAO does not support updating entries as there is no reset password functionality");
    }

    public void createTable() {
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS GroupUpUsers("
                            + "userID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userName VARCHAR NOT NULL, "
                            + "firstName VARCHAR NOT NULL, "
                            + "lastName VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL UNIQUE, "
                            + "phoneNumber STRING(10) NOT NULL, "
                            + "age STRING NOT NULL, "
                            + "password VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void insert(GroupUpUser groupUpUser) throws CustomSQLException {
        try {
            PreparedStatement insertUser = connectionToDatabase.prepareStatement(
                    "INSERT INTO GroupUpUsers (userName, firstName, lastName, email, phoneNumber, age, password) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            insertUser.setString(1, groupUpUser.getUserName());
            insertUser.setString(2, groupUpUser.getFirstName());
            insertUser.setString(3, groupUpUser.getLastName());
            insertUser.setString(4, groupUpUser.getEmail());
            insertUser.setString(5, groupUpUser.getPhoneNumber());
            insertUser.setString(6, groupUpUser.getAge());
            insertUser.setString(7, groupUpUser.getPassword());
            insertUser.execute();

        }

        catch (SQLException exception) {
            if (exception.getMessage().contains("SQLITE_CONSTRAINT_UNIQUE")){
                throw new CustomSQLException("The email you entered " +
                        "belongs to a registered user already. \nPlease consider logging in under this email instead");
            }

            else{
                throw new CustomSQLException("An error occurred while inserting your details into the database! " +
                        "Please try again,  confirm all details are correct and ensure a database is found in the directory of GroupUp!");
            }
        }

    }

    public GroupUpUser getUserRecordByEmail(String email) throws CustomSQLException, SQLException{
        ResultSet resultSet = null;
        try {
            PreparedStatement getAccount = connectionToDatabase.prepareStatement("SELECT * FROM GroupUpUsers WHERE email = ?");
            getAccount.setString(1, email);
            resultSet = getAccount.executeQuery();
            if (resultSet.next()) {
                return new GroupUpUser(
                        resultSet.getInt("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("age"),
                        resultSet.getString("password")
                );
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        finally{
            // Ensure data for result set is no longer fetched/opened in the database to prevent locking on future operations
            resultSet.close();
        }

        return null;
    }


    public void delete(int ID){
        try {
            PreparedStatement deleteUserAccount = connectionToDatabase.prepareStatement("DELETE FROM GroupUpUsers WHERE userID = ?");
            deleteUserAccount.setInt(1, ID);
            deleteUserAccount.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public String convertStringToInt(String valueToConvert){
        try{
            Integer.parseInt(valueToConvert);
            return valueToConvert;

        }
        catch (NumberFormatException error) {
            return ErrorConstants.INT_PARSE_ERROR.getErrorValue();
        }
    }

    // Check if the password supplied has atleast one capital and lowercase letter, number and special character
    public boolean isPasswordValid(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object to match the password against the pattern
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the pattern, indicating it meets all criteria
        return matcher.matches();
    }

    //Ensure data integrity of phone number before entry into database
    public String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return convertStringToInt(phoneNumber);
        }
        return ErrorConstants.INVALID_PHONE_NUMBER.getErrorValue();
    }

    //Ensure data integrity of age before entry into database
    public String validateAge(String Age) {
        if (convertStringToInt(Age) != ErrorConstants.INT_PARSE_ERROR.getErrorValue()) {
            //Already validated that age is safe to parse as int
            if (Integer.parseInt(Age) >= 18){
                return Age;
            }
            else{
                return ErrorConstants.INVALID_AGE.getErrorValue();
            }
        }
        return convertStringToInt(Age);
    }
}