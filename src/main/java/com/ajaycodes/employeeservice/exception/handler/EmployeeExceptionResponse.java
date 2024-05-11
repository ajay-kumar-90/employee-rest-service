package com.ajaycodes.employeeservice.exception.handler;

import java.time.LocalDateTime;

public class EmployeeExceptionResponse {
    private String errorMessage;
    private String description;

    public EmployeeExceptionResponse(String errorMessage, String description, LocalDateTime dateTime) {
        this.errorMessage = errorMessage;
        this.description = description;
        this.dateTime = dateTime;
    }

    private LocalDateTime dateTime;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
