package com.store.model.dto;

import java.util.List;

public class ErrorResponse
{
    public ErrorResponse(String message, List<StackTraceElement> details) {
        super();
        this.message = message;
        this.details = details;
    }

    //General error message about nature of error
    private String message;

    //Specific errors in API request processing
    private List<StackTraceElement> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StackTraceElement> getDetails() {
        return details;
    }

    public void setDetails(List<StackTraceElement> details) {
        this.details = details;
    }

    //Getter and setters
}
