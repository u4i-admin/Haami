package com.haami.haami.models.responses;

import com.haami.haami.models.enums.UserTypeEnum;
import java.util.Date;
import java.util.List;

public class UserResponse {
    private long userId;
    private String name;
    private String userName;
    private Date startDate;
    private String startDatePersian;
    private Integer userType;
    private String token;
    private String telegramId;
    private String email;

    private List<ArticleResponse> articles;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartDatePersian() {
        return startDatePersian;
    }

    public void setStartDatePersian(String startDatePersian) {
        this.startDatePersian = startDatePersian;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public List<ArticleResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponse> articles) {
        this.articles = articles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
