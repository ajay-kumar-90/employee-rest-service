package com.ajaycodes.employeeservice.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserDetails")
public class UserDetails {
    private int userId;
    private String userName;
    private String panNumber;

    public UserDetails(int userId, String userName, String panNumber) {
        this.userId = userId;
        this.userName = userName;
        this.panNumber = panNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}
