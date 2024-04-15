package com.example.groupupcab302;

public class GroupUpUser {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private int age;
    private String password;
    private String gender;

    // userID will be automatically generated for each user upon database entry. Not required for constructor
    public GroupUpUser( String userName, String firstName, String lastName, String email, int phoneNumber, int age, String password, String gender){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.password = password;
        this.gender = gender;
    }

    /*
    public int getuserId() {
        return id;
    } */

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

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public int getAge(){
        return age;
    }

    public String getPassword(){
        return password;
    }

    public String getGender(){
        return gender;
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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAllUserDetails(){
        String userDetails = ("userName: " + userName + ", First Name: " + firstName +", LastName: "
                + lastName + ", email: " + email  + ", phoneNumber: " + age + ", password: " +
                password + ", gender: " + gender);

        return userDetails;

    }
}
