package com.haami.haami.models.requests;

import com.haami.haami.models.enums.UserTypeEnum;

public class UserRequest {
    private String name;
    private String userName;
    private String password;
    private String startDatePersian;
    private UserTypeEnum userType;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartDatePersian() {
        return startDatePersian;
    }

    public void setStartDatePersian(String startDatePersian) {
        this.startDatePersian = startDatePersian;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
