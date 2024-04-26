package com.example.groupupcab302;

public enum ErrorConstants {

    //Define constants to refer to errors
    //Note: Error constant values are negative to avoid confusion with user input

    // As the phone number received is 10 integers long, having any integer greater than 2 at index 0 exceeds range of integer creating error
    // Ensure error message now specifies this for user 
    INT_PARSE_ERROR("-1", "Please ensure entries for age and phone number are fulfilled with only integers and that phone number starts with a leading 0"),
    INVALID_AGE("-2", "You must be 18 years or older to use this site!"),
    INVALID_PHONE_NUMBER("-3", "Your phone number must be 10 digits long! Do not include spaces, + or -"),
    INVALID_PASSWORD("-4", "The passwords you entered either do not match or your passwords do not" +
            " have at least: 1 upper case letter, 1 lowercase, 1 special character (i.e $,. ^),  1 number or are not 8 characters long"),
    INVALID_USERINPUT("-5","One or more input fields have not been filled out!"),
    INVALID_EMAIL("-6", "Email entered is invalid. Please make sure it has a @ in the input!"),

    EMAIL_IN_USE("-7", "Email entered for signup is already registered to a user. Please consider logging in under this email!"),

    INSERTING_USER_ERROR("-8", "Error occurred when trying to register you to the database. Please try again and make sure a database has been initialized");

    private String value;
    private String errorDescription;


    ErrorConstants(String value, String errorDescription){
        this.value = value;
        this.errorDescription = errorDescription;
    }

    public String getErrorValue(){
        return value;
    }

    public String getErrorDescription(){
        return errorDescription;
    }

    // Dynamically retrieve the description of an error
    // Different to getter, used in cases when this must be decided at runtime/runtime polymorphism
    public static String retrieveErrorConstantDescription(String errorValue){
        for (ErrorConstants errorConstant : ErrorConstants.values()){
            if (errorConstant.getErrorValue() == errorValue){
                return errorConstant.getErrorDescription();
            }
        }
        return null;
    }
}



