package com.haami.haami.models.responses;

import com.haami.haami.models.enums.CategoryEnum;

public class ArticleResponse {
    private long articleId;
    private String title;
    private String body;
    private String articleDate;
    private String articleDatePersian;
    private CategoryEnum categoryType;
    private String writingDate;
    private String writingDatePersian;
    private long categoryId;
    private long userId;
    private Boolean isApproved;
    private String categoryName;
    private String picUrl;
    private String address;

    private CategoryResponse category;
    private UserResponse user;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleDatePersian() {
        return articleDatePersian;
    }

    public void setArticleDatePersian(String articleDatePersian) {
        this.articleDatePersian = articleDatePersian;
    }

    public CategoryEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryEnum categoryType) {
        this.categoryType = categoryType;
    }

    public String getWritingDate() {
        return writingDate;
    }

    public void setWritingDate(String writingDate) {
        this.writingDate = writingDate;
    }

    public String getWritingDatePersian() {
        return writingDatePersian;
    }

    public void setWritingDatePersian(String writingDatePersian) {
        this.writingDatePersian = writingDatePersian;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
