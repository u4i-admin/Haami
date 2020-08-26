package com.haami.haami.models.requests;

import com.haami.haami.models.enums.CategoryEnum;

public class ArticleRequest {
    private String title;
    private String body;
    private String articleDate;
    private CategoryEnum categoryType;
    private long categoryId;
    private String picUrl;

    public ArticleRequest(String title, String body, String articleDate, CategoryEnum categoryType, long categoryId, String picUrl) {
        this.title = title;
        this.body = body;
        this.articleDate = articleDate;
        this.categoryType = categoryType;
        this.categoryId = categoryId;
        this.picUrl = picUrl;
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

    public CategoryEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryEnum categoryType) {
        this.categoryType = categoryType;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicUrl() { return picUrl; }

    public void setPicUrl(String picUrl) { this.picUrl = picUrl; }
}
