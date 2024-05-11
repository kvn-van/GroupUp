import com.example.groupupcab302.misc.DatabaseConnection;
import com.example.groupupcab302.Objects.GroupUpUser;
import com.example.groupupcab302.mockDAO.MockUserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

// Development of unit test cases for user DAO
// Unit test ensure relevant CRUD operations and table creation work before complete implementation
public class MockUserDAOSTS {
    private Connection connectionToDatabase;

    // Construct group up user with fixed ID to prevent complications with ID changing due to varying test cases
    final GroupUpUser groupUpUser  =  new GroupUpUser(1,"FREESHEFFG", "Sheff", "G", "FlowsPart2@gmail.com", "1234567891", "18", "freeSHEFFGAND8THBLOCK");
    private MockUserDAO mockUserDAO;
    @BeforeEach
    void setUp(){
        connectionToDatabase = DatabaseConnection.getInstance();
        mockUserDAO = new MockUserDAO();
    }

    @Test
    public void testCreateTable(){
        assertDoesNotThrow(() -> {
            mockUserDAO.createTable();
        }, "Table creation should not throw an exception");
        try{
            DatabaseMetaData dbm = connectionToDatabase.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "MockGroupUpUsers", null);
            if (tables.next()){
                //if tables.next is true then MockGroupUpUsers exists!
                // STS test case passes

                // Close the result set as it keeps a lock on the database resources accessed (MockGroupUpUsers table)
                // This prevents CRUD operations
                tables.close();

            }
            else{
                fail("MockGroupUpUsers was not successfully created!");
            }
        }

        catch (SQLException sqlException){
            fail("SQL Error recieved upon trying to validate the metadata and existance of MockGroupUpUsers table");
        }

        finally{
            // Delete the table in preparation for next unit test case
            mockUserDAO.deleteTable();
        }

    }

    @Test
    public void testDelete() throws SQLException {
        try{
            mockUserDAO.createTable();
            mockUserDAO.insert(groupUpUser);
            assertDoesNotThrow(() -> {mockUserDAO.delete(groupUpUser.getUserID());},
                    "Error occured when trying to delete record");

            GroupUpUser queriedUser = mockUserDAO.getRecordByEmail(groupUpUser.getEmail());
            if (queriedUser != null){
                fail("Object with the corresponding email was returned from the database instead of being deleted");
            }
        }

        finally{
                //Delete table in preparation for next unit case
                mockUserDAO.deleteTable();
        }
    }

    @Test
    public void testInsert() throws SQLException {
        try {
            // Initialize test case by creating table
            // No need to validate create table method as it passed unit case already
            mockUserDAO.createTable();

            GroupUpUser dbQueriedGroupUpUser = null;

            // Check for errors/exceptions when inserting data into table
            assertDoesNotThrow(() -> {
                mockUserDAO.insert(groupUpUser);
            }, "There was an error when attempting to insert the record into the database!");

            PreparedStatement getUserRecord = connectionToDatabase.prepareStatement("SELECT * FROM MockGroupUpUsers WHERE email = ?");
            getUserRecord.setString(1, groupUpUser.getEmail());
            ResultSet resultSet = getUserRecord.executeQuery();
            while (resultSet.next()) {
                dbQueriedGroupUpUser = new GroupUpUser(resultSet.getInt("userID"),
                        resultSet.getString("userName"), resultSet.getString("firstName"),
                        resultSet.getString("lastName"), resultSet.getString("email"),
                        resultSet.getString("phoneNumber"), resultSet.getString("age"),
                        resultSet.getString("password"));
            }

            assertEquals(groupUpUser.getUserID(), dbQueriedGroupUpUser.getUserID());
            assertEquals(groupUpUser.getUserName(), dbQueriedGroupUpUser.getUserName());
            assertEquals(groupUpUser.getFirstName(), dbQueriedGroupUpUser.getFirstName());
            assertEquals(groupUpUser.getLastName(), dbQueriedGroupUpUser.getLastName());
            assertEquals(groupUpUser.getEmail(), dbQueriedGroupUpUser.getEmail());
            assertEquals(groupUpUser.getPhoneNumber(), dbQueriedGroupUpUser.getPhoneNumber());
            assertEquals(groupUpUser.getAge(), dbQueriedGroupUpUser.getAge());
            assertEquals(groupUpUser.getPassword(), dbQueriedGroupUpUser.getPassword());
        }

        finally{
            //Delete table in preparation for next unit case
            mockUserDAO.deleteTable();
        }

    }


    @Test
    public void testGetRecordByEmail(){
        try {
            mockUserDAO.createTable();
            mockUserDAO.insert(groupUpUser);

            // Values cant be assigned to variables inside lambda function due to scope unless defined as final
            // Defining datatype to be array is sufficient as they are mutable objects
            GroupUpUser[] dbQueriedGroupUpUser = {null};
            assertDoesNotThrow(() -> {
                dbQueriedGroupUpUser[0] = mockUserDAO.getRecordByEmail("FlowsPart2@gmail.com");
            }, "An error was thrown when attempting to query the database");

            if (dbQueriedGroupUpUser[0] != null) {
                // Must assert by the values of the individuals fields
                // Java objects are never stored in the same memory stack location leading to unit case failiure
                assertEquals(groupUpUser.getUserID(), dbQueriedGroupUpUser[0].getUserID());
                assertEquals(groupUpUser.getUserName(), dbQueriedGroupUpUser[0].getUserName());
                assertEquals(groupUpUser.getFirstName(), dbQueriedGroupUpUser[0].getFirstName());
                assertEquals(groupUpUser.getLastName(), dbQueriedGroupUpUser[0].getLastName());
                assertEquals(groupUpUser.getEmail(), dbQueriedGroupUpUser[0].getEmail());
                assertEquals(groupUpUser.getPhoneNumber(), dbQueriedGroupUpUser[0].getPhoneNumber());
                assertEquals(groupUpUser.getAge(), dbQueriedGroupUpUser[0].getAge());
                assertEquals(groupUpUser.getPassword(), dbQueriedGroupUpUser[0].getPassword());
            } else {
                fail("No database object was returned from the query");
            }
        }

        finally{
            //Delete table in preparation for next unit case
            mockUserDAO.deleteTable();
        }


    }

    @Test
    public void testUpdate(){

        try{
            mockUserDAO.update();
            fail("Unsupported operation exception not thrown. Update is not supported by userDAO");
        }

        catch(UnsupportedOperationException unsupportedOperationException){
        }
    }



}
