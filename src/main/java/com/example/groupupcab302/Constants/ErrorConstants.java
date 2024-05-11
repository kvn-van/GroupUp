package com.example.groupupcab302.Constants;

public enum ErrorConstants {

    //Define constants to refer to errors
    //Note: Error constant values are negative to avoid confusion with user input

    INT_PARSE_ERROR("-1", "Please use only integers for age and phone number, with no spaces \nor special characters. Phone number should start with 0"),
    INVALID_AGE("-2", "You must be 18 or older to sign up"),
    INVALID_PHONE_NUMBER("-3", "Phone number must be 10 digits long, without spaces, +, or -"),
    INVALID_PASSWORD("-4", "Passwords must match and contain: 1 uppercase letter, \n1 lowercase letter, 1 special character ($, ., ^), 1 number,\n and be at least 8 characters long"),
    INVALID_USERINPUT("-5", "One or more fields are empty. Please fill out all fields"),
    INVALID_EMAIL("-6", "Please enter a valid email address containing '@'"),
    EMAIL_IN_USE("-7", "Email is already registered. Please log in"),
    INSERTING_USER_ERROR("-8", "Error registering user. Please try again"),
    INVALID_ACCOUNT_CREDENTIALS("-9", "Either the email entered was invalid or the password was incorrect! Please try again");

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
    // Different to getter, used in cases when description of error must be decided at runtime/runtime polymorphism
    public static String retrieveErrorConstantDescription(String errorValue){
        for (ErrorConstants errorConstant : ErrorConstants.values()){
            if (errorConstant.getErrorValue() == errorValue){
                return errorConstant.getErrorDescription();
            }
        }
        return null;
    }
}


