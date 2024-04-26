package com.example.groupupcab302.mockDAO;

import com.example.groupupcab302.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Create a mock class similiar to the end solution
public class MockUserDAO {
    private Connection connectionToDatabase;
    public MockUserDAO(){
        connectionToDatabase = DatabaseConnection.getInstance();
    }


    public void update() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("UserDAO does not support updating entries as there is no reset password functionality");
    }

    // Define a special method only available for  mock DAO during STS
    // Allows table to be deleted after STS unit case run in preparation for next case/re-run
    public void deleteTable(){
        try{
            PreparedStatement deleteTableStatement = connectionToDatabase.prepareStatement("DROP TABLE IF EXISTS MockGroupUpUsers");
            deleteTableStatement.execute();
        }

        catch (SQLException sqlException){
            System.out.println(sqlException);
        }

    }

    public void createTable(){
        try {
            Statement createTable = connectionToDatabase.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS MockGroupUpUsers("
                            + "userID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userName VARCHAR NOT NULL, "
                            + "firstName VARCHAR NOT NULL, "
                            + "lastName VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL UNIQUE, "
                            + "phoneNumber INT(10) NOT NULL, "
                            + "age INT NOT NULL, "
                            + "password VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }


    public void insert(GroupUpUser groupUpUser){
        try {
            PreparedStatement insertUser = connectionToDatabase.prepareStatement(
                    "INSERT INTO MockGroupUpUsers (userName, firstName, lastName, email, phoneNumber, age, password) VALUES (?, ?, ?, ?, ?, ?, ?)"
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
            System.out.println(exception);
        }

    }

    public GroupUpUser getRecordByEmail(String email) throws SQLException {
        ResultSet resultSet = null;
        try {
            PreparedStatement getAccount = connectionToDatabase.prepareStatement("SELECT * FROM MockGroupUpUsers WHERE email = ?");
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
        }

        catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }

        finally{
            resultSet.close();
        }

        return null;
    }


    public void delete(int ID){
        try {
            PreparedStatement deleteUserAccount = connectionToDatabase.prepareStatement("DELETE FROM MockGroupUpUsers WHERE userID = ?");
            deleteUserAccount.setInt(1, ID);
            deleteUserAccount.execute();

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx);
        }
    }
    // TODO: implement the code to retrieve the last record to change the ID counter of the user via select *from getLastRecord ORDER BY id DESC LIMIT 1 or think about re-designing
    // may just be better to add the auto increment and leave the ID honestly and just have the user be created automatically tbh



}