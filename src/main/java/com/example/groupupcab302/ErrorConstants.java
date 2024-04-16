package com.example.groupupcab302;

public enum ErrorConstants {

    //Define constants to refer to errors
    //Note: Error constant values are negative to avoid confusion with user input
    PARSE_ERROR(-1, "Please ensure entries for age and phone number are fulfilled with only integers"),
    INVALID_AGE(-2, "You must be 18 years or older to use this site!"),
    INVALID_PHONE_NUMBER(-3, "Your phone number must be atleast 10 digits long! Do not include spaces, + or -"),
    INVALID_PASSWORD(-4, "The passwords you entered either do not match or your passwords do not" +
            " have atleast: 1 upper case letter, 1 lowercase, 1 special character (i.e $,. ^),  1 number or are not 8 characters long"),
    INVALID_USERINPUT(-5,"One or more input fields for either username, first name, last name, password or password " +
            "confimation fields were not filled in correctly."),
    INVALID_EMAIL(-6, "Email entered is invalid. Please make sure it has a @ in the input!");

    private Integer value;
    private String errorDescription;


    ErrorConstants(Integer value, String errorDescription){
        this.value = value;
        this.errorDescription = errorDescription;
    };

    public Integer getErrorValue(){
        return value;
    }

    public String getErrorDescription(){
        return errorDescription;
    }
}



