package com.example.groupupcab302.misc;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
    private String errorDescription;

    public CustomSQLException(String reason){
        super(reason);
    }
}
