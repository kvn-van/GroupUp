package com.example.groupupcab302;

public class GroupUpUser {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String age;
    private String password;

    // userID not required for normal instantiation of object
    public GroupUpUser( String userName, String firstName, String lastName, String email, String phoneNumber, String age, String password){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.password = password;
    }

    // Second constructor for instances where user is being created based on  results from query into database i.e getrecord
    public GroupUpUser( int userID, String userName, String firstName, String lastName, String email, String phoneNumber, String age, String password){
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }
    public String getUserName(){
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getAge(){
        return age;
    }

    public String getPassword(){
        return password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public void setAge(String age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getAllUserDetails(){

        return ("userName: " + userName + ", First Name: " + firstName +", Last Name: "
                + lastName + ", email: " + email  + ", phoneNumber: " + phoneNumber + ", age: " + age + ", password: " +
                password);

    }
}
